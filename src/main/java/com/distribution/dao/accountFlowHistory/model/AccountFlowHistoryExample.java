package com.distribution.dao.accountFlowHistory.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AccountFlowHistoryExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public AccountFlowHistoryExample() {
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

        public Criteria andTypeIsNull() {
            addCriterion("type is null");
            return (Criteria) this;
        }

        public Criteria andTypeIsNotNull() {
            addCriterion("type is not null");
            return (Criteria) this;
        }

        public Criteria andTypeEqualTo(String value) {
            addCriterion("type =", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotEqualTo(String value) {
            addCriterion("type <>", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeGreaterThan(String value) {
            addCriterion("type >", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeGreaterThanOrEqualTo(String value) {
            addCriterion("type >=", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLessThan(String value) {
            addCriterion("type <", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLessThanOrEqualTo(String value) {
            addCriterion("type <=", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLike(String value) {
            addCriterion("type like", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotLike(String value) {
            addCriterion("type not like", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeIn(List<String> values) {
            addCriterion("type in", values, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotIn(List<String> values) {
            addCriterion("type not in", values, "type");
            return (Criteria) this;
        }

        public Criteria andTypeBetween(String value1, String value2) {
            addCriterion("type between", value1, value2, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotBetween(String value1, String value2) {
            addCriterion("type not between", value1, value2, "type");
            return (Criteria) this;
        }

        public Criteria andTotalAmtIsNull() {
            addCriterion("total_amt is null");
            return (Criteria) this;
        }

        public Criteria andTotalAmtIsNotNull() {
            addCriterion("total_amt is not null");
            return (Criteria) this;
        }

        public Criteria andTotalAmtEqualTo(BigDecimal value) {
            addCriterion("total_amt =", value, "totalAmt");
            return (Criteria) this;
        }

        public Criteria andTotalAmtNotEqualTo(BigDecimal value) {
            addCriterion("total_amt <>", value, "totalAmt");
            return (Criteria) this;
        }

        public Criteria andTotalAmtGreaterThan(BigDecimal value) {
            addCriterion("total_amt >", value, "totalAmt");
            return (Criteria) this;
        }

        public Criteria andTotalAmtGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("total_amt >=", value, "totalAmt");
            return (Criteria) this;
        }

        public Criteria andTotalAmtLessThan(BigDecimal value) {
            addCriterion("total_amt <", value, "totalAmt");
            return (Criteria) this;
        }

        public Criteria andTotalAmtLessThanOrEqualTo(BigDecimal value) {
            addCriterion("total_amt <=", value, "totalAmt");
            return (Criteria) this;
        }

        public Criteria andTotalAmtIn(List<BigDecimal> values) {
            addCriterion("total_amt in", values, "totalAmt");
            return (Criteria) this;
        }

        public Criteria andTotalAmtNotIn(List<BigDecimal> values) {
            addCriterion("total_amt not in", values, "totalAmt");
            return (Criteria) this;
        }

        public Criteria andTotalAmtBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("total_amt between", value1, value2, "totalAmt");
            return (Criteria) this;
        }

        public Criteria andTotalAmtNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("total_amt not between", value1, value2, "totalAmt");
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

        public Criteria andFlowTypeIsNull() {
            addCriterion("flow_type is null");
            return (Criteria) this;
        }

        public Criteria andFlowTypeIsNotNull() {
            addCriterion("flow_type is not null");
            return (Criteria) this;
        }

        public Criteria andFlowTypeEqualTo(String value) {
            addCriterion("flow_type =", value, "flowType");
            return (Criteria) this;
        }

        public Criteria andFlowTypeNotEqualTo(String value) {
            addCriterion("flow_type <>", value, "flowType");
            return (Criteria) this;
        }

        public Criteria andFlowTypeGreaterThan(String value) {
            addCriterion("flow_type >", value, "flowType");
            return (Criteria) this;
        }

        public Criteria andFlowTypeGreaterThanOrEqualTo(String value) {
            addCriterion("flow_type >=", value, "flowType");
            return (Criteria) this;
        }

        public Criteria andFlowTypeLessThan(String value) {
            addCriterion("flow_type <", value, "flowType");
            return (Criteria) this;
        }

        public Criteria andFlowTypeLessThanOrEqualTo(String value) {
            addCriterion("flow_type <=", value, "flowType");
            return (Criteria) this;
        }

        public Criteria andFlowTypeLike(String value) {
            addCriterion("flow_type like", value, "flowType");
            return (Criteria) this;
        }

        public Criteria andFlowTypeNotLike(String value) {
            addCriterion("flow_type not like", value, "flowType");
            return (Criteria) this;
        }

        public Criteria andFlowTypeIn(List<String> values) {
            addCriterion("flow_type in", values, "flowType");
            return (Criteria) this;
        }

        public Criteria andFlowTypeNotIn(List<String> values) {
            addCriterion("flow_type not in", values, "flowType");
            return (Criteria) this;
        }

        public Criteria andFlowTypeBetween(String value1, String value2) {
            addCriterion("flow_type between", value1, value2, "flowType");
            return (Criteria) this;
        }

        public Criteria andFlowTypeNotBetween(String value1, String value2) {
            addCriterion("flow_type not between", value1, value2, "flowType");
            return (Criteria) this;
        }

        public Criteria andRemarkIsNull() {
            addCriterion("remark is null");
            return (Criteria) this;
        }

        public Criteria andRemarkIsNotNull() {
            addCriterion("remark is not null");
            return (Criteria) this;
        }

        public Criteria andRemarkEqualTo(String value) {
            addCriterion("remark =", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotEqualTo(String value) {
            addCriterion("remark <>", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkGreaterThan(String value) {
            addCriterion("remark >", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkGreaterThanOrEqualTo(String value) {
            addCriterion("remark >=", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLessThan(String value) {
            addCriterion("remark <", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLessThanOrEqualTo(String value) {
            addCriterion("remark <=", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLike(String value) {
            addCriterion("remark like", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotLike(String value) {
            addCriterion("remark not like", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkIn(List<String> values) {
            addCriterion("remark in", values, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotIn(List<String> values) {
            addCriterion("remark not in", values, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkBetween(String value1, String value2) {
            addCriterion("remark between", value1, value2, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotBetween(String value1, String value2) {
            addCriterion("remark not between", value1, value2, "remark");
            return (Criteria) this;
        }

        public Criteria andOldTotalBonusAmtIsNull() {
            addCriterion("old_total_bonus_amt is null");
            return (Criteria) this;
        }

        public Criteria andOldTotalBonusAmtIsNotNull() {
            addCriterion("old_total_bonus_amt is not null");
            return (Criteria) this;
        }

        public Criteria andOldTotalBonusAmtEqualTo(BigDecimal value) {
            addCriterion("old_total_bonus_amt =", value, "oldTotalBonusAmt");
            return (Criteria) this;
        }

        public Criteria andOldTotalBonusAmtNotEqualTo(BigDecimal value) {
            addCriterion("old_total_bonus_amt <>", value, "oldTotalBonusAmt");
            return (Criteria) this;
        }

        public Criteria andOldTotalBonusAmtGreaterThan(BigDecimal value) {
            addCriterion("old_total_bonus_amt >", value, "oldTotalBonusAmt");
            return (Criteria) this;
        }

        public Criteria andOldTotalBonusAmtGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("old_total_bonus_amt >=", value, "oldTotalBonusAmt");
            return (Criteria) this;
        }

        public Criteria andOldTotalBonusAmtLessThan(BigDecimal value) {
            addCriterion("old_total_bonus_amt <", value, "oldTotalBonusAmt");
            return (Criteria) this;
        }

        public Criteria andOldTotalBonusAmtLessThanOrEqualTo(BigDecimal value) {
            addCriterion("old_total_bonus_amt <=", value, "oldTotalBonusAmt");
            return (Criteria) this;
        }

        public Criteria andOldTotalBonusAmtIn(List<BigDecimal> values) {
            addCriterion("old_total_bonus_amt in", values, "oldTotalBonusAmt");
            return (Criteria) this;
        }

        public Criteria andOldTotalBonusAmtNotIn(List<BigDecimal> values) {
            addCriterion("old_total_bonus_amt not in", values, "oldTotalBonusAmt");
            return (Criteria) this;
        }

        public Criteria andOldTotalBonusAmtBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("old_total_bonus_amt between", value1, value2, "oldTotalBonusAmt");
            return (Criteria) this;
        }

        public Criteria andOldTotalBonusAmtNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("old_total_bonus_amt not between", value1, value2, "oldTotalBonusAmt");
            return (Criteria) this;
        }

        public Criteria andNewTotalBonusAmtIsNull() {
            addCriterion("new_total_bonus_amt is null");
            return (Criteria) this;
        }

        public Criteria andNewTotalBonusAmtIsNotNull() {
            addCriterion("new_total_bonus_amt is not null");
            return (Criteria) this;
        }

        public Criteria andNewTotalBonusAmtEqualTo(BigDecimal value) {
            addCriterion("new_total_bonus_amt =", value, "newTotalBonusAmt");
            return (Criteria) this;
        }

        public Criteria andNewTotalBonusAmtNotEqualTo(BigDecimal value) {
            addCriterion("new_total_bonus_amt <>", value, "newTotalBonusAmt");
            return (Criteria) this;
        }

        public Criteria andNewTotalBonusAmtGreaterThan(BigDecimal value) {
            addCriterion("new_total_bonus_amt >", value, "newTotalBonusAmt");
            return (Criteria) this;
        }

        public Criteria andNewTotalBonusAmtGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("new_total_bonus_amt >=", value, "newTotalBonusAmt");
            return (Criteria) this;
        }

        public Criteria andNewTotalBonusAmtLessThan(BigDecimal value) {
            addCriterion("new_total_bonus_amt <", value, "newTotalBonusAmt");
            return (Criteria) this;
        }

        public Criteria andNewTotalBonusAmtLessThanOrEqualTo(BigDecimal value) {
            addCriterion("new_total_bonus_amt <=", value, "newTotalBonusAmt");
            return (Criteria) this;
        }

        public Criteria andNewTotalBonusAmtIn(List<BigDecimal> values) {
            addCriterion("new_total_bonus_amt in", values, "newTotalBonusAmt");
            return (Criteria) this;
        }

        public Criteria andNewTotalBonusAmtNotIn(List<BigDecimal> values) {
            addCriterion("new_total_bonus_amt not in", values, "newTotalBonusAmt");
            return (Criteria) this;
        }

        public Criteria andNewTotalBonusAmtBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("new_total_bonus_amt between", value1, value2, "newTotalBonusAmt");
            return (Criteria) this;
        }

        public Criteria andNewTotalBonusAmtNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("new_total_bonus_amt not between", value1, value2, "newTotalBonusAmt");
            return (Criteria) this;
        }

        public Criteria andOldTotalSeedAmtIsNull() {
            addCriterion("old_total_seed_amt is null");
            return (Criteria) this;
        }

        public Criteria andOldTotalSeedAmtIsNotNull() {
            addCriterion("old_total_seed_amt is not null");
            return (Criteria) this;
        }

        public Criteria andOldTotalSeedAmtEqualTo(BigDecimal value) {
            addCriterion("old_total_seed_amt =", value, "oldTotalSeedAmt");
            return (Criteria) this;
        }

        public Criteria andOldTotalSeedAmtNotEqualTo(BigDecimal value) {
            addCriterion("old_total_seed_amt <>", value, "oldTotalSeedAmt");
            return (Criteria) this;
        }

        public Criteria andOldTotalSeedAmtGreaterThan(BigDecimal value) {
            addCriterion("old_total_seed_amt >", value, "oldTotalSeedAmt");
            return (Criteria) this;
        }

        public Criteria andOldTotalSeedAmtGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("old_total_seed_amt >=", value, "oldTotalSeedAmt");
            return (Criteria) this;
        }

        public Criteria andOldTotalSeedAmtLessThan(BigDecimal value) {
            addCriterion("old_total_seed_amt <", value, "oldTotalSeedAmt");
            return (Criteria) this;
        }

        public Criteria andOldTotalSeedAmtLessThanOrEqualTo(BigDecimal value) {
            addCriterion("old_total_seed_amt <=", value, "oldTotalSeedAmt");
            return (Criteria) this;
        }

        public Criteria andOldTotalSeedAmtIn(List<BigDecimal> values) {
            addCriterion("old_total_seed_amt in", values, "oldTotalSeedAmt");
            return (Criteria) this;
        }

        public Criteria andOldTotalSeedAmtNotIn(List<BigDecimal> values) {
            addCriterion("old_total_seed_amt not in", values, "oldTotalSeedAmt");
            return (Criteria) this;
        }

        public Criteria andOldTotalSeedAmtBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("old_total_seed_amt between", value1, value2, "oldTotalSeedAmt");
            return (Criteria) this;
        }

        public Criteria andOldTotalSeedAmtNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("old_total_seed_amt not between", value1, value2, "oldTotalSeedAmt");
            return (Criteria) this;
        }

        public Criteria andNewTotalSeedAmtIsNull() {
            addCriterion("new_total_seed_amt is null");
            return (Criteria) this;
        }

        public Criteria andNewTotalSeedAmtIsNotNull() {
            addCriterion("new_total_seed_amt is not null");
            return (Criteria) this;
        }

        public Criteria andNewTotalSeedAmtEqualTo(BigDecimal value) {
            addCriterion("new_total_seed_amt =", value, "newTotalSeedAmt");
            return (Criteria) this;
        }

        public Criteria andNewTotalSeedAmtNotEqualTo(BigDecimal value) {
            addCriterion("new_total_seed_amt <>", value, "newTotalSeedAmt");
            return (Criteria) this;
        }

        public Criteria andNewTotalSeedAmtGreaterThan(BigDecimal value) {
            addCriterion("new_total_seed_amt >", value, "newTotalSeedAmt");
            return (Criteria) this;
        }

        public Criteria andNewTotalSeedAmtGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("new_total_seed_amt >=", value, "newTotalSeedAmt");
            return (Criteria) this;
        }

        public Criteria andNewTotalSeedAmtLessThan(BigDecimal value) {
            addCriterion("new_total_seed_amt <", value, "newTotalSeedAmt");
            return (Criteria) this;
        }

        public Criteria andNewTotalSeedAmtLessThanOrEqualTo(BigDecimal value) {
            addCriterion("new_total_seed_amt <=", value, "newTotalSeedAmt");
            return (Criteria) this;
        }

        public Criteria andNewTotalSeedAmtIn(List<BigDecimal> values) {
            addCriterion("new_total_seed_amt in", values, "newTotalSeedAmt");
            return (Criteria) this;
        }

        public Criteria andNewTotalSeedAmtNotIn(List<BigDecimal> values) {
            addCriterion("new_total_seed_amt not in", values, "newTotalSeedAmt");
            return (Criteria) this;
        }

        public Criteria andNewTotalSeedAmtBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("new_total_seed_amt between", value1, value2, "newTotalSeedAmt");
            return (Criteria) this;
        }

        public Criteria andNewTotalSeedAmtNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("new_total_seed_amt not between", value1, value2, "newTotalSeedAmt");
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