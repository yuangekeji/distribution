package com.distribution.service;

import com.distribution.common.constant.Constant;
import com.distribution.common.utils.Page;
import com.distribution.dao.accountFlowHistory.mapper.AccountFlowHistoryMapper;
import com.distribution.dao.accountFlowHistory.model.AccountFlowHistory;
import com.distribution.dao.accountManager.mapper.more.MoreAccountManagerMapper;
import com.distribution.dao.accountManager.model.AccountManager;
import com.distribution.dao.admin.model.Admin;
import com.distribution.dao.memberChargeApply.mapper.more.MoreMemberChargeApplyMapper;
import com.distribution.dao.memberChargeApply.model.MemberChargeApply;
import com.distribution.dao.memberChargeApply.model.more.MoreMemberChargeApply;
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
import java.util.List;
import java.util.Map;

@Service
public class AdmMemberChargeService {
    @Autowired
    private MoreMemberChargeApplyMapper moreMemberChargeApplyMapper;
    @Autowired
    private MoreAccountManagerMapper moreAccountManagerMapper;
    @Autowired
    private AccountFlowHistoryMapper accountFlowHistoryMapper;

    /**
     * description 充值申请列表
     * @author sijeong
     * */
    public Page memberChargeList(Page page) {
        page.setTotalCount(moreMemberChargeApplyMapper.getMemberChargeListCount(page));
        page.setResult( moreMemberChargeApplyMapper.getMemberChargeList(page));
        return page;
    }

    /**
     * description 充值申请批准，驳回
     * @author sijeong
     * */
    public String confirmMemberCharge(MoreMemberChargeApply moreMemberChargeApply, Admin admin) {

        Date date = new Date();
        moreMemberChargeApply.setChargeApplyTime(date);
        moreMemberChargeApply.setUpdateId(admin.getId());
        moreMemberChargeApply.setUpdateTime(date);
        int cnt = moreMemberChargeApplyMapper.confirmMemberCharge(moreMemberChargeApply);
        if(cnt > 0){
            return "success";
        }else{
            throw new RuntimeException();
        }
    }
    /**
     * description 充值
     * @author sijeong
     * */
    public String AddMemberCharge(MoreMemberChargeApply moreMemberChargeApply, Admin admin){

        MemberChargeApply mmca = moreMemberChargeApplyMapper.selectByPrimaryKey(moreMemberChargeApply.getId());
        if ("1".equals(mmca.getStatus())) {
            AccountFlowHistory af = new AccountFlowHistory();

            AccountManager am = moreAccountManagerMapper.selectByMemberId(moreMemberChargeApply.getMemberId());
            Date date = new Date();
            am.setTotalBonus(am.getTotalBonus().add(moreMemberChargeApply.getChargeAmt()));
            //充值前余额
            af.setOldTotalBonusAmt(am.getBonusAmt());
            am.setBonusAmt(am.getBonusAmt().add(moreMemberChargeApply.getChargeAmt()));
            //充值后余额
            af.setNewTotalBonusAmt(am.getBonusAmt());
            am.setUpdateId(admin.getId());
            am.setUpdateTime(date);
            int am1 = moreAccountManagerMapper.updateByMemberId(am);
            if(am1 < 1){
                throw new RuntimeException();
            }
            MoreMemberChargeApply mmc = new MoreMemberChargeApply();
            mmc.setId(moreMemberChargeApply.getId());
            mmc.setStatus(moreMemberChargeApply.getStatus());
            mmc.setChargeTime(date);
            mmc.setUpdateTime(date);
            mmc.setUpdateId(admin.getId());
            int count = moreMemberChargeApplyMapper.updateByPrimaryKeySelective(mmc);
            if(count < 1){
                throw new RuntimeException();
            }

            af.setMemberId(moreMemberChargeApply.getMemberId());
            af.setCreateTime(date);
            af.setCreateId(admin.getId());
            af.setType("2");//转入
            af.setTotalAmt(moreMemberChargeApply.getChargeAmt());
            af.setBonusAmt(moreMemberChargeApply.getChargeAmt());
            af.setSeedAmt(new BigDecimal(0));
            af.setFlowType(Constant.MEMBERCHARGE);//管理员充值
            af.setNewTotalSeedAmt(am.getSeedAmt());
            af.setOldTotalSeedAmt(am.getSeedAmt());

            int af1 = accountFlowHistoryMapper.insert(af);
            if(af1 < 1){
                throw new RuntimeException();
            }
            return "success";
        }else {
            return "chargeWarning";
        }
    }

    /**
     * description 充值列表下载
     * @author sijeong
     * */
    public XSSFWorkbook exportData(Map map, HttpServletResponse response) throws IOException, InvocationTargetException {
        List<MoreMemberChargeApply> result = moreMemberChargeApplyMapper.getExcelMemberChargeList(map);
        //定义表头
        String[] excelHeader = {"申请人", "手机号", "打款时间", "打款方式", "申请时间", "审批时间", "充值金额", "充值时间","充值方式", "状态", "申请备注"};

        return  this.exportExcel("充值列表", excelHeader, result, response.getOutputStream());
    }

    public XSSFWorkbook exportExcel(String title, String[] headers, List<MoreMemberChargeApply> list, OutputStream out) throws InvocationTargetException {
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
        sheet.setColumnWidth(0, 22 * 256);
        sheet.setColumnWidth(1, 15 * 256);
        sheet.setColumnWidth(2, 15 * 256);
        sheet.setColumnWidth(3, 20 * 256);
        sheet.setColumnWidth(4, 20 * 256);
        sheet.setColumnWidth(5, 20 * 256);
        sheet.setColumnWidth(6, 15 * 256);
        sheet.setColumnWidth(7, 20 * 256);
        sheet.setColumnWidth(8, 15 * 256);
        sheet.setColumnWidth(9, 15 * 256);
        sheet.setColumnWidth(10, 40 * 256);

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
            bodyRow.createCell(2).setCellValue(new SimpleDateFormat("yyyy-MM-dd").format(list.get(j).getPayMoneyTime()));
            bodyRow.createCell(3).setCellValue(list.get(j).getPayMoneyType());
            bodyRow.createCell(4).setCellValue(new SimpleDateFormat("yyyy-MM-dd HH:mm").format(list.get(j).getChargeRequestTime()));
            bodyRow.createCell(5).setCellValue(
                    list.get(j).getChargeApplyTime() == null ? "" : new SimpleDateFormat("yyyy-MM-dd HH:mm").format(list.get(j).getChargeApplyTime()));
            bodyRow.createCell(6).setCellValue(list.get(j).getChargeAmt().toString());
            bodyRow.createCell(7).setCellValue(
                    list.get(j).getChargeTime() == null ? "" : new SimpleDateFormat("yyyy-MM-dd HH:mm").format(list.get(j).getChargeTime()));
            bodyRow.createCell(8).setCellValue(chargeMoneyTypeName(list.get(j).getChargeMoneyType()));
            bodyRow.createCell(9).setCellValue(statusName(list.get(j).getStatus()));
            bodyRow.createCell(10).setCellValue(list.get(j).getApplyInfo());

            bodyRow.getCell(0).setCellStyle(styleCenter);
            bodyRow.getCell(1).setCellStyle(styleCenter);
            bodyRow.getCell(2).setCellStyle(styleCenter);
            bodyRow.getCell(4).setCellStyle(styleCenter);
            bodyRow.getCell(5).setCellStyle(styleCenter);
            bodyRow.getCell(6).setCellStyle(styleRight);
            bodyRow.getCell(7).setCellStyle(styleCenter);
            bodyRow.getCell(8).setCellStyle(styleCenter);
            bodyRow.getCell(9).setCellStyle(styleCenter);

            p = p.add(list.get(j).getChargeAmt());
            t=j;
        }
        XSSFRow bodyRow1 = sheet.createRow(t + 3);
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);//居中
        style.setFont(font);

        XSSFCell cel1 = bodyRow1.createCell(0);
        cel1.setCellStyle(style);
        cel1.setCellValue("充值总金额(元)");
        XSSFCell cel2 = bodyRow1.createCell(1);
        cel2.setCellStyle(styleRight);
        cel2.setCellValue(p.toString());

        return  workbook;
    }
    private String chargeMoneyTypeName(String chargeMoneyType) {
        String chargeMoneyTypeName = "";
        if (chargeMoneyType.equals("0")){
            chargeMoneyTypeName = "会员申请";
        }else if (chargeMoneyType.equals("1")){
            chargeMoneyTypeName = "管理员充值";
        }
        return chargeMoneyTypeName;
    }
    private String statusName(String status) {
        String statusName = "";
        if (status.equals("0")){
            statusName = "审核中";
        }else if (status.equals("1")){
            statusName = "待充值";
        }else if (status.equals("2")){
            statusName = "审核驳回";
        }else if (status.equals("3")){
            statusName = "充值成功";
        }
        return statusName;
    }
}