package com.distribution.service;

import com.distribution.common.utils.Page;
import com.distribution.dao.dividend.mapper.more.MoreDividendMapper;
import com.distribution.dao.memberBonus.mapper.more.MoreMemberBonusMapper;
import com.distribution.dao.memberBonus.model.MemberBonus;
import com.distribution.dao.memberBonus.model.more.DividendExcelVO;
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
        List<DividendExcelVO> result = memberBonusMapper.getExcelMemberBonusList(map);
        //定义表头
        String[] excelHeader = {"订单编号", "订单金额", "分红包个数",  "会员名称",  "领取时间",  "当日分红包金额", "管理费", "领取金额"};

        return  this.exportExcel("红包明细列表", excelHeader, result, response.getOutputStream());
    }

    public XSSFWorkbook exportExcel(String title, String[] headers, List<DividendExcelVO> list, OutputStream out) throws InvocationTargetException {
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
        sheet.setColumnWidth(1, 15 * 256);
        sheet.setColumnWidth(2, 15 * 256);
        sheet.setColumnWidth(3, 18 * 256);
        sheet.setColumnWidth(4, 18 * 256);
        sheet.setColumnWidth(5, 18 * 256);
        sheet.setColumnWidth(6, 18 * 256);
        sheet.setColumnWidth(7, 18 * 256);

        //构建表体
        XSSFCellStyle styleRight = workbook.createCellStyle();
        styleRight.setAlignment(HSSFCellStyle.ALIGN_RIGHT);//居右
        XSSFCellStyle styleCenter = workbook.createCellStyle();
        styleCenter.setAlignment(HSSFCellStyle.ALIGN_CENTER);//居中

        int t = 0;
        double e = 0;
        double m = 0;
        double p = 0;

        for(int j=0;j<list.size();j++){
            XSSFRow bodyRow = sheet.createRow(j + 1);
            System.out.println(j);
            bodyRow.createCell(0).setCellValue(list.get(j).getOrderNo().toString());
            bodyRow.createCell(1).setCellValue(list.get(j).getOrderAmount().toString());
            bodyRow.createCell(2).setCellValue(list.get(j).getDividendCount().toString());
            bodyRow.createCell(3).setCellValue(list.get(j).getMemberName().toString());
            bodyRow.createCell(4).setCellValue(new SimpleDateFormat("yyyy-MM-dd").format(list.get(j).getBonusDate()));
            bodyRow.createCell(5).setCellValue(list.get(j).getAmout().toString());
            bodyRow.createCell(6).setCellValue(list.get(j).getManageFee().toString());
            bodyRow.createCell(7).setCellValue(list.get(j).getActualAmout().toString());

            bodyRow.getCell(0).setCellStyle(styleCenter);
            bodyRow.getCell(1).setCellStyle(styleRight);
            bodyRow.getCell(2).setCellStyle(styleRight);
            bodyRow.getCell(3).setCellStyle(styleCenter);
            bodyRow.getCell(4).setCellStyle(styleCenter);
            bodyRow.getCell(5).setCellStyle(styleRight);
            bodyRow.getCell(6).setCellStyle(styleRight);
            bodyRow.getCell(7).setCellStyle(styleRight);

            t=j;

            e += list.get(j).getAmout().doubleValue();
            p += list.get(j).getManageFee().doubleValue();
            m += list.get(j).getActualAmout().doubleValue();
        }
        XSSFRow bodyRow1 = sheet.createRow(t + 2);
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);//居中
        style.setFont(font);

        XSSFCell cel1 = bodyRow1.createCell(0);
        cel1.setCellStyle(style);
        cel1.setCellValue("统计:");
        XSSFCell cel2 = bodyRow1.createCell(5);
        cel2.setCellValue(e);
        XSSFCell cel3 = bodyRow1.createCell(6);
        cel3.setCellValue(p);
        XSSFCell cel4 = bodyRow1.createCell(7);
        cel4.setCellValue(m);


        return  workbook;
    }
}
