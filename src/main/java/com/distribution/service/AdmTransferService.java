package com.distribution.service;

import com.distribution.common.constant.Constant;
import com.distribution.common.utils.CryptoUtil;
import com.distribution.common.utils.Page;
import com.distribution.dao.accountFlowHistory.mapper.AccountFlowHistoryMapper;
import com.distribution.dao.accountFlowHistory.model.AccountFlowHistory;
import com.distribution.dao.accountManager.mapper.more.MoreAccountManagerMapper;
import com.distribution.dao.accountManager.model.AccountManager;
import com.distribution.dao.admin.model.Admin;
import com.distribution.dao.member.mapper.more.MoreMemberMapper;
import com.distribution.dao.member.model.Member;
import com.distribution.dao.memberChargeApply.model.MemberChargeApply;
import com.distribution.dao.memberChargeApply.model.more.MoreMemberChargeApply;
import com.distribution.dao.transfer.mapper.TransferMapper;
import com.distribution.dao.transfer.mapper.more.MoreTransferMapper;
import com.distribution.dao.transfer.model.Transfer;
import com.distribution.dao.transfer.model.more.MoreTransfer;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.xssf.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by sijeong on 2017/11/05.
 */

@Service
public class AdmTransferService {

    @Autowired
    private MoreTransferMapper moreTransferMapper;
    @Autowired
    private MoreAccountManagerMapper accountManagerMapper;
    @Autowired
    private AccountFlowHistoryMapper accountFlowHistoryMapper;

    /**
     * description 转账记录查询
     * @author sijeong
     * */
    public Page transferList(Page page) {
        page.setTotalCount(moreTransferMapper.getTransferListCount(page));
        page.setResult( moreTransferMapper.getTransferList(page));
        return page;
    }

    /**
     * 查询账户信息
     * @param accountManager
     * @return
     */
    public AccountManager selectAccountManager(AccountManager accountManager){
        return accountManagerMapper.selectAccountManager(accountManager);
    }

    /**
     * description 转账撤销
     * @author sijeong
     * */
    public String CancelTransfer(Transfer transfer, Admin admin){

        Transfer tf = moreTransferMapper.selectByPrimaryKey(transfer.getId());
        if ("0".equals(tf.getStatus())) {

            Date date = new Date();

            //修改转账明细表
            Transfer tsf = new Transfer();
            tsf.setId(transfer.getId());
            tsf.setStatus(transfer.getStatus());
            tsf.setCancelTime(date);

            //step 1)账户转出计算
            AccountManager transAccount = new AccountManager();
            transAccount.setMemberId(transfer.getReceiveId());

            transAccount = this.selectAccountManager(transAccount);

            //判断如果账户余额小于转账金额就失败
            if(transAccount.getBonusAmt().compareTo(transfer.getTransferAmt()) == -1){
                return "transferFail";
                //throw new RuntimeException();
            }
            transAccount.setBonusAmt(transAccount.getBonusAmt().subtract(transfer.getTransferAmt()));//从奖金币中扣除
            transAccount.setTotalBonus(transAccount.getBonusAmt().add(transAccount.getSeedAmt()));//计算总奖金字段
            transAccount.setUpdateId(admin.getId());
            transAccount.setUpdateTime(date);

            //step 2)转入账户计算
            AccountManager recivedAccount = new AccountManager();
            recivedAccount.setMemberId(transfer.getMemberId());
            recivedAccount = this.selectAccountManager(recivedAccount);

            recivedAccount.setBonusAmt(recivedAccount.getBonusAmt().add(transfer.getTransferAmt()));//增加奖金币
            recivedAccount.setTotalBonus(recivedAccount.getBonusAmt().add(recivedAccount.getSeedAmt()));//计算总奖金字段
            recivedAccount.setUpdateId(admin.getId());
            recivedAccount.setUpdateTime(date);

            AccountFlowHistory historyout = new AccountFlowHistory();
            historyout.setMemberId(transfer.getReceiveId());
            historyout.setCreateTime(date);
            historyout.setCreateId(admin.getId());
            historyout.setType("1");      //1支出 进账2
            historyout.setFlowType(Constant.TRANSFEROUT); //转出
            historyout.setBonusAmt(transfer.getTransferAmt());

            AccountFlowHistory historyin = new AccountFlowHistory();
            historyin.setMemberId(transfer.getMemberId());
            historyin.setCreateTime(date);
            historyin.setCreateId(admin.getId());
            historyin.setType("2");      //1支出 进账2
            historyin.setFlowType(Constant.TRANSFERIN); //转出
            historyin.setBonusAmt(transfer.getTransferAmt());

            if(transAccount.getId() != null && transAccount.getId() >0
                    && recivedAccount.getId() !=null && recivedAccount.getId() >0){
                //step1-3) 修改转账明细表，计算各账户金额
                int  cnt1 = moreTransferMapper.updateByPrimaryKeySelective(tsf);

                int  cnt2 = accountManagerMapper.updateAccountManagerAmt(transAccount);

                int  cnt3 = accountManagerMapper.updateAccountManagerAmt(recivedAccount);

                int  cnt4= accountFlowHistoryMapper.insert(historyout);

                int  cnt5= accountFlowHistoryMapper.insert(historyin);

                if(cnt1 >0 && cnt2 >0 && cnt3>0 && cnt4 >0 && cnt5 >0){
                    //转账成功
                    return "success";
                }else{
                    throw new RuntimeException();
                }
            }else{
                throw new RuntimeException();
            }
        }else {
            return "transferWarning";
        }
    }
    /**
     * description 转账列表下载
     * @author sijeong
     * */
    public XSSFWorkbook exportData(Map map, HttpServletResponse response) throws IOException, InvocationTargetException {
        List<Transfer> result = moreTransferMapper.getExcelTransferList(map);
        //定义表头
        String[] excelHeader = {"转账人", "转账人电话", "收款人", "收款人电话", "转账时间", "转账金额", "撤销时间", "状态"};

        return  this.exportExcel("转账列表", excelHeader, result, response.getOutputStream());
    }

    public XSSFWorkbook exportExcel(String title, String[] headers, List<Transfer> list, OutputStream out) throws InvocationTargetException {
        // 声明一个工作薄
        XSSFWorkbook workbook = new XSSFWorkbook();
        // 生成一个表格
        XSSFSheet sheet = workbook.createSheet(title);
        //定义字体
        XSSFFont font = workbook.createFont();
        font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);//粗体显示
        font.setFontHeightInPoints((short) 12);
        //定义样式
        XSSFCellStyle style = workbook.createCellStyle();
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);//居中
        style.setFont(font);
        // 产生表格标题行
        XSSFRow row = sheet.createRow(0);
        for (int i = 0; i < headers.length; i++){
            XSSFCell cell = row.createCell(i);
            cell.setCellStyle(style);
            cell.setCellValue(headers[i]);
        }
        //设置表格宽度
        sheet.setColumnWidth(0, 15 * 256);
        sheet.setColumnWidth(1, 15 * 256);
        sheet.setColumnWidth(2, 15 * 256);
        sheet.setColumnWidth(3, 15 * 256);
        sheet.setColumnWidth(4, 20 * 256);
        sheet.setColumnWidth(5, 15 * 256);
        sheet.setColumnWidth(6, 20 * 256);
        sheet.setColumnWidth(7, 15 * 256);

        //构建表体
        //定义body样式
        XSSFCellStyle styleRight = workbook.createCellStyle();
        styleRight.setAlignment(HSSFCellStyle.ALIGN_RIGHT);//居右
        XSSFCellStyle styleCenter = workbook.createCellStyle();
        styleCenter.setAlignment(HSSFCellStyle.ALIGN_CENTER);//居中
        int t = 0;
        BigDecimal p =  new BigDecimal("0");
        for(int j=0;j<list.size();j++){
            XSSFRow bodyRow = sheet.createRow(j + 1);

            bodyRow.createCell(0).setCellValue(list.get(j).getMemberName());
            bodyRow.createCell(1).setCellValue(list.get(j).getMemberPhone());
            bodyRow.createCell(2).setCellValue(list.get(j).getReceiveName());
            bodyRow.createCell(3).setCellValue(list.get(j).getReceivePhone());
            bodyRow.createCell(4).setCellValue(new SimpleDateFormat("yyyy-MM-dd HH:mm").format(list.get(j).getTransferTime()));
            bodyRow.createCell(5).setCellValue(list.get(j).getTransferAmt().toString());
            bodyRow.createCell(6).setCellValue(
                    list.get(j).getCancelTime() == null ? "" : new SimpleDateFormat("yyyy-MM-dd HH:mm").format(list.get(j).getCancelTime()));
            bodyRow.createCell(7).setCellValue(statusName(list.get(j).getStatus()));

            bodyRow.getCell(0).setCellStyle(styleCenter);
            bodyRow.getCell(1).setCellStyle(styleCenter);
            bodyRow.getCell(2).setCellStyle(styleCenter);
            bodyRow.getCell(3).setCellStyle(styleCenter);
            bodyRow.getCell(4).setCellStyle(styleCenter);
            bodyRow.getCell(5).setCellStyle(styleRight);
            bodyRow.getCell(6).setCellStyle(styleCenter);
            bodyRow.getCell(7).setCellStyle(styleCenter);

            p = p.add(list.get(j).getTransferAmt());
            t=j;
        }
        XSSFRow bodyRow1 = sheet.createRow(t + 3);
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);//居中
        style.setFont(font);

        XSSFCell cel1 = bodyRow1.createCell(0);
        cel1.setCellStyle(style);
        cel1.setCellValue("转账总金额(元)");
        XSSFCell cel2 = bodyRow1.createCell(5);
        cel2.setCellStyle(styleRight);
        cel2.setCellValue(p.toString());

        return  workbook;
    }
    private String statusName(String status) {
        String statusName = "";
        if (status.equals("0")){
            statusName = "转账成功";
        }else if (status.equals("1")){
            statusName = "已撤销";
        }
        return statusName;
    }
}
