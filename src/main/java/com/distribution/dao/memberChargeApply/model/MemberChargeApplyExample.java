package com.distribution.dao.memberChargeApply.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MemberChargeApplyExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public MemberChargeApplyExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Integer value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Integer value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Integer value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Integer value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Integer value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Integer> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Integer> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Integer value1, Integer value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Integer value1, Integer value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andMemberIdIsNull() {
            addCriterion("member_id is null");
            return (Criteria) this;
        }

        public Criteria andMemberIdIsNotNull() {
            addCriterion("member_id is not null");
            return (Criteria) this;
        }

        public Criteria andMemberIdEqualTo(Integer value) {
            addCriterion("member_id =", value, "memberId");
            return (Criteria) this;
        }

        public Criteria andMemberIdNotEqualTo(Integer value) {
            addCriterion("member_id <>", value, "memberId");
            return (Criteria) this;
        }

        public Criteria andMemberIdGreaterThan(Integer value) {
            addCriterion("member_id >", value, "memberId");
            return (Criteria) this;
        }

        public Criteria andMemberIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("member_id >=", value, "memberId");
            return (Criteria) this;
        }

        public Criteria andMemberIdLessThan(Integer value) {
            addCriterion("member_id <", value, "memberId");
            return (Criteria) this;
        }

        public Criteria andMemberIdLessThanOrEqualTo(Integer value) {
            addCriterion("member_id <=", value, "memberId");
            return (Criteria) this;
        }

        public Criteria andMemberIdIn(List<Integer> values) {
            addCriterion("member_id in", values, "memberId");
            return (Criteria) this;
        }

        public Criteria andMemberIdNotIn(List<Integer> values) {
            addCriterion("member_id not in", values, "memberId");
            return (Criteria) this;
        }

        public Criteria andMemberIdBetween(Integer value1, Integer value2) {
            addCriterion("member_id between", value1, value2, "memberId");
            return (Criteria) this;
        }

        public Criteria andMemberIdNotBetween(Integer value1, Integer value2) {
            addCriterion("member_id not between", value1, value2, "memberId");
            return (Criteria) this;
        }

        public Criteria andStatusIsNull() {
            addCriterion("status is null");
            return (Criteria) this;
        }

        public Criteria andStatusIsNotNull() {
            addCriterion("status is not null");
            return (Criteria) this;
        }

        public Criteria andStatusEqualTo(String value) {
            addCriterion("status =", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotEqualTo(String value) {
            addCriterion("status <>", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThan(String value) {
            addCriterion("status >", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThanOrEqualTo(String value) {
            addCriterion("status >=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThan(String value) {
            addCriterion("status <", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThanOrEqualTo(String value) {
            addCriterion("status <=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLike(String value) {
            addCriterion("status like", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotLike(String value) {
            addCriterion("status not like", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusIn(List<String> values) {
            addCriterion("status in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotIn(List<String> values) {
            addCriterion("status not in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusBetween(String value1, String value2) {
            addCriterion("status between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotBetween(String value1, String value2) {
            addCriterion("status not between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andChargeRequestTimeIsNull() {
            addCriterion("charge_request_time is null");
            return (Criteria) this;
        }

        public Criteria andChargeRequestTimeIsNotNull() {
            addCriterion("charge_request_time is not null");
            return (Criteria) this;
        }

        public Criteria andChargeRequestTimeEqualTo(Date value) {
            addCriterion("charge_request_time =", value, "chargeRequestTime");
            return (Criteria) this;
        }

        public Criteria andChargeRequestTimeNotEqualTo(Date value) {
            addCriterion("charge_request_time <>", value, "chargeRequestTime");
            return (Criteria) this;
        }

        public Criteria andChargeRequestTimeGreaterThan(Date value) {
            addCriterion("charge_request_time >", value, "chargeRequestTime");
            return (Criteria) this;
        }

        public Criteria andChargeRequestTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("charge_request_time >=", value, "chargeRequestTime");
            return (Criteria) this;
        }

        public Criteria andChargeRequestTimeLessThan(Date value) {
            addCriterion("charge_request_time <", value, "chargeRequestTime");
            return (Criteria) this;
        }

        public Criteria andChargeRequestTimeLessThanOrEqualTo(Date value) {
            addCriterion("charge_request_time <=", value, "chargeRequestTime");
            return (Criteria) this;
        }

        public Criteria andChargeRequestTimeIn(List<Date> values) {
            addCriterion("charge_request_time in", values, "chargeRequestTime");
            return (Criteria) this;
        }

        public Criteria andChargeRequestTimeNotIn(List<Date> values) {
            addCriterion("charge_request_time not in", values, "chargeRequestTime");
            return (Criteria) this;
        }

        public Criteria andChargeRequestTimeBetween(Date value1, Date value2) {
            addCriterion("charge_request_time between", value1, value2, "chargeRequestTime");
            return (Criteria) this;
        }

        public Criteria andChargeRequestTimeNotBetween(Date value1, Date value2) {
            addCriterion("charge_request_time not between", value1, value2, "chargeRequestTime");
            return (Criteria) this;
        }

        public Criteria andPayMoneyTypeIsNull() {
            addCriterion("pay_money_type is null");
            return (Criteria) this;
        }

        public Criteria andPayMoneyTypeIsNotNull() {
            addCriterion("pay_money_type is not null");
            return (Criteria) this;
        }

        public Criteria andPayMoneyTypeEqualTo(String value) {
            addCriterion("pay_money_type =", value, "payMoneyType");
            return (Criteria) this;
        }

        public Criteria andPayMoneyTypeNotEqualTo(String value) {
            addCriterion("pay_money_type <>", value, "payMoneyType");
            return (Criteria) this;
        }

        public Criteria andPayMoneyTypeGreaterThan(String value) {
            addCriterion("pay_money_type >", value, "payMoneyType");
            return (Criteria) this;
        }

        public Criteria andPayMoneyTypeGreaterThanOrEqualTo(String value) {
            addCriterion("pay_money_type >=", value, "payMoneyType");
            return (Criteria) this;
        }

        public Criteria andPayMoneyTypeLessThan(String value) {
            addCriterion("pay_money_type <", value, "payMoneyType");
            return (Criteria) this;
        }

        public Criteria andPayMoneyTypeLessThanOrEqualTo(String value) {
            addCriterion("pay_money_type <=", value, "payMoneyType");
            return (Criteria) this;
        }

        public Criteria andPayMoneyTypeLike(String value) {
            addCriterion("pay_money_type like", value, "payMoneyType");
            return (Criteria) this;
        }

        public Criteria andPayMoneyTypeNotLike(String value) {
            addCriterion("pay_money_type not like", value, "payMoneyType");
            return (Criteria) this;
        }

        public Criteria andPayMoneyTypeIn(List<String> values) {
            addCriterion("pay_money_type in", values, "payMoneyType");
            return (Criteria) this;
        }

        public Criteria andPayMoneyTypeNotIn(List<String> values) {
            addCriterion("pay_money_type not in", values, "payMoneyType");
            return (Criteria) this;
        }

        public Criteria andPayMoneyTypeBetween(String value1, String value2) {
            addCriterion("pay_money_type between", value1, value2, "payMoneyType");
            return (Criteria) this;
        }

        public Criteria andPayMoneyTypeNotBetween(String value1, String value2) {
            addCriterion("pay_money_type not between", value1, value2, "payMoneyType");
            return (Criteria) this;
        }

        public Criteria andPayMoneyTimeIsNull() {
            addCriterion("pay_money_time is null");
            return (Criteria) this;
        }

        public Criteria andPayMoneyTimeIsNotNull() {
            addCriterion("pay_money_time is not null");
            return (Criteria) this;
        }

        public Criteria andPayMoneyTimeEqualTo(Date value) {
            addCriterion("pay_money_time =", value, "payMoneyTime");
            return (Criteria) this;
        }

        public Criteria andPayMoneyTimeNotEqualTo(Date value) {
            addCriterion("pay_money_time <>", value, "payMoneyTime");
            return (Criteria) this;
        }

        public Criteria andPayMoneyTimeGreaterThan(Date value) {
            addCriterion("pay_money_time >", value, "payMoneyTime");
            return (Criteria) this;
        }

        public Criteria andPayMoneyTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("pay_money_time >=", value, "payMoneyTime");
            return (Criteria) this;
        }

        public Criteria andPayMoneyTimeLessThan(Date value) {
            addCriterion("pay_money_time <", value, "payMoneyTime");
            return (Criteria) this;
        }

        public Criteria andPayMoneyTimeLessThanOrEqualTo(Date value) {
            addCriterion("pay_money_time <=", value, "payMoneyTime");
            return (Criteria) this;
        }

        public Criteria andPayMoneyTimeIn(List<Date> values) {
            addCriterion("pay_money_time in", values, "payMoneyTime");
            return (Criteria) this;
        }

        public Criteria andPayMoneyTimeNotIn(List<Date> values) {
            addCriterion("pay_money_time not in", values, "payMoneyTime");
            return (Criteria) this;
        }

        public Criteria andPayMoneyTimeBetween(Date value1, Date value2) {
            addCriterion("pay_money_time between", value1, value2, "payMoneyTime");
            return (Criteria) this;
        }

        public Criteria andPayMoneyTimeNotBetween(Date value1, Date value2) {
            addCriterion("pay_money_time not between", value1, value2, "payMoneyTime");
            return (Criteria) this;
        }

        public Criteria andChargeAmtIsNull() {
            addCriterion("charge_amt is null");
            return (Criteria) this;
        }

        public Criteria andChargeAmtIsNotNull() {
            addCriterion("charge_amt is not null");
            return (Criteria) this;
        }

        public Criteria andChargeAmtEqualTo(BigDecimal value) {
            addCriterion("charge_amt =", value, "chargeAmt");
            return (Criteria) this;
        }

        public Criteria andChargeAmtNotEqualTo(BigDecimal value) {
            addCriterion("charge_amt <>", value, "chargeAmt");
            return (Criteria) this;
        }

        public Criteria andChargeAmtGreaterThan(BigDecimal value) {
            addCriterion("charge_amt >", value, "chargeAmt");
            return (Criteria) this;
        }

        public Criteria andChargeAmtGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("charge_amt >=", value, "chargeAmt");
            return (Criteria) this;
        }

        public Criteria andChargeAmtLessThan(BigDecimal value) {
            addCriterion("charge_amt <", value, "chargeAmt");
            return (Criteria) this;
        }

        public Criteria andChargeAmtLessThanOrEqualTo(BigDecimal value) {
            addCriterion("charge_amt <=", value, "chargeAmt");
            return (Criteria) this;
        }

        public Criteria andChargeAmtIn(List<BigDecimal> values) {
            addCriterion("charge_amt in", values, "chargeAmt");
            return (Criteria) this;
        }

        public Criteria andChargeAmtNotIn(List<BigDecimal> values) {
            addCriterion("charge_amt not in", values, "chargeAmt");
            return (Criteria) this;
        }

        public Criteria andChargeAmtBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("charge_amt between", value1, value2, "chargeAmt");
            return (Criteria) this;
        }

        public Criteria andChargeAmtNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("charge_amt not between", value1, value2, "chargeAmt");
            return (Criteria) this;
        }

        public Criteria andChargeMoneyTypeIsNull() {
            addCriterion("charge_money_type is null");
            return (Criteria) this;
        }

        public Criteria andChargeMoneyTypeIsNotNull() {
            addCriterion("charge_money_type is not null");
            return (Criteria) this;
        }

        public Criteria andChargeMoneyTypeEqualTo(String value) {
            addCriterion("charge_money_type =", value, "chargeMoneyType");
            return (Criteria) this;
        }

        public Criteria andChargeMoneyTypeNotEqualTo(String value) {
            addCriterion("charge_money_type <>", value, "chargeMoneyType");
            return (Criteria) this;
        }

        public Criteria andChargeMoneyTypeGreaterThan(String value) {
            addCriterion("charge_money_type >", value, "chargeMoneyType");
            return (Criteria) this;
        }

        public Criteria andChargeMoneyTypeGreaterThanOrEqualTo(String value) {
            addCriterion("charge_money_type >=", value, "chargeMoneyType");
            return (Criteria) this;
        }

        public Criteria andChargeMoneyTypeLessThan(String value) {
            addCriterion("charge_money_type <", value, "chargeMoneyType");
            return (Criteria) this;
        }

        public Criteria andChargeMoneyTypeLessThanOrEqualTo(String value) {
            addCriterion("charge_money_type <=", value, "chargeMoneyType");
            return (Criteria) this;
        }

        public Criteria andChargeMoneyTypeLike(String value) {
            addCriterion("charge_money_type like", value, "chargeMoneyType");
            return (Criteria) this;
        }

        public Criteria andChargeMoneyTypeNotLike(String value) {
            addCriterion("charge_money_type not like", value, "chargeMoneyType");
            return (Criteria) this;
        }

        public Criteria andChargeMoneyTypeIn(List<String> values) {
            addCriterion("charge_money_type in", values, "chargeMoneyType");
            return (Criteria) this;
        }

        public Criteria andChargeMoneyTypeNotIn(List<String> values) {
            addCriterion("charge_money_type not in", values, "chargeMoneyType");
            return (Criteria) this;
        }

        public Criteria andChargeMoneyTypeBetween(String value1, String value2) {
            addCriterion("charge_money_type between", value1, value2, "chargeMoneyType");
            return (Criteria) this;
        }

        public Criteria andChargeMoneyTypeNotBetween(String value1, String value2) {
            addCriterion("charge_money_type not between", value1, value2, "chargeMoneyType");
            return (Criteria) this;
        }

        public Criteria andChargeApplyTimeIsNull() {
            addCriterion("charge_apply_time is null");
            return (Criteria) this;
        }

        public Criteria andChargeApplyTimeIsNotNull() {
            addCriterion("charge_apply_time is not null");
            return (Criteria) this;
        }

        public Criteria andChargeApplyTimeEqualTo(Date value) {
            addCriterion("charge_apply_time =", value, "chargeApplyTime");
            return (Criteria) this;
        }

        public Criteria andChargeApplyTimeNotEqualTo(Date value) {
            addCriterion("charge_apply_time <>", value, "chargeApplyTime");
            return (Criteria) this;
        }

        public Criteria andChargeApplyTimeGreaterThan(Date value) {
            addCriterion("charge_apply_time >", value, "chargeApplyTime");
            return (Criteria) this;
        }

        public Criteria andChargeApplyTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("charge_apply_time >=", value, "chargeApplyTime");
            return (Criteria) this;
        }

        public Criteria andChargeApplyTimeLessThan(Date value) {
            addCriterion("charge_apply_time <", value, "chargeApplyTime");
            return (Criteria) this;
        }

        public Criteria andChargeApplyTimeLessThanOrEqualTo(Date value) {
            addCriterion("charge_apply_time <=", value, "chargeApplyTime");
            return (Criteria) this;
        }

        public Criteria andChargeApplyTimeIn(List<Date> values) {
            addCriterion("charge_apply_time in", values, "chargeApplyTime");
            return (Criteria) this;
        }

        public Criteria andChargeApplyTimeNotIn(List<Date> values) {
            addCriterion("charge_apply_time not in", values, "chargeApplyTime");
            return (Criteria) this;
        }

        public Criteria andChargeApplyTimeBetween(Date value1, Date value2) {
            addCriterion("charge_apply_time between", value1, value2, "chargeApplyTime");
            return (Criteria) this;
        }

        public Criteria andChargeApplyTimeNotBetween(Date value1, Date value2) {
            addCriterion("charge_apply_time not between", value1, value2, "chargeApplyTime");
            return (Criteria) this;
        }

        public Criteria andChargeTimeIsNull() {
            addCriterion("charge_time is null");
            return (Criteria) this;
        }

        public Criteria andChargeTimeIsNotNull() {
            addCriterion("charge_time is not null");
            return (Criteria) this;
        }

        public Criteria andChargeTimeEqualTo(Date value) {
            addCriterion("charge_time =", value, "chargeTime");
            return (Criteria) this;
        }

        public Criteria andChargeTimeNotEqualTo(Date value) {
            addCriterion("charge_time <>", value, "chargeTime");
            return (Criteria) this;
        }

        public Criteria andChargeTimeGreaterThan(Date value) {
            addCriterion("charge_time >", value, "chargeTime");
            return (Criteria) this;
        }

        public Criteria andChargeTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("charge_time >=", value, "chargeTime");
            return (Criteria) this;
        }

        public Criteria andChargeTimeLessThan(Date value) {
            addCriterion("charge_time <", value, "chargeTime");
            return (Criteria) this;
        }

        public Criteria andChargeTimeLessThanOrEqualTo(Date value) {
            addCriterion("charge_time <=", value, "chargeTime");
            return (Criteria) this;
        }

        public Criteria andChargeTimeIn(List<Date> values) {
            addCriterion("charge_time in", values, "chargeTime");
            return (Criteria) this;
        }

        public Criteria andChargeTimeNotIn(List<Date> values) {
            addCriterion("charge_time not in", values, "chargeTime");
            return (Criteria) this;
        }

        public Criteria andChargeTimeBetween(Date value1, Date value2) {
            addCriterion("charge_time between", value1, value2, "chargeTime");
            return (Criteria) this;
        }

        public Criteria andChargeTimeNotBetween(Date value1, Date value2) {
            addCriterion("charge_time not between", value1, value2, "chargeTime");
            return (Criteria) this;
        }

        public Criteria andApplyInfoIsNull() {
            addCriterion("apply_info is null");
            return (Criteria) this;
        }

        public Criteria andApplyInfoIsNotNull() {
            addCriterion("apply_info is not null");
            return (Criteria) this;
        }

        public Criteria andApplyInfoEqualTo(String value) {
            addCriterion("apply_info =", value, "applyInfo");
            return (Criteria) this;
        }

        public Criteria andApplyInfoNotEqualTo(String value) {
            addCriterion("apply_info <>", value, "applyInfo");
            return (Criteria) this;
        }

        public Criteria andApplyInfoGreaterThan(String value) {
            addCriterion("apply_info >", value, "applyInfo");
            return (Criteria) this;
        }

        public Criteria andApplyInfoGreaterThanOrEqualTo(String value) {
            addCriterion("apply_info >=", value, "applyInfo");
            return (Criteria) this;
        }

        public Criteria andApplyInfoLessThan(String value) {
            addCriterion("apply_info <", value, "applyInfo");
            return (Criteria) this;
        }

        public Criteria andApplyInfoLessThanOrEqualTo(String value) {
            addCriterion("apply_info <=", value, "applyInfo");
            return (Criteria) this;
        }

        public Criteria andApplyInfoLike(String value) {
            addCriterion("apply_info like", value, "applyInfo");
            return (Criteria) this;
        }

        public Criteria andApplyInfoNotLike(String value) {
            addCriterion("apply_info not like", value, "applyInfo");
            return (Criteria) this;
        }

        public Criteria andApplyInfoIn(List<String> values) {
            addCriterion("apply_info in", values, "applyInfo");
            return (Criteria) this;
        }

        public Criteria andApplyInfoNotIn(List<String> values) {
            addCriterion("apply_info not in", values, "applyInfo");
            return (Criteria) this;
        }

        public Criteria andApplyInfoBetween(String value1, String value2) {
            addCriterion("apply_info between", value1, value2, "applyInfo");
            return (Criteria) this;
        }

        public Criteria andApplyInfoNotBetween(String value1, String value2) {
            addCriterion("apply_info not between", value1, value2, "applyInfo");
            return (Criteria) this;
        }

        public Criteria andRemarksIsNull() {
            addCriterion("remarks is null");
            return (Criteria) this;
        }

        public Criteria andRemarksIsNotNull() {
            addCriterion("remarks is not null");
            return (Criteria) this;
        }

        public Criteria andRemarksEqualTo(String value) {
            addCriterion("remarks =", value, "remarks");
            return (Criteria) this;
        }

        public Criteria andRemarksNotEqualTo(String value) {
            addCriterion("remarks <>", value, "remarks");
            return (Criteria) this;
        }

        public Criteria andRemarksGreaterThan(String value) {
            addCriterion("remarks >", value, "remarks");
            return (Criteria) this;
        }

        public Criteria andRemarksGreaterThanOrEqualTo(String value) {
            addCriterion("remarks >=", value, "remarks");
            return (Criteria) this;
        }

        public Criteria andRemarksLessThan(String value) {
            addCriterion("remarks <", value, "remarks");
            return (Criteria) this;
        }

        public Criteria andRemarksLessThanOrEqualTo(String value) {
            addCriterion("remarks <=", value, "remarks");
            return (Criteria) this;
        }

        public Criteria andRemarksLike(String value) {
            addCriterion("remarks like", value, "remarks");
            return (Criteria) this;
        }

        public Criteria andRemarksNotLike(String value) {
            addCriterion("remarks not like", value, "remarks");
            return (Criteria) this;
        }

        public Criteria andRemarksIn(List<String> values) {
            addCriterion("remarks in", values, "remarks");
            return (Criteria) this;
        }

        public Criteria andRemarksNotIn(List<String> values) {
            addCriterion("remarks not in", values, "remarks");
            return (Criteria) this;
        }

        public Criteria andRemarksBetween(String value1, String value2) {
            addCriterion("remarks between", value1, value2, "remarks");
            return (Criteria) this;
        }

        public Criteria andRemarksNotBetween(String value1, String value2) {
            addCriterion("remarks not between", value1, value2, "remarks");
            return (Criteria) this;
        }

        public Criteria andCreateIdIsNull() {
            addCriterion("create_id is null");
            return (Criteria) this;
        }

        public Criteria andCreateIdIsNotNull() {
            addCriterion("create_id is not null");
            return (Criteria) this;
        }

        public Criteria andCreateIdEqualTo(Integer value) {
            addCriterion("create_id =", value, "createId");
            return (Criteria) this;
        }

        public Criteria andCreateIdNotEqualTo(Integer value) {
            addCriterion("create_id <>", value, "createId");
            return (Criteria) this;
        }

        public Criteria andCreateIdGreaterThan(Integer value) {
            addCriterion("create_id >", value, "createId");
            return (Criteria) this;
        }

        public Criteria andCreateIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("create_id >=", value, "createId");
            return (Criteria) this;
        }

        public Criteria andCreateIdLessThan(Integer value) {
            addCriterion("create_id <", value, "createId");
            return (Criteria) this;
        }

        public Criteria andCreateIdLessThanOrEqualTo(Integer value) {
            addCriterion("create_id <=", value, "createId");
            return (Criteria) this;
        }

        public Criteria andCreateIdIn(List<Integer> values) {
            addCriterion("create_id in", values, "createId");
            return (Criteria) this;
        }

        public Criteria andCreateIdNotIn(List<Integer> values) {
            addCriterion("create_id not in", values, "createId");
            return (Criteria) this;
        }

        public Criteria andCreateIdBetween(Integer value1, Integer value2) {
            addCriterion("create_id between", value1, value2, "createId");
            return (Criteria) this;
        }

        public Criteria andCreateIdNotBetween(Integer value1, Integer value2) {
            addCriterion("create_id not between", value1, value2, "createId");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNull() {
            addCriterion("create_time is null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNotNull() {
            addCriterion("create_time is not null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeEqualTo(Date value) {
            addCriterion("create_time =", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotEqualTo(Date value) {
            addCriterion("create_time <>", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThan(Date value) {
            addCriterion("create_time >", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("create_time >=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThan(Date value) {
            addCriterion("create_time <", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThanOrEqualTo(Date value) {
            addCriterion("create_time <=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIn(List<Date> values) {
            addCriterion("create_time in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotIn(List<Date> values) {
            addCriterion("create_time not in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeBetween(Date value1, Date value2) {
            addCriterion("create_time between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotBetween(Date value1, Date value2) {
            addCriterion("create_time not between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andUpdateIdIsNull() {
            addCriterion("update_id is null");
            return (Criteria) this;
        }

        public Criteria andUpdateIdIsNotNull() {
            addCriterion("update_id is not null");
            return (Criteria) this;
        }

        public Criteria andUpdateIdEqualTo(Integer value) {
            addCriterion("update_id =", value, "updateId");
            return (Criteria) this;
        }

        public Criteria andUpdateIdNotEqualTo(Integer value) {
            addCriterion("update_id <>", value, "updateId");
            return (Criteria) this;
        }

        public Criteria andUpdateIdGreaterThan(Integer value) {
            addCriterion("update_id >", value, "updateId");
            return (Criteria) this;
        }

        public Criteria andUpdateIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("update_id >=", value, "updateId");
            return (Criteria) this;
        }

        public Criteria andUpdateIdLessThan(Integer value) {
            addCriterion("update_id <", value, "updateId");
            return (Criteria) this;
        }

        public Criteria andUpdateIdLessThanOrEqualTo(Integer value) {
            addCriterion("update_id <=", value, "updateId");
            return (Criteria) this;
        }

        public Criteria andUpdateIdIn(List<Integer> values) {
            addCriterion("update_id in", values, "updateId");
            return (Criteria) this;
        }

        public Criteria andUpdateIdNotIn(List<Integer> values) {
            addCriterion("update_id not in", values, "updateId");
            return (Criteria) this;
        }

        public Criteria andUpdateIdBetween(Integer value1, Integer value2) {
            addCriterion("update_id between", value1, value2, "updateId");
            return (Criteria) this;
        }

        public Criteria andUpdateIdNotBetween(Integer value1, Integer value2) {
            addCriterion("update_id not between", value1, value2, "updateId");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIsNull() {
            addCriterion("update_time is null");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIsNotNull() {
            addCriterion("update_time is not null");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeEqualTo(Date value) {
            addCriterion("update_time =", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotEqualTo(Date value) {
            addCriterion("update_time <>", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeGreaterThan(Date value) {
            addCriterion("update_time >", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("update_time >=", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLessThan(Date value) {
            addCriterion("update_time <", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLessThanOrEqualTo(Date value) {
            addCriterion("update_time <=", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIn(List<Date> values) {
            addCriterion("update_time in", values, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotIn(List<Date> values) {
            addCriterion("update_time not in", values, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeBetween(Date value1, Date value2) {
            addCriterion("update_time between", value1, value2, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotBetween(Date value1, Date value2) {
            addCriterion("update_time not between", value1, value2, "updateTime");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}