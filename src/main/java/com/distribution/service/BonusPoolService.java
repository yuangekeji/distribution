/*
  * Pactera Technology International Ltd.
  * Copyright (c) 2014-2015 All Rights Reserved.
  */
package com.distribution.service;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.distribution.common.constant.BonusConstant;
import com.distribution.common.utils.DateHelper;
import com.distribution.dao.bonusCachePool.mapper.BonusCachePoolMapper;
import com.distribution.dao.bonusCachePool.model.BonusCachePool;
import com.distribution.dao.bonusCachePool.model.BonusCachePoolExample;
import com.distribution.dao.bonusPool.mapper.BonusPoolMapper;
import com.distribution.dao.bonusPool.model.BonusPool;
import com.distribution.dao.bonusPool.model.BonusPoolExample;
import com.distribution.dao.bonusPoolHistory.mapper.BonusPoolHistoryMapper;
import com.distribution.dao.bonusPoolHistory.model.BonusPoolHistory;
import com.distribution.dao.platformAccount.mapper.PlatformAccountMapper;
import com.distribution.dao.platformAccount.model.PlatformAccount;
import com.distribution.dao.platformAccountHistory.mapper.more.MorePlatformAccountHistoryMapper;
import com.distribution.dao.platformAccountHistory.model.PlatformAccountHistory;

@Service
public class BonusPoolService {
	@Autowired
	private BonusPoolMapper bonusPoolMapper;
	@Autowired
	private BonusCachePoolMapper bonusCachePoolMapper;
	@Autowired
	private BonusPoolHistoryMapper bonusPoolHistoryMapper;
	@Autowired
	private MorePlatformAccountHistoryMapper platformAccountHistoryMapper;
	@Autowired
	private PlatformAccountMapper platformAccountMapper;

	
	/**
	 * 更新奖金池
	 * 更新的业务包括当日发奖剩余的营业额、管理员划拨奖金到缓存池；
	 */
	public void updatePool(BigDecimal amount,int poolType,int addOrReduce){
		if(amount.doubleValue() <= 0){
			return;
		}
		//查询现有奖金次信息
		BonusPool pool = null;
		if(poolType == BonusConstant.POOL_TYPE_DIVIDEND){
			pool = bonusPoolMapper.selectByPrimaryKey(BonusConstant.POOL_ID_DIVIDEND);
		}else if(poolType == BonusConstant.POOL_TYPE_NODE){
			pool = bonusPoolMapper.selectByPrimaryKey(BonusConstant.POOL_ID_NODE);
		}else{
			return;
		}
		double oldAmount = pool.getTotalAmount();
		BigDecimal bonusResult;
		//计算奖金
		if(addOrReduce == BonusConstant.POOL_BONUS_ADD){
			bonusResult = new BigDecimal(pool.getTotalAmount()).add(amount); 
		}else if(addOrReduce == BonusConstant.POOL_BONUS_REDUCE && pool.getTotalAmount().doubleValue()>=amount.doubleValue()){
			bonusResult = new BigDecimal(pool.getTotalAmount()).subtract(amount); 
		}else{
			return;
		}
		pool.setTotalAmount(bonusResult.doubleValue());
		pool.setUpdateTime(new Date());
		pool.setUpdateBy(0);
		//更新奖金池
		bonusPoolMapper.updateByPrimaryKeySelective(pool);
		//记录奖金流水池
		BonusPoolHistory history = new BonusPoolHistory();
		history.setPoolId(pool.getId());
		history.setAddRemove(addOrReduce);
		history.setOldAmout(oldAmount);
		history.setAmout(amount.doubleValue());
		history.setNewAmout(bonusResult.doubleValue());
		history.setCreateBy(0);
		history.setCreateTime(new Date());
		history.setOperateTime(new Date());
		//history.setRemarks(remarks);
		bonusPoolHistoryMapper.insert(history);
	}
	/**
	 * 
	 * 更新奖金缓存池
	 * 更新业务包括，发奖扣减缓存池、管理员划拨奖金到缓存池；
	 * @date 2017年9月2日 下午1:35:51
	 */
	public void updateCachePool(BigDecimal amount,int poolType,int addOrReduce){
		if(amount.doubleValue() <= 0){
			return;
		}
		//查询现有缓存池信息
		BonusCachePool cachePool = null;
		if(poolType == BonusConstant.POOL_TYPE_DIVIDEND){
			cachePool = bonusCachePoolMapper.selectByPrimaryKey(BonusConstant.POOL_ID_DIVIDEND);
		}else if(poolType == BonusConstant.POOL_TYPE_NODE){
			cachePool = bonusCachePoolMapper.selectByPrimaryKey(BonusConstant.POOL_ID_NODE);
		}else{
			return;
		}
		BigDecimal bonusResult;
		//计算奖金
		if(addOrReduce == BonusConstant.POOL_BONUS_ADD){
			bonusResult = new BigDecimal(cachePool.getTotalAmount()).add(amount); 
		}else if(addOrReduce == BonusConstant.POOL_BONUS_REDUCE && cachePool.getTotalAmount().doubleValue()>=amount.doubleValue()){
			bonusResult = new BigDecimal(cachePool.getTotalAmount()).subtract(amount); 
		}else{
			return;
		}
		cachePool.setTotalAmount(bonusResult.doubleValue());
		cachePool.setUpdateTime(new Date());
		cachePool.setUpdateBy(0);
		//更新缓存池
		bonusCachePoolMapper.updateByPrimaryKeySelective(cachePool);
	}
	/**
	 * 
	 * 根据缓存池id
	 * 查询余额
	 * @author su
	 * @date 2017年9月7日 下午5:55:52
	 * @param id
	 * @return
	 */
	public double getBonusCachePool(int id){
		double total = 0;
		BonusCachePool pool = bonusCachePoolMapper.selectByPrimaryKey(id);
		if(null != pool){
			total = pool.getTotalAmount();
		}
		return total;
	}
	
	public double getBonusPool(int id){
		double total = 0;
		BonusPool pool = bonusPoolMapper.selectByPrimaryKey(id);
		if(null != pool){
			total = pool.getTotalAmount();
		}
		return total;
	}


	/**
	 * 获取资金池数据
	 * @param poolType
	 * @return
     */
	public BonusPool getBonusPool(String poolType){
		BonusPoolExample bonusPoolExample = new BonusPoolExample();
		BonusPoolExample.Criteria bonusPoolCriteria = bonusPoolExample.createCriteria();
		bonusPoolCriteria.andPoolTypeEqualTo(poolType);

		List<BonusPool> bonusPools = bonusPoolMapper.selectByExample(bonusPoolExample);
		if(bonusPools != null && bonusPools.size() >0)
			return  bonusPools.get(0);
		else
	      return  new BonusPool();
	}

	/**
	 * 获取缓存池数据
	 * @param poolType
	 * @return
     */
	public BonusCachePool getBonusCachePool(String poolType){
		BonusCachePoolExample bonusCachePoolExample = new BonusCachePoolExample();
		BonusCachePoolExample.Criteria bonusCachePoolCriteria = bonusCachePoolExample.createCriteria();
		bonusCachePoolCriteria.andPoolTypeEqualTo(poolType);

		List<BonusCachePool> bonusCachePools = bonusCachePoolMapper.selectByExample(bonusCachePoolExample);
		if(bonusCachePools != null && bonusCachePools.size() >0)
			return  bonusCachePools.get(0);
		else
			return  new BonusCachePool();
	}

	public boolean updatePayAmtPoolProc(BigDecimal amount,int poolType){
		this.updateCachePool(amount,poolType,BonusConstant.POOL_BONUS_ADD);
		this.updatePool(amount,poolType,BonusConstant.POOL_BONUS_REDUCE);
		return true;
	}
	/**
	 * Name: 保存平台账户流水
	 * Description: 
	 * @author BAB1703658
	 * @date 2017年12月6日 上午6:24:54
	 * @param createBy
	 * @param accountAmountNew
	 * @param flowAmount
	 */
	public void savePlatformAccountflow(PlatformAccount pa,String createBy,BigDecimal accountAmountNew,BigDecimal accountAmountOld,BigDecimal flowAmount) {
		PlatformAccountHistory hs = new PlatformAccountHistory();
		pa.setId(null);
		BeanUtils.copyProperties(pa, hs);
		hs.setAccountAmountNew(accountAmountNew);
		hs.setAccountAmountOld(accountAmountOld);
		hs.setFlowAmount(flowAmount);
		hs.setCreateDate(new Date());
		hs.setCreateBy(createBy);
		platformAccountHistoryMapper.insert(hs);
	}
	/**
	 * Name:更新 账户资金
	 * Description: 
	 * @author BAB1703658
	 * @date 2017年12月6日 上午6:28:38
	 * @param pa
	 */
	public void updatePlatformAccount(PlatformAccount pa) {
		platformAccountMapper.updateByPrimaryKey(pa);
	}
	/**
	 * Name: 更新平台账户资金并生成流水
	 * Description: 
	 * @author BAB1703658
	 * @date 2017年12月6日 上午6:44:05
	 * @param flowAmout
	 * @param createBy
	 */
	public void updatePlatformAccountByFlow(BigDecimal flowAmout,String createBy) {
		PlatformAccount pa = getPlatformAccountById();
		BigDecimal accountAmountOld = pa.getAccountAmount();
		BigDecimal accountAmountNew = accountAmountOld.subtract(flowAmout);
		pa.setAccountAmount(accountAmountNew);
		pa.setUpdateBy(createBy);
		pa.setUpdateTime(new Date());
		platformAccountMapper.updateByPrimaryKeySelective(pa);
		this.savePlatformAccountflow(pa,createBy,accountAmountNew,accountAmountOld,flowAmout);
	}
	/**
	 * Name: 划拨资金到奖金池
	 * Description: 
	 * @author BAB1703658
	 * @date 2017年12月6日 上午6:53:33
	 * @param flowAmout
	 * @param createBy
	 * @param poolType
	 */
	public void saveAccountToBonus(BigDecimal flowAmout,String createBy,int poolType) {
		this.updatePlatformAccountByFlow(flowAmout,createBy);
		this.updatePool(flowAmout, poolType, BonusConstant.POOL_BONUS_ADD);
	}
	public PlatformAccount getPlatformAccountById() {
		PlatformAccount pa = platformAccountMapper.selectByPrimaryKey(1);
		return pa;
	}
	/**
	 * Name: 查询平台资金流出
	 * Description: 
	 * @author BAB1703658
	 * @date 2017年12月9日 下午2:23:27
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public List<PlatformAccountHistory> listPlatformAccountHistory(String startDate,String endDate){
		Map<String,Object> param = new HashMap<String,Object>();
		param.put("startDate", startDate);
		param.put("endDate", endDate);
		List<PlatformAccountHistory> list = platformAccountHistoryMapper.listPlatformAccountHistory(param);
		return list;
	}
	/**
	 * Name: 平台资金流水导出
	 * Description: 
	 * @author BAB1703658
	 * @date 2017年12月9日 下午2:34:05
	 * @param map
	 * @param response
	 * @return
	 * @throws IOException
	 * @throws InvocationTargetException
	 */
	public XSSFWorkbook exportData(String startDate,String endDate) throws IOException, InvocationTargetException {
		List<PlatformAccountHistory> list = listPlatformAccountHistory(startDate,endDate);
		//定义表头
		String[] excelHeader = {"总销售额","奖金发放总额","平台资金总额", "账户总额", "奖金池总额", "账户原总额", "账户新总额",  "流水金额", "流水创建时间", "创建人"};

		return this.exportExcel("平台账户资金流水明细", excelHeader, list);
	}

	public XSSFWorkbook exportExcel(String title, String[] headers, List<PlatformAccountHistory> list) throws InvocationTargetException {
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
		sheet.setColumnWidth(0, 20 * 256);
		sheet.setColumnWidth(1, 20 * 256);
		sheet.setColumnWidth(2, 20 * 256);
		sheet.setColumnWidth(3, 20 * 256);
		sheet.setColumnWidth(4, 20 * 256);
		sheet.setColumnWidth(5, 20 * 256);
		sheet.setColumnWidth(6, 20 * 256);
		sheet.setColumnWidth(7, 20 * 256);
		sheet.setColumnWidth(8, 20 * 256);
		sheet.setColumnWidth(9, 20 * 256);
		//定义body样式
		XSSFCellStyle styleRight = workbook.createCellStyle();
		styleRight.setAlignment(HSSFCellStyle.ALIGN_RIGHT);//居右
		XSSFCellStyle styleCenter = workbook.createCellStyle();
		styleCenter.setAlignment(HSSFCellStyle.ALIGN_CENTER);//居中

		for(int j=0;j<list.size();j++){
			XSSFRow bodyRow = sheet.createRow(j+1);
			PlatformAccountHistory history = list.get(j);
			bodyRow.createCell(0).setCellValue(history.getTotalSales().toString());
			bodyRow.createCell(1).setCellValue(history.getTotalBonus().toString());
			bodyRow.createCell(2).setCellValue(history.getPlatformAmount().toString());
			bodyRow.createCell(3).setCellValue(history.getAccountAmount().toString());
			bodyRow.createCell(4).setCellValue(history.getPoolAmount().toString());
			bodyRow.createCell(5).setCellValue(history.getAccountAmountOld().toString());
			bodyRow.createCell(6).setCellValue(history.getAccountAmountNew().toString());
			bodyRow.createCell(7).setCellValue(history.getFlowAmount().toString());
			bodyRow.createCell(8).setCellValue(DateHelper.formatDate(history.getCreateDate(), DateHelper.YYYY_MM_DD_HH_MM_SS));
			bodyRow.createCell(9).setCellValue(history.getCreateBy().toString());
		}
		return  workbook;
	}

}
