package com.distribution.common.constant;

/**
 * Created by lijingxin by 2017/08/21
 */
public class Constant {


    /**
     * session 存储对象
     */
    public static final String SESSION_CURRENT_USER="currentUser";
    public static final String SESSION_CURRENT_ROLE="currentUserRole";
    public static final String SESSION_CURRENT_STATUS="currentUserStatus";

    //资金流向类型（支出：复投1，转出2，提现3 , 报单单 7, 折扣单 8；收入：分红包奖金4，转入5，动态奖金6）
    //account_flow_history.flow_type字段

    public static final String REORDER     = "1";    //复投    1
    public static final String TRANSFEROUT = "2";    //转出   1
    public static final String ADVANCE      = "3";   //提现   1
//    public static final String TRANSFERIN   = "5";    //转入  2
    public static final String TRANSFERIN   = "11";   //转入  2
    public static final String MEMBERORDER   = "7";  //报单 1
    public static final String DISCOUNTORDER = "8";  //折扣单 1
    public static final String MEMBERCHARGE   = "9";  //用户充值申请或管理员充值 2
    public static final String CANCLE_ADVANCE = "10"; //取消提现  2

    public static final String BONUSACCOUNTTYPE_SEED ="1"; //种子币
    public static final String BONUSACCOUNTTYPE_BONUS ="2";//奖金币

    //管理员操作管理
    public static final String ADMINHANDLETYPE_ADDACCOUNT = "0"; //会员管理-会员充值
    public static final String ADMINHANDLETYPE_APPLYSTATUS = "1";//运营中心管理-审批通过/审批驳回
    public static final String ADMINHANDLETYPE_APPLYORDER = "2";//订单管理-确认发货、下载
    public static final String ADMINHANDLETYPE_APPLYADVANCE = "3";//提现管理-批准、驳回
    public static final String ADMINHANDLETYPE_APPLYPRODUCT = "4";//商品管理-上架、下架、修改、添加
    public static final String ADMINHANDLETYPE_BASICSETTING = "5";//基本配置-提现设置、分红包设置、广告宣传奖设置
    public static final String ADMINHANDLETYPE_ADMINSETTING = "6";//管理员权限-管理员创建、启用、禁用
    public static final String ADMINHANDLETYPE_PAYAMTPROC = "7";//job奖金发放管理-补发分红包、补发广告宣传奖
    public static final String ADMINHANDLETYPE_MEMBERCHARGE = "8";//充值管理-批准、驳回、充值
    public static final String ADMINHANDLETYPE_ADDNOTICE = "9";//公告管理-添加
    public static final String ADMINHANDLETYPE_BONUS = "10";//分销管理-下载
    public static final String ADMINHANDLETYPE_DIVIDEND = "11";//分红包管理-下载
    public static final String ADMINHANDLETYPE_TRANSFER = "12";//转账管理-下载
    public static final String ADMINHANDLETYPE_APPLYPOINT = "13";//积分兑换管理
}
