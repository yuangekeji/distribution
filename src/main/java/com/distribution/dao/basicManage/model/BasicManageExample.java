package com.distribution.dao.basicManage.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class BasicManageExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public BasicManageExample() {
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

        public Criteria andDetailCodeIsNull() {
            addCriterion("detail_code is null");
            return (Criteria) this;
        }

        public Criteria andDetailCodeIsNotNull() {
            addCriterion("detail_code is not null");
            return (Criteria) this;
        }

        public Criteria andDetailCodeEqualTo(String value) {
            addCriterion("detail_code =", value, "detailCode");
            return (Criteria) this;
        }

        public Criteria andDetailCodeNotEqualTo(String value) {
            addCriterion("detail_code <>", value, "detailCode");
            return (Criteria) this;
        }

        public Criteria andDetailCodeGreaterThan(String value) {
            addCriterion("detail_code >", value, "detailCode");
            return (Criteria) this;
        }

        public Criteria andDetailCodeGreaterThanOrEqualTo(String value) {
            addCriterion("detail_code >=", value, "detailCode");
            return (Criteria) this;
        }

        public Criteria andDetailCodeLessThan(String value) {
            addCriterion("detail_code <", value, "detailCode");
            return (Criteria) this;
        }

        public Criteria andDetailCodeLessThanOrEqualTo(String value) {
            addCriterion("detail_code <=", value, "detailCode");
            return (Criteria) this;
        }

        public Criteria andDetailCodeLike(String value) {
            addCriterion("detail_code like", value, "detailCode");
            return (Criteria) this;
        }

        public Criteria andDetailCodeNotLike(String value) {
            addCriterion("detail_code not like", value, "detailCode");
            return (Criteria) this;
        }

        public Criteria andDetailCodeIn(List<String> values) {
            addCriterion("detail_code in", values, "detailCode");
            return (Criteria) this;
        }

        public Criteria andDetailCodeNotIn(List<String> values) {
            addCriterion("detail_code not in", values, "detailCode");
            return (Criteria) this;
        }

        public Criteria andDetailCodeBetween(String value1, String value2) {
            addCriterion("detail_code between", value1, value2, "detailCode");
            return (Criteria) this;
        }

        public Criteria andDetailCodeNotBetween(String value1, String value2) {
            addCriterion("detail_code not between", value1, value2, "detailCode");
            return (Criteria) this;
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

        public Criteria andTypeCodeIsNull() {
            addCriterion("type_code is null");
            return (Criteria) this;
        }

        public Criteria andTypeCodeIsNotNull() {
            addCriterion("type_code is not null");
            return (Criteria) this;
        }

        public Criteria andTypeCodeEqualTo(String value) {
            addCriterion("type_code =", value, "typeCode");
            return (Criteria) this;
        }

        public Criteria andTypeCodeNotEqualTo(String value) {
            addCriterion("type_code <>", value, "typeCode");
            return (Criteria) this;
        }

        public Criteria andTypeCodeGreaterThan(String value) {
            addCriterion("type_code >", value, "typeCode");
            return (Criteria) this;
        }

        public Criteria andTypeCodeGreaterThanOrEqualTo(String value) {
            addCriterion("type_code >=", value, "typeCode");
            return (Criteria) this;
        }

        public Criteria andTypeCodeLessThan(String value) {
            addCriterion("type_code <", value, "typeCode");
            return (Criteria) this;
        }

        public Criteria andTypeCodeLessThanOrEqualTo(String value) {
            addCriterion("type_code <=", value, "typeCode");
            return (Criteria) this;
        }

        public Criteria andTypeCodeLike(String value) {
            addCriterion("type_code like", value, "typeCode");
            return (Criteria) this;
        }

        public Criteria andTypeCodeNotLike(String value) {
            addCriterion("type_code not like", value, "typeCode");
            return (Criteria) this;
        }

        public Criteria andTypeCodeIn(List<String> values) {
            addCriterion("type_code in", values, "typeCode");
            return (Criteria) this;
        }

        public Criteria andTypeCodeNotIn(List<String> values) {
            addCriterion("type_code not in", values, "typeCode");
            return (Criteria) this;
        }

        public Criteria andTypeCodeBetween(String value1, String value2) {
            addCriterion("type_code between", value1, value2, "typeCode");
            return (Criteria) this;
        }

        public Criteria andTypeCodeNotBetween(String value1, String value2) {
            addCriterion("type_code not between", value1, value2, "typeCode");
            return (Criteria) this;
        }

        public Criteria andDetailNmIsNull() {
            addCriterion("detail_nm is null");
            return (Criteria) this;
        }

        public Criteria andDetailNmIsNotNull() {
            addCriterion("detail_nm is not null");
            return (Criteria) this;
        }

        public Criteria andDetailNmEqualTo(String value) {
            addCriterion("detail_nm =", value, "detailNm");
            return (Criteria) this;
        }

        public Criteria andDetailNmNotEqualTo(String value) {
            addCriterion("detail_nm <>", value, "detailNm");
            return (Criteria) this;
        }

        public Criteria andDetailNmGreaterThan(String value) {
            addCriterion("detail_nm >", value, "detailNm");
            return (Criteria) this;
        }

        public Criteria andDetailNmGreaterThanOrEqualTo(String value) {
            addCriterion("detail_nm >=", value, "detailNm");
            return (Criteria) this;
        }

        public Criteria andDetailNmLessThan(String value) {
            addCriterion("detail_nm <", value, "detailNm");
            return (Criteria) this;
        }

        public Criteria andDetailNmLessThanOrEqualTo(String value) {
            addCriterion("detail_nm <=", value, "detailNm");
            return (Criteria) this;
        }

        public Criteria andDetailNmLike(String value) {
            addCriterion("detail_nm like", value, "detailNm");
            return (Criteria) this;
        }

        public Criteria andDetailNmNotLike(String value) {
            addCriterion("detail_nm not like", value, "detailNm");
            return (Criteria) this;
        }

        public Criteria andDetailNmIn(List<String> values) {
            addCriterion("detail_nm in", values, "detailNm");
            return (Criteria) this;
        }

        public Criteria andDetailNmNotIn(List<String> values) {
            addCriterion("detail_nm not in", values, "detailNm");
            return (Criteria) this;
        }

        public Criteria andDetailNmBetween(String value1, String value2) {
            addCriterion("detail_nm between", value1, value2, "detailNm");
            return (Criteria) this;
        }

        public Criteria andDetailNmNotBetween(String value1, String value2) {
            addCriterion("detail_nm not between", value1, value2, "detailNm");
            return (Criteria) this;
        }

        public Criteria andMaxPercentIsNull() {
            addCriterion("max_percent is null");
            return (Criteria) this;
        }

        public Criteria andMaxPercentIsNotNull() {
            addCriterion("max_percent is not null");
            return (Criteria) this;
        }

        public Criteria andMaxPercentEqualTo(BigDecimal value) {
            addCriterion("max_percent =", value, "maxPercent");
            return (Criteria) this;
        }

        public Criteria andMaxPercentNotEqualTo(BigDecimal value) {
            addCriterion("max_percent <>", value, "maxPercent");
            return (Criteria) this;
        }

        public Criteria andMaxPercentGreaterThan(BigDecimal value) {
            addCriterion("max_percent >", value, "maxPercent");
            return (Criteria) this;
        }

        public Criteria andMaxPercentGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("max_percent >=", value, "maxPercent");
            return (Criteria) this;
        }

        public Criteria andMaxPercentLessThan(BigDecimal value) {
            addCriterion("max_percent <", value, "maxPercent");
            return (Criteria) this;
        }

        public Criteria andMaxPercentLessThanOrEqualTo(BigDecimal value) {
            addCriterion("max_percent <=", value, "maxPercent");
            return (Criteria) this;
        }

        public Criteria andMaxPercentIn(List<BigDecimal> values) {
            addCriterion("max_percent in", values, "maxPercent");
            return (Criteria) this;
        }

        public Criteria andMaxPercentNotIn(List<BigDecimal> values) {
            addCriterion("max_percent not in", values, "maxPercent");
            return (Criteria) this;
        }

        public Criteria andMaxPercentBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("max_percent between", value1, value2, "maxPercent");
            return (Criteria) this;
        }

        public Criteria andMaxPercentNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("max_percent not between", value1, value2, "maxPercent");
            return (Criteria) this;
        }

        public Criteria andMinPercentIsNull() {
            addCriterion("min_percent is null");
            return (Criteria) this;
        }

        public Criteria andMinPercentIsNotNull() {
            addCriterion("min_percent is not null");
            return (Criteria) this;
        }

        public Criteria andMinPercentEqualTo(BigDecimal value) {
            addCriterion("min_percent =", value, "minPercent");
            return (Criteria) this;
        }

        public Criteria andMinPercentNotEqualTo(BigDecimal value) {
            addCriterion("min_percent <>", value, "minPercent");
            return (Criteria) this;
        }

        public Criteria andMinPercentGreaterThan(BigDecimal value) {
            addCriterion("min_percent >", value, "minPercent");
            return (Criteria) this;
        }

        public Criteria andMinPercentGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("min_percent >=", value, "minPercent");
            return (Criteria) this;
        }

        public Criteria andMinPercentLessThan(BigDecimal value) {
            addCriterion("min_percent <", value, "minPercent");
            return (Criteria) this;
        }

        public Criteria andMinPercentLessThanOrEqualTo(BigDecimal value) {
            addCriterion("min_percent <=", value, "minPercent");
            return (Criteria) this;
        }

        public Criteria andMinPercentIn(List<BigDecimal> values) {
            addCriterion("min_percent in", values, "minPercent");
            return (Criteria) this;
        }

        public Criteria andMinPercentNotIn(List<BigDecimal> values) {
            addCriterion("min_percent not in", values, "minPercent");
            return (Criteria) this;
        }

        public Criteria andMinPercentBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("min_percent between", value1, value2, "minPercent");
            return (Criteria) this;
        }

        public Criteria andMinPercentNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("min_percent not between", value1, value2, "minPercent");
            return (Criteria) this;
        }

        public Criteria andMaxAmtIsNull() {
            addCriterion("max_amt is null");
            return (Criteria) this;
        }

        public Criteria andMaxAmtIsNotNull() {
            addCriterion("max_amt is not null");
            return (Criteria) this;
        }

        public Criteria andMaxAmtEqualTo(BigDecimal value) {
            addCriterion("max_amt =", value, "maxAmt");
            return (Criteria) this;
        }

        public Criteria andMaxAmtNotEqualTo(BigDecimal value) {
            addCriterion("max_amt <>", value, "maxAmt");
            return (Criteria) this;
        }

        public Criteria andMaxAmtGreaterThan(BigDecimal value) {
            addCriterion("max_amt >", value, "maxAmt");
            return (Criteria) this;
        }

        public Criteria andMaxAmtGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("max_amt >=", value, "maxAmt");
            return (Criteria) this;
        }

        public Criteria andMaxAmtLessThan(BigDecimal value) {
            addCriterion("max_amt <", value, "maxAmt");
            return (Criteria) this;
        }

        public Criteria andMaxAmtLessThanOrEqualTo(BigDecimal value) {
            addCriterion("max_amt <=", value, "maxAmt");
            return (Criteria) this;
        }

        public Criteria andMaxAmtIn(List<BigDecimal> values) {
            addCriterion("max_amt in", values, "maxAmt");
            return (Criteria) this;
        }

        public Criteria andMaxAmtNotIn(List<BigDecimal> values) {
            addCriterion("max_amt not in", values, "maxAmt");
            return (Criteria) this;
        }

        public Criteria andMaxAmtBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("max_amt between", value1, value2, "maxAmt");
            return (Criteria) this;
        }

        public Criteria andMaxAmtNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("max_amt not between", value1, value2, "maxAmt");
            return (Criteria) this;
        }

        public Criteria andMinAmtIsNull() {
            addCriterion("min_amt is null");
            return (Criteria) this;
        }

        public Criteria andMinAmtIsNotNull() {
            addCriterion("min_amt is not null");
            return (Criteria) this;
        }

        public Criteria andMinAmtEqualTo(BigDecimal value) {
            addCriterion("min_amt =", value, "minAmt");
            return (Criteria) this;
        }

        public Criteria andMinAmtNotEqualTo(BigDecimal value) {
            addCriterion("min_amt <>", value, "minAmt");
            return (Criteria) this;
        }

        public Criteria andMinAmtGreaterThan(BigDecimal value) {
            addCriterion("min_amt >", value, "minAmt");
            return (Criteria) this;
        }

        public Criteria andMinAmtGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("min_amt >=", value, "minAmt");
            return (Criteria) this;
        }

        public Criteria andMinAmtLessThan(BigDecimal value) {
            addCriterion("min_amt <", value, "minAmt");
            return (Criteria) this;
        }

        public Criteria andMinAmtLessThanOrEqualTo(BigDecimal value) {
            addCriterion("min_amt <=", value, "minAmt");
            return (Criteria) this;
        }

        public Criteria andMinAmtIn(List<BigDecimal> values) {
            addCriterion("min_amt in", values, "minAmt");
            return (Criteria) this;
        }

        public Criteria andMinAmtNotIn(List<BigDecimal> values) {
            addCriterion("min_amt not in", values, "minAmt");
            return (Criteria) this;
        }

        public Criteria andMinAmtBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("min_amt between", value1, value2, "minAmt");
            return (Criteria) this;
        }

        public Criteria andMinAmtNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("min_amt not between", value1, value2, "minAmt");
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