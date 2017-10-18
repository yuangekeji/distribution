package com.distribution.dao.dateBonusHistory.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DateBonusHistoryExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public DateBonusHistoryExample() {
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

        public Criteria andDateIsNull() {
            addCriterion("date is null");
            return (Criteria) this;
        }

        public Criteria andDateIsNotNull() {
            addCriterion("date is not null");
            return (Criteria) this;
        }

        public Criteria andDateEqualTo(Date value) {
            addCriterion("date =", value, "date");
            return (Criteria) this;
        }

        public Criteria andDateNotEqualTo(Date value) {
            addCriterion("date <>", value, "date");
            return (Criteria) this;
        }

        public Criteria andDateGreaterThan(Date value) {
            addCriterion("date >", value, "date");
            return (Criteria) this;
        }

        public Criteria andDateGreaterThanOrEqualTo(Date value) {
            addCriterion("date >=", value, "date");
            return (Criteria) this;
        }

        public Criteria andDateLessThan(Date value) {
            addCriterion("date <", value, "date");
            return (Criteria) this;
        }

        public Criteria andDateLessThanOrEqualTo(Date value) {
            addCriterion("date <=", value, "date");
            return (Criteria) this;
        }

        public Criteria andDateIn(List<Date> values) {
            addCriterion("date in", values, "date");
            return (Criteria) this;
        }

        public Criteria andDateNotIn(List<Date> values) {
            addCriterion("date not in", values, "date");
            return (Criteria) this;
        }

        public Criteria andDateBetween(Date value1, Date value2) {
            addCriterion("date between", value1, value2, "date");
            return (Criteria) this;
        }

        public Criteria andDateNotBetween(Date value1, Date value2) {
            addCriterion("date not between", value1, value2, "date");
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

        public Criteria andDividendTotalIsNull() {
            addCriterion("dividend_total is null");
            return (Criteria) this;
        }

        public Criteria andDividendTotalIsNotNull() {
            addCriterion("dividend_total is not null");
            return (Criteria) this;
        }

        public Criteria andDividendTotalEqualTo(BigDecimal value) {
            addCriterion("dividend_total =", value, "dividendTotal");
            return (Criteria) this;
        }

        public Criteria andDividendTotalNotEqualTo(BigDecimal value) {
            addCriterion("dividend_total <>", value, "dividendTotal");
            return (Criteria) this;
        }

        public Criteria andDividendTotalGreaterThan(BigDecimal value) {
            addCriterion("dividend_total >", value, "dividendTotal");
            return (Criteria) this;
        }

        public Criteria andDividendTotalGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("dividend_total >=", value, "dividendTotal");
            return (Criteria) this;
        }

        public Criteria andDividendTotalLessThan(BigDecimal value) {
            addCriterion("dividend_total <", value, "dividendTotal");
            return (Criteria) this;
        }

        public Criteria andDividendTotalLessThanOrEqualTo(BigDecimal value) {
            addCriterion("dividend_total <=", value, "dividendTotal");
            return (Criteria) this;
        }

        public Criteria andDividendTotalIn(List<BigDecimal> values) {
            addCriterion("dividend_total in", values, "dividendTotal");
            return (Criteria) this;
        }

        public Criteria andDividendTotalNotIn(List<BigDecimal> values) {
            addCriterion("dividend_total not in", values, "dividendTotal");
            return (Criteria) this;
        }

        public Criteria andDividendTotalBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("dividend_total between", value1, value2, "dividendTotal");
            return (Criteria) this;
        }

        public Criteria andDividendTotalNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("dividend_total not between", value1, value2, "dividendTotal");
            return (Criteria) this;
        }

        public Criteria andJdBonusTotalIsNull() {
            addCriterion("jd_bonus_total is null");
            return (Criteria) this;
        }

        public Criteria andJdBonusTotalIsNotNull() {
            addCriterion("jd_bonus_total is not null");
            return (Criteria) this;
        }

        public Criteria andJdBonusTotalEqualTo(BigDecimal value) {
            addCriterion("jd_bonus_total =", value, "jdBonusTotal");
            return (Criteria) this;
        }

        public Criteria andJdBonusTotalNotEqualTo(BigDecimal value) {
            addCriterion("jd_bonus_total <>", value, "jdBonusTotal");
            return (Criteria) this;
        }

        public Criteria andJdBonusTotalGreaterThan(BigDecimal value) {
            addCriterion("jd_bonus_total >", value, "jdBonusTotal");
            return (Criteria) this;
        }

        public Criteria andJdBonusTotalGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("jd_bonus_total >=", value, "jdBonusTotal");
            return (Criteria) this;
        }

        public Criteria andJdBonusTotalLessThan(BigDecimal value) {
            addCriterion("jd_bonus_total <", value, "jdBonusTotal");
            return (Criteria) this;
        }

        public Criteria andJdBonusTotalLessThanOrEqualTo(BigDecimal value) {
            addCriterion("jd_bonus_total <=", value, "jdBonusTotal");
            return (Criteria) this;
        }

        public Criteria andJdBonusTotalIn(List<BigDecimal> values) {
            addCriterion("jd_bonus_total in", values, "jdBonusTotal");
            return (Criteria) this;
        }

        public Criteria andJdBonusTotalNotIn(List<BigDecimal> values) {
            addCriterion("jd_bonus_total not in", values, "jdBonusTotal");
            return (Criteria) this;
        }

        public Criteria andJdBonusTotalBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("jd_bonus_total between", value1, value2, "jdBonusTotal");
            return (Criteria) this;
        }

        public Criteria andJdBonusTotalNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("jd_bonus_total not between", value1, value2, "jdBonusTotal");
            return (Criteria) this;
        }

        public Criteria andUseDividendTotalIsNull() {
            addCriterion("use_dividend_total is null");
            return (Criteria) this;
        }

        public Criteria andUseDividendTotalIsNotNull() {
            addCriterion("use_dividend_total is not null");
            return (Criteria) this;
        }

        public Criteria andUseDividendTotalEqualTo(BigDecimal value) {
            addCriterion("use_dividend_total =", value, "useDividendTotal");
            return (Criteria) this;
        }

        public Criteria andUseDividendTotalNotEqualTo(BigDecimal value) {
            addCriterion("use_dividend_total <>", value, "useDividendTotal");
            return (Criteria) this;
        }

        public Criteria andUseDividendTotalGreaterThan(BigDecimal value) {
            addCriterion("use_dividend_total >", value, "useDividendTotal");
            return (Criteria) this;
        }

        public Criteria andUseDividendTotalGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("use_dividend_total >=", value, "useDividendTotal");
            return (Criteria) this;
        }

        public Criteria andUseDividendTotalLessThan(BigDecimal value) {
            addCriterion("use_dividend_total <", value, "useDividendTotal");
            return (Criteria) this;
        }

        public Criteria andUseDividendTotalLessThanOrEqualTo(BigDecimal value) {
            addCriterion("use_dividend_total <=", value, "useDividendTotal");
            return (Criteria) this;
        }

        public Criteria andUseDividendTotalIn(List<BigDecimal> values) {
            addCriterion("use_dividend_total in", values, "useDividendTotal");
            return (Criteria) this;
        }

        public Criteria andUseDividendTotalNotIn(List<BigDecimal> values) {
            addCriterion("use_dividend_total not in", values, "useDividendTotal");
            return (Criteria) this;
        }

        public Criteria andUseDividendTotalBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("use_dividend_total between", value1, value2, "useDividendTotal");
            return (Criteria) this;
        }

        public Criteria andUseDividendTotalNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("use_dividend_total not between", value1, value2, "useDividendTotal");
            return (Criteria) this;
        }

        public Criteria andUseJdBonusTotalIsNull() {
            addCriterion("use_jd_bonus_total is null");
            return (Criteria) this;
        }

        public Criteria andUseJdBonusTotalIsNotNull() {
            addCriterion("use_jd_bonus_total is not null");
            return (Criteria) this;
        }

        public Criteria andUseJdBonusTotalEqualTo(Long value) {
            addCriterion("use_jd_bonus_total =", value, "useJdBonusTotal");
            return (Criteria) this;
        }

        public Criteria andUseJdBonusTotalNotEqualTo(Long value) {
            addCriterion("use_jd_bonus_total <>", value, "useJdBonusTotal");
            return (Criteria) this;
        }

        public Criteria andUseJdBonusTotalGreaterThan(Long value) {
            addCriterion("use_jd_bonus_total >", value, "useJdBonusTotal");
            return (Criteria) this;
        }

        public Criteria andUseJdBonusTotalGreaterThanOrEqualTo(Long value) {
            addCriterion("use_jd_bonus_total >=", value, "useJdBonusTotal");
            return (Criteria) this;
        }

        public Criteria andUseJdBonusTotalLessThan(Long value) {
            addCriterion("use_jd_bonus_total <", value, "useJdBonusTotal");
            return (Criteria) this;
        }

        public Criteria andUseJdBonusTotalLessThanOrEqualTo(Long value) {
            addCriterion("use_jd_bonus_total <=", value, "useJdBonusTotal");
            return (Criteria) this;
        }

        public Criteria andUseJdBonusTotalIn(List<Long> values) {
            addCriterion("use_jd_bonus_total in", values, "useJdBonusTotal");
            return (Criteria) this;
        }

        public Criteria andUseJdBonusTotalNotIn(List<Long> values) {
            addCriterion("use_jd_bonus_total not in", values, "useJdBonusTotal");
            return (Criteria) this;
        }

        public Criteria andUseJdBonusTotalBetween(Long value1, Long value2) {
            addCriterion("use_jd_bonus_total between", value1, value2, "useJdBonusTotal");
            return (Criteria) this;
        }

        public Criteria andUseJdBonusTotalNotBetween(Long value1, Long value2) {
            addCriterion("use_jd_bonus_total not between", value1, value2, "useJdBonusTotal");
            return (Criteria) this;
        }

        public Criteria andRemainDividendIsNull() {
            addCriterion("remain_dividend is null");
            return (Criteria) this;
        }

        public Criteria andRemainDividendIsNotNull() {
            addCriterion("remain_dividend is not null");
            return (Criteria) this;
        }

        public Criteria andRemainDividendEqualTo(Long value) {
            addCriterion("remain_dividend =", value, "remainDividend");
            return (Criteria) this;
        }

        public Criteria andRemainDividendNotEqualTo(Long value) {
            addCriterion("remain_dividend <>", value, "remainDividend");
            return (Criteria) this;
        }

        public Criteria andRemainDividendGreaterThan(Long value) {
            addCriterion("remain_dividend >", value, "remainDividend");
            return (Criteria) this;
        }

        public Criteria andRemainDividendGreaterThanOrEqualTo(Long value) {
            addCriterion("remain_dividend >=", value, "remainDividend");
            return (Criteria) this;
        }

        public Criteria andRemainDividendLessThan(Long value) {
            addCriterion("remain_dividend <", value, "remainDividend");
            return (Criteria) this;
        }

        public Criteria andRemainDividendLessThanOrEqualTo(Long value) {
            addCriterion("remain_dividend <=", value, "remainDividend");
            return (Criteria) this;
        }

        public Criteria andRemainDividendIn(List<Long> values) {
            addCriterion("remain_dividend in", values, "remainDividend");
            return (Criteria) this;
        }

        public Criteria andRemainDividendNotIn(List<Long> values) {
            addCriterion("remain_dividend not in", values, "remainDividend");
            return (Criteria) this;
        }

        public Criteria andRemainDividendBetween(Long value1, Long value2) {
            addCriterion("remain_dividend between", value1, value2, "remainDividend");
            return (Criteria) this;
        }

        public Criteria andRemainDividendNotBetween(Long value1, Long value2) {
            addCriterion("remain_dividend not between", value1, value2, "remainDividend");
            return (Criteria) this;
        }

        public Criteria andRemainJdBonusIsNull() {
            addCriterion("remain_jd_bonus is null");
            return (Criteria) this;
        }

        public Criteria andRemainJdBonusIsNotNull() {
            addCriterion("remain_jd_bonus is not null");
            return (Criteria) this;
        }

        public Criteria andRemainJdBonusEqualTo(Long value) {
            addCriterion("remain_jd_bonus =", value, "remainJdBonus");
            return (Criteria) this;
        }

        public Criteria andRemainJdBonusNotEqualTo(Long value) {
            addCriterion("remain_jd_bonus <>", value, "remainJdBonus");
            return (Criteria) this;
        }

        public Criteria andRemainJdBonusGreaterThan(Long value) {
            addCriterion("remain_jd_bonus >", value, "remainJdBonus");
            return (Criteria) this;
        }

        public Criteria andRemainJdBonusGreaterThanOrEqualTo(Long value) {
            addCriterion("remain_jd_bonus >=", value, "remainJdBonus");
            return (Criteria) this;
        }

        public Criteria andRemainJdBonusLessThan(Long value) {
            addCriterion("remain_jd_bonus <", value, "remainJdBonus");
            return (Criteria) this;
        }

        public Criteria andRemainJdBonusLessThanOrEqualTo(Long value) {
            addCriterion("remain_jd_bonus <=", value, "remainJdBonus");
            return (Criteria) this;
        }

        public Criteria andRemainJdBonusIn(List<Long> values) {
            addCriterion("remain_jd_bonus in", values, "remainJdBonus");
            return (Criteria) this;
        }

        public Criteria andRemainJdBonusNotIn(List<Long> values) {
            addCriterion("remain_jd_bonus not in", values, "remainJdBonus");
            return (Criteria) this;
        }

        public Criteria andRemainJdBonusBetween(Long value1, Long value2) {
            addCriterion("remain_jd_bonus between", value1, value2, "remainJdBonus");
            return (Criteria) this;
        }

        public Criteria andRemainJdBonusNotBetween(Long value1, Long value2) {
            addCriterion("remain_jd_bonus not between", value1, value2, "remainJdBonus");
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

        public Criteria andAlarmStatusIsNull() {
            addCriterion("alarm_status is null");
            return (Criteria) this;
        }

        public Criteria andAlarmStatusIsNotNull() {
            addCriterion("alarm_status is not null");
            return (Criteria) this;
        }

        public Criteria andAlarmStatusEqualTo(Integer value) {
            addCriterion("alarm_status =", value, "alarmStatus");
            return (Criteria) this;
        }

        public Criteria andAlarmStatusNotEqualTo(Integer value) {
            addCriterion("alarm_status <>", value, "alarmStatus");
            return (Criteria) this;
        }

        public Criteria andAlarmStatusGreaterThan(Integer value) {
            addCriterion("alarm_status >", value, "alarmStatus");
            return (Criteria) this;
        }

        public Criteria andAlarmStatusGreaterThanOrEqualTo(Integer value) {
            addCriterion("alarm_status >=", value, "alarmStatus");
            return (Criteria) this;
        }

        public Criteria andAlarmStatusLessThan(Integer value) {
            addCriterion("alarm_status <", value, "alarmStatus");
            return (Criteria) this;
        }

        public Criteria andAlarmStatusLessThanOrEqualTo(Integer value) {
            addCriterion("alarm_status <=", value, "alarmStatus");
            return (Criteria) this;
        }

        public Criteria andAlarmStatusIn(List<Integer> values) {
            addCriterion("alarm_status in", values, "alarmStatus");
            return (Criteria) this;
        }

        public Criteria andAlarmStatusNotIn(List<Integer> values) {
            addCriterion("alarm_status not in", values, "alarmStatus");
            return (Criteria) this;
        }

        public Criteria andAlarmStatusBetween(Integer value1, Integer value2) {
            addCriterion("alarm_status between", value1, value2, "alarmStatus");
            return (Criteria) this;
        }

        public Criteria andAlarmStatusNotBetween(Integer value1, Integer value2) {
            addCriterion("alarm_status not between", value1, value2, "alarmStatus");
            return (Criteria) this;
        }

        public Criteria andJdAlarmStatusIsNull() {
            addCriterion("jd_alarm_status is null");
            return (Criteria) this;
        }

        public Criteria andJdAlarmStatusIsNotNull() {
            addCriterion("jd_alarm_status is not null");
            return (Criteria) this;
        }

        public Criteria andJdAlarmStatusEqualTo(Integer value) {
            addCriterion("jd_alarm_status =", value, "jdAlarmStatus");
            return (Criteria) this;
        }

        public Criteria andJdAlarmStatusNotEqualTo(Integer value) {
            addCriterion("jd_alarm_status <>", value, "jdAlarmStatus");
            return (Criteria) this;
        }

        public Criteria andJdAlarmStatusGreaterThan(Integer value) {
            addCriterion("jd_alarm_status >", value, "jdAlarmStatus");
            return (Criteria) this;
        }

        public Criteria andJdAlarmStatusGreaterThanOrEqualTo(Integer value) {
            addCriterion("jd_alarm_status >=", value, "jdAlarmStatus");
            return (Criteria) this;
        }

        public Criteria andJdAlarmStatusLessThan(Integer value) {
            addCriterion("jd_alarm_status <", value, "jdAlarmStatus");
            return (Criteria) this;
        }

        public Criteria andJdAlarmStatusLessThanOrEqualTo(Integer value) {
            addCriterion("jd_alarm_status <=", value, "jdAlarmStatus");
            return (Criteria) this;
        }

        public Criteria andJdAlarmStatusIn(List<Integer> values) {
            addCriterion("jd_alarm_status in", values, "jdAlarmStatus");
            return (Criteria) this;
        }

        public Criteria andJdAlarmStatusNotIn(List<Integer> values) {
            addCriterion("jd_alarm_status not in", values, "jdAlarmStatus");
            return (Criteria) this;
        }

        public Criteria andJdAlarmStatusBetween(Integer value1, Integer value2) {
            addCriterion("jd_alarm_status between", value1, value2, "jdAlarmStatus");
            return (Criteria) this;
        }

        public Criteria andJdAlarmStatusNotBetween(Integer value1, Integer value2) {
            addCriterion("jd_alarm_status not between", value1, value2, "jdAlarmStatus");
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