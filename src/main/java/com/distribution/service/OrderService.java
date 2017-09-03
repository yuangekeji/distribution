package com.distribution.service;

import com.distribution.common.utils.Page;
import com.distribution.dao.accountFlowHistory.mapper.AccountFlowHistoryMapper;
import com.distribution.dao.accountFlowHistory.model.AccountFlowHistory;
import com.distribution.dao.accountManager.mapper.more.MoreAccountManagerMapper;
import com.distribution.dao.accountManager.model.AccountManager;
import com.distribution.dao.dividend.mapper.DividendMapper;
import com.distribution.dao.dividend.model.Dividend;
import com.distribution.dao.member.model.Member;
import com.distribution.dao.order.mapper.more.MoreOrderMapper;
import com.distribution.dao.order.model.more.MoreOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

@Service
public class OrderService {

    @Autowired
    private MoreOrderMapper moreOrderMapper;

    @Autowired
    private MoreAccountManagerMapper moreAccountManagerMapper;

    @Autowired
    private AccountFlowHistoryMapper accountFlowHistoryMapper;

    @Autowired
    private DividendMapper dividendMapper;
    /**
     * description 订单列表查询
     * @author WYN
     * */
    public Page orderList(Page page){
        if(null!=page.getParameterMap().get("startTime") && !"".equals(page.getParameterMap().get("startTime")))
            page.getParameterMap().put("start",page.getParameterMap().get("startTime").toString()+" 00:00:00");
        if(null!=page.getParameterMap().get("endTime") && !"".equals(page.getParameterMap().get("endTime")))
            page.getParameterMap().put("end",page.getParameterMap().get("endTime").toString()+" 23:59:59");

        page.setTotalCount(moreOrderMapper.getOrderListCount(page));
        page.setResult(moreOrderMapper.getOrderList(page));
        return page;
    }

    /**
     * description 插入订单
     * @author Bright
     * */
    public String insertOrder(MoreOrder moreOrder, Member member){
        int cnt1 = 0;
        int cnt2 = 0;
        int cnt3 = 0;
        int cnt4 = 0;
        int cnt5 = 0;

        //BigInteger orderNo = this.getOrderNo();

        Long orderNo = this.getOrderNo();

        moreOrder.setOrderNo(orderNo);

        //order_master insert
        int orderId = moreOrderMapper.insertOrder(moreOrder);

        if(orderId > 0){
            //order_detail_insert
            cnt1 = moreOrderMapper.insertOrderDetail(moreOrder);
        }

        //扣款登记 account_manager
        if(cnt1 > 0){
            AccountManager accountManager = new AccountManager();

            accountManager.setMemberId(moreOrder.getMemberId());
            accountManager.setSeedAmt(moreOrder.getSeedAmt());
            accountManager.setBonusAmt(moreOrder.getBonusAmt());
            accountManager.setCreateId(member.getId());
            accountManager.setCreateTime(new Date());
            accountManager.setUpdateId(member.getId());
            accountManager.setUpdateTime(new Date());

            cnt2 = moreAccountManagerMapper.updateAccountManager(accountManager);
        }

        //账户流水登记account_flow_history
        if(cnt2 > 0){
            AccountFlowHistory accountFlowHistory = new AccountFlowHistory();

            String flow_type = "";
            accountFlowHistory.setMemberId(moreOrder.getMemberId());
            accountFlowHistory.setSeedAmt(moreOrder.getSeedAmt());
            accountFlowHistory.setBonusAmt(moreOrder.getBonusAmt());
            accountFlowHistory.setCreateId(member.getId());
            accountFlowHistory.setCreateTime(new Date());
            accountFlowHistory.setTotalAmt(moreOrder.getSeedAmt().add(moreOrder.getBonusAmt()));
            accountFlowHistory.setType("1");
            if("1".equals(moreOrder.getOrderCategory())){
                flow_type = "7";
            }
            if("2".equals(moreOrder.getOrderCategory())){
                flow_type = "1";
            }
            if("3".equals(moreOrder.getOrderCategory())){
                flow_type = "8";
            }

            cnt3 = accountFlowHistoryMapper.insert(accountFlowHistory);
        }

        //报单，复投分红包处理
        if(cnt3 > 0 && ("1".equals(moreOrder.getOrderCategory()) || "2".equals(moreOrder.getOrderCategory()))){
            Dividend dividend = new Dividend();
            dividend.setOrderId(orderId);
            dividend.setOrderNo(orderNo);
            dividend.setOrderAmount(moreOrder.getOrderAmt());
            if(new BigDecimal(600).compareTo(moreOrder.getOrderAmt()) == 0){
                dividend.setDividendCount(new Integer(1));
            }else if(new BigDecimal(1800).compareTo(moreOrder.getOrderAmt()) == 0){
                dividend.setDividendCount(new Integer(3));
            }else if(new BigDecimal(3000).compareTo(moreOrder.getOrderAmt()) == 0){
                dividend.setDividendCount(new Integer(5));
            }else if(new BigDecimal(9000).compareTo(moreOrder.getOrderAmt()) == 0){
                dividend.setDividendCount(new Integer(15));
            }else if(new BigDecimal(30000).compareTo(moreOrder.getOrderAmt()) == 0){
                dividend.setDividendCount(new Integer(50));
            }else if(new BigDecimal(60000).compareTo(moreOrder.getOrderAmt()) == 0){
                dividend.setDividendCount(new Integer(100));
            }
            dividend.setDividendLimit(moreOrder.getOrderAmt().divide(new BigDecimal(0.75)));
            dividend.setDividendStatus("1");
            dividend.setMgmtFee(moreOrder.getOrderAmt().divide(new BigDecimal(0.75)).multiply(new BigDecimal(0.06)));
            dividend.setCreateId(member.getId());
            dividend.setCreateTime(new Date());
            dividend.setUpdateId(member.getId());
            dividend.setUpdateTime(new Date());

            cnt4 = dividendMapper.insert(dividend);
        }else{
            cnt4 = 1;
        }

        //报单，复投做奖金处理
        if(cnt4 > 0 && ("1".equals(moreOrder.getOrderCategory()) || "2".equals(moreOrder.getOrderCategory()))){
            cnt5 = 0; //todo 奖金接口调用 order
        }else{
            cnt5 = 1;
        }

        if(cnt1 > 0 && cnt2 > 0 && cnt3 > 0 && cnt4 >0 && cnt5 >0){
            return "success";
        }else{
            throw new RuntimeException();
        }


    }

    /**
     * description 插入订单
     * @author Bright
     * */
    public Long getOrderNo(){
        int max = 100;
        int min = 1;

        long t = System.currentTimeMillis();//获得当前时间的毫秒数
        Random rd = new Random(t);//作为种子数传入到Random的构造器中

        int s = rd.nextInt(max)%(max-min+1) + min;

        Date d = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        String sd = sdf.format(d);

        String str = sd + s + "";

        long orderNo = Long.valueOf(str).longValue();
       // BigInteger orderNo = BigInteger.valueOf(order);

        return orderNo;
    }
}
