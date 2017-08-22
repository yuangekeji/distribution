package com.distribution.dao.accountManager.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class AccountManagerExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public AccountManagerExample() {
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
            addCriterion("\"id\" is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("\"id\" is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Integer value) {
            addCriterion("\"id\" =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Integer value) {
            addCriterion("\"id\" <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Integer value) {
            addCriterion("\"id\" >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("\"id\" >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Integer value) {
            addCriterion("\"id\" <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Integer value) {
            addCriterion("\"id\" <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Integer> values) {
            addCriterion("\"id\" in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Integer> values) {
            addCriterion("\"id\" not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Integer value1, Integer value2) {
            addCriterion("\"id\" between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Integer value1, Integer value2) {
            addCriterion("\"id\" not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andSeedAmtIsNull() {
            addCriterion("\"seed_amt\" is null");
            return (Criteria) this;
        }

        public Criteria andSeedAmtIsNotNull() {
            addCriterion("\"seed_amt\" is not null");
            return (Criteria) this;
        }

        public Criteria andSeedAmtEqualTo(BigDecimal value) {
            addCriterion("\"seed_amt\" =", value, "seedAmt");
            return (Criteria) this;
        }

        public Criteria andSeedAmtNotEqualTo(BigDecimal value) {
            addCriterion("\"seed_amt\" <>", value, "seedAmt");
            return (Criteria) this;
        }

        public Criteria andSeedAmtGreaterThan(BigDecimal value) {
            addCriterion("\"seed_amt\" >", value, "seedAmt");
            return (Criteria) this;
        }

        public Criteria andSeedAmtGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("\"seed_amt\" >=", value, "seedAmt");
            return (Criteria) this;
        }

        public Criteria andSeedAmtLessThan(BigDecimal value) {
            addCriterion("\"seed_amt\" <", value, "seedAmt");
            return (Criteria) this;
        }

        public Criteria andSeedAmtLessThanOrEqualTo(BigDecimal value) {
            addCriterion("\"seed_amt\" <=", value, "seedAmt");
            return (Criteria) this;
        }

        public Criteria andSeedAmtIn(List<BigDecimal> values) {
            addCriterion("\"seed_amt\" in", values, "seedAmt");
            return (Criteria) this;
        }

        public Criteria andSeedAmtNotIn(List<BigDecimal> values) {
            addCriterion("\"seed_amt\" not in", values, "seedAmt");
            return (Criteria) this;
        }

        public Criteria andSeedAmtBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("\"seed_amt\" between", value1, value2, "seedAmt");
            return (Criteria) this;
        }

        public Criteria andSeedAmtNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("\"seed_amt\" not between", value1, value2, "seedAmt");
            return (Criteria) this;
        }

        public Criteria andBonusAmtIsNull() {
            addCriterion("\"bonus_amt\" is null");
            return (Criteria) this;
        }

        public Criteria andBonusAmtIsNotNull() {
            addCriterion("\"bonus_amt\" is not null");
            return (Criteria) this;
        }

        public Criteria andBonusAmtEqualTo(BigDecimal value) {
            addCriterion("\"bonus_amt\" =", value, "bonusAmt");
            return (Criteria) this;
        }

        public Criteria andBonusAmtNotEqualTo(BigDecimal value) {
            addCriterion("\"bonus_amt\" <>", value, "bonusAmt");
            return (Criteria) this;
        }

        public Criteria andBonusAmtGreaterThan(BigDecimal value) {
            addCriterion("\"bonus_amt\" >", value, "bonusAmt");
            return (Criteria) this;
        }

        public Criteria andBonusAmtGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("\"bonus_amt\" >=", value, "bonusAmt");
            return (Criteria) this;
        }

        public Criteria andBonusAmtLessThan(BigDecimal value) {
            addCriterion("\"bonus_amt\" <", value, "bonusAmt");
            return (Criteria) this;
        }

        public Criteria andBonusAmtLessThanOrEqualTo(BigDecimal value) {
            addCriterion("\"bonus_amt\" <=", value, "bonusAmt");
            return (Criteria) this;
        }

        public Criteria andBonusAmtIn(List<BigDecimal> values) {
            addCriterion("\"bonus_amt\" in", values, "bonusAmt");
            return (Criteria) this;
        }

        public Criteria andBonusAmtNotIn(List<BigDecimal> values) {
            addCriterion("\"bonus_amt\" not in", values, "bonusAmt");
            return (Criteria) this;
        }

        public Criteria andBonusAmtBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("\"bonus_amt\" between", value1, value2, "bonusAmt");
            return (Criteria) this;
        }

        public Criteria andBonusAmtNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("\"bonus_amt\" not between", value1, value2, "bonusAmt");
            return (Criteria) this;
        }

        public Criteria andAdvanceAmtIsNull() {
            addCriterion("\"advance_amt\" is null");
            return (Criteria) this;
        }

        public Criteria andAdvanceAmtIsNotNull() {
            addCriterion("\"advance_amt\" is not null");
            return (Criteria) this;
        }

        public Criteria andAdvanceAmtEqualTo(BigDecimal value) {
            addCriterion("\"advance_amt\" =", value, "advanceAmt");
            return (Criteria) this;
        }

        public Criteria andAdvanceAmtNotEqualTo(BigDecimal value) {
            addCriterion("\"advance_amt\" <>", value, "advanceAmt");
            return (Criteria) this;
        }

        public Criteria andAdvanceAmtGreaterThan(BigDecimal value) {
            addCriterion("\"advance_amt\" >", value, "advanceAmt");
            return (Criteria) this;
        }

        public Criteria andAdvanceAmtGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("\"advance_amt\" >=", value, "advanceAmt");
            return (Criteria) this;
        }

        public Criteria andAdvanceAmtLessThan(BigDecimal value) {
            addCriterion("\"advance_amt\" <", value, "advanceAmt");
            return (Criteria) this;
        }

        public Criteria andAdvanceAmtLessThanOrEqualTo(BigDecimal value) {
            addCriterion("\"advance_amt\" <=", value, "advanceAmt");
            return (Criteria) this;
        }

        public Criteria andAdvanceAmtIn(List<BigDecimal> values) {
            addCriterion("\"advance_amt\" in", values, "advanceAmt");
            return (Criteria) this;
        }

        public Criteria andAdvanceAmtNotIn(List<BigDecimal> values) {
            addCriterion("\"advance_amt\" not in", values, "advanceAmt");
            return (Criteria) this;
        }

        public Criteria andAdvanceAmtBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("\"advance_amt\" between", value1, value2, "advanceAmt");
            return (Criteria) this;
        }

        public Criteria andAdvanceAmtNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("\"advance_amt\" not between", value1, value2, "advanceAmt");
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