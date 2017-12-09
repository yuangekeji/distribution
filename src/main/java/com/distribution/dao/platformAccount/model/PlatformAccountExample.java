package com.distribution.dao.platformAccount.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PlatformAccountExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public PlatformAccountExample() {
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

        public Criteria andTotalSalesIsNull() {
            addCriterion("total_sales is null");
            return (Criteria) this;
        }

        public Criteria andTotalSalesIsNotNull() {
            addCriterion("total_sales is not null");
            return (Criteria) this;
        }

        public Criteria andTotalSalesEqualTo(BigDecimal value) {
            addCriterion("total_sales =", value, "totalSales");
            return (Criteria) this;
        }

        public Criteria andTotalSalesNotEqualTo(BigDecimal value) {
            addCriterion("total_sales <>", value, "totalSales");
            return (Criteria) this;
        }

        public Criteria andTotalSalesGreaterThan(BigDecimal value) {
            addCriterion("total_sales >", value, "totalSales");
            return (Criteria) this;
        }

        public Criteria andTotalSalesGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("total_sales >=", value, "totalSales");
            return (Criteria) this;
        }

        public Criteria andTotalSalesLessThan(BigDecimal value) {
            addCriterion("total_sales <", value, "totalSales");
            return (Criteria) this;
        }

        public Criteria andTotalSalesLessThanOrEqualTo(BigDecimal value) {
            addCriterion("total_sales <=", value, "totalSales");
            return (Criteria) this;
        }

        public Criteria andTotalSalesIn(List<BigDecimal> values) {
            addCriterion("total_sales in", values, "totalSales");
            return (Criteria) this;
        }

        public Criteria andTotalSalesNotIn(List<BigDecimal> values) {
            addCriterion("total_sales not in", values, "totalSales");
            return (Criteria) this;
        }

        public Criteria andTotalSalesBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("total_sales between", value1, value2, "totalSales");
            return (Criteria) this;
        }

        public Criteria andTotalSalesNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("total_sales not between", value1, value2, "totalSales");
            return (Criteria) this;
        }

        public Criteria andTotalBonusIsNull() {
            addCriterion("total_bonus is null");
            return (Criteria) this;
        }

        public Criteria andTotalBonusIsNotNull() {
            addCriterion("total_bonus is not null");
            return (Criteria) this;
        }

        public Criteria andTotalBonusEqualTo(BigDecimal value) {
            addCriterion("total_bonus =", value, "totalBonus");
            return (Criteria) this;
        }

        public Criteria andTotalBonusNotEqualTo(BigDecimal value) {
            addCriterion("total_bonus <>", value, "totalBonus");
            return (Criteria) this;
        }

        public Criteria andTotalBonusGreaterThan(BigDecimal value) {
            addCriterion("total_bonus >", value, "totalBonus");
            return (Criteria) this;
        }

        public Criteria andTotalBonusGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("total_bonus >=", value, "totalBonus");
            return (Criteria) this;
        }

        public Criteria andTotalBonusLessThan(BigDecimal value) {
            addCriterion("total_bonus <", value, "totalBonus");
            return (Criteria) this;
        }

        public Criteria andTotalBonusLessThanOrEqualTo(BigDecimal value) {
            addCriterion("total_bonus <=", value, "totalBonus");
            return (Criteria) this;
        }

        public Criteria andTotalBonusIn(List<BigDecimal> values) {
            addCriterion("total_bonus in", values, "totalBonus");
            return (Criteria) this;
        }

        public Criteria andTotalBonusNotIn(List<BigDecimal> values) {
            addCriterion("total_bonus not in", values, "totalBonus");
            return (Criteria) this;
        }

        public Criteria andTotalBonusBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("total_bonus between", value1, value2, "totalBonus");
            return (Criteria) this;
        }

        public Criteria andTotalBonusNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("total_bonus not between", value1, value2, "totalBonus");
            return (Criteria) this;
        }

        public Criteria andPlatformAmountIsNull() {
            addCriterion("platform_amount is null");
            return (Criteria) this;
        }

        public Criteria andPlatformAmountIsNotNull() {
            addCriterion("platform_amount is not null");
            return (Criteria) this;
        }

        public Criteria andPlatformAmountEqualTo(BigDecimal value) {
            addCriterion("platform_amount =", value, "platformAmount");
            return (Criteria) this;
        }

        public Criteria andPlatformAmountNotEqualTo(BigDecimal value) {
            addCriterion("platform_amount <>", value, "platformAmount");
            return (Criteria) this;
        }

        public Criteria andPlatformAmountGreaterThan(BigDecimal value) {
            addCriterion("platform_amount >", value, "platformAmount");
            return (Criteria) this;
        }

        public Criteria andPlatformAmountGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("platform_amount >=", value, "platformAmount");
            return (Criteria) this;
        }

        public Criteria andPlatformAmountLessThan(BigDecimal value) {
            addCriterion("platform_amount <", value, "platformAmount");
            return (Criteria) this;
        }

        public Criteria andPlatformAmountLessThanOrEqualTo(BigDecimal value) {
            addCriterion("platform_amount <=", value, "platformAmount");
            return (Criteria) this;
        }

        public Criteria andPlatformAmountIn(List<BigDecimal> values) {
            addCriterion("platform_amount in", values, "platformAmount");
            return (Criteria) this;
        }

        public Criteria andPlatformAmountNotIn(List<BigDecimal> values) {
            addCriterion("platform_amount not in", values, "platformAmount");
            return (Criteria) this;
        }

        public Criteria andPlatformAmountBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("platform_amount between", value1, value2, "platformAmount");
            return (Criteria) this;
        }

        public Criteria andPlatformAmountNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("platform_amount not between", value1, value2, "platformAmount");
            return (Criteria) this;
        }

        public Criteria andAccountAmountIsNull() {
            addCriterion("account_amount is null");
            return (Criteria) this;
        }

        public Criteria andAccountAmountIsNotNull() {
            addCriterion("account_amount is not null");
            return (Criteria) this;
        }

        public Criteria andAccountAmountEqualTo(BigDecimal value) {
            addCriterion("account_amount =", value, "accountAmount");
            return (Criteria) this;
        }

        public Criteria andAccountAmountNotEqualTo(BigDecimal value) {
            addCriterion("account_amount <>", value, "accountAmount");
            return (Criteria) this;
        }

        public Criteria andAccountAmountGreaterThan(BigDecimal value) {
            addCriterion("account_amount >", value, "accountAmount");
            return (Criteria) this;
        }

        public Criteria andAccountAmountGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("account_amount >=", value, "accountAmount");
            return (Criteria) this;
        }

        public Criteria andAccountAmountLessThan(BigDecimal value) {
            addCriterion("account_amount <", value, "accountAmount");
            return (Criteria) this;
        }

        public Criteria andAccountAmountLessThanOrEqualTo(BigDecimal value) {
            addCriterion("account_amount <=", value, "accountAmount");
            return (Criteria) this;
        }

        public Criteria andAccountAmountIn(List<BigDecimal> values) {
            addCriterion("account_amount in", values, "accountAmount");
            return (Criteria) this;
        }

        public Criteria andAccountAmountNotIn(List<BigDecimal> values) {
            addCriterion("account_amount not in", values, "accountAmount");
            return (Criteria) this;
        }

        public Criteria andAccountAmountBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("account_amount between", value1, value2, "accountAmount");
            return (Criteria) this;
        }

        public Criteria andAccountAmountNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("account_amount not between", value1, value2, "accountAmount");
            return (Criteria) this;
        }

        public Criteria andPoolAmountIsNull() {
            addCriterion("pool_amount is null");
            return (Criteria) this;
        }

        public Criteria andPoolAmountIsNotNull() {
            addCriterion("pool_amount is not null");
            return (Criteria) this;
        }

        public Criteria andPoolAmountEqualTo(BigDecimal value) {
            addCriterion("pool_amount =", value, "poolAmount");
            return (Criteria) this;
        }

        public Criteria andPoolAmountNotEqualTo(BigDecimal value) {
            addCriterion("pool_amount <>", value, "poolAmount");
            return (Criteria) this;
        }

        public Criteria andPoolAmountGreaterThan(BigDecimal value) {
            addCriterion("pool_amount >", value, "poolAmount");
            return (Criteria) this;
        }

        public Criteria andPoolAmountGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("pool_amount >=", value, "poolAmount");
            return (Criteria) this;
        }

        public Criteria andPoolAmountLessThan(BigDecimal value) {
            addCriterion("pool_amount <", value, "poolAmount");
            return (Criteria) this;
        }

        public Criteria andPoolAmountLessThanOrEqualTo(BigDecimal value) {
            addCriterion("pool_amount <=", value, "poolAmount");
            return (Criteria) this;
        }

        public Criteria andPoolAmountIn(List<BigDecimal> values) {
            addCriterion("pool_amount in", values, "poolAmount");
            return (Criteria) this;
        }

        public Criteria andPoolAmountNotIn(List<BigDecimal> values) {
            addCriterion("pool_amount not in", values, "poolAmount");
            return (Criteria) this;
        }

        public Criteria andPoolAmountBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("pool_amount between", value1, value2, "poolAmount");
            return (Criteria) this;
        }

        public Criteria andPoolAmountNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("pool_amount not between", value1, value2, "poolAmount");
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

        public Criteria andUpdateByIsNull() {
            addCriterion("update_by is null");
            return (Criteria) this;
        }

        public Criteria andUpdateByIsNotNull() {
            addCriterion("update_by is not null");
            return (Criteria) this;
        }

        public Criteria andUpdateByEqualTo(String value) {
            addCriterion("update_by =", value, "updateBy");
            return (Criteria) this;
        }

        public Criteria andUpdateByNotEqualTo(String value) {
            addCriterion("update_by <>", value, "updateBy");
            return (Criteria) this;
        }

        public Criteria andUpdateByGreaterThan(String value) {
            addCriterion("update_by >", value, "updateBy");
            return (Criteria) this;
        }

        public Criteria andUpdateByGreaterThanOrEqualTo(String value) {
            addCriterion("update_by >=", value, "updateBy");
            return (Criteria) this;
        }

        public Criteria andUpdateByLessThan(String value) {
            addCriterion("update_by <", value, "updateBy");
            return (Criteria) this;
        }

        public Criteria andUpdateByLessThanOrEqualTo(String value) {
            addCriterion("update_by <=", value, "updateBy");
            return (Criteria) this;
        }

        public Criteria andUpdateByLike(String value) {
            addCriterion("update_by like", value, "updateBy");
            return (Criteria) this;
        }

        public Criteria andUpdateByNotLike(String value) {
            addCriterion("update_by not like", value, "updateBy");
            return (Criteria) this;
        }

        public Criteria andUpdateByIn(List<String> values) {
            addCriterion("update_by in", values, "updateBy");
            return (Criteria) this;
        }

        public Criteria andUpdateByNotIn(List<String> values) {
            addCriterion("update_by not in", values, "updateBy");
            return (Criteria) this;
        }

        public Criteria andUpdateByBetween(String value1, String value2) {
            addCriterion("update_by between", value1, value2, "updateBy");
            return (Criteria) this;
        }

        public Criteria andUpdateByNotBetween(String value1, String value2) {
            addCriterion("update_by not between", value1, value2, "updateBy");
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