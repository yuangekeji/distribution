/*
  * Pactera Technology International Ltd.
  * Copyright (c) 2014-2015 All Rights Reserved.
  */
package com.distribution.service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.distribution.common.constant.BonusConstant;
import com.distribution.dao.accountFlowHistory.mapper.AccountFlowHistoryMapper;
import com.distribution.dao.accountFlowHistory.model.AccountFlowHistory;
import com.distribution.dao.accountManager.mapper.more.MoreAccountManagerMapper;
import com.distribution.dao.accountManager.model.AccountManager;
import com.distribution.dao.basicManage.mapper.BasicManageMapper;
import com.distribution.dao.basicManage.model.BasicManage;
import com.distribution.dao.basicManage.model.BasicManageExample;
import com.distribution.dao.dictionary.model.Dictionary;
import com.distribution.dao.memberBonus.mapper.more.MoreMemberBonusMapper;
import com.distribution.dao.memberBonus.model.MemberBonus;
import com.distribution.dao.memberNode.mapper.more.MoreMemberNodeMapper;
import com.distribution.dao.order.model.OrderMaster;
import com.distribution.common.utils.Page;

@Service
public class BonusService {
	
	@Autowired
	private MoreMemberBonusMapper moreMemberBonusMapper;
	@Autowired
	private MoreAccountManagerMapper moreAccountManagerMapper;
	@Autowired
	private AccountFlowHistoryMapper accountFlowHistoryMapper;
	@Autowired
	private MoreMemberNodeMapper moreNodeMapper;
	@Autowired
	private BasicManageMapper basicManageMapper;
	
	/**
     * description 查詢奖金明细列表
     * @author shiqing
     * */
    public Page selectMemberBonusList(Page page){
        page.setTotalCount(moreMemberBonusMapper.selectMemberBonusListCount(page));
        page.setResult( moreMemberBonusMapper.selectMemberBonusList(page));
        return page;
    }
    /**
     * description 查詢获奖明细
     * @author shiqing
     * */
    public Page selectMemberBonusDetail(Page page){
        page.setResult( moreMemberBonusMapper.selectMemberBonusDetail(page));
        return page;
    }
    
	/**
	 * 奖金处理总入口方法
	 * 当订单生效时调用此方法
	 * order对象中必须包含订单的主体信息
	 * @date 2017年9月1日 下午3:22:21
	 * @param orderMaster
	 */
	public void calculateOrderBonus(OrderMaster orderMaster){
	   //初始化计算所需变量

      //销售奖

      //1代奖

      //2代奖

      //级差奖

      //工作室&运营中心奖
	  
	  //生成分红包记录

	}
	/**
	 * 计算级差奖
	 * @param nodeId
	 * @param orderAmount
	 * @param orderId
	 * @param createId
	 */
	public void calculateMemberLevelBonus(int nodeId,double orderAmount,int orderId,int createId){
		//查找当前节点的所有父节点非普通会员信息，从小到大升序排列；
		Map<String,String> param = new HashMap<String,String>();
		param.put("nodeId", String.valueOf(nodeId));
		param.put("memberLevel", BonusConstant.POST_LEVEL1);
        List<Map<String,String>> list = moreNodeMapper.listParentIsManageLevelNodes(param);
        for(Map<String,String> m : list){
        	int memberId = Integer.parseInt(m.get("memberId"));
        	String memberLevel = m.get("memberLevel");
            if(memberLevel.equals(BonusConstant.POST_LEVEL2)){
            	//计算主任奖金
            	buidBonus(BonusConstant.D05,BonusConstant.CODE_01,orderAmount,BonusConstant.BONUS_TYPE_5,memberId,orderId,createId);
            }else if(memberLevel.equals(BonusConstant.POST_LEVEL3)){
            	//计算经理奖金
            	buidBonus(BonusConstant.D05,BonusConstant.CODE_02,orderAmount,BonusConstant.BONUS_TYPE_5,memberId,orderId,createId);
            }else if(memberLevel.equals(BonusConstant.POST_LEVEL4)){
            	//计算总监奖金
            	buidBonus(BonusConstant.D05,BonusConstant.CODE_03,orderAmount,BonusConstant.BONUS_TYPE_5,memberId,orderId,createId);
            }else if(memberLevel.equals(BonusConstant.POST_LEVEL5)){
            	//计算董事奖金
            	buidBonus(BonusConstant.D05,BonusConstant.CODE_04,orderAmount,BonusConstant.BONUS_TYPE_5,memberId,orderId,createId);
            }
       }
	}
	/**
	 * 构建奖金对象并保存
	 * @param bonusType
	 * @param memberId
	 * @param orderId
	 * @return
	 */
	private MemberBonus buidBonus(String typeCode,String detailCode,double orderAmount,String bonusType,int memberId,int orderId,int createId){
		//奖金比例
		double bonusPercent = getMaxPercent(typeCode,detailCode);
		double bonusAmt =  multiply(orderAmount,bonusPercent);
		//管理费
		double managePercent = getMaxPercent(BonusConstant.D07,BonusConstant.CODE_00);
		double manageFee = multiply(bonusAmt,managePercent);
		MemberBonus bonus = new MemberBonus();
		bonus.setActualAmout(bonusAmt-manageFee);
		bonus.setAmout(bonusAmt);
		bonus.setBonusDate(new Date());
		bonus.setBonusType(bonusType);
		bonus.setCreateBy(createId);
		bonus.setCreateDate(new Date());
		bonus.setManageFee(manageFee);
		bonus.setMemberId(memberId);
		bonus.setOrderId(orderId);
		//保存奖金
    	saveBonus(bonus);
		return bonus;
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
	//
	private double getMaxPercent(String typeCode,String detailCode){
		BigDecimal big = null;
		BasicManageExample example = new BasicManageExample();
		List<BasicManage> list = basicManageMapper.selectByExample(example);
		for(BasicManage basic : list){
			if(basic.getTypeCode().equals(typeCode) && basic.getDetailCode().equals(detailCode)){
				big = basic.getMaxPercent();
				break;
			}
		}
		return big.doubleValue()/100;
	}
	
	/*public static void main(String[] arg){
		double v1 = 1.96;
		double v2 = bonus_percent;
		System.out.println(multiply(v1,v2));
	}*/
}
