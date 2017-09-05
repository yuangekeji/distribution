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
import com.distribution.common.utils.Page;
import com.distribution.dao.accountFlowHistory.mapper.AccountFlowHistoryMapper;
import com.distribution.dao.accountFlowHistory.model.AccountFlowHistory;
import com.distribution.dao.accountManager.mapper.more.MoreAccountManagerMapper;
import com.distribution.dao.accountManager.model.AccountManager;
import com.distribution.dao.memberBonus.mapper.more.MoreMemberBonusMapper;
import com.distribution.dao.memberBonus.model.MemberBonus;
import com.distribution.dao.memberNode.mapper.more.MoreMemberNodeMapper;
import com.distribution.dao.memberNode.model.more.MoreMemberNode;
import com.distribution.dao.order.model.OrderMaster;

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
	private CommonService commonService;
	
	
	
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
	 * @param order 对象中必须包含订单的主体信息
	 * @author su
	 */
	public void calculateOrderBonus(OrderMaster order){
	    //初始化计算所需配置中的变量
		commonService.initBasicManageList();;
		//销售奖
		order.getCreateId();
		order.getOrderNo();
		//1代奖
		
		//2代奖直推人的推荐人获得2代奖

		//级差奖
		
		//分红包记录

		//工作室&运营中心奖/扶持奖
	  
		//处理会员晋升
	}
	/**
	 * 销售奖计算
	 * 直接给推荐人
	 * @param memberId 得奖人
	 * @param order 订单
	 * @author su
	 */
	public void calculateSalseBonus(int memberId,OrderMaster order){
		//计算奖金金额
		double bonusPercent = commonService.getSalesBonusPercent(BonusConstant.D01,BonusConstant.CODE_01,BonusConstant.SALES_BONUS_PER);
	    //生成奖金
		buidAndSaveBonus(bonusPercent,BonusConstant.BONUS_TYPE_0,memberId,order);
	}
	/**
	 * 1代奖金结算方法
	 * 直接给推荐人
	 * @param memberId 得奖人
	 * @param order 订单
	 * @author su
	 */
	public void calculateFirstGenerationBonus(int memberId,OrderMaster order){
		//计算奖金金额
		double bonusPercent = commonService.getSalesBonusPercent(BonusConstant.D01,BonusConstant.CODE_01,BonusConstant.SALES_BONUS_PER1);
	    //生成奖金
		buidAndSaveBonus(bonusPercent,BonusConstant.BONUS_TYPE_1,memberId,order);
	}

	/**
	 * 2代奖金结算方法
	 * 需要查找当前订单的推荐人的推荐人
	 * @param memberId 得奖人
	 * @param order 订单
	 * @author su
	 */
	public void calculateSecondGenerationBonus(int memberId,OrderMaster order){
		//计算奖金金额
		double bonusPercent = commonService.getSalesBonusPercent(BonusConstant.D01,BonusConstant.CODE_01,BonusConstant.SALES_BONUS_PER2);
	    //生成奖金
		buidAndSaveBonus(bonusPercent,BonusConstant.BONUS_TYPE_2,memberId,order);
	}

	/**
	 * 计算级差奖
	 * @param nodeId 当前订单人所在节点
	 * @param order 订单
	 * @author su
	 */
	public void calculateMemberLevelBonus(int nodeId,OrderMaster order){
		//查找当前节点的所有父节点非普通会员信息，从小到大升序排列；
		Map<String,String> param = new HashMap<String,String>();
		param.put("nodeId", String.valueOf(nodeId));
		param.put("memberLevel", BonusConstant.POST_LEVEL1);
        List<MoreMemberNode> list = moreNodeMapper.listParentIsManageLevelNodes(param);
        for(MoreMemberNode m : list){
        	//当前节点不参与奖金计算
        	if(null != m.getId() && m.getId().intValue() == nodeId){
        		continue;
        	}
        	int memberId = m.getMemberId();
        	String memberLevel = m.getMemberLevel();
            if(memberLevel.equals(BonusConstant.POST_LEVEL2)){
            	//奖金比例
        		double bonusPercent = commonService.getMaxPercent(BonusConstant.D05,BonusConstant.CODE_01);
            	//计算主任奖金
        		buidAndSaveBonus(bonusPercent,BonusConstant.BONUS_TYPE_5,memberId,order);
            }else if(memberLevel.equals(BonusConstant.POST_LEVEL3)){
            	//奖金比例
        		double bonusPercent = commonService.getMaxPercent(BonusConstant.D05,BonusConstant.CODE_02);
            	//计算经理奖金
        		buidAndSaveBonus(bonusPercent,BonusConstant.BONUS_TYPE_5,memberId,order);
            }else if(memberLevel.equals(BonusConstant.POST_LEVEL4)){
            	//奖金比例
        		double bonusPercent = commonService.getMaxPercent(BonusConstant.D05,BonusConstant.CODE_03);
            	//计算总监奖金
        		buidAndSaveBonus(bonusPercent,BonusConstant.BONUS_TYPE_5,memberId,order);
            }else if(memberLevel.equals(BonusConstant.POST_LEVEL5)){
            	//奖金比例
        		double bonusPercent = commonService.getMaxPercent(BonusConstant.D05,BonusConstant.CODE_04);
            	//计算董事奖金
        		buidAndSaveBonus(bonusPercent,BonusConstant.BONUS_TYPE_5,memberId,order);
            }
       }
	}
	/**
	 * 计算工作室奖&运营中心扶持奖&运营中心奖
	 * @author su
	 * @date 2017年9月5日 下午8:54:54
	 * @param nodeId
	 * @param order
	 */
	public void calculateWorkRoomAndOperatingCenterBonus(int nodeId,OrderMaster order){
	    //根据当前节点ID查找所有上级会员节点
		List<MoreMemberNode> list = moreNodeMapper.listParentNodesWithMemberInfo(nodeId);
		for(int i=0;i<list.size();i++){
			MoreMemberNode m = list.get(i);
			//忽略当前节点
        	if(m.getId().intValue() == nodeId){
        		continue;
        	}
        	//如果是工作室发工作室奖，然后继续向上发运营中心奖。
        	if(null != m.getIsSalesDept() && m.getIsSalesDept().equalsIgnoreCase("Y")){
        		double bonusPercent = commonService.getMaxPercent(BonusConstant.D06,BonusConstant.CODE_03);
        		//发工作室奖
        		buidAndSaveBonus(bonusPercent,BonusConstant.BONUS_TYPE_7,m.getMemberId(),order);
        		//发运营中心扶持奖，从当前节点开始向上找，遇到普通会员结束，遇到运营中心发奖。
        		calculateOperatingCenterHelpBonus(list,i+1,order);
        		break;
        	}else if(null != m.getIsOperator() && m.getIsOperator().equalsIgnoreCase("Y")){
        		//如果是运营中心发运营中心奖，发奖结束。
        		double bonusPercent = commonService.getMaxPercent(BonusConstant.D06,BonusConstant.CODE_01);
        		buidAndSaveBonus(bonusPercent,BonusConstant.BONUS_TYPE_8,m.getMemberId(),order);
        		break;
        	}else{
        		continue;
        	}
		}
	}
	/**
	 * 发放运营中心扶持奖
	 * 发完工作室奖后继续向上找运营中心
	 * 如果是运营中心就跳过
	 * 是普通会员就结束
	 * 是运营中心就发奖
	 * @date 2017年9月5日 下午9:06:38
	 * @param list
	 * @param num
	 * @param order
	 */
	public void calculateOperatingCenterHelpBonus(List<MoreMemberNode> list,int num,OrderMaster order){
	    //接着刚才循环的节点继续向上找
		for(int i=num;i<list.size();i++){
			MoreMemberNode m = list.get(i);
        	//如果是工作室循环继续下一次
        	if(null != m.getIsSalesDept() && m.getIsSalesDept().equalsIgnoreCase("Y")){
        		continue;
        	}else if(null != m.getIsOperator() && m.getIsOperator().equalsIgnoreCase("Y")){
        		//如果是运营中心发运营中心扶持奖，发奖结束。
        		double bonusPercent = commonService.getMaxPercent(BonusConstant.D06,BonusConstant.CODE_02);
        		buidAndSaveBonus(bonusPercent,BonusConstant.BONUS_TYPE_9,m.getMemberId(),order);
        		break;
        	}else{
        		//非工作室，非运营中心，即是普通会员，发奖结束。
        		break;
        	}
		}
	}

	/**
	 * 构建奖金对象并保存
	 * @param bonusPercent 奖金比例
	 * @param bonusType 奖金类型
	 * @param memberId 得奖会员
	 * @param order 订单
	 * @return
	 * @author su
	 */
	private MemberBonus buidAndSaveBonus(double bonusPercent,String bonusType,int memberId,OrderMaster order){
		double bonusAmt =  commonService.multiply(order.getOrderAmt().doubleValue(),bonusPercent);
		//管理费
		double managePercent = commonService.getMaxPercent(BonusConstant.D07,BonusConstant.CODE_00);
		double manageFee = commonService.multiply(bonusAmt,managePercent);
		MemberBonus bonus = new MemberBonus();
		bonus.setActualAmout(bonusAmt-manageFee);
		bonus.setAmout(bonusAmt);
		bonus.setBonusDate(new Date());
		bonus.setBonusType(bonusType);
		bonus.setCreateBy(order.getCreateId());
		bonus.setCreateDate(new Date());
		bonus.setManageFee(manageFee);
		bonus.setMemberId(memberId);
		bonus.setOrderId(order.getId());
		bonus.setOrderNo(order.getOrderNo());
		//保存奖金
    	saveBonus(bonus);
		return bonus;
	}
	/**
	 * 保存奖金，更新账户余额，记录账号流水。
	 * @param bonus 计算完封装好的奖金对象
	 * @author su
	 */
	public void saveBonus(MemberBonus bonus){
		
		//实际获得奖金总数
		double totalBonus = bonus.getActualAmout();
		//根据实际获得奖金数量计算种子币
		double seedBonus = commonService.multiply(totalBonus,BonusConstant.SEED_PERCENT);
		//根据实际获得奖金数量计算奖金币
		double bonus_rest = commonService.multiply(totalBonus,BonusConstant.BONUS_PERCENT);
		
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
	
	
	/*public static void main(String[] arg){
		double v1 = 1.96;
		double v2 = bonus_percent;
		System.out.println(multiply(v1,v2));
	}*/
}
