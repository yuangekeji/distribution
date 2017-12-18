package com.distribution.service;

import com.distribution.common.utils.Page;
import com.distribution.dao.admin.model.Admin;
import com.distribution.dao.order.mapper.OrderMasterMapper;
import com.distribution.dao.order.mapper.more.MoreOrderMasterMapper;
import com.distribution.dao.order.model.OrderMaster;
import com.distribution.dao.order.model.more.MoreOrderMaster;
import com.distribution.dao.pointOrder.mapper.more.MorePointOrderMapper;
import com.distribution.dao.pointOrder.model.PointOrder;
import com.distribution.dao.pointOrder.model.more.MorePointOrder;
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
public class AdmPointService {

    @Autowired
    private MorePointOrderMapper morePointOrderMapper;
    /**
     * description 兑换单列表查询
     * @author sijeong
     * */
    public Page pointOrderList(Page page){
        page.setTotalCount(morePointOrderMapper.getPointOrderListCount(page));
        page.setResult(morePointOrderMapper.getPointOrderList(page));
        return page;
    }

    /**
     * description 确认发货
     * @author WYN
     * */
    public String confirmSendOrder(PointOrder pointOrder) {
        int cnt = morePointOrderMapper.updateByPrimaryKeySelective(pointOrder);

        if(cnt > 0){
            return "success";
        }else{
            throw new RuntimeException();
        }
    }
    /**
     * description 管理员确认收货
     * @author sijeong
     * */
    public String confirmReceiveOrder(PointOrder po, Admin admin) {

        PointOrder pointOrder = new PointOrder();
        pointOrder.setId(po.getId());
        pointOrder.setOrderStatues(po.getOrderStatues());
        pointOrder.setUpdateId(admin.getId());
        pointOrder.setUpdateTime(new Date());
        int cnt = morePointOrderMapper.updateByPrimaryKeySelective(pointOrder);

        if(cnt > 0){
            return "success";
        }else{
            throw new RuntimeException();
        }
    }

    public XSSFWorkbook exportDataAll(Map map, HttpServletResponse response) throws IOException, InvocationTargetException {
        List<MorePointOrder> result = morePointOrderMapper.getExcelPointOrderListAll(map);
        //定义表头
        String[] excelHeader = {"兑换单号", "会员", "登录账号", "兑换单金额", "支付积分", "快递费", "商品名信息", "商品数量", "兑换时间", "兑换单状态", "物流信息","收货人","收货电话"};

        return  this.exportExcel("兑换单列表", excelHeader, result, response.getOutputStream(), "兑换单数量统计:");
    }

    public XSSFWorkbook exportExcel(String title, String[] headers, List<MorePointOrder> list, OutputStream out, String str) throws InvocationTargetException {
        // 声明一个工作薄
        XSSFWorkbook workbook = new XSSFWorkbook();
        // 生成一个表格
        XSSFSheet sheet = workbook.createSheet(title);
        //定义字体
        XSSFFont font = workbook.createFont();
        font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);//粗体显示
        font.setFontHeightInPoints((short) 12);
        //定义样式
        XSSFCellStyle styleRight = workbook.createCellStyle();
        styleRight.setAlignment(HSSFCellStyle.ALIGN_RIGHT);//居右
        XSSFCellStyle styleCenter = workbook.createCellStyle();
        styleCenter.setAlignment(HSSFCellStyle.ALIGN_CENTER);//居中

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
        sheet.setColumnWidth(1, 15 * 256);
        sheet.setColumnWidth(2, 15 * 256);
        sheet.setColumnWidth(3, 22 * 256);
        sheet.setColumnWidth(4, 20 * 256);
        sheet.setColumnWidth(5, 15 * 256);
        sheet.setColumnWidth(6, 30 * 256);
        sheet.setColumnWidth(7, 15 * 256);
        sheet.setColumnWidth(8, 20 * 256);
        sheet.setColumnWidth(9, 20 * 256);
        sheet.setColumnWidth(10, 30 * 256);
        sheet.setColumnWidth(11, 15 * 256);
        sheet.setColumnWidth(12, 15 * 256);
        //构建表体
        int t = 0;
        int p = 0; //商品总数量

        BigDecimal totalOrderAmt = new BigDecimal(0);
        BigDecimal totalPointAmt = new BigDecimal(0);
        BigDecimal totalExpressFee = new BigDecimal(0);

        for(int j=0;j<list.size();j++){

            XSSFRow bodyRow = sheet.createRow(j + 1);
            String goods = "";
            MorePointOrder order = list.get(j);

            if(order.getGoodsNm() != null && !"".equals(order.getGoodsNm())){
                goods = goods + order.getGoodsNm();
            }

            Integer totalOrdQty = null != order.getOrderQty() ? order.getOrderQty() : 0;

            p+=totalOrdQty;
            totalOrderAmt=totalOrderAmt.add(order.getOrderAmt());
            totalPointAmt=totalPointAmt.add(order.getPointAmt());
            totalExpressFee=totalExpressFee.add(order.getExpressFee());

            bodyRow.createCell(0).setCellValue(order.getOrderNo().toString());
            bodyRow.createCell(1).setCellValue(order.getMemberName());
            bodyRow.createCell(2).setCellValue(order.getMemberPhone());
            bodyRow.createCell(3).setCellValue(String.valueOf(order.getOrderAmt()));
            bodyRow.createCell(4).setCellValue(String.valueOf(order.getPointAmt()));
            bodyRow.createCell(5).setCellValue(String.valueOf(order.getExpressFee()));
            bodyRow.createCell(6).setCellValue(goods);
            bodyRow.createCell(7).setCellValue(totalOrdQty + " 个");
            bodyRow.createCell(8).setCellValue(new SimpleDateFormat("yyyy-MM-dd").format(order.getCreateTime()));
            bodyRow.createCell(9).setCellValue(this.orderStatusFilter(order.getOrderStatues()));
            bodyRow.createCell(10).setCellValue(order.getExpressAddress());
            bodyRow.createCell(11).setCellValue(order.getReceiveName());
            bodyRow.createCell(12).setCellValue(order.getRecevivePhone());

            bodyRow.getCell(0).setCellStyle(styleCenter);
            bodyRow.getCell(1).setCellStyle(styleCenter);
            bodyRow.getCell(2).setCellStyle(styleCenter);
            bodyRow.getCell(3).setCellStyle(styleRight);
            bodyRow.getCell(4).setCellStyle(styleRight);
            bodyRow.getCell(5).setCellStyle(styleRight);
            bodyRow.getCell(7).setCellStyle(styleRight);
            bodyRow.getCell(8).setCellStyle(styleCenter);
            bodyRow.getCell(9).setCellStyle(styleCenter);
            bodyRow.getCell(11).setCellStyle(styleCenter);
            bodyRow.getCell(12).setCellStyle(styleCenter);


            t=j;
        }
        XSSFRow bodyRow = sheet.createRow(t + 2);
        XSSFCellStyle styleRightBoot = workbook.createCellStyle();
        styleRightBoot.setAlignment(HSSFCellStyle.ALIGN_RIGHT);//居右
        styleRightBoot.setFont(font);

        XSSFCell cel1 = bodyRow.createCell(0);
        cel1.setCellStyle(styleRightBoot);
        cel1.setCellValue(str);

        XSSFCell cel2 = bodyRow.createCell(3);
        cel2.setCellStyle(styleRightBoot);
        cel2.setCellValue(totalOrderAmt.toString());

        XSSFCell cel3 = bodyRow.createCell(4);
        cel3.setCellStyle(styleRightBoot);
        cel3.setCellValue(totalPointAmt.toString());

        XSSFCell cel4 = bodyRow.createCell(5);
        cel4.setCellStyle(styleRightBoot);
        cel4.setCellValue(totalExpressFee.toString());

        XSSFCell cel5 = bodyRow.createCell(7);
        cel5.setCellStyle(styleRightBoot);
        cel5.setCellValue(p+"个");

        return  workbook;
    }

    public String orderStatusFilter(String orderStatus){
        String result;
        switch (orderStatus){
            case "1":
                result = "待支付";
                break;
            case "2":
                result = "待发货";
                break;
            case "3":
                result = "待收货";
                break;
            case "4":
                result = "兑换单完成";
                break;
            default:
                result = "";
                break;
        }
        return result;
    }
}
