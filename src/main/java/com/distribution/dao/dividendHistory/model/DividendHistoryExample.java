package com.distribution.dao.dividendHistory.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DividendHistoryExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public DividendHistoryExample() {
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

        public Criteria andDividendIdIsNull() {
            addCriterion("dividend_id is null");
            return (Criteria) this;
        }

        public Criteria andDividendIdIsNotNull() {
            addCriterion("dividend_id is not null");
            return (Criteria) this;
        }

        public Criteria andDividendIdEqualTo(Integer value) {
            addCriterion("dividend_id =", value, "dividendId");
            return (Criteria) this;
        }

        public Criteria andDividendIdNotEqualTo(Integer value) {
            addCriterion("dividend_id <>", value, "dividendId");
            return (Criteria) this;
        }

        public Criteria andDividendIdGreaterThan(Integer value) {
            addCriterion("dividend_id >", value, "dividendId");
            return (Criteria) this;
        }

        public Criteria andDividendIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("dividend_id >=", value, "dividendId");
            return (Criteria) this;
        }

        public Criteria andDividendIdLessThan(Integer value) {
            addCriterion("dividend_id <", value, "dividendId");
            return (Criteria) this;
        }

        public Criteria andDividendIdLessThanOrEqualTo(Integer value) {
            addCriterion("dividend_id <=", value, "dividendId");
            return (Criteria) this;
        }

        public Criteria andDividendIdIn(List<Integer> values) {
            addCriterion("dividend_id in", values, "dividendId");
            return (Criteria) this;
        }

        public Criteria andDividendIdNotIn(List<Integer> values) {
            addCriterion("dividend_id not in", values, "dividendId");
            return (Criteria) this;
        }

        public Criteria andDividendIdBetween(Integer value1, Integer value2) {
            addCriterion("dividend_id between", value1, value2, "dividendId");
            return (Criteria) this;
        }

        public Criteria andDividendIdNotBetween(Integer value1, Integer value2) {
            addCriterion("dividend_id not between", value1, value2, "dividendId");
            return (Criteria) this;
        }

        public Criteria andReceivedTimeIsNull() {
            addCriterion("received_time is null");
            return (Criteria) this;
        }

        public Criteria andReceivedTimeIsNotNull() {
            addCriterion("received_time is not null");
            return (Criteria) this;
        }

        public Criteria andReceivedTimeEqualTo(Date value) {
            addCriterion("received_time =", value, "receivedTime");
            return (Criteria) this;
        }

        public Criteria andReceivedTimeNotEqualTo(Date value) {
            addCriterion("received_time <>", value, "receivedTime");
            return (Criteria) this;
        }

        public Criteria andReceivedTimeGreaterThan(Date value) {
            addCriterion("received_time >", value, "receivedTime");
            return (Criteria) this;
        }

        public Criteria andReceivedTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("received_time >=", value, "receivedTime");
            return (Criteria) this;
        }

        public Criteria andReceivedTimeLessThan(Date value) {
            addCriterion("received_time <", value, "receivedTime");
            return (Criteria) this;
        }

        public Criteria andReceivedTimeLessThanOrEqualTo(Date value) {
            addCriterion("received_time <=", value, "receivedTime");
            return (Criteria) this;
        }

        public Criteria andReceivedTimeIn(List<Date> values) {
            addCriterion("received_time in", values, "receivedTime");
            return (Criteria) this;
        }

        public Criteria andReceivedTimeNotIn(List<Date> values) {
            addCriterion("received_time not in", values, "receivedTime");
            return (Criteria) this;
        }

        public Criteria andReceivedTimeBetween(Date value1, Date value2) {
            addCriterion("received_time between", value1, value2, "receivedTime");
            return (Criteria) this;
        }

        public Criteria andReceivedTimeNotBetween(Date value1, Date value2) {
            addCriterion("received_time not between", value1, value2, "receivedTime");
            return (Criteria) this;
        }

        public Criteria andDevidendCountIsNull() {
            addCriterion("devidend_count is null");
            return (Criteria) this;
        }

        public Criteria andDevidendCountIsNotNull() {
            addCriterion("devidend_count is not null");
            return (Criteria) this;
        }

        public Criteria andDevidendCountEqualTo(Integer value) {
            addCriterion("devidend_count =", value, "devidendCount");
            return (Criteria) this;
        }

        public Criteria andDevidendCountNotEqualTo(Integer value) {
            addCriterion("devidend_count <>", value, "devidendCount");
            return (Criteria) this;
        }

        public Criteria andDevidendCountGreaterThan(Integer value) {
            addCriterion("devidend_count >", value, "devidendCount");
            return (Criteria) this;
        }

        public Criteria andDevidendCountGreaterThanOrEqualTo(Integer value) {
            addCriterion("devidend_count >=", value, "devidendCount");
            return (Criteria) this;
        }

        public Criteria andDevidendCountLessThan(Integer value) {
            addCriterion("devidend_count <", value, "devidendCount");
            return (Criteria) this;
        }

        public Criteria andDevidendCountLessThanOrEqualTo(Integer value) {
            addCriterion("devidend_count <=", value, "devidendCount");
            return (Criteria) this;
        }

        public Criteria andDevidendCountIn(List<Integer> values) {
            addCriterion("devidend_count in", values, "devidendCount");
            return (Criteria) this;
        }

        public Criteria andDevidendCountNotIn(List<Integer> values) {
            addCriterion("devidend_count not in", values, "devidendCount");
            return (Criteria) this;
        }

        public Criteria andDevidendCountBetween(Integer value1, Integer value2) {
            addCriterion("devidend_count between", value1, value2, "devidendCount");
            return (Criteria) this;
        }

        public Criteria andDevidendCountNotBetween(Integer value1, Integer value2) {
            addCriterion("devidend_count not between", value1, value2, "devidendCount");
            return (Criteria) this;
        }

        public Criteria andAmountIsNull() {
            addCriterion("amount is null");
            return (Criteria) this;
        }

        public Criteria andAmountIsNotNull() {
            addCriterion("amount is not null");
            return (Criteria) this;
        }

        public Criteria andAmountEqualTo(BigDecimal value) {
            addCriterion("amount =", value, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountNotEqualTo(BigDecimal value) {
            addCriterion("amount <>", value, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountGreaterThan(BigDecimal value) {
            addCriterion("amount >", value, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("amount >=", value, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountLessThan(BigDecimal value) {
            addCriterion("amount <", value, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountLessThanOrEqualTo(BigDecimal value) {
            addCriterion("amount <=", value, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountIn(List<BigDecimal> values) {
            addCriterion("amount in", values, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountNotIn(List<BigDecimal> values) {
            addCriterion("amount not in", values, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("amount between", value1, value2, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("amount not between", value1, value2, "amount");
            return (Criteria) this;
        }

        public Criteria andTotalReceivedIsNull() {
            addCriterion("total_received is null");
            return (Criteria) this;
        }

        public Criteria andTotalReceivedIsNotNull() {
            addCriterion("total_received is not null");
            return (Criteria) this;
        }

        public Criteria andTotalReceivedEqualTo(BigDecimal value) {
            addCriterion("total_received =", value, "totalReceived");
            return (Criteria) this;
        }

        public Criteria andTotalReceivedNotEqualTo(BigDecimal value) {
            addCriterion("total_received <>", value, "totalReceived");
            return (Criteria) this;
        }

        public Criteria andTotalReceivedGreaterThan(BigDecimal value) {
            addCriterion("total_received >", value, "totalReceived");
            return (Criteria) this;
        }

        public Criteria andTotalReceivedGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("total_received >=", value, "totalReceived");
            return (Criteria) this;
        }

        public Criteria andTotalReceivedLessThan(BigDecimal value) {
            addCriterion("total_received <", value, "totalReceived");
            return (Criteria) this;
        }

        public Criteria andTotalReceivedLessThanOrEqualTo(BigDecimal value) {
            addCriterion("total_received <=", value, "totalReceived");
            return (Criteria) this;
        }

        public Criteria andTotalReceivedIn(List<BigDecimal> values) {
            addCriterion("total_received in", values, "totalReceived");
            return (Criteria) this;
        }

        public Criteria andTotalReceivedNotIn(List<BigDecimal> values) {
            addCriterion("total_received not in", values, "totalReceived");
            return (Criteria) this;
        }

        public Criteria andTotalReceivedBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("total_received between", value1, value2, "totalReceived");
            return (Criteria) this;
        }

        public Criteria andTotalReceivedNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("total_received not between", value1, value2, "totalReceived");
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

        public Criteria andMgmtFeeIsNull() {
            addCriterion("mgmt_fee is null");
            return (Criteria) this;
        }

        public Criteria andMgmtFeeIsNotNull() {
            addCriterion("mgmt_fee is not null");
            return (Criteria) this;
        }

        public Criteria andMgmtFeeEqualTo(BigDecimal value) {
            addCriterion("mgmt_fee =", value, "mgmtFee");
            return (Criteria) this;
        }

        public Criteria andMgmtFeeNotEqualTo(BigDecimal value) {
            addCriterion("mgmt_fee <>", value, "mgmtFee");
            return (Criteria) this;
        }

        public Criteria andMgmtFeeGreaterThan(BigDecimal value) {
            addCriterion("mgmt_fee >", value, "mgmtFee");
            return (Criteria) this;
        }

        public Criteria andMgmtFeeGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("mgmt_fee >=", value, "mgmtFee");
            return (Criteria) this;
        }

        public Criteria andMgmtFeeLessThan(BigDecimal value) {
            addCriterion("mgmt_fee <", value, "mgmtFee");
            return (Criteria) this;
        }

        public Criteria andMgmtFeeLessThanOrEqualTo(BigDecimal value) {
            addCriterion("mgmt_fee <=", value, "mgmtFee");
            return (Criteria) this;
        }

        public Criteria andMgmtFeeIn(List<BigDecimal> values) {
            addCriterion("mgmt_fee in", values, "mgmtFee");
            return (Criteria) this;
        }

        public Criteria andMgmtFeeNotIn(List<BigDecimal> values) {
            addCriterion("mgmt_fee not in", values, "mgmtFee");
            return (Criteria) this;
        }

        public Criteria andMgmtFeeBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("mgmt_fee between", value1, value2, "mgmtFee");
            return (Criteria) this;
        }

        public Criteria andMgmtFeeNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("mgmt_fee not between", value1, value2, "mgmtFee");
            return (Criteria) this;
        }

        public Criteria andBalanceStatusIsNull() {
            addCriterion("balance_status is null");
            return (Criteria) this;
        }

        public Criteria andBalanceStatusIsNotNull() {
            addCriterion("balance_status is not null");
            return (Criteria) this;
        }

        public Criteria andBalanceStatusEqualTo(Integer value) {
            addCriterion("balance_status =", value, "balanceStatus");
            return (Criteria) this;
        }

        public Criteria andBalanceStatusNotEqualTo(Integer value) {
            addCriterion("balance_status <>", value, "balanceStatus");
            return (Criteria) this;
        }

        public Criteria andBalanceStatusGreaterThan(Integer value) {
            addCriterion("balance_status >", value, "balanceStatus");
            return (Criteria) this;
        }

        public Criteria andBalanceStatusGreaterThanOrEqualTo(Integer value) {
            addCriterion("balance_status >=", value, "balanceStatus");
            return (Criteria) this;
        }

        public Criteria andBalanceStatusLessThan(Integer value) {
            addCriterion("balance_status <", value, "balanceStatus");
            return (Criteria) this;
        }

        public Criteria andBalanceStatusLessThanOrEqualTo(Integer value) {
            addCriterion("balance_status <=", value, "balanceStatus");
            return (Criteria) this;
        }

        public Criteria andBalanceStatusIn(List<Integer> values) {
            addCriterion("balance_status in", values, "balanceStatus");
            return (Criteria) this;
        }

        public Criteria andBalanceStatusNotIn(List<Integer> values) {
            addCriterion("balance_status not in", values, "balanceStatus");
            return (Criteria) this;
        }

        public Criteria andBalanceStatusBetween(Integer value1, Integer value2) {
            addCriterion("balance_status between", value1, value2, "balanceStatus");
            return (Criteria) this;
        }

        public Criteria andBalanceStatusNotBetween(Integer value1, Integer value2) {
            addCriterion("balance_status not between", value1, value2, "balanceStatus");
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