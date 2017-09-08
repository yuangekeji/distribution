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

    //资金流向类型（支出：复投1，转出2，提现3 , 报单单 7, 折扣单 8；收入：分红包奖金4，转入5，动态奖金6）

    public static final String REORDER = "1";
    public static final String TRANSFEROUT = "2";
    public static final String ADVANCE = "3";
    public static final String DIVIDEND = "4";
    public static final String TRANSFERIN = "5";
    public static final String BONUS = "6";
    public static final String MEMBERORDER = "7";
    public static final String DISCOUNTORDER = "8";
    public static final String MEMBERCHARGE = "9";
}
