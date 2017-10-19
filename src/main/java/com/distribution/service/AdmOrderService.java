package com.distribution.service;

import com.distribution.common.utils.Page;
import com.distribution.dao.admin.model.Admin;
import com.distribution.dao.order.mapper.OrderMasterMapper;
import com.distribution.dao.order.mapper.more.MoreOrderMasterMapper;
import com.distribution.dao.order.model.OrderMaster;
import com.distribution.dao.order.model.more.MoreOrderMaster;
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
public class AdmOrderService {
    @Autowired
    private MoreOrderMasterMapper moreOrderMasterMapper;

    @Autowired
    private OrderMasterMapper orderMasterMapper;
    /**
     * description 订单列表查询
     * @author WYN
     * */
    public Page orderList(Page page){
        /*if(null!=page.getParameterMap().get("startTime") && !"".equals(page.getParameterMap().get("startTime")))
            page.getParameterMap().put("startTime",page.getParameterMap().get("startTime").toString()+" 00:00:00");
        if(null!=page.getParameterMap().get("endTime") && !"".equals(page.getParameterMap().get("endTime")))
            page.getParameterMap().put("endTime",page.getParameterMap().get("endTime").toString()+" 23:59:59");*/

        page.setTotalCount(moreOrderMasterMapper.getOrderListCount(page));
        page.setResult(moreOrderMasterMapper.getOrderList(page));
        return page;
    }

    /**
     * description 确认发货
     * @author WYN
     * */
    public String confirmSendOrder(OrderMaster orderMaster) {
        int cnt = orderMasterMapper.updateByPrimaryKeySelective(orderMaster);

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
    public String confirmReceiveOrder(OrderMaster om, Admin admin) {

        OrderMaster orderMaster = new OrderMaster();
        orderMaster.setId(om.getId());
        orderMaster.setOrderStatues(om.getOrderStatues());
        orderMaster.setUpdateId(admin.getId());
        orderMaster.setUpdateTime(new Date());
        int cnt = orderMasterMapper.updateByPrimaryKeySelective(orderMaster);

        if(cnt > 0){
            return "success";
        }else{
            throw new RuntimeException();
        }
    }

    public XSSFWorkbook exportData(Map map, HttpServletResponse response) throws IOException, InvocationTargetException {
        List<MoreOrderMaster> result = moreOrderMasterMapper.getExcelOrderList(map);
        //定义表头
        String[] excelHeader = {"订单号", "订单来源", "会员", "会员级别", "订单金额", "支付金额", "快递费", "商品名信息", "订单时间", "订单状态", "物流信息","收货人","收货电话"};

        return  this.exportExcel("待发货列表", excelHeader, result, response.getOutputStream(), "待发货商品数量:");
    }

    public XSSFWorkbook exportData1(Map map, HttpServletResponse response) throws IOException, InvocationTargetException {
        List<MoreOrderMaster> result = moreOrderMasterMapper.getExcelOrderList1(map);
        //定义表头
        String[] excelHeader = {"订单号", "订单来源", "会员", "订单金额", "支付金额", "快递费", "商品名信息", "商品数量", "订单时间", "订单状态", "物流信息","收货人","收货电话"};

        return  this.exportExcel("已发货列表", excelHeader, result, response.getOutputStream(), "已出库商品数量:");
    }

    public XSSFWorkbook exportExcel(String title, String[] headers, List<MoreOrderMaster> list, OutputStream out, String str) throws InvocationTargetException {
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
        sheet.setColumnWidth(5, 30 * 256);
        sheet.setColumnWidth(6, 30 * 256);
        sheet.setColumnWidth(7, 30 * 256);
        sheet.setColumnWidth(8, 30 * 256);
        sheet.setColumnWidth(9, 30 * 256);
        sheet.setColumnWidth(10, 30 * 256);
        sheet.setColumnWidth(11, 12 * 256);
        sheet.setColumnWidth(12, 20 * 256);
        //构建表体
        int t = 0;
        int p = 0;
        for(int j=0;j<list.size();j++){
            XSSFRow bodyRow = sheet.createRow(j + 1);
            String goods = "";
            if(list.get(j).getGoodsNm() != null && !"".equals(list.get(j).getGoodsNm())){
                goods = goods + list.get(j).getGoodsNm();
            }
            /*if(list.get(j).getOrderQty() != null && !"".equals(list.get(j).getOrderQty())){
                goods = goods + "," + list.get(j).getOrderQty() + "个";
            }*/

            p+=(null!=list.get(j)?(null!=list.get(j).getOrderQty()?list.get(j).getOrderQty():0):0);
            System.out.println(j);
            bodyRow.createCell(0).setCellValue(list.get(j).getOrderNo().toString());
            bodyRow.createCell(1).setCellValue(this.orderCategoryFilter(list.get(j).getOrderCategory()));
            bodyRow.createCell(2).setCellValue(list.get(j).getMemberName());
//            bodyRow.createCell(3).setCellValue(this.memberLevelFilter(null!=list.get(j).getMemberLevel()?list.get(j).getMemberLevel():""));
            bodyRow.createCell(3).setCellValue(String.valueOf(list.get(j).getOrderAmt()));
            bodyRow.createCell(4).setCellValue(String.valueOf(list.get(j).getActAmt()));
            bodyRow.createCell(5).setCellValue(String.valueOf(list.get(j).getExpressFee()));
            bodyRow.createCell(6).setCellValue(goods);
            bodyRow.createCell(7).setCellValue(null==list.get(j).getOrderQty()?"":list.get(j).getOrderQty() + " 个");
            bodyRow.createCell(8).setCellValue(new SimpleDateFormat("yyyy-MM-dd").format(list.get(j).getCreateTime()));
            bodyRow.createCell(9).setCellValue(this.orderStatusFilter(list.get(j).getOrderStatues()));
            bodyRow.createCell(10).setCellValue(list.get(j).getExpressAddress());
            bodyRow.createCell(11).setCellValue(list.get(j).getReceiveName());
            bodyRow.createCell(12).setCellValue(list.get(j).getRecevivePhone());
            t=j;
        }
        XSSFRow bodyRow = sheet.createRow(t + 2);
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);//居中
        style.setFont(font);

        XSSFCell cel1 = bodyRow.createCell(0);
        cel1.setCellStyle(style);
        cel1.setCellValue(str);
        XSSFCell cel2 = bodyRow.createCell(1);
        cel2.setCellStyle(style);
        cel2.setCellValue(p+"个");

        return  workbook;
    }

    public String orderCategoryFilter(String orderCategory){
        String result;
        switch (orderCategory){
            case "1":
                result = "报单";
                break;
            case "2":
                result = "复投";
                break;
            case "3":
                result = "折扣单";
                break;
            default:
                result = "";
                break;
        }
        return result;
    }

    public String memberLevelFilter(String memberLevel){
        String result;
        switch (memberLevel){
            case "member_level1":
                result = "普卡";
                break;
            case "member_level2":
                result = "铜卡";
                break;
            case "member_level3":
                result = "银卡";
                break;
            case "member_level4":
                result = "金卡";
                break;
            case "member_level5":
                result = "白金卡";
                break;
//            case "member_level6":
//                result = "黑金卡";
//                break;
            default:
                result = "";
                break;
        }
        return result;
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
                result = "订单完成";
                break;
            default:
                result = "";
                break;
        }
        return result;
    }
}
