package com.distribution.dao.platformAccountHistory.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PlatformAccountHistoryExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public PlatformAccountHistoryExample() {
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

        public Criteria andAccountAmountOldIsNull() {
            addCriterion("account_amount_old is null");
            return (Criteria) this;
        }

        public Criteria andAccountAmountOldIsNotNull() {
            addCriterion("account_amount_old is not null");
            return (Criteria) this;
        }

        public Criteria andAccountAmountOldEqualTo(BigDecimal value) {
            addCriterion("account_amount_old =", value, "accountAmountOld");
            return (Criteria) this;
        }

        public Criteria andAccountAmountOldNotEqualTo(BigDecimal value) {
            addCriterion("account_amount_old <>", value, "accountAmountOld");
            return (Criteria) this;
        }

        public Criteria andAccountAmountOldGreaterThan(BigDecimal value) {
            addCriterion("account_amount_old >", value, "accountAmountOld");
            return (Criteria) this;
        }

        public Criteria andAccountAmountOldGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("account_amount_old >=", value, "accountAmountOld");
            return (Criteria) this;
        }

        public Criteria andAccountAmountOldLessThan(BigDecimal value) {
            addCriterion("account_amount_old <", value, "accountAmountOld");
            return (Criteria) this;
        }

        public Criteria andAccountAmountOldLessThanOrEqualTo(BigDecimal value) {
            addCriterion("account_amount_old <=", value, "accountAmountOld");
            return (Criteria) this;
        }

        public Criteria andAccountAmountOldIn(List<BigDecimal> values) {
            addCriterion("account_amount_old in", values, "accountAmountOld");
            return (Criteria) this;
        }

        public Criteria andAccountAmountOldNotIn(List<BigDecimal> values) {
            addCriterion("account_amount_old not in", values, "accountAmountOld");
            return (Criteria) this;
        }

        public Criteria andAccountAmountOldBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("account_amount_old between", value1, value2, "accountAmountOld");
            return (Criteria) this;
        }

        public Criteria andAccountAmountOldNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("account_amount_old not between", value1, value2, "accountAmountOld");
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

        public Criteria andFlowAmountIsNull() {
            addCriterion("flow_amount is null");
            return (Criteria) this;
        }

        public Criteria andFlowAmountIsNotNull() {
            addCriterion("flow_amount is not null");
            return (Criteria) this;
        }

        public Criteria andFlowAmountEqualTo(BigDecimal value) {
            addCriterion("flow_amount =", value, "flowAmount");
            return (Criteria) this;
        }

        public Criteria andFlowAmountNotEqualTo(BigDecimal value) {
            addCriterion("flow_amount <>", value, "flowAmount");
            return (Criteria) this;
        }

        public Criteria andFlowAmountGreaterThan(BigDecimal value) {
            addCriterion("flow_amount >", value, "flowAmount");
            return (Criteria) this;
        }

        public Criteria andFlowAmountGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("flow_amount >=", value, "flowAmount");
            return (Criteria) this;
        }

        public Criteria andFlowAmountLessThan(BigDecimal value) {
            addCriterion("flow_amount <", value, "flowAmount");
            return (Criteria) this;
        }

        public Criteria andFlowAmountLessThanOrEqualTo(BigDecimal value) {
            addCriterion("flow_amount <=", value, "flowAmount");
            return (Criteria) this;
        }

        public Criteria andFlowAmountIn(List<BigDecimal> values) {
            addCriterion("flow_amount in", values, "flowAmount");
            return (Criteria) this;
        }

        public Criteria andFlowAmountNotIn(List<BigDecimal> values) {
            addCriterion("flow_amount not in", values, "flowAmount");
            return (Criteria) this;
        }

        public Criteria andFlowAmountBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("flow_amount between", value1, value2, "flowAmount");
            return (Criteria) this;
        }

        public Criteria andFlowAmountNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("flow_amount not between", value1, value2, "flowAmount");
            return (Criteria) this;
        }

        public Criteria andAccountAmountNewIsNull() {
            addCriterion("account_amount_new is null");
            return (Criteria) this;
        }

        public Criteria andAccountAmountNewIsNotNull() {
            addCriterion("account_amount_new is not null");
            return (Criteria) this;
        }

        public Criteria andAccountAmountNewEqualTo(BigDecimal value) {
            addCriterion("account_amount_new =", value, "accountAmountNew");
            return (Criteria) this;
        }

        public Criteria andAccountAmountNewNotEqualTo(BigDecimal value) {
            addCriterion("account_amount_new <>", value, "accountAmountNew");
            return (Criteria) this;
        }

        public Criteria andAccountAmountNewGreaterThan(BigDecimal value) {
            addCriterion("account_amount_new >", value, "accountAmountNew");
            return (Criteria) this;
        }

        public Criteria andAccountAmountNewGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("account_amount_new >=", value, "accountAmountNew");
            return (Criteria) this;
        }

        public Criteria andAccountAmountNewLessThan(BigDecimal value) {
            addCriterion("account_amount_new <", value, "accountAmountNew");
            return (Criteria) this;
        }

        public Criteria andAccountAmountNewLessThanOrEqualTo(BigDecimal value) {
            addCriterion("account_amount_new <=", value, "accountAmountNew");
            return (Criteria) this;
        }

        public Criteria andAccountAmountNewIn(List<BigDecimal> values) {
            addCriterion("account_amount_new in", values, "accountAmountNew");
            return (Criteria) this;
        }

        public Criteria andAccountAmountNewNotIn(List<BigDecimal> values) {
            addCriterion("account_amount_new not in", values, "accountAmountNew");
            return (Criteria) this;
        }

        public Criteria andAccountAmountNewBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("account_amount_new between", value1, value2, "accountAmountNew");
            return (Criteria) this;
        }

        public Criteria andAccountAmountNewNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("account_amount_new not between", value1, value2, "accountAmountNew");
            return (Criteria) this;
        }

        public Criteria andCreateDateIsNull() {
            addCriterion("create_date is null");
            return (Criteria) this;
        }

        public Criteria andCreateDateIsNotNull() {
            addCriterion("create_date is not null");
            return (Criteria) this;
        }

        public Criteria andCreateDateEqualTo(Date value) {
            addCriterion("create_date =", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateNotEqualTo(Date value) {
            addCriterion("create_date <>", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateGreaterThan(Date value) {
            addCriterion("create_date >", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateGreaterThanOrEqualTo(Date value) {
            addCriterion("create_date >=", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateLessThan(Date value) {
            addCriterion("create_date <", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateLessThanOrEqualTo(Date value) {
            addCriterion("create_date <=", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateIn(List<Date> values) {
            addCriterion("create_date in", values, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateNotIn(List<Date> values) {
            addCriterion("create_date not in", values, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateBetween(Date value1, Date value2) {
            addCriterion("create_date between", value1, value2, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateNotBetween(Date value1, Date value2) {
            addCriterion("create_date not between", value1, value2, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateByIsNull() {
            addCriterion("create_by is null");
            return (Criteria) this;
        }

        public Criteria andCreateByIsNotNull() {
            addCriterion("create_by is not null");
            return (Criteria) this;
        }

        public Criteria andCreateByEqualTo(String value) {
            addCriterion("create_by =", value, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByNotEqualTo(String value) {
            addCriterion("create_by <>", value, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByGreaterThan(String value) {
            addCriterion("create_by >", value, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByGreaterThanOrEqualTo(String value) {
            addCriterion("create_by >=", value, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByLessThan(String value) {
            addCriterion("create_by <", value, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByLessThanOrEqualTo(String value) {
            addCriterion("create_by <=", value, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByLike(String value) {
            addCriterion("create_by like", value, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByNotLike(String value) {
            addCriterion("create_by not like", value, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByIn(List<String> values) {
            addCriterion("create_by in", values, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByNotIn(List<String> values) {
            addCriterion("create_by not in", values, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByBetween(String value1, String value2) {
            addCriterion("create_by between", value1, value2, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByNotBetween(String value1, String value2) {
            addCriterion("create_by not between", value1, value2, "createBy");
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