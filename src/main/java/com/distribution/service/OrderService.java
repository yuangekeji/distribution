package com.distribution.service;

import com.distribution.common.constant.BonusConstant;
import com.distribution.common.constant.Constant;
import com.distribution.common.utils.CryptoUtil;
import com.distribution.common.utils.Page;
import com.distribution.dao.accountFlowHistory.mapper.AccountFlowHistoryMapper;
import com.distribution.dao.accountFlowHistory.model.AccountFlowHistory;
import com.distribution.dao.accountManager.mapper.more.MoreAccountManagerMapper;
import com.distribution.dao.accountManager.model.AccountManager;
import com.distribution.dao.dividend.mapper.DividendMapper;
import com.distribution.dao.dividend.model.Dividend;
import com.distribution.dao.goods.mapper.GoodsMapper;
import com.distribution.dao.goods.mapper.more.MoreGoodsMapper;
import com.distribution.dao.goods.model.Goods;
import com.distribution.dao.member.mapper.more.MoreMemberMapper;
import com.distribution.dao.member.model.Member;
import com.distribution.dao.order.mapper.OrderMasterMapper;
import com.distribution.dao.order.mapper.more.MoreOrderMasterMapper;
import com.distribution.dao.order.model.OrderMaster;
import com.distribution.dao.order.model.more.MoreOrderMaster;
import org.aspectj.weaver.ast.Or;
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

    @Autowired
    private MoreGoodsMapper moreGoodsMapper;

    @Autowired
    private GoodsMapper goodsMapper;

    @Autowired
    private CommonService commonService;

    /**
     * description 订单列表查询
     * @author WYN
     * */
    public Page orderList(Page page){
        /*if(null!=page.getParameterMap().get("startTime") && !"".equals(page.getParameterMap().get("startTime")))
            page.getParameterMap().put("startTime",page.getParameterMap().get("startTime").toString()+" 00:00:00");
        if(null!=page.getParameterMap().get("endTime") && !"".equals(page.getParameterMap().get("endTime")))
            page.getParameterMap().put("endTime",page.getParameterMap().get("endTime").toString()+" 23:59:59");*/

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

        //判断是否是种子币复投
        boolean reOrderSeed = ("2".equals(moreOrderMaster.getOrderCategory()) && Constant.BONUSACCOUNTTYPE_SEED.equals(moreOrderMaster.getBonusType())) ? true :false ;

        //order_master insert
        //复投订单状态并用种子币支付的直接为订单完成（奖金币支付除外）
        if(reOrderSeed){
            moreOrderMaster.setOrderStatues("4");
            moreOrderMaster.setBonusAccountType(Constant.BONUSACCOUNTTYPE_SEED); //种子币
        }else{
            moreOrderMaster.setBonusAccountType(Constant.BONUSACCOUNTTYPE_BONUS); //奖金币
            //自提的话直接发货完成
            if("1".equals(moreOrderMaster.getSendbypostyn())){
                moreOrderMaster.setOrderStatues("3");//待收货
            }
        }

        //自提的话不填写地址
        if("1".equals(moreOrderMaster.getSendbypostyn())){
            moreOrderMaster.setReceiveName(null);
            moreOrderMaster.setRecevivePhone(null);
            moreOrderMaster.setExpressAddress(null);
        }

        cnt1 = moreOrderMasterMapper.insertOrder(moreOrderMaster);

        if(cnt1 == 0){
            throw new RuntimeException("Order insert Error");
        }

        int orderId = moreOrderMaster.getId();

        //奖金币复投有产品，种子币复投没有产品
        //order_detail_inser(复投没有商品不插入订单详细表)
        if(!reOrderSeed) {
            moreOrderMaster.setGoodsCd(1);
            cnt2 = moreOrderMasterMapper.insertOrderDetail(moreOrderMaster);
        }else{
            cnt2 = 1;
        }

        if(cnt2 == 0){
           throw new RuntimeException();
        }

        //商品库存处理
        if(!reOrderSeed) {
            Goods goods = new Goods();
            goods.setId(moreOrderMaster.getGoodsCd());
            goods.setGoodsNum(moreOrderMaster.getOrderQty());
            //减少库存updateByPrimaryKeySelective
            cnt3 = moreGoodsMapper.updateGoodsQty(goods);
        }else{
            cnt3 = 1;
        }
        if(cnt3 == 0){
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

        cnt4 = moreAccountManagerMapper.updateAccountManager(accountManager);
        if(cnt4 == 0){
            throw new RuntimeException();
        }

        //账户流水登记account_flow_history

        AccountFlowHistory accountFlowHistory = new AccountFlowHistory();

        accountFlowHistory.setMemberId(moreOrderMaster.getMemberId());
        accountFlowHistory.setSeedAmt(moreOrderMaster.getSeedAmt() == null ? new BigDecimal(0):moreOrderMaster.getSeedAmt());
        accountFlowHistory.setBonusAmt(moreOrderMaster.getBonusAmt() ==null ? new BigDecimal(0):moreOrderMaster.getBonusAmt());
        accountFlowHistory.setCreateId(moreOrderMaster.getCreateId());
        accountFlowHistory.setCreateTime(new Date());
        accountFlowHistory.setTotalAmt(accountFlowHistory.getSeedAmt().add(accountFlowHistory.getBonusAmt()));
        accountFlowHistory.setType("1");
        if("1".equals(moreOrderMaster.getOrderCategory())){
            accountFlowHistory.setFlowType(Constant.MEMBERORDER);
        }else if("2".equals(moreOrderMaster.getOrderCategory())){
            accountFlowHistory.setFlowType(Constant.REORDER);
        }else if("3".equals(moreOrderMaster.getOrderCategory())){
            accountFlowHistory.setFlowType(Constant.DISCOUNTORDER);
        }

        cnt5 = accountFlowHistoryMapper.insert(accountFlowHistory);

        if(cnt5 == 0){
            throw new RuntimeException();
        }

        //报单，复投分红包处理
        if("1".equals(moreOrderMaster.getOrderCategory()) || "2".equals(moreOrderMaster.getOrderCategory())){

            //领取分红包 600 -> 760 ，从百分比75%更换到固定数额
            double dividendLimitPercent = commonService.getMaxAmt(BonusConstant.D02,BonusConstant.CODE_02);

            Dividend dividend = new Dividend();
            BigDecimal divideCount = moreOrderMaster.getOrderAmt().divide(new BigDecimal(600));

            dividend.setOrderId(orderId);
            dividend.setOrderNo(orderNo);
            dividend.setOrderAmount(moreOrderMaster.getOrderAmt());
            dividend.setMemberId(moreOrderMaster.getMemberId());
            dividend.setDividendCount(divideCount.intValue());
            dividend.setDividendLimit(new BigDecimal(dividendLimitPercent).multiply(divideCount));
            dividend.setRemainAmount(dividend.getDividendLimit());
            dividend.setReceivedAmount(new BigDecimal(0)); //已领取金额给个0
            dividend.setDividendStatus("1");
            dividend.setMgmtFee(dividend.getDividendLimit().multiply(new BigDecimal(0.06)));
            dividend.setCreateId(moreOrderMaster.getCreateId());
            dividend.setCreateTime(new Date());
            dividend.setUpdateId(moreOrderMaster.getCreateId());
            dividend.setUpdateTime(new Date());

           cnt6 = dividendMapper.insert(dividend);
           if(cnt6 == 0){
                throw new RuntimeException();
            }
        }

        //报单，复投做奖金处理
        if("1".equals(moreOrderMaster.getOrderCategory()) || "2".equals(moreOrderMaster.getOrderCategory())){

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

    /**
     *
     * @param moreOrderMaster
     * @param currentUser
     * @return
     */
    public String insertReOrder(MoreOrderMaster moreOrderMaster,Member currentUser){

        //根据member_id 和 paypwd 查询会员是否存在
        Map param = new HashMap();
        param.put("memberId",currentUser.getId());
        param.put("memberPhone",currentUser.getMemberPhone());
        param.put("payPassword", CryptoUtil.md5ByHex(moreOrderMaster.getPayPassword()));

        Integer count = memberMapper.findMatchMemberQueryPwd(param);

        if(count != null  && count >0 ) {

            //种子币复投
            if("1".equals(moreOrderMaster.getBonusType())) {
                moreOrderMaster.setSeedAmt(moreOrderMaster.getOrderAmt());

            //奖金币复投
            }else  if("2".equals(moreOrderMaster.getBonusType())){
                moreOrderMaster.setBonusAmt(moreOrderMaster.getOrderAmt());

            }else{
                throw new RuntimeException("ERROR bonusType is null,memberId="+currentUser.getId());
            }

            //判断当日复投的金额是否在30000之内
            double maxOrderAmt =  moreOrderMasterMapper.selectMaxOrderAmt(currentUser.getId());

            if((moreOrderMaster.getOrderAmt().add(new BigDecimal(maxOrderAmt))).compareTo(new BigDecimal(30000)) == 1){
                return "reOrderLimit";
            }

            moreOrderMaster.setOrderCategory("2");
            moreOrderMaster.setDiscount(0);
            moreOrderMaster.setActAmt(moreOrderMaster.getOrderAmt());
            moreOrderMaster.setExpressFee(new BigDecimal(0));
            moreOrderMaster.setMemberId(currentUser.getId());
            moreOrderMaster.setMemberLevel(currentUser.getMemberLevel());
            moreOrderMaster.setOrderStatues("2");
            moreOrderMaster.setCreateId(currentUser.getId());
            moreOrderMaster.setCreateTime(new Date());
            moreOrderMaster.setUpdateId(currentUser.getId());
            moreOrderMaster.setUpdateTime(new Date());
            String result = this.insertOrder(moreOrderMaster);

//           if(currentUser.getIsSalesDept() ==null || !"Y".equals(currentUser.getIsSalesDept())){
//                //判断累计的订单金额 如果是超过3万 就更新为工销售部
//                Double orderTotalAmount = moreOrderMasterMapper.countOrderAmcountByMemberId(currentUser.getId());
//                if(orderTotalAmount >= 30000){
//                    //更新为工作室
//                    memberMapper.updateMemberSalesDept(currentUser.getId());
//                }
//           }

            return result;
        }

        return "pwdWrong";

    }

    /**
     * 折扣订单处理
     * @param moreOrderMaster
     * @param currentUser
     * @return
     */
    public String insertDisOrder(MoreOrderMaster moreOrderMaster,Member currentUser){
            moreOrderMaster.setOrderCategory("3");
            moreOrderMaster.setDiscount(40);
            moreOrderMaster.setOrderAmt(moreOrderMaster.getGoodsPrice().multiply(new BigDecimal(moreOrderMaster.getOrderQty())));
            moreOrderMaster.setActAmt(moreOrderMaster.getGoodsPrice().multiply(new BigDecimal(moreOrderMaster.getOrderQty())).multiply(new BigDecimal(0.4)));
            moreOrderMaster.setBonusAmt(moreOrderMaster.getGoodsPrice().multiply(new BigDecimal(moreOrderMaster.getOrderQty())).multiply(new BigDecimal(0.4)));
            int compareValue = moreOrderMaster.getActAmt().compareTo(new BigDecimal(600));
            moreOrderMaster.setExpressFee((compareValue > -1) ? new BigDecimal(0) : new BigDecimal(10));
            moreOrderMaster.setMemberId(currentUser.getId());
            moreOrderMaster.setMemberLevel(currentUser.getMemberLevel());
            moreOrderMaster.setOrderStatues("2");
            moreOrderMaster.setCreateId(currentUser.getId());
            moreOrderMaster.setCreateTime(new Date());
            moreOrderMaster.setUpdateId(currentUser.getId());
            moreOrderMaster.setUpdateTime(new Date());

            if("1".equals(moreOrderMaster.getSendbypostyn())){
                moreOrderMaster.setRecevivePhone(null);
                moreOrderMaster.setReceiveName(null);
                moreOrderMaster.setExpressAddress(null);
            }

            //step 1)库存查询
            Goods goods = new Goods();
            goods = goodsMapper.selectByPrimaryKey(moreOrderMaster.getGoodsCd());

            //判断如果库存小于购买数量就失败
            if(goods.getGoodsNum().compareTo(moreOrderMaster.getOrderQty()) == -1){
                throw new RuntimeException();
            }

            //step 2)账户余额查询
            AccountManager accountManager = new AccountManager();
            accountManager.setMemberId(currentUser.getId());

            accountManager = moreAccountManagerMapper.selectAccountManager(accountManager);

            //判断如果账户余额小于购买金额就失败
            if(accountManager.getBonusAmt().compareTo(moreOrderMaster.getActAmt()) == -1){
                //throw new RuntimeException();
                return "cannotBuy";
            }

            return this.insertOrder(moreOrderMaster);
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
