package com.distribution.dao.invest.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class InvestExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public InvestExample() {
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

        public Criteria andInvestCntIsNull() {
            addCriterion("invest_cnt is null");
            return (Criteria) this;
        }

        public Criteria andInvestCntIsNotNull() {
            addCriterion("invest_cnt is not null");
            return (Criteria) this;
        }

        public Criteria andInvestCntEqualTo(Integer value) {
            addCriterion("invest_cnt =", value, "investCnt");
            return (Criteria) this;
        }

        public Criteria andInvestCntNotEqualTo(Integer value) {
            addCriterion("invest_cnt <>", value, "investCnt");
            return (Criteria) this;
        }

        public Criteria andInvestCntGreaterThan(Integer value) {
            addCriterion("invest_cnt >", value, "investCnt");
            return (Criteria) this;
        }

        public Criteria andInvestCntGreaterThanOrEqualTo(Integer value) {
            addCriterion("invest_cnt >=", value, "investCnt");
            return (Criteria) this;
        }

        public Criteria andInvestCntLessThan(Integer value) {
            addCriterion("invest_cnt <", value, "investCnt");
            return (Criteria) this;
        }

        public Criteria andInvestCntLessThanOrEqualTo(Integer value) {
            addCriterion("invest_cnt <=", value, "investCnt");
            return (Criteria) this;
        }

        public Criteria andInvestCntIn(List<Integer> values) {
            addCriterion("invest_cnt in", values, "investCnt");
            return (Criteria) this;
        }

        public Criteria andInvestCntNotIn(List<Integer> values) {
            addCriterion("invest_cnt not in", values, "investCnt");
            return (Criteria) this;
        }

        public Criteria andInvestCntBetween(Integer value1, Integer value2) {
            addCriterion("invest_cnt between", value1, value2, "investCnt");
            return (Criteria) this;
        }

        public Criteria andInvestCntNotBetween(Integer value1, Integer value2) {
            addCriterion("invest_cnt not between", value1, value2, "investCnt");
            return (Criteria) this;
        }

        public Criteria andSeedAmtIsNull() {
            addCriterion("seed_amt is null");
            return (Criteria) this;
        }

        public Criteria andSeedAmtIsNotNull() {
            addCriterion("seed_amt is not null");
            return (Criteria) this;
        }

        public Criteria andSeedAmtEqualTo(BigDecimal value) {
            addCriterion("seed_amt =", value, "seedAmt");
            return (Criteria) this;
        }

        public Criteria andSeedAmtNotEqualTo(BigDecimal value) {
            addCriterion("seed_amt <>", value, "seedAmt");
            return (Criteria) this;
        }

        public Criteria andSeedAmtGreaterThan(BigDecimal value) {
            addCriterion("seed_amt >", value, "seedAmt");
            return (Criteria) this;
        }

        public Criteria andSeedAmtGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("seed_amt >=", value, "seedAmt");
            return (Criteria) this;
        }

        public Criteria andSeedAmtLessThan(BigDecimal value) {
            addCriterion("seed_amt <", value, "seedAmt");
            return (Criteria) this;
        }

        public Criteria andSeedAmtLessThanOrEqualTo(BigDecimal value) {
            addCriterion("seed_amt <=", value, "seedAmt");
            return (Criteria) this;
        }

        public Criteria andSeedAmtIn(List<BigDecimal> values) {
            addCriterion("seed_amt in", values, "seedAmt");
            return (Criteria) this;
        }

        public Criteria andSeedAmtNotIn(List<BigDecimal> values) {
            addCriterion("seed_amt not in", values, "seedAmt");
            return (Criteria) this;
        }

        public Criteria andSeedAmtBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("seed_amt between", value1, value2, "seedAmt");
            return (Criteria) this;
        }

        public Criteria andSeedAmtNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("seed_amt not between", value1, value2, "seedAmt");
            return (Criteria) this;
        }

        public Criteria andBonusAmtIsNull() {
            addCriterion("bonus_amt is null");
            return (Criteria) this;
        }

        public Criteria andBonusAmtIsNotNull() {
            addCriterion("bonus_amt is not null");
            return (Criteria) this;
        }

        public Criteria andBonusAmtEqualTo(BigDecimal value) {
            addCriterion("bonus_amt =", value, "bonusAmt");
            return (Criteria) this;
        }

        public Criteria andBonusAmtNotEqualTo(BigDecimal value) {
            addCriterion("bonus_amt <>", value, "bonusAmt");
            return (Criteria) this;
        }

        public Criteria andBonusAmtGreaterThan(BigDecimal value) {
            addCriterion("bonus_amt >", value, "bonusAmt");
            return (Criteria) this;
        }

        public Criteria andBonusAmtGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("bonus_amt >=", value, "bonusAmt");
            return (Criteria) this;
        }

        public Criteria andBonusAmtLessThan(BigDecimal value) {
            addCriterion("bonus_amt <", value, "bonusAmt");
            return (Criteria) this;
        }

        public Criteria andBonusAmtLessThanOrEqualTo(BigDecimal value) {
            addCriterion("bonus_amt <=", value, "bonusAmt");
            return (Criteria) this;
        }

        public Criteria andBonusAmtIn(List<BigDecimal> values) {
            addCriterion("bonus_amt in", values, "bonusAmt");
            return (Criteria) this;
        }

        public Criteria andBonusAmtNotIn(List<BigDecimal> values) {
            addCriterion("bonus_amt not in", values, "bonusAmt");
            return (Criteria) this;
        }

        public Criteria andBonusAmtBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("bonus_amt between", value1, value2, "bonusAmt");
            return (Criteria) this;
        }

        public Criteria andBonusAmtNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("bonus_amt not between", value1, value2, "bonusAmt");
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