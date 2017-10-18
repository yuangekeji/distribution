package com.distribution.service;

import com.distribution.common.utils.Page;
import com.distribution.dao.dividend.mapper.more.MoreDividendMapper;
import com.distribution.dao.memberBonus.mapper.more.MoreMemberBonusMapper;
import com.distribution.dao.memberBonus.model.MemberBonus;
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
        List<MemberBonus> result = memberBonusMapper.getExcelMemberBonusList(map);
        //定义表头
        String[] excelHeader = {"订单编号", "领取时间", "当日分红包金额", "领取金额", "管理费"};

        return  this.exportExcel("红包明细列表", excelHeader, result, response.getOutputStream());
    }

    public XSSFWorkbook exportExcel(String title, String[] headers, List<MemberBonus> list, OutputStream out) throws InvocationTargetException {
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
        //构建表体
        int t = 0;
        double e = 0;
        double m = 0;
        double p = 0;
        for(int j=0;j<list.size();j++){
            XSSFRow bodyRow = sheet.createRow(j + 1);
            System.out.println(j);
            bodyRow.createCell(0).setCellValue(list.get(j).getOrderNo().toString());
            bodyRow.createCell(1).setCellValue(new SimpleDateFormat("yyyy-MM-dd").format(list.get(j).getBonusDate()));
            bodyRow.createCell(2).setCellValue(list.get(j).getAmout().toString());
            bodyRow.createCell(3).setCellValue(list.get(j).getActualAmout().toString());
            bodyRow.createCell(4).setCellValue(list.get(j).getManageFee().toString());
            t=j;
            e+=list.get(j).getAmout().doubleValue();
            m+=list.get(j).getActualAmout().doubleValue();
            p+=list.get(j).getManageFee().doubleValue();
        }
        XSSFRow bodyRow1 = sheet.createRow(t + 2);
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);//居中
        style.setFont(font);

        XSSFCell cel1 = bodyRow1.createCell(0);
        cel1.setCellStyle(style);
        cel1.setCellValue("统计:");
        XSSFCell cel2 = bodyRow1.createCell(2);
        cel2.setCellValue(e);
        XSSFCell cel3 = bodyRow1.createCell(3);
        cel3.setCellValue(m);
        XSSFCell cel4 = bodyRow1.createCell(4);
        cel4.setCellValue(p);


        return  workbook;
    }
}
