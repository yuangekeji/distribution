package com.distribution.service;

import com.distribution.common.constant.Constant;
import com.distribution.common.utils.CryptoUtil;
import com.distribution.common.utils.Page;
import com.distribution.dao.accountFlowHistory.mapper.AccountFlowHistoryMapper;
import com.distribution.dao.accountFlowHistory.model.AccountFlowHistory;
import com.distribution.dao.accountManager.mapper.more.MoreAccountManagerMapper;
import com.distribution.dao.accountManager.model.AccountManager;
import com.distribution.dao.dividend.mapper.DividendMapper;
import com.distribution.dao.dividend.model.Dividend;
import com.distribution.dao.member.mapper.more.MoreMemberMapper;
import com.distribution.dao.member.model.Member;
import com.distribution.dao.order.mapper.OrderMasterMapper;
import com.distribution.dao.order.mapper.more.MoreOrderMasterMapper;
import com.distribution.dao.order.model.OrderMaster;
import com.distribution.dao.order.model.more.MoreOrderMaster;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@Service
public class OrderService {

    @Autowired
    private OrderMasterMapper orderMasterMapper;

    @Autowired
    private MoreOrderMasterMapper moreOrderMasterMapper;

    @Autowired
    private MoreAccountManagerMapper moreAccountManagerMapper;

    @Autowired
    private AccountFlowHistoryMapper accountFlowHistoryMapper;

    @Autowired
    private DividendMapper dividendMapper;

    @Autowired
    private MoreMemberMapper memberMapper;
    
    @Autowired
    private BonusService bonusService;
    /**
     * description 订单列表查询
     * @author WYN
     * */
    public Page orderList(Page page){
        if(null!=page.getParameterMap().get("startTime") && !"".equals(page.getParameterMap().get("startTime")))
            page.getParameterMap().put("start",page.getParameterMap().get("startTime").toString()+" 00:00:00");
        if(null!=page.getParameterMap().get("endTime") && !"".equals(page.getParameterMap().get("endTime")))
            page.getParameterMap().put("end",page.getParameterMap().get("endTime").toString()+" 23:59:59");

        page.setTotalCount(moreOrderMasterMapper.getOrderListCount(page));
        page.setResult(moreOrderMasterMapper.getOrderList(page));
        return page;
    }

    /**
     * description 插入订单
     * @author WYN
     * */
    public String insertOrder(MoreOrderMaster moreOrderMaster){
        int cnt1 = 0;
        int cnt2 = 0;
        int cnt3 = 0;
        int cnt4 = 0;
        int cnt5 = 0;
        int cnt6 = 0;

        //BigInteger orderNo = this.getOrderNo();
        Long orderNo = this.getOrderNo();
        moreOrderMaster.setOrderNo(orderNo);
        //order_master insert
        cnt1 = moreOrderMasterMapper.insertOrder(moreOrderMaster);
        if(cnt1 == 0){
            throw new RuntimeException();
        }

        int orderId = moreOrderMaster.getId();
            //order_detail_inser
        moreOrderMaster.setGoodsCd(1);
        cnt2 = moreOrderMasterMapper.insertOrderDetail(moreOrderMaster);
        if(cnt2 == 0){
           throw new RuntimeException();
         }

        //扣款登记 account_manager

            AccountManager accountManager = new AccountManager();

            accountManager.setMemberId(moreOrderMaster.getMemberId());
            accountManager.setSeedAmt(moreOrderMaster.getSeedAmt() == null ? new BigDecimal(0):moreOrderMaster.getSeedAmt());
            accountManager.setBonusAmt(moreOrderMaster.getBonusAmt() ==null ? new BigDecimal(0):moreOrderMaster.getBonusAmt());
            accountManager.setCreateId(moreOrderMaster.getCreateId());
            accountManager.setCreateTime(new Date());
            accountManager.setUpdateId(moreOrderMaster.getCreateId());
            accountManager.setUpdateTime(new Date());

            cnt3 = moreAccountManagerMapper.updateAccountManager(accountManager);
           if(cnt3 == 0){
              throw new RuntimeException();
           }

        //账户流水登记account_flow_history

            AccountFlowHistory accountFlowHistory = new AccountFlowHistory();

            accountFlowHistory.setMemberId(moreOrderMaster.getMemberId());
            accountFlowHistory.setSeedAmt(moreOrderMaster.getSeedAmt() == null ? new BigDecimal(0):moreOrderMaster.getSeedAmt());
            accountFlowHistory.setBonusAmt(moreOrderMaster.getBonusAmt());
            accountFlowHistory.setCreateId(moreOrderMaster.getCreateId());
            accountFlowHistory.setCreateTime(new Date());
            accountFlowHistory.setTotalAmt(accountFlowHistory.getSeedAmt().add(moreOrderMaster.getBonusAmt()));
            accountFlowHistory.setType("1");
            if("1".equals(moreOrderMaster.getOrderCategory())){
                accountFlowHistory.setFlowType(Constant.MEMBERORDER);
            }else if("2".equals(moreOrderMaster.getOrderCategory())){
                accountFlowHistory.setFlowType(Constant.REORDER);
            }else if("3".equals(moreOrderMaster.getOrderCategory())){
                accountFlowHistory.setFlowType(Constant.DISCOUNTORDER);
            }

            cnt4 = accountFlowHistoryMapper.insert(accountFlowHistory);

            if(cnt4 == 0){
                throw new RuntimeException();
            }

        //报单，复投分红包处理
        if("1".equals(moreOrderMaster.getOrderCategory()) || "2".equals(moreOrderMaster.getOrderCategory())){
            Dividend dividend = new Dividend();
            dividend.setOrderId(orderId);
            dividend.setOrderNo(orderNo);
            dividend.setOrderAmount(moreOrderMaster.getOrderAmt());
            dividend.setMemberId(moreOrderMaster.getMemberId());
            if(new BigDecimal(600).compareTo(moreOrderMaster.getOrderAmt()) == 0){
                dividend.setDividendCount(new Integer(1));
            }else if(new BigDecimal(1800).compareTo(moreOrderMaster.getOrderAmt()) == 0){
                dividend.setDividendCount(new Integer(3));
            }else if(new BigDecimal(3000).compareTo(moreOrderMaster.getOrderAmt()) == 0){
                dividend.setDividendCount(new Integer(5));
            }else if(new BigDecimal(9000).compareTo(moreOrderMaster.getOrderAmt()) == 0){
                dividend.setDividendCount(new Integer(15));
            }else if(new BigDecimal(30000).compareTo(moreOrderMaster.getOrderAmt()) == 0){
                dividend.setDividendCount(new Integer(50));
            }else if(new BigDecimal(60000).compareTo(moreOrderMaster.getOrderAmt()) == 0){
                dividend.setDividendCount(new Integer(100));
            }
            dividend.setDividendLimit(moreOrderMaster.getOrderAmt().divide(new BigDecimal(0.75)));
            dividend.setDividendStatus("1");
            dividend.setMgmtFee(moreOrderMaster.getOrderAmt().divide(new BigDecimal(0.75)).multiply(new BigDecimal(0.06)));
            dividend.setCreateId(moreOrderMaster.getCreateId());
            dividend.setCreateTime(new Date());
            dividend.setUpdateId(moreOrderMaster.getCreateId());
            dividend.setUpdateTime(new Date());
            dividend.setReceivedAmount(new BigDecimal(0)); //已领取金额给个0
            dividend.setRemainAmount(new BigDecimal(0));   //还未领取金额个0

           cnt5 = dividendMapper.insert(dividend);
           if(cnt5 == 0){
                throw new RuntimeException();
            }
        }

        //报单，复投做奖金处理
        if("1".equals(moreOrderMaster.getOrderCategory()) || "2".equals(moreOrderMaster.getOrderCategory())){
            cnt6 = 1; //todo 奖金接口调用 order
            if(cnt6 ==0){
                throw new RuntimeException();
            }
            bonusService.processOrderBonus(moreOrderMaster);
        }

        return "success";
    }

    /**
     * description 订单号生成
     * @author WYN
     * */
    public Long getOrderNo(){
        int max = 100;
        int min = 1;

        long timeMillis = System.currentTimeMillis();//获得当前时间的毫秒数
        Random rd = new Random(timeMillis);//作为种子数传入到Random的构造器中

        int num = rd.nextInt(max)%(max-min+1) + min;
        String strNum = "";
        if(num < 10){
            strNum = "00" + num;
        }else if (num >= 10 && num < 100){
            strNum = "0" + num;
        }else{
            strNum = num + "";
        }

        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyMMddHHmmss");
        String strDate = sdf.format(date);

        String str = strDate + strNum;

        long orderNo = Long.valueOf(str).longValue();

        return orderNo;
    }


    public String insertReOrder(MoreOrderMaster moreOrderMaster,Member currentUser){

        //根据member_id 和 paypwd 查询会员是否存在
        Map param = new HashMap();
        param.put("memberId",currentUser.getId());
        param.put("memberPhone",currentUser.getMemberPhone());
        param.put("payPassword", CryptoUtil.md5ByHex(moreOrderMaster.getPayPassword()));

        Integer count = memberMapper.findMatchMemberQueryPwd(param);

        if(count != null  && count >0 ) {

            if(currentUser.getIsSalesDept() !=null && "N".equals(currentUser.getIsSalesDept())){
                //判断累计的订单金额 如果是超过3万 就更新为工销售部
                Double orderTotalAmount = moreOrderMasterMapper.countOrderAmcountByMemberId(currentUser.getId());

                if(orderTotalAmount >= 30000){
                    //更新为工作室
                    memberMapper.updateMemberSalesDept(currentUser.getId());
                }
            }

            moreOrderMaster.setOrderCategory("2");
            moreOrderMaster.setDiscount(0);
            moreOrderMaster.setActAmt(moreOrderMaster.getOrderAmt());
            moreOrderMaster.setExpressFee(new BigDecimal(0));
            moreOrderMaster.setMemberId(currentUser.getId());
            moreOrderMaster.setReceiveName(currentUser.getConsignee());
            moreOrderMaster.setExpressAddress(currentUser.getExpressAddress());
            moreOrderMaster.setMemberLevel(currentUser.getMemberLevel());
            moreOrderMaster.setOrderStatues("2");
            moreOrderMaster.setCreateId(currentUser.getId());
            moreOrderMaster.setCreateTime(new Date());
            moreOrderMaster.setUpdateId(currentUser.getId());
            moreOrderMaster.setUpdateTime(new Date());
            String result = this.insertOrder(moreOrderMaster);

            return result;
        }

        return "pwdWrong";

    }

    /**
     * description 确认收货
     * @author WYN
     * */
    public String confirmOrder(OrderMaster orderMaster) {
        int cnt = orderMasterMapper.updateByPrimaryKeySelective(orderMaster);

        if(cnt > 0){
            return "success";
        }else{
            throw new RuntimeException();
        }
    }
}
