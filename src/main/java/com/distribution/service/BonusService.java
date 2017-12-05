/*
  * Pactera Technology International Ltd.
  * Copyright (c) 2014-2015 All Rights Reserved.
  */
package com.distribution.service;

import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.distribution.dao.memberBonus.model.more.MoreMemberBonus;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.xssf.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.distribution.common.constant.BonusConstant;
import com.distribution.common.utils.DateHelper;
import com.distribution.common.utils.Page;
import com.distribution.dao.accountFlowHistory.mapper.AccountFlowHistoryMapper;
import com.distribution.dao.accountFlowHistory.model.AccountFlowHistory;
import com.distribution.dao.accountManager.mapper.more.MoreAccountManagerMapper;
import com.distribution.dao.accountManager.model.AccountManager;
import com.distribution.dao.bonusPoolHistory.mapper.BonusPoolHistoryMapper;
import com.distribution.dao.dateBonusHistory.mapper.more.MoreDateBonusHistoryMapper;
import com.distribution.dao.dateBonusHistory.model.DateBonusHistory;
import com.distribution.dao.dividend.mapper.more.MoreDividendMapper;
import com.distribution.dao.dividend.model.Dividend;
import com.distribution.dao.dividendHistory.mapper.more.MoreDividendHistoryMapper;
import com.distribution.dao.dividendHistory.model.DividendHistory;
import com.distribution.dao.dividendHistory.model.more.MoreDividendHistory;
import com.distribution.dao.member.mapper.MemberMapper;
import com.distribution.dao.member.model.Member;
import com.distribution.dao.memberBonus.mapper.more.MoreMemberBonusMapper;
import com.distribution.dao.memberBonus.model.MemberBonus;
import com.distribution.dao.memberNode.mapper.more.MoreMemberNodeMapper;
import com.distribution.dao.memberNode.model.more.MoreMemberNode;
import com.distribution.dao.nodeBonusHistory.mapper.more.MoreNodeBonusHistoryMapper;
import com.distribution.dao.nodeBonusHistory.model.NodeBonusHistory;
import com.distribution.dao.nodeBonusHistory.model.more.MoreNodeBonusHistory;
import com.distribution.dao.order.mapper.more.MoreOrderMasterMapper;
import com.distribution.dao.order.model.OrderMaster;

import javax.servlet.http.HttpServletResponse;

@Service
public class BonusService {
	private static Logger logs = Logger.getLogger(BonusService.class);
	
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
	@Autowired
	private BonusPoolHistoryMapper bonusPoolHistoryMapper;
	
	
	
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
    public void processOrderBonus(OrderMaster order){
    	
    	this.insertOrderBonus(order);
    }
    /**
     * 
     * 奖金计算处理方法，独立分组可以进行手动事务控制。
     * @author su
     * @date 2017年9月9日 下午10:14:59
     * @param order
     */
	public void insertOrderBonus(OrderMaster order){
		//订单主人会员
    	Member owner = memberMapper.selectByPrimaryKey(order.getMemberId());
    	//订单主人的推荐人
    	Member ownerRecomend = memberMapper.selectByPrimaryKey(owner.getRecommendId());
		//订单主人推荐人不为空发奖
		if(null != ownerRecomend){
			//初始化计算所需配置中的变量
			commonService.initBasicManageList();
			//当前订单的主人对应的会员节点
			int nodeId = owner.getNodeId();
			//1代奖 订单的推荐人
			insertSalseBonus(ownerRecomend.getId(),order);
			//订单主人二级推荐人
			Member recomend_2 = memberMapper.selectByPrimaryKey(ownerRecomend.getRecommendId());
			if(null != recomend_2){
				//2代奖 订单二级推荐人
				insertFirstGenerationBonus(recomend_2.getId(),order);
				//订单主人的3级推荐人
				Member recomend_3 = memberMapper.selectByPrimaryKey(recomend_2.getRecommendId());
				//三级推荐人不为空时
				if(null != recomend_3){
					//3代奖 订单三级推荐人
					insertSecondGenerationBonus(recomend_3.getId(),order);
				}else{
					//推荐人的上级不存在时，三代奖发给二代推荐人。
				}
			}else{
				//推荐人的上级不存在时，二代奖发给直接推荐人。
			}
			//级差奖 当前节点所有上级
			insertMemberLevelBonus(nodeId,order);
			//工作室&运营中心奖/扶持奖 当前节点的所有上级
			insertWorkRoomAndOperatingCenterBonus(nodeId,order);
			//生成会员见点奖，只有报单有。订单类型（1.报单，2.复投， 3.折扣订单）
			/*if(null != order.getOrderCategory() && order.getOrderCategory().equals(BonusConstant.ORDER_CATEGORY_1)){
				nodeService.insertMemberNodeBonus(nodeId,order);
			}*/
			//20170930 调整为复投也计算见点奖(1.报单，2.复投)
			if(null != order.getOrderCategory() && !order.getOrderCategory().equals(BonusConstant.ORDER_CATEGORY_3)){
				nodeService.insertMemberNodeBonus(nodeId,order);
			}
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
		Map<String,Object> ids = nodeService.getParentNodeIdsMap(nodeId);
        List<MoreMemberNode> list = moreNodeMapper.listParentIsManageLevelNodes(ids);
        for(MoreMemberNode m : list){
        	//当前节点不参与奖金计算
        	int memberId = m.getMemberId();
        	if(order.getMemberId().intValue() == memberId){
        		continue;
        	}
        	String memberPost = m.getMemberPost();
            if(memberPost.equals(BonusConstant.POST_LEVEL2)){
            	//奖金比例
        		double bonusPercent = commonService.getMaxPercent(BonusConstant.D05,BonusConstant.CODE_01);
            	//计算主任奖金
        		insertAndSaveBonus(bonusPercent,BonusConstant.BONUS_TYPE_5,memberId,order);
            }else if(memberPost.equals(BonusConstant.POST_LEVEL3)){
            	//奖金比例
        		double bonusPercent = commonService.getMaxPercent(BonusConstant.D05,BonusConstant.CODE_02);
            	//计算经理奖金
        		insertAndSaveBonus(bonusPercent,BonusConstant.BONUS_TYPE_5,memberId,order);
            }else if(memberPost.equals(BonusConstant.POST_LEVEL4)){
            	//奖金比例
        		double bonusPercent = commonService.getMaxPercent(BonusConstant.D05,BonusConstant.CODE_03);
            	//计算总监奖金
        		insertAndSaveBonus(bonusPercent,BonusConstant.BONUS_TYPE_5,memberId,order);
            }else if(memberPost.equals(BonusConstant.POST_LEVEL5)){
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
		Map<String,Object> ids = nodeService.getParentNodeIdsMap(nodeId);
		List<MoreMemberNode> list = moreNodeMapper.listParentNodesWithMemberInfo(ids);
		for(int i=0;i<list.size();i++){
			MoreMemberNode m = list.get(i);
			//忽略当前节点
        	if(m.getId().intValue() == nodeId){
        		continue;
        	}
        	//20170930 去掉工作室奖
        	//如果是工作室发工作室奖，然后继续向上发运营中心奖。
        	/*if(null != m.getIsSalesDept() && m.getIsSalesDept().equalsIgnoreCase("Y")){
        		double bonusPercent = commonService.getMaxPercent(BonusConstant.D06,BonusConstant.CODE_03);
        		//发工作室奖
        		insertAndSaveBonus(bonusPercent,BonusConstant.BONUS_TYPE_7,m.getMemberId(),order);
        		//发运营中心扶持奖，从当前节点开始向上找，遇到普通会员结束，遇到运营中心发奖。
        		insertOperatingCenterHelpBonus(list,i+1,order);
        		break;
        	}else*/ 
        	if(null != m.getIsOperator() && m.getIsOperator().equalsIgnoreCase("Y")){
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
			//如果是运营中心发运营中心扶持奖，发奖结束。
        	if(null != m.getIsOperator() && m.getIsOperator().equalsIgnoreCase("Y")){
        		//判断左右分支是否都是工作室，如果都是才可以得到扶持奖。
        		Map<String,Object> map = moreNodeMapper.getSubNodesIsSalesDept(m.getId());
        		String leftSalesDept = map.get("left_sales_dept")==null?"":map.get("left_sales_dept").toString();
        		String rightSalesDept = map.get("right_sales_dept")==null?"":map.get("right_sales_dept").toString();
        		//左工作室右边空，左空右工作室，左右都是工作室
        		if((leftSalesDept.equalsIgnoreCase("Y") && rightSalesDept.equalsIgnoreCase("Y"))
        			||(null == map.get("left_id") && rightSalesDept.equalsIgnoreCase("Y"))
        			||(null == map.get("right_id") && leftSalesDept.equalsIgnoreCase("Y"))
        		  ){
        			double bonusPercent = commonService.getMaxPercent(BonusConstant.D06,BonusConstant.CODE_02);
        			insertAndSaveBonus(bonusPercent,BonusConstant.BONUS_TYPE_9,m.getMemberId(),order);
        			break;
        		}else{
        			break;
        		}
        	}else if(null != m.getIsSalesDept() && m.getIsSalesDept().equalsIgnoreCase("Y")){
        		//如果是工作室循环继续下一次
        		continue;
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
		flow.setOldTotalSeedAmt(account.getSeedAmt());
		flow.setOldTotalBonusAmt(account.getBonusAmt());

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

		flow.setNewTotalSeedAmt(account.getSeedAmt());
		flow.setNewTotalBonusAmt(account.getBonusAmt());

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
	 * 计算当天可用发放的奖金
	 * @author su
	 * @date 2017年9月7日 下午2:45:27
	 * @return
	 */
	public DateBonusHistory findCurrentDayBonusHistory(String yesterday){
		//查找同一天的执行记录
		String date = DateHelper.formatDate(new Date(), DateHelper.YYYY_MM_DD);
		DateBonusHistory history = moreDateBonusHistoryMapper.selectCurrentDaySalesAndBonus(date);
		if(null == history){
			history = new DateBonusHistory();
			history.setCreateId(0);
			history.setCreateTime(new Date());
			//设置奖金时间
			history.setDate(DateHelper.getYesterDay());
			//设置营业额相关数据
			double totalSales = moreOrderMasterMapper.findCurrentDayOrderSales(yesterday);
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
	 * Job统一处理发放见点奖
	 * @author su
	 * @date 2017年9月7日 下午2:34:28
	 */
	public Map<String,Object> saveNodeBonusFromNodeHistory(Map<String,Object> result){
		//数据查询范围昨天
		String date = DateHelper.formatDate(DateHelper.getYesterDay(), DateHelper.YYYY_MM_DD);
		result.put("date", date);
		DateBonusHistory history = findCurrentDayBonusHistory(date);
		//如果标识为成功，直接返回。
		if(null != history.getJdAlarmStatus() && history.getJdAlarmStatus() == 1){
			result.put("result", "NodeBonus Has already send success,Today!");
			return result;
		}
		result.put("totalSalesAmount", history.getTotalSales());
		result.put("nodeBonusAmount", history.getJdBonusTotal());
        //double totalBonus = nodeBonusHistoryMapper.findCurrentDayNodeBonus(date);
		//所有奖金生成日期小于等于今天未发放的数据
		List<MoreNodeBonusHistory> list = nodeBonusHistoryMapper.listCurrentDayNodeBonus(date);
		//见点奖金额
		double bonusNum = commonService.getMaxAmt(BonusConstant.D03,BonusConstant.CODE_00);
		//待发放的奖金总数（数量乘以单价）
		//double totalBonus = commonService.multiply(list.size(), bonusNum);
		//20170930 调整见点奖金为分红数量乘以奖金金额
		double totalBonus = nodeService.calculateNodeBonusAmount(list,bonusNum);
		result.put("toBeSentTotalNodeBonus", totalBonus);
        //查找奖金发放池，奖金余额
        double lessNodeBonus = bonusPoolService.getBonusCachePool(BonusConstant.POOL_ID_NODE); 
        result.put("cachePoolRestAmount", lessNodeBonus);
        //如果营业额+缓存池的钱大于等于要发放的奖金，则执行发放。
        BigDecimal nowPoolTotal = new BigDecimal(lessNodeBonus).add(history.getJdBonusTotal());
        if(list.size() > 0 && nowPoolTotal.compareTo(new BigDecimal(totalBonus)) >= 0){
            //更新要发的见点奖列表，计入奖金金额，更新发放状态，记入时间。
            nodeService.updateNodeBonusHistory(list);
            //将发放剩余的奖金计入奖金池，并计入流水。
            double rest = history.getJdBonusTotal().doubleValue()- totalBonus;
            //如果营业额的10% 减去 要发的奖金，否则更新发放池。
            if(rest > 0){
            	//结果大于0，有余额计入奖金池。
            	bonusPoolService.updatePool(new BigDecimal(rest),BonusConstant.POOL_TYPE_NODE,BonusConstant.POOL_BONUS_ADD);
            	//10%剩余的见点奖金
            	history.setRemainJdBonus(new BigDecimal(rest).longValue());
            	result.put("sendRestAmountAddToPool", rest);
            }else if(rest < 0){
            	//结果小于0，钱不够，更新发放池。
            	bonusPoolService.updateCachePool(new BigDecimal(rest).abs(),BonusConstant.POOL_TYPE_NODE, BonusConstant.POOL_BONUS_REDUCE);
            	result.put("sendRestAmountReduceFromCachePool", rest);
            	history.setRemainJdBonus(new BigDecimal(0).longValue());
            }else{
            }
			
			//更新发放状态成功
			history.setJdAlarmStatus(BonusConstant.BONUS_STATUS_1);
			//更新历史所有失败的见点奖记录
			updateDateBonusHistorySuccess(date,"jd");
			result.put("result", "Send bonus ok!");
       }else{
    	    //更新发放状态
    	    if(list.size() > 0){
    	    	history.setJdAlarmStatus(BonusConstant.BONUS_STATUS_0);
    	    	result.put("result", "Donot send bonus,the rest money is less then needs.");
    	    }else{
    	    	history.setJdAlarmStatus(BonusConstant.BONUS_STATUS_1);
    	    	result.put("result", "Donot send bonus,no bonus to send.");
    	    }
    	    if(history.getJdBonusTotal().doubleValue() > 0){
    	    	result.put("nodeBonusAmountAddToPool", history.getJdBonusTotal());
    	    	//更新今日营业额到奖金池，并计入奖金池流水。
    	    	bonusPoolService.updatePool(history.getJdBonusTotal(),BonusConstant.POOL_TYPE_NODE,BonusConstant.POOL_BONUS_ADD);
    	    }
    	    history.setRemainJdBonus(history.getJdBonusTotal().longValue());
       }
       //发放的见点奖
       history.setUseJdBonusTotal(new BigDecimal(totalBonus).longValue());
       history.setUpdateId(0);
       history.setUpdateTime(new Date());
       if(null != history.getId() && history.getId() > 0){
    	   moreDateBonusHistoryMapper.updateByPrimaryKeySelective(history);
       }else{
    	   moreDateBonusHistoryMapper.insert(history);
       }
       return result;
	}
	public void updateDateBonusHistorySuccess(String date,String flag){
		Map<String,Object> param = new HashMap<String,Object>();
		param.put("date", date);
		param.put("updateId", 0);
		param.put("updateTime", new Date());
		if(flag.equals("jd")){
			param.put("jdAlarmStatus", BonusConstant.BONUS_STATUS_1);
		}else{
			param.put("alarmStatus", BonusConstant.BONUS_STATUS_1);
		}
		moreDateBonusHistoryMapper.updateDateBonusHistorySuccess(param);
	}
	/**
	 * 
	 * Job结算昨天发放的见点奖，并更新状态。
	 * @author su
	 * @date 2017年9月7日 下午2:34:24
	 */
	public Map<String,Object> saveBalanceMemberNodeBonus(Map<String,Object> result){
		String yesterday = DateHelper.formatDate(DateHelper.getYesterDay(), DateHelper.YYYY_MM_DD);
        //查找所有昨天发放的见点奖,包括以前没有结算的。
		List<NodeBonusHistory> list = nodeBonusHistoryMapper.listAllYesterdayNodeBonusHistory(yesterday);
		result.put("toBeBalanceItemsAcount", list.size());
		//见点奖到账处理，所有见点奖入奖金表，计算管理费。
        for(NodeBonusHistory history:list){
        	OrderMaster order = new OrderMaster();
        	order.setOrderAmt(new BigDecimal(history.getBonusAmount()));
        	order.setId(history.getOrderId());
        	order.setOrderNo(history.getOrderNo());
        	order.setCreateTime(history.getOrderDate());
        	order.setCreateId(0);
        	insertAndSaveBonus(1,BonusConstant.BONUS_TYPE_4,history.getMebmerId(),order);
        }
        //更新见点奖为已结算
        if(list.size() > 0){
        	nodeService.updateNodeBonusHistoryStatusEnd(list);
        	result.put("result", "success");
        }else{
        	result.put("result", "No bonus need to balance.");
        }
        return result;
	}
	
	
	/**
	 * Job统一处理发放分红奖
	 * @author su
	 * @date 2017年9月7日 下午2:34:28
	 */
	public Map<String,Object> saveDividendBonus(Map<String,Object> result){
		//数据查询范围昨天
		String date = DateHelper.formatDate(DateHelper.getYesterDay(), DateHelper.YYYY_MM_DD);
		//String date = DateHelper.formatDate(new Date(), DateHelper.YYYY_MM_DD);
		result.put("date", date);
		DateBonusHistory history = findCurrentDayBonusHistory(date);
		//如果标识为成功，直接返回。
		if(null != history.getAlarmStatus() && history.getAlarmStatus()== 1){
			result.put("result", "DividendBonus Has already send success,Today!");
			return result;
		}
		result.put("totalSalesAmount", history.getTotalSales());
		result.put("dividendBonusAmount", history.getDividendTotal());
		//计算奖金并生成分红包明细
        Map<String,Object> map = calculateDividendHistoryBonus(date);
        BigDecimal totalBonus = new BigDecimal(map.get("actualBonus").toString());
        result.put("toBeSentTotalDividendBonus", totalBonus.doubleValue());
        //查找奖金发放池，奖金余额
        double lessNodeBonus = bonusPoolService.getBonusCachePool(BonusConstant.POOL_ID_DIVIDEND); 
        result.put("cachePoolRestAmount", lessNodeBonus);
        //如果营业额+缓存池的钱大于等于要发放的奖金，则执行发放。
        BigDecimal nowTotal = new BigDecimal(lessNodeBonus).add(history.getDividendTotal());
        //判断有奖需要发，并且钱够
        if(totalBonus.doubleValue() > 0 && nowTotal.compareTo(totalBonus) >= 0){
            //更新订单分红记录,生成分红明细
            this.updateAllNeedSendDividends(map);
            //将发放剩余的奖金计入奖金池，并计入流水。
            double rest = history.getDividendTotal().subtract(totalBonus).doubleValue();
            //如果营业额的20% 减去 要发的奖金，否则更新发放池。
            if(rest > 0){
            	//结果大于0，有余额计入奖金池。
            	bonusPoolService.updatePool(new BigDecimal(rest),BonusConstant.POOL_TYPE_DIVIDEND,BonusConstant.POOL_BONUS_ADD);
            	//20%剩余的分红包奖金
            	history.setRemainDividend(new BigDecimal(rest).longValue());
            	result.put("sendRestAmountAddToPool", rest);
            }else if(rest < 0){
            	//结果小于0，钱不够，更新发放池。
            	bonusPoolService.updateCachePool(new BigDecimal(rest).abs(),BonusConstant.POOL_TYPE_DIVIDEND, BonusConstant.POOL_BONUS_REDUCE);
            	result.put("sendRestAmountReduceFromCachePool", rest);
            	history.setRemainDividend(new BigDecimal(0).longValue());
            }else{
            }
			
			result.put("sendActualTotalBonus", totalBonus);
			//发放成功
			history.setAlarmStatus(BonusConstant.BONUS_STATUS_1);
			//更新历史所有失败的见点奖记录,20171111 发放失败需要补发，不能更新。
			//updateDateBonusHistorySuccess(date,"div");
			result.put("result", "Send bonus ok!");
       }else{
    	    if(totalBonus.doubleValue() > 0){
    	    	//发放失败钱不够
    	    	history.setAlarmStatus(BonusConstant.BONUS_STATUS_0);
    	    	result.put("result", "Donot send bonus,the rest money is less then needs.");
    	    }else{
    	    	//不需要发奖
    	    	history.setAlarmStatus(BonusConstant.BONUS_STATUS_1);
    	    	result.put("result", "Donot send bonus,no bonus to send.");
    	    }
    	    if(history.getDividendTotal().doubleValue() > 0){
    	    	result.put("nodeBonusAmountAddToPool", history.getDividendTotal());
    	    	//更新今日营业额到奖金池，并计入奖金池流水。
    	    	bonusPoolService.updatePool(history.getDividendTotal(),BonusConstant.POOL_TYPE_DIVIDEND,BonusConstant.POOL_BONUS_ADD);
    	    }
    	    history.setRemainDividend(history.getDividendTotal().longValue());
       }
       //发放的分红奖数量
       history.setUseDividendTotal(totalBonus);
       history.setUpdateId(0);
       history.setUpdateTime(new Date());
       if(null != history.getId() && history.getId() > 0){
    	   moreDateBonusHistoryMapper.updateByPrimaryKeySelective(history);
       }else{
    	   moreDateBonusHistoryMapper.insert(history);
       }
       return result;
	}
	/**
	 * Job 补发失败分红奖
	 * @author su
	 * @date 2017年11月11日 
	 */
	public Map<String,Object> saveFailureDividendBonus(Map<String,Object> result,DateBonusHistory history){
		result.put("should send date", history.getDate());
		//计算奖金并生成分红包明细
        Map<String,Object> map = calculateDividendHistoryBonus(DateHelper.formatDate(history.getDate(), DateHelper.YYYY_MM_DD));
        BigDecimal totalBonus = new BigDecimal(map.get("actualBonus").toString());
        //查找奖金发放池，奖金余额
        double lessNodeBonus = bonusPoolService.getBonusCachePool(BonusConstant.POOL_ID_DIVIDEND); 
        double rest = new BigDecimal(lessNodeBonus).subtract(totalBonus).doubleValue();
        //判断有奖需要发，缓存池钱够
        if(totalBonus.doubleValue() > 0 && rest >= 0){
            //更新订单分红记录,生成分红明细
            this.updateAllNeedSendDividends(map);
            //扣除缓存池的钱
        	bonusPoolService.updateCachePool(totalBonus.abs(),BonusConstant.POOL_TYPE_DIVIDEND, BonusConstant.POOL_BONUS_REDUCE);
			history.setAlarmStatus(BonusConstant.BONUS_STATUS_1);
			//发放的分红奖数量
			history.setUseDividendTotal(totalBonus);
			//计算 并设置余额
			long remain =  history.getDividendTotal().subtract(totalBonus).longValue();
			if(remain > 0){
				history.setRemainDividend(remain);
			}else{
				history.setRemainDividend(new BigDecimal(0).longValue());
			}
			history.setUpdateId(0);
			history.setUpdateTime(new Date());
			moreDateBonusHistoryMapper.updateByPrimaryKeySelective(history);
			result.put("result", "Send bonus ok!");
       }else{
    	    result.put("result", "Send bonus failuer,the cache pool money is less then needs.");
       }
       return result;
	}
	/**
	 * 生成分红包明细表 
	 * @author su
	 * @date 2017年9月7日 下午10:00:57
	 */
	public Map<String,Object> calculateDividendHistoryBonus(String date){
		//定义实际需要发送奖金
	    BigDecimal actualBonus = new BigDecimal(0);
		List<DividendHistory> hisList = new ArrayList<DividendHistory>();
		//需要的分红奖金列表,小于等于应发奖日期的所有数据
        List<Dividend> list = moreDividendMapper.listAllNeedSendDividends(date);
		 //分红包奖金额
        double bonusNum = commonService.getMaxAmt(BonusConstant.D02,BonusConstant.CODE_00);
		for(Dividend div : list){
			DividendHistory divHis = new DividendHistory();
			//单个分红包金额
			divHis.setAmount(new BigDecimal(bonusNum));
			divHis.setCreateId(0);
			divHis.setCreateTime(new Date());
			//分红包个数
			divHis.setDevidendCount(div.getDividendCount());
			//订单分红包id
			divHis.setDividendId(div.getId());
			//发奖日期
			divHis.setReceivedTime(DateHelper.getDateFromStr(date, DateHelper.YYYY_MM_DD));
			//本次领取金额
			double total = commonService.multiply(bonusNum, div.getDividendCount().doubleValue());
			//如果已经领取的值 大于余额，那么只能领取余额。
			if(null != div.getRemainAmount() && div.getRemainAmount().doubleValue() >0 && total >= div.getRemainAmount().doubleValue()){
				divHis.setTotalReceived(div.getRemainAmount());
				actualBonus = actualBonus.add(div.getRemainAmount());
				div.setReceivedAmount(div.getReceivedAmount().add(div.getRemainAmount()));
			}else{
				divHis.setTotalReceived(new BigDecimal(total));
				actualBonus = actualBonus.add(new BigDecimal(total));
				//已领取金额相加
				BigDecimal receivedAmount = div.getReceivedAmount()==null?new BigDecimal(0):div.getReceivedAmount();
				div.setReceivedAmount(receivedAmount.add(new BigDecimal(total)));
			}
			//剩余金额相减
			BigDecimal remainAmount = div.getDividendLimit().subtract(div.getReceivedAmount());
			//设置状态和余额
			if(remainAmount.doubleValue() <=0){
				div.setRemainAmount(new BigDecimal(0));
				div.setDividendStatus(BonusConstant.BONUS_STATUS_2);
			}else{
				div.setRemainAmount(remainAmount);
			}
			
			divHis.setUpdateId(0);
			divHis.setUpdateTime(new Date());
			//未结算
			divHis.setBalanceStatus(BonusConstant.BONUS_STATUS_0);
			hisList.add(divHis);
			//设置dividend更新变量
			div.setUpdateId(0);
			div.setUpdateTime(new Date());
		}
	    Map<String,Object> map = new HashMap<String,Object>();
        map.put("hisList", hisList);
        map.put("divList", list);
        map.put("actualBonus", actualBonus);
        return map;
	}
	/**
	 * 
	 * 更新订单分红包
	 */
	public void updateAllNeedSendDividends(Map<String,Object> map){
        //更新订单分红记录
        moreDividendMapper.updateAllNeedSendDividends(map);
        //生成分红包记录
    	dividendHistoryMapper.insertDividendHistoryBatch(map);
	}
	/**
	 * Job分红包结算
	 * @author su
	 * @date 2017年9月7日 下午11:15:55
	 */
	public Map<String,Object> saveBalanceMemberDividendBonus(Map<String,Object> result){
		
		String yesterday = DateHelper.formatDate(DateHelper.getYesterDay(), DateHelper.YYYY_MM_DD);
        //查找所有昨天发放的分红奖,包括历史发放未结算的
		List<MoreDividendHistory> list = dividendHistoryMapper.listAllYesterdayDividendHistory(yesterday);
		if(null != list && list.size() > 0){
			result.put("toBeBalanceItemsAcount", list.size());
	        //分红奖到账处理，入奖金表，计算管理费。
			for(MoreDividendHistory history : list){
				OrderMaster order = new OrderMaster();
				//实际收到的分红奖
	        	order.setOrderAmt(history.getTotalReceived());
	        	order.setOrderNo(history.getOrderNo());
	        	order.setId(history.getOrderId());
	        	order.setCreateId(0);
	        	order.setCreateTime(history.getOrderTime());
	        	insertAndSaveBonus(1,BonusConstant.BONUS_TYPE_3,history.getMemberId(),order);
			}
	        //更新分红奖为已结算
			Map<String,Object> map = new HashMap<String,Object>();
	        map.put("list", list);
	        map.put("balanceStatus", BonusConstant.BONUS_STATUS_1);
			map.put("updateId", 0);
			map.put("updateTime", new Date());
			result.put("result", "success");
			dividendHistoryMapper.updateAllYesterdayDividendHistory(map);
		}else{
			result.put("result", "No bonus need to balance");
		}
		return result;
	}

	/*public static void main(String[] arg){
		double v1 = 1.96;
		double v2 = bonus_percent;
		System.out.println(multiply(v1,v2));
	}*/
	/**
	 * description 分销记录详情列表下载
	 * @author sijeong
	 * */
	public XSSFWorkbook exportData(Map map, HttpServletResponse response) throws IOException, InvocationTargetException {
		List<MoreMemberBonus> result = moreMemberBonusMapper.getMemberBonusExcelList(map);
		//定义表头
		String[] excelHeader = {"订单编号","订单时间","订单金额", "推荐人", "购买会员", "领取时间", "获奖项",  "当日奖金金额", "管理费", "实得金额"};

		return  this.exportExcel("分销记录详情列表", excelHeader, result, response.getOutputStream());
	}

	public XSSFWorkbook exportExcel(String title, String[] headers, List<MoreMemberBonus> list, OutputStream out) throws InvocationTargetException {
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
		sheet.setColumnWidth(2, 20 * 256);
		sheet.setColumnWidth(3, 15 * 256);
		sheet.setColumnWidth(4, 15 * 256);
		sheet.setColumnWidth(5, 20 * 256);
		sheet.setColumnWidth(6, 18 * 256);
		sheet.setColumnWidth(7, 18 * 256);
		sheet.setColumnWidth(8, 18 * 256);
		sheet.setColumnWidth(9, 18 * 256);
		//构建表体
		int t = 0;
		double e = 0;
		double m = 0;
		double p = 0;
		//定义body样式
		XSSFCellStyle styleRight = workbook.createCellStyle();
		styleRight.setAlignment(HSSFCellStyle.ALIGN_RIGHT);//居右
		XSSFCellStyle styleCenter = workbook.createCellStyle();
		styleCenter.setAlignment(HSSFCellStyle.ALIGN_CENTER);//居中

		for(int j=0;j<list.size();j++){
			XSSFRow bodyRow = sheet.createRow(j + 1);
			if (list.get(j).getBonusType().equals("6")){
				bodyRow.createCell(0).setCellValue("全国董事奖");
			}else {
				bodyRow.createCell(0).setCellValue(list.get(j).getOrderNo().toString());
			}

			bodyRow.createCell(1).setCellValue(list.get(j).getOrderAmt().toString());
			bodyRow.createCell(2).setCellValue(new SimpleDateFormat("yyyy-MM-dd hh:mm").format(list.get(j).getCreateTime()));
			bodyRow.createCell(3).setCellValue(list.get(j).getRecommendName());
			bodyRow.createCell(4).setCellValue(list.get(j).getMemberName());
			bodyRow.createCell(5).setCellValue(new SimpleDateFormat("yyyy-MM-dd hh:mm").format(list.get(j).getBonusDate()));
			bodyRow.createCell(6).setCellValue(bonusTypeName(list.get(j).getBonusType()));
			bodyRow.createCell(7).setCellValue(list.get(j).getAmout().toString());
			bodyRow.createCell(8).setCellValue(list.get(j).getManageFee().toString());
			bodyRow.createCell(9).setCellValue(list.get(j).getActualAmout().toString());

			bodyRow.getCell(0).setCellStyle(styleCenter);
			bodyRow.getCell(1).setCellStyle(styleRight);
			bodyRow.getCell(3).setCellStyle(styleCenter);
			bodyRow.getCell(4).setCellStyle(styleCenter);
			bodyRow.getCell(7).setCellStyle(styleRight);
			bodyRow.getCell(8).setCellStyle(styleRight);
			bodyRow.getCell(9).setCellStyle(styleRight);
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
		XSSFCell cel2 = bodyRow1.createCell(7);
		cel2.setCellValue(e);
		XSSFCell cel3 = bodyRow1.createCell(8);
		cel3.setCellValue(p);
		XSSFCell cel4 = bodyRow1.createCell(9);
		cel4.setCellValue(m);

		return  workbook;
	}
	private String bonusTypeName(String bonusType) {
		String bonusTypeName = "";
		if (bonusType.equals(BonusConstant.BONUS_TYPE_0)){
			bonusTypeName = "一代奖";
		}else if (bonusType.equals(BonusConstant.BONUS_TYPE_1)){
			bonusTypeName = "二代奖";
		}else if (bonusType.equals(BonusConstant.BONUS_TYPE_2)){
			bonusTypeName = "三代奖";
		}else if (bonusType.equals(BonusConstant.BONUS_TYPE_4)){
			bonusTypeName = "广告宣传奖";
		}else if (bonusType.equals(BonusConstant.BONUS_TYPE_5)){
			bonusTypeName = "级差奖";
		}else if (bonusType.equals(BonusConstant.BONUS_TYPE_6)){
			bonusTypeName = "全国董事奖";
		}else if (bonusType.equals(BonusConstant.BONUS_TYPE_7)){
			bonusTypeName = "销售部奖";
		}else if (bonusType.equals(BonusConstant.BONUS_TYPE_8)){
			bonusTypeName = "运营中心奖";
		}else if (bonusType.equals(BonusConstant.BONUS_TYPE_9)){
			bonusTypeName = "运营中心扶持奖";
		}
		return bonusTypeName;
	}
	public void updateAcountManager(String dateStr){
		List<Map<String,Object>> list = moreAccountManagerMapper.listBonusTemp(dateStr);
		StringBuffer sql1 = new StringBuffer();
		StringBuffer sql2 = new StringBuffer();

		for(int i=0;i<list.size();i++){
			Map<String,Object> map = list.get(i);
			sql1.append("UPDATE account_manager SET total_bonus = total_bonus -" + map.get("total")
					+ ", bonus_amt = bonus_amt - " + map.get("bonus")
					+ ", seed_amt = seed_amt - " + map.get("seed")
					+ " WHERE member_id = " + map.get("member_id") + ";\n");


			sql2.append("INSERT INTO account_flow_history VALUES (null,"+ map.get("member_id")+",NOW(),0,1,"+map.get("total")+","+map.get("bonus")+","+map.get("seed")+",'10月31日见点奖恢复');\n");


		}
		System.out.println(sql1);
		System.out.println(sql2);
	}
	public List<DateBonusHistory> listFailureDividendBonus(String date){
		List<DateBonusHistory> list = moreDateBonusHistoryMapper.listFailureDividendBonus(date);
		return list;
	}
	/**
	 * 查询平台资金沉淀流水
	 * @param date
	 * @return
	 */
	public Map<String,Object> getDaySalesAndBonusAmount(String date){
		Map<String,Object> map = new HashMap<String,Object>();
		//总销售额,不算折扣单
		map.put("totalSalesAmount", moreOrderMasterMapper.selectTotalSalesAmount());
		//当日营业额
		map.put("dayDiscountSalesAmount", moreOrderMasterMapper.findCurrentDayOrderSales(date));
		//当日折扣单销售额
		map.put("dayDiscountSalesAmount", moreOrderMasterMapper.selectDayDiscountSalesAmount(date));
		//总奖金额
		map.put("totalMemberBonusAmount", moreMemberBonusMapper.getTotalMemberBonus());
		//当日奖金额
		map.put("dayMemberBonusAmount", moreMemberBonusMapper.getDayMemberBonus(date));
		//总提现额
		map.put("totalAdvanceAmount", moreMemberBonusMapper.getTotalAdvance());
		//当日提现额
		map.put("dayAdvanceAmount", moreMemberBonusMapper.getDayAdvance(date));
		return map;
	}
	/**
	 * 保存或更新日奖金流水
	 * @param record
	 */
	public void saveOrUpdateDateHistory(DateBonusHistory record) {
		if(null != record.getId()) {
			moreDateBonusHistoryMapper.updateByPrimaryKey(record);
		}else {
			moreDateBonusHistoryMapper.insert(record);
		}
	}
	/**
	 * 通过date查询DateBonusHistory
	 * @param date
	 * @return
	 */
	public DateBonusHistory getDateBonusHistory(String date) {
		DateBonusHistory history = moreDateBonusHistoryMapper.getDateBonusHistoryByDate(date);
		return history;
	}

	/**
	 * 平台资金数据merge
	 * @return
	 */
	public List selectNeedMergeDates(){
		return moreDateBonusHistoryMapper.selectNeedMergeDates();
	}
}
