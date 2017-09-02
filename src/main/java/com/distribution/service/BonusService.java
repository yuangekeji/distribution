/*
  * Pactera Technology International Ltd.
  * Copyright (c) 2014-2015 All Rights Reserved.
  */
package com.distribution.service;

import java.math.BigDecimal;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.distribution.common.constant.BonusConstant;
import com.distribution.dao.accountFlowHistory.mapper.AccountFlowHistoryMapper;
import com.distribution.dao.accountFlowHistory.model.AccountFlowHistory;
import com.distribution.dao.accountManager.mapper.more.MoreAccountManagerMapper;
import com.distribution.dao.accountManager.model.AccountManager;
import com.distribution.dao.memberBonus.mapper.more.MoreMemberBonusMapper;
import com.distribution.dao.memberBonus.model.MemberBonus;
import com.distribution.dao.order.model.Order;

@Service
public class BonusService {
	
	@Autowired
	private MoreMemberBonusMapper moreMemberBonusMapper;
	private MoreAccountManagerMapper moreAccountManagerMapper;
	private AccountFlowHistoryMapper accountFlowHistoryMapper;
	
	/**
	 * 奖金处理总入口方法
	 * 当订单生效时调用此方法
	 * order对象中必须包含订单的主体信息
	 * @date 2017年9月1日 下午3:22:21
	 * @param order
	 */
	public void calculateOrderBonus(Order order){
	   //初始化计算所需变量

      //销售奖

      //1代奖

      //2代奖

      //级差奖

      //工作室&运营中心奖
	  
	  //生成分红包记录

	}
	
	//保存奖金，更新账户余额，记录账号流水。
	public void saveBonus(MemberBonus bonus){
		
		//实际获得奖金总数
		double totalBonus = bonus.getActualAmout();
		//根据实际获得奖金数量计算种子币
		double seedBonus = multiply(totalBonus,BonusConstant.SEED_PERCENT);
		//根据实际获得奖金数量计算奖金币
		double bonus_rest = multiply(totalBonus,BonusConstant.BONUS_PERCENT);
		
		//查询现有账号信息
		AccountManager account = new AccountManager();
		account.setMemberId(bonus.getMemberId());
		account = moreAccountManagerMapper.selectAccountManager(account);
		//对账户余额进行赋值
		BigDecimal total = account.getTotalBonus().add(new BigDecimal(totalBonus));
		BigDecimal seedAmt = account.getSeedAmt().add(new BigDecimal(seedBonus));
		BigDecimal bonusAmt = account.getBonusAmt().add(new BigDecimal(bonus_rest));
		account.setBonusAmt(bonusAmt);
		account.setSeedAmt(seedAmt);
		account.setTotalBonus(total);
		account.setUpdateId(bonus.getMemberId());
		account.setUpdateTime(new Date());
		
		AccountFlowHistory flow = new AccountFlowHistory();
		flow.setBonusAmt(bonusAmt);
		flow.setCreateId(bonus.getMemberId());
		flow.setCreateTime(new Date());
		//奖金类型，参照奖金表设置
		flow.setFlowType(bonus.getBonusType());
		flow.setMemberId(account.getMemberId());
		flow.setSeedAmt(seedAmt);
		//设置入账
		flow.setTotalAmt(total);
		flow.setType(BonusConstant.ACCOUNT_TYPE_IN);
		//保存奖金
		moreMemberBonusMapper.insert(bonus);
		//更新账户余额
		moreAccountManagerMapper.updateByPrimaryKeySelective(account);
		//记录账号资金出入情况
		accountFlowHistoryMapper.insert(flow);
	}
	//相乘保留两位小数，四舍五入。
	private static double multiply(double v1,double v2){
		BigDecimal a1 = new BigDecimal(v1);
		BigDecimal b1 = new BigDecimal(v2);
		BigDecimal result = a1.multiply(b1);
		return result.setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue();
	}
	/*public static void main(String[] arg){
		double v1 = 1.96;
		double v2 = bonus_percent;
		System.out.println(multiply(v1,v2));
	}*/
}
