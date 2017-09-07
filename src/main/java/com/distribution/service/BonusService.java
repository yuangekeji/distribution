/*
  * Pactera Technology International Ltd.
  * Copyright (c) 2014-2015 All Rights Reserved.
  */
package com.distribution.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.distribution.common.constant.BonusConstant;
import com.distribution.common.utils.DateHelper;
import com.distribution.common.utils.Page;
import com.distribution.dao.accountFlowHistory.mapper.AccountFlowHistoryMapper;
import com.distribution.dao.accountFlowHistory.model.AccountFlowHistory;
import com.distribution.dao.accountManager.mapper.more.MoreAccountManagerMapper;
import com.distribution.dao.accountManager.model.AccountManager;
import com.distribution.dao.dateBonusHistory.mapper.more.MoreDateBonusHistoryMapper;
import com.distribution.dao.dateBonusHistory.model.DateBonusHistory;
import com.distribution.dao.dividend.mapper.more.MoreDividendMapper;
import com.distribution.dao.dividend.model.Dividend;
import com.distribution.dao.dividendHistory.mapper.more.MoreDividendHistoryMapper;
import com.distribution.dao.dividendHistory.model.DividendHistory;
import com.distribution.dao.jobLogs.mapper.JobLogsMapper;
import com.distribution.dao.member.mapper.MemberMapper;
import com.distribution.dao.member.model.Member;
import com.distribution.dao.memberBonus.mapper.more.MoreMemberBonusMapper;
import com.distribution.dao.memberBonus.model.MemberBonus;
import com.distribution.dao.memberNode.mapper.more.MoreMemberNodeMapper;
import com.distribution.dao.memberNode.model.more.MoreMemberNode;
import com.distribution.dao.nodeBonusHistory.mapper.more.MoreNodeBonusHistoryMapper;
import com.distribution.dao.order.mapper.more.MoreOrderMasterMapper;
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
	private MemberMapper memberMapper;
	@Autowired
	private CommonService commonService;
	@Autowired
	private NodeService nodeService;
	@Autowired
    private JobLogsMapper jobLogsMapper;
	@Autowired
    private MoreDateBonusHistoryMapper moreDateBonusHistoryMapper;
	@Autowired
	MoreOrderMasterMapper moreOrderMasterMapper;
	@Autowired
	private MoreNodeBonusHistoryMapper nodeBonusHistoryMapper;
	@Autowired
	private BonusPoolService bonusPoolService;
	@Autowired
	private MoreDividendMapper moreDividendMapper;
	@Autowired
	private MoreDividendHistoryMapper dividendHistoryMapper;
	
	
	
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
	public void insertOrderBonus(OrderMaster order){
		//订单主人会员
		Member owner = memberMapper.selectByPrimaryKey(order.getMemberId());
		//有推荐人才发奖
		if(null != owner.getRecommendId() && owner.getRecommendId() > 0){
			//初始化计算所需配置中的变量
			commonService.initBasicManageList();
			//当前订单的主人的推荐人
			int recommendUser = owner.getRecommendId();
			//订单主人的推荐人
			Member ownerRecomend = memberMapper.selectByPrimaryKey(recommendUser);
			//当前订单的主人对应的会员节点
			int nodeId = owner.getNodeId();
			//当前订单的主人对应的推荐人的推荐人
			int recommendUserParent = ownerRecomend.getRecommendId();
			//销售奖 订单的推荐人
			insertSalseBonus(recommendUser,order);
			//1代奖 订单的推荐人
			insertFirstGenerationBonus(recommendUser,order);
			//推荐人的上级存在才发奖
			if(null != ownerRecomend.getRecommendId() && ownerRecomend.getRecommendId() > 0){
				//2代奖直推人的推荐人获得2代奖
				insertSecondGenerationBonus(recommendUserParent,order);
			}
			//级差奖 当前节点所有上级
			insertMemberLevelBonus(nodeId,order);
			//工作室&运营中心奖/扶持奖 当前节点的所有上级
			insertWorkRoomAndOperatingCenterBonus(nodeId,order);
			//处理会员晋升 当前节点的所有上级
			nodeService.processMemberPromotion(nodeId,order.getCreateId());
		}
	}
	/**
	 * 销售奖计算
	 * 直接给推荐人
	 * @param memberId 得奖人
	 * @param order 订单
	 * @author su
	 */
	public void insertSalseBonus(int memberId,OrderMaster order){
		//计算奖金金额
		double bonusPercent = commonService.getSalesBonusPercent(BonusConstant.D01,BonusConstant.CODE_01,BonusConstant.SALES_BONUS_PER);
	    //生成奖金
		insertAndSaveBonus(bonusPercent,BonusConstant.BONUS_TYPE_0,memberId,order);
	}
	/**
	 * 1代奖金结算方法
	 * 直接给推荐人
	 * @param memberId 得奖人
	 * @param order 订单
	 * @author su
	 */
	public void insertFirstGenerationBonus(int memberId,OrderMaster order){
		//计算奖金金额
		double bonusPercent = commonService.getSalesBonusPercent(BonusConstant.D01,BonusConstant.CODE_01,BonusConstant.SALES_BONUS_PER1);
	    //生成奖金
		insertAndSaveBonus(bonusPercent,BonusConstant.BONUS_TYPE_1,memberId,order);
	}

	/**
	 * 2代奖金结算方法
	 * 需要查找当前订单的推荐人的推荐人
	 * @param memberId 得奖人
	 * @param order 订单
	 * @author su
	 */
	public void insertSecondGenerationBonus(int memberId,OrderMaster order){
		//计算奖金金额
		double bonusPercent = commonService.getSalesBonusPercent(BonusConstant.D01,BonusConstant.CODE_01,BonusConstant.SALES_BONUS_PER2);
	    //生成奖金
		insertAndSaveBonus(bonusPercent,BonusConstant.BONUS_TYPE_2,memberId,order);
	}

	/**
	 * 计算级差奖
	 * @param nodeId 当前订单人所在节点
	 * @param order 订单
	 * @author su
	 */
	public void insertMemberLevelBonus(int nodeId,OrderMaster order){
		//查找当前节点的所有父节点信息，从小到大升序排列；
        List<MoreMemberNode> list = moreNodeMapper.listParentIsManageLevelNodes(nodeId);
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
        		insertAndSaveBonus(bonusPercent,BonusConstant.BONUS_TYPE_5,memberId,order);
            }else if(memberLevel.equals(BonusConstant.POST_LEVEL3)){
            	//奖金比例
        		double bonusPercent = commonService.getMaxPercent(BonusConstant.D05,BonusConstant.CODE_02);
            	//计算经理奖金
        		insertAndSaveBonus(bonusPercent,BonusConstant.BONUS_TYPE_5,memberId,order);
            }else if(memberLevel.equals(BonusConstant.POST_LEVEL4)){
            	//奖金比例
        		double bonusPercent = commonService.getMaxPercent(BonusConstant.D05,BonusConstant.CODE_03);
            	//计算总监奖金
        		insertAndSaveBonus(bonusPercent,BonusConstant.BONUS_TYPE_5,memberId,order);
            }else if(memberLevel.equals(BonusConstant.POST_LEVEL5)){
            	//奖金比例
        		double bonusPercent = commonService.getMaxPercent(BonusConstant.D05,BonusConstant.CODE_04);
            	//计算董事奖金
        		insertAndSaveBonus(bonusPercent,BonusConstant.BONUS_TYPE_5,memberId,order);
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
	public void insertWorkRoomAndOperatingCenterBonus(int nodeId,OrderMaster order){
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
        		insertAndSaveBonus(bonusPercent,BonusConstant.BONUS_TYPE_7,m.getMemberId(),order);
        		//发运营中心扶持奖，从当前节点开始向上找，遇到普通会员结束，遇到运营中心发奖。
        		insertOperatingCenterHelpBonus(list,i+1,order);
        		break;
        	}else if(null != m.getIsOperator() && m.getIsOperator().equalsIgnoreCase("Y")){
        		//如果是运营中心发运营中心奖，发奖结束。
        		double bonusPercent = commonService.getMaxPercent(BonusConstant.D06,BonusConstant.CODE_01);
        		insertAndSaveBonus(bonusPercent,BonusConstant.BONUS_TYPE_8,m.getMemberId(),order);
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
	public void insertOperatingCenterHelpBonus(List<MoreMemberNode> list,int num,OrderMaster order){
	    //接着刚才循环的节点继续向上找
		for(int i=num;i<list.size();i++){
			MoreMemberNode m = list.get(i);
        	//如果是工作室循环继续下一次
        	if(null != m.getIsSalesDept() && m.getIsSalesDept().equalsIgnoreCase("Y")){
        		continue;
        	}else if(null != m.getIsOperator() && m.getIsOperator().equalsIgnoreCase("Y")){
        		//如果是运营中心发运营中心扶持奖，发奖结束。
        		double bonusPercent = commonService.getMaxPercent(BonusConstant.D06,BonusConstant.CODE_02);
        		insertAndSaveBonus(bonusPercent,BonusConstant.BONUS_TYPE_9,m.getMemberId(),order);
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
	private MemberBonus insertAndSaveBonus(double bonusPercent,String bonusType,int memberId,OrderMaster order){
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
		bonus.setOrderDate(order.getCreateTime());
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
		double bonusRest = commonService.multiply(totalBonus,BonusConstant.BONUS_PERCENT);
		AccountFlowHistory flow = new AccountFlowHistory();
		flow.setCreateId(bonus.getCreateBy());
		flow.setCreateTime(new Date());
		//奖金类型，参照奖金表设置
		flow.setFlowType(bonus.getBonusType());
		flow.setMemberId(bonus.getMemberId());
		//设置入账
		flow.setSeedAmt(new BigDecimal(seedBonus));
		flow.setBonusAmt(new BigDecimal(bonusRest));
		flow.setTotalAmt(new BigDecimal(totalBonus));
		flow.setType(BonusConstant.ACCOUNT_TYPE_IN);
		
		//查询现有账号信息
		AccountManager param = new AccountManager();
		param.setMemberId(bonus.getMemberId());
		AccountManager account = moreAccountManagerMapper.selectAccountManager(param);
		if(null == account){
			account = param;
			account.setTotalBonus(new BigDecimal(0));
			account.setSeedAmt(new BigDecimal(0));
			account.setBonusAmt(new BigDecimal(0));
			account.setAdvanceAmt(new BigDecimal(0));
			account.setCreateId(bonus.getCreateBy());
			account.setCreateTime(new Date());
			account.setUpdateId(bonus.getCreateBy());
			account.setUpdateTime(new Date());
		}
		//对现有余额进行相加
		BigDecimal total = account.getTotalBonus().add(new BigDecimal(totalBonus));
		BigDecimal seedAmt = account.getSeedAmt().add(new BigDecimal(seedBonus));
		BigDecimal bonusAmt = account.getBonusAmt().add(new BigDecimal(bonusRest));
		//对账户余额进行赋值
		account.setBonusAmt(bonusAmt);
		account.setSeedAmt(seedAmt);
		account.setTotalBonus(total);
		account.setUpdateId(bonus.getMemberId());
		account.setUpdateTime(new Date());
		//保存奖金
		moreMemberBonusMapper.insert(bonus);
		if(null != account.getId() && account.getId() > 0){
			//更新账户余额
			moreAccountManagerMapper.updateByPrimaryKeySelective(account);
		}else{
			//创建账户
			moreAccountManagerMapper.insert(account);
		}
		//记录账号资金出入情况
		accountFlowHistoryMapper.insert(flow);
	}
	/**
	 * 查询当天营业额总数
	 * 计算当天要发的奖金
	 * @author su
	 * @date 2017年9月7日 下午2:45:27
	 * @return
	 */
	public DateBonusHistory findCurrentDayBonusHistory(String date){
		DateBonusHistory history = moreDateBonusHistoryMapper.selectCurrentDaySalesAndBonus(date);
		if(null == history){
			history = new DateBonusHistory();
			history.setCreateId(0);
			history.setCreateTime(new Date());
			history.setDate(new Date());
			//设置营业额相关数据
			double totalSales = moreOrderMasterMapper.findCurrentDayOrderSales(date);
			double nodePercent = commonService.getMaxPercent(BonusConstant.D03, BonusConstant.CODE_01);
			double dividendPercent = commonService.getMaxPercent(BonusConstant.D02, BonusConstant.CODE_01);
			//总营业额
			history.setTotalSales(new BigDecimal(totalSales));
			//可用于分红包领取的金额
			history.setDividendTotal(new BigDecimal(commonService.multiply(dividendPercent, totalSales)));
			//可用于见点奖领取的金额
			history.setJdBonusTotal(new BigDecimal(commonService.multiply(nodePercent, totalSales)));
		}
		return history;
	}
	/**
	 * 统一处理发放见点奖
	 * @author su
	 * @date 2017年9月7日 下午2:34:28
	 */
	public void saveNodeBonusFromNodeHistory(){
		String date = DateHelper.formatDate(new Date(), DateHelper.YYYY_MM_DD);
		DateBonusHistory history = findCurrentDayBonusHistory(date);
        //当日需要发放的见点奖总数
        double totalBonus = nodeBonusHistoryMapper.findCurrentDayNodeBonus(date);
        //查找奖金发放池，奖金余额
        double lessNodeBonus = bonusPoolService.getBonusCachePool(BonusConstant.POOL_ID_NODE); 
        
        //如果营业额+缓存池的钱大于等于要发放的奖金，则执行发放。
        BigDecimal nowTotal = new BigDecimal(lessNodeBonus).add(history.getJdBonusTotal());
        if(nowTotal.compareTo(new BigDecimal(totalBonus)) >= 0){
        	//见点奖金额
            double bonusNum = commonService.getMaxAmt(BonusConstant.D03,BonusConstant.CODE_00);
            //更新要发的见点奖列表，计入奖金金额；
            nodeService.updateNodeBonusHistory(date,bonusNum);
            //将发放剩余的奖金计入奖金池，并计入流水。
            double rest = history.getJdBonusTotal().doubleValue()- totalBonus;
            //如果营业额的10% 减去 要发的奖金，否则更新发放池。
            if(rest > 0){
            	//结果大于0，有余额计入奖金池。
            	bonusPoolService.updatePool(new BigDecimal(rest),BonusConstant.POOL_TYPE_NODE,BonusConstant.POOL_BONUS_ADD);
            	//10%剩余的见点奖金
            	history.setRemainJdBonus(new BigDecimal(rest).longValue());
            }else if(rest < 0){
            	//结果小于0，钱不够，更新发放池。
            	bonusPoolService.updateCachePool(new BigDecimal(rest),BonusConstant.POOL_TYPE_NODE, BonusConstant.POOL_BONUS_REDUCE);
            }else{
            }
			//发放的见点奖
			history.setUseJdBonusTotal(history.getJdBonusTotal().longValue());
			history.setAlarmStatus(BonusConstant.BONUS_STATUS_0);
       }else{
    	    history.setAlarmStatus(BonusConstant.BONUS_STATUS_1);
            //更新今日营业额到奖金池，并计入奖金池流水。
    	   bonusPoolService.updatePool(history.getJdBonusTotal(),BonusConstant.POOL_TYPE_NODE,BonusConstant.POOL_BONUS_ADD);
       }
       history.setUpdateId(0);
       history.setUpdateTime(new Date());
       //设置状态
       if(null != history.getId() && history.getId() > 0){
    	   moreDateBonusHistoryMapper.updateByPrimaryKey(history);
       }else{
    	   moreDateBonusHistoryMapper.insert(history);
       }
	}
	/**
	 * 
	 * Name: 
	 * Description: 
	 * @author su
	 * @date 2017年9月7日 下午2:34:24
	 */
	public void balanceMemberNodeBonus(){
        //查找所有昨天发放的见点奖
       
        //见点奖到账处理，所有见点奖入奖金表，计算管理费。

        //更新见点奖为已结算
	}
	/**
	 * 统一处理发放分红奖
	 * @author su
	 * @date 2017年9月7日 下午2:34:28
	 */
	public void saveDividendBonus(){
		String date = DateHelper.formatDate(new Date(), DateHelper.YYYY_MM_DD);
		DateBonusHistory history = findCurrentDayBonusHistory(date);
        //当日需要发放的分红奖总数
		int totalDividend = moreDividendMapper.getAllNeedSendDividendCount();
        //分红包奖金额
        double bonusNum = commonService.getMaxAmt(BonusConstant.D02,BonusConstant.CODE_00);
        //需要的分红奖金总钱数
        double totalBonus = commonService.multiply(totalDividend, bonusNum);
        //查找奖金发放池，奖金余额
        double lessNodeBonus = bonusPoolService.getBonusCachePool(BonusConstant.POOL_ID_DIVIDEND); 
        
        //如果营业额+缓存池的钱大于等于要发放的奖金，则执行发放。
        BigDecimal nowTotal = new BigDecimal(lessNodeBonus).add(history.getDividendTotal());
        //判断有奖需要发，并且钱够
        if(totalDividend > 0 && nowTotal.compareTo(new BigDecimal(totalBonus)) >= 0){
            List<Dividend> list = moreDividendMapper.listAllNeedSendDividends();
            //生成分红包记录
            this.saveDividendHistoryBonus(list,bonusNum);
            //更新订单分红记录
            this.updateAllNeedSendDividends(list,bonusNum);
            //将发放剩余的奖金计入奖金池，并计入流水。
            double rest = history.getDividendTotal().doubleValue()- totalBonus;
            //如果营业额的20% 减去 要发的奖金，否则更新发放池。
            if(rest > 0){
            	//结果大于0，有余额计入奖金池。
            	bonusPoolService.updatePool(new BigDecimal(rest),BonusConstant.POOL_TYPE_NODE,BonusConstant.POOL_BONUS_ADD);
            	//10%剩余的见点奖金
            	history.setRemainDividend(new BigDecimal(rest).longValue());
            }else if(rest < 0){
            	//结果小于0，钱不够，更新发放池。
            	bonusPoolService.updateCachePool(new BigDecimal(rest),BonusConstant.POOL_TYPE_NODE, BonusConstant.POOL_BONUS_REDUCE);
            }else{
            }
			//发放的见点奖
			history.setUseDividendTotal(history.getDividendTotal());
			history.setAlarmStatus(BonusConstant.BONUS_STATUS_1);
       }else{
    	    history.setAlarmStatus(BonusConstant.BONUS_STATUS_0);
            //更新今日营业额到奖金池，并计入奖金池流水。
    	    bonusPoolService.updatePool(history.getJdBonusTotal(),BonusConstant.POOL_TYPE_NODE,BonusConstant.POOL_BONUS_ADD);
       }
       history.setUpdateId(0);
       history.setUpdateTime(new Date());
       //设置状态
       if(null != history.getId() && history.getId() > 0){
    	   moreDateBonusHistoryMapper.updateByPrimaryKey(history);
       }else{
    	   moreDateBonusHistoryMapper.insert(history);
       }
	}
	/**
	 * 生成分红包明细表 
	 * @author su
	 * @date 2017年9月7日 下午10:00:57
	 * @param list
	 * @param bonusNum
	 */
	public void saveDividendHistoryBonus(List<Dividend> list,double bonusNum){
		List<DividendHistory> history = new ArrayList<DividendHistory>();
		for(Dividend d : list){
			DividendHistory dh = new DividendHistory();
			//单个分红包金额
			dh.setAmount(new BigDecimal(bonusNum));
			dh.setCreateId(0);
			dh.setCreateTime(new Date());
			//分红包个数
			dh.setDevidendCount(d.getDividendCount());
			//订单分红包id
			dh.setDividendId(d.getId());
			//领取时间
			dh.setReceivedTime(new Date());
			//本次领取金额
			double total = commonService.multiply(bonusNum, d.getDividendCount().doubleValue());
			//如果已经领取的值 大于余额，那么只能领取余额。
			if(total >= d.getRemainAmount().doubleValue()){
				dh.setTotalReceived(d.getRemainAmount());
			}else{
				dh.setTotalReceived(new BigDecimal(total));
			}
			//未结算
			dh.setBalanceStatus(BonusConstant.BONUS_STATUS_0);
			history.add(dh);
		}
	    Map<String,Object> map = new HashMap<String,Object>();
        map.put("list", list);
        dividendHistoryMapper.insertDividendHistoryBatch(map);
	}
	/**
	 * 
	 * 更新订单分红包
	 * @param list
	 * @param bonusNum
	 */
	public void updateAllNeedSendDividends(List<Dividend> list,double bonusNum){
		for(Dividend d : list){
			//本次领取金额
			double total = commonService.multiply(bonusNum, d.getDividendCount().doubleValue());
			//如果本次领取金额 大于余额，标识状态为领取完。
			if(total >= d.getRemainAmount().doubleValue()){
				d.setReceivedAmount(d.getReceivedAmount().add(d.getRemainAmount()));
				d.setRemainAmount(new BigDecimal(0));
				//设置状态
				d.setDividendStatus(BonusConstant.BONUS_STATUS_2);
			}else{
				//已领取金额相加
				d.setReceivedAmount(d.getReceivedAmount().add(new BigDecimal(total)));
				//剩余金额相减
				d.setRemainAmount(d.getRemainAmount().subtract(new BigDecimal(total)));
				//设置状态
				d.setDividendStatus(d.getDividendStatus());
			}
			d.setUpdateId(0);
			d.setUpdateTime(new Date());
		}
		Map<String,Object> map = new HashMap<String,Object>();
        map.put("list", list);
        //更新订单分红记录
        moreDividendMapper.updateAllNeedSendDividends(map);
	}
	/**
	 * 
	 * Name: 
	 * Description: 
	 * @author su
	 * @date 2017年9月7日 下午11:15:55
	 */
	public void balanceMemberDividendBonus(){
        //查找所有昨天发放的分红奖
       
        //分红奖到账处理，入奖金表，计算管理费。
        //更新见点奖为已结算
	}

	/*public static void main(String[] arg){
		double v1 = 1.96;
		double v2 = bonus_percent;
		System.out.println(multiply(v1,v2));
	}*/

	/**
	 * 从资金池拨钱到发放池
	 * @param map
	 * @return
     */
	public boolean insertPayAmtProc(Map map){

		int cnt1 = moreDateBonusHistoryMapper.updateBonusCachePool(map);
		int cnt2 = moreDateBonusHistoryMapper.updateBonusPool(map);


		return false;
	}
}
