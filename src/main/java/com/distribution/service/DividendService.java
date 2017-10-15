package com.distribution.service;

import com.distribution.common.utils.Page;
import com.distribution.dao.dividend.mapper.more.MoreDividendMapper;
import com.distribution.dao.dividend.model.Dividend;
import com.distribution.dao.memberBonus.mapper.more.MoreMemberBonusMapper;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by lijingx on 8/28/2017.
 */

@Service
public class DividendService {

    @Autowired
    private MoreDividendMapper dividendMapper;

    @Autowired
    private MoreMemberBonusMapper memberBonusMapper;



    /**
     * description 分紅包列表
     * @author jingxin
     * */
    public Page dividendList(Page page){

        page.setTotalCount(dividendMapper.getDividendListCount(page));
        page.setResult( dividendMapper.getDividendList(page));
        return page;
    }

    /**
     * 查詢分紅包領取明細
     * @param page
     * @return
     */
    public Page dividendDetails(Page page){

        page.setTotalCount(memberBonusMapper.getDividendDetailsCount(page));
        page.setResult( memberBonusMapper.getDividendDetails(page));
        return page;
    }

    public Map memberDividendCount(Integer memberId){
        Map map = new HashMap();

        List<Map> dividendCount = dividendMapper.memberDividendCount(memberId);

        for(Map dc : dividendCount){

            if("1".equals(dc.get("dividendStatus"))){
                map.put("validDividendCount",dc.get("dividendCount"));
                break;
            }else if("2".equals(dc.get("dividendStatus"))){
                map.put("inValidDividendCount",dc.get("dividendCount"));
                break;
            }

        }
        BigDecimal validDividendCount    = map.containsKey("validDividendCount")   ?  (BigDecimal)map.get("validDividendCount") :   new BigDecimal(0);
        BigDecimal inValidDividendCount  = map.containsKey("inValidDividendCount") ?  (BigDecimal) map.get("inValidDividendCount") : new BigDecimal(0);

        map.put("validDividendCount", validDividendCount );
        map.put("inValidDividendCount",inValidDividendCount);
        map.put("totalDividendCount", validDividendCount.add(inValidDividendCount) );
        return map;
    }

    /**
     * description 红包列表下载
     * @author Bright
     * */
    public XSSFWorkbook exportData(Map map, HttpServletResponse response) throws IOException, InvocationTargetException {
        List<Dividend> result = dividendMapper.getExcelDividendList(map);
        //定义表头
        String[] excelHeader = {"订单号", "分红包个数", "订单金额", "已领取金额", "未领取金额", "领取金额上限"};

        return  this.exportExcel("红包列表", excelHeader, result, response.getOutputStream());
    }

    public XSSFWorkbook exportExcel(String title, String[] headers, List<Dividend> list, OutputStream out) throws InvocationTargetException {
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
        //构建表体
        int t = 0;
        int p = 0;
        int m = 0;
        for(int j=0;j<list.size();j++){
            XSSFRow bodyRow = sheet.createRow(j + 1);

            bodyRow.createCell(0).setCellValue(list.get(j).getOrderNo().toString());
            bodyRow.createCell(1).setCellValue(list.get(j).getDividendCount());
            bodyRow.createCell(2).setCellValue(list.get(j).getOrderAmount().toString());
            bodyRow.createCell(3).setCellValue(list.get(j).getReceivedAmount().toString());
            bodyRow.createCell(4).setCellValue(list.get(j).getRemainAmount().toString());
            bodyRow.createCell(5).setCellValue(list.get(j).getDividendLimit().toString());
            p+=list.get(j).getReceivedAmount().intValue();
            m+=list.get(j).getRemainAmount().intValue();
            t=j;
        }
        XSSFRow bodyRow1 = sheet.createRow(t + 5);
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);//居中
        style.setFont(font);

        XSSFCell cel1 = bodyRow1.createCell(0);
        cel1.setCellStyle(style);
        cel1.setCellValue("已领取奖金总金额:");
        XSSFCell cel2 = bodyRow1.createCell(1);
        cel2.setCellStyle(style);
        cel2.setCellValue(p+"元");

        /***************************/

        XSSFRow bodyRow2 = sheet.createRow(t + 7);
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);//居中
        style.setFont(font);

        XSSFCell cel3 = bodyRow2.createCell(0);
        cel3.setCellStyle(style);
        cel3.setCellValue("未领取奖金总金额:");
        XSSFCell cel4 = bodyRow2.createCell(1);
        cel4.setCellStyle(style);
        cel4.setCellValue(m+"元");

        return  workbook;
    }
}
