package com.distribution.service;

import com.distribution.common.constant.Constant;
import com.distribution.common.utils.Page;
import com.distribution.dao.accountFlowHistory.mapper.AccountFlowHistoryMapper;
import com.distribution.dao.accountFlowHistory.model.AccountFlowHistory;
import com.distribution.dao.accountManager.mapper.more.MoreAccountManagerMapper;
import com.distribution.dao.accountManager.model.AccountManager;
import com.distribution.dao.advance.mapper.AdvanceMapper;
import com.distribution.dao.advance.mapper.more.MoreAdvanceMapper;
import com.distribution.dao.advance.model.Advance;
import com.distribution.dao.advance.model.more.MoreAdvance;
import com.distribution.dao.dividend.model.Dividend;
import com.distribution.dao.member.mapper.more.MoreMemberMapper;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.xssf.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class AdmAdvanceService {
    @Autowired
    private MoreAdvanceMapper moreAdvanceMapper;

    @Autowired
    private MoreAccountManagerMapper moreAccountManagerMapper;

    @Autowired
    private AccountFlowHistoryMapper accountFlowHistoryMapper;

    /**
     * description 提现列表
     * @author WYN
     * */
    public Page advanceList(Page page) {
        /*if(null!=page.getParameterMap().get("startTime") && !"".equals(page.getParameterMap().get("startTime")))
            page.getParameterMap().put("startTime",page.getParameterMap().get("startTime").toString()+" 00:00:00");
        if(null!=page.getParameterMap().get("endTime") && !"".equals(page.getParameterMap().get("endTime")))
            page.getParameterMap().put("endTime",page.getParameterMap().get("endTime").toString()+" 23:59:59");*/

        page.setTotalCount(moreAdvanceMapper.getAdvanceListCount(page));
        page.setResult( moreAdvanceMapper.getAdvanceList(page));
        return page;
    }

    /**
     * description 提现批准，驳回
     * @author WYN
     * */
    public String confirmAdvance(MoreAdvance moreAdvance) {
        int cnt1 = 0;
        int cnt2 = 0;
        int cnt3 = 0;
        //提现批准入账
        if("2".equals(moreAdvance.getStatues())){
            //提现账户余额查询与计算
            AccountManager advanceAccount = new AccountManager();
            advanceAccount.setMemberId(moreAdvance.getMemberId());

            advanceAccount = this.selectAccountManager(advanceAccount);

            //判断如果账户余额小于提现金额就失败
            if(advanceAccount.getBonusAmt().compareTo(moreAdvance.getReqAmt()) == -1){
                throw new RuntimeException();
            }

            advanceAccount.setBonusAmt(advanceAccount.getBonusAmt().subtract(moreAdvance.getReqAmt()));//从奖金币中扣除提现申请金额（提现申请金额=实际提现金额+手续费）
            advanceAccount.setTotalBonus(advanceAccount.getBonusAmt().add(advanceAccount.getSeedAmt()));//计算总奖金字段
            advanceAccount.setAdvanceAmt(advanceAccount.getAdvanceAmt().add(moreAdvance.getReqAmt()));//提现总额 = 原提现总额 + 实际提现金额
            advanceAccount.setUpdateId(moreAdvance.getMemberId());
            advanceAccount.setUpdateTime(new Date());


            //step 3)账户流水
            AccountFlowHistory historyout = new AccountFlowHistory();
            historyout.setMemberId(moreAdvance.getMemberId());
            historyout.setCreateTime(new Date());
            historyout.setCreateId(moreAdvance.getMemberId());
            historyout.setType("1");      //1支出 进账2
            historyout.setFlowType(Constant.ADVANCE); //提现
            historyout.setBonusAmt(moreAdvance.getReqAmt());
            historyout.setTotalAmt(moreAdvance.getReqAmt());

            cnt1 = moreAccountManagerMapper.updateAccountManagerAmtWhileAdvance(advanceAccount);
            cnt2= accountFlowHistoryMapper.insert(historyout);

        }else {
            cnt1 = 1;
            cnt2 = 1;
        }

        cnt3 = moreAdvanceMapper.confirmAdvance(moreAdvance);

        if(cnt1 > 0 && cnt2 > 0 && cnt3 > 0){
            return "success";
        }else{
            throw new RuntimeException();
        }
    }

    /**
     * 查询账户信息
     * @param accountManager
     * @return
     */
    public AccountManager selectAccountManager(AccountManager accountManager){
        return moreAccountManagerMapper.selectAccountManager(accountManager);
    }

    /**
     * description 红包列表下载
     * @author Bright
     * */
    public XSSFWorkbook exportData(Map map, HttpServletResponse response) throws IOException, InvocationTargetException {
        List<MoreAdvance> result = moreAdvanceMapper.getExcelAdvanceList(map);
        //定义表头
        String[] excelHeader = {"会员名", "提现金额", "手续费", "实到金额", "申请日期", "账户信息", "状态"};

        return  this.exportExcel("提现列表", excelHeader, result, response.getOutputStream());
    }

    public XSSFWorkbook exportExcel(String title, String[] headers, List<MoreAdvance> list, OutputStream out) throws InvocationTargetException {
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
        sheet.setColumnWidth(0, 18 * 256);
        sheet.setColumnWidth(1, 20 * 256);
        sheet.setColumnWidth(2, 12 * 256);
        sheet.setColumnWidth(3, 22 * 256);
        sheet.setColumnWidth(4, 20 * 256);
        sheet.setColumnWidth(5, 40 * 256);
        sheet.setColumnWidth(6, 30 * 256);
        //构建表体
        int t = 0;
        int e = 0;
        int m = 0;
        int p = 0;
        for(int j=0;j<list.size();j++){
            XSSFRow bodyRow = sheet.createRow(j + 1);

            bodyRow.createCell(0).setCellValue(list.get(j).getMemberName());
            bodyRow.createCell(1).setCellValue(list.get(j).getReqAmt().toString());
            bodyRow.createCell(2).setCellValue(list.get(j).getFeeAmt().toString());
            bodyRow.createCell(3).setCellValue(list.get(j).getActAmt().toString());
            bodyRow.createCell(4).setCellValue(new SimpleDateFormat("yyyy-MM-dd").format(list.get(j).getRequestDate()));
            bodyRow.createCell(5).setCellValue(list.get(j).getCardName().toString()+"|"+list.get(j).getBankName().toString()+"|"+list.get(j).getCardNo().toString());
            bodyRow.createCell(6).setCellValue("已执行");
            t=j;
            e+=list.get(j).getReqAmt().intValue();
            m+=list.get(j).getFeeAmt().intValue();
            p+=list.get(j).getActAmt().intValue();
        }
        XSSFRow bodyRow1 = sheet.createRow(t + 2);
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);//居中
        style.setFont(font);

        XSSFCell cel1 = bodyRow1.createCell(0);
        cel1.setCellStyle(style);
        cel1.setCellValue("已提现总金额:");
        XSSFCell cel2 = bodyRow1.createCell(1);
        cel2.setCellValue(e);
        XSSFCell cel3 = bodyRow1.createCell(2);
        cel3.setCellValue(m);
        XSSFCell cel4 = bodyRow1.createCell(3);
        cel4.setCellValue(p);

        return  workbook;
    }
}
