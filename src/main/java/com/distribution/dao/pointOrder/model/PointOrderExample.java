package com.distribution.dao.pointOrder.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PointOrderExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public PointOrderExample() {
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

        public Criteria andOrderNoIsNull() {
            addCriterion("order_no is null");
            return (Criteria) this;
        }

        public Criteria andOrderNoIsNotNull() {
            addCriterion("order_no is not null");
            return (Criteria) this;
        }

        public Criteria andOrderNoEqualTo(Long value) {
            addCriterion("order_no =", value, "orderNo");
            return (Criteria) this;
        }

        public Criteria andOrderNoNotEqualTo(Long value) {
            addCriterion("order_no <>", value, "orderNo");
            return (Criteria) this;
        }

        public Criteria andOrderNoGreaterThan(Long value) {
            addCriterion("order_no >", value, "orderNo");
            return (Criteria) this;
        }

        public Criteria andOrderNoGreaterThanOrEqualTo(Long value) {
            addCriterion("order_no >=", value, "orderNo");
            return (Criteria) this;
        }

        public Criteria andOrderNoLessThan(Long value) {
            addCriterion("order_no <", value, "orderNo");
            return (Criteria) this;
        }

        public Criteria andOrderNoLessThanOrEqualTo(Long value) {
            addCriterion("order_no <=", value, "orderNo");
            return (Criteria) this;
        }

        public Criteria andOrderNoIn(List<Long> values) {
            addCriterion("order_no in", values, "orderNo");
            return (Criteria) this;
        }

        public Criteria andOrderNoNotIn(List<Long> values) {
            addCriterion("order_no not in", values, "orderNo");
            return (Criteria) this;
        }

        public Criteria andOrderNoBetween(Long value1, Long value2) {
            addCriterion("order_no between", value1, value2, "orderNo");
            return (Criteria) this;
        }

        public Criteria andOrderNoNotBetween(Long value1, Long value2) {
            addCriterion("order_no not between", value1, value2, "orderNo");
            return (Criteria) this;
        }

        public Criteria andOrderCategoryIsNull() {
            addCriterion("order_category is null");
            return (Criteria) this;
        }

        public Criteria andOrderCategoryIsNotNull() {
            addCriterion("order_category is not null");
            return (Criteria) this;
        }

        public Criteria andOrderCategoryEqualTo(String value) {
            addCriterion("order_category =", value, "orderCategory");
            return (Criteria) this;
        }

        public Criteria andOrderCategoryNotEqualTo(String value) {
            addCriterion("order_category <>", value, "orderCategory");
            return (Criteria) this;
        }

        public Criteria andOrderCategoryGreaterThan(String value) {
            addCriterion("order_category >", value, "orderCategory");
            return (Criteria) this;
        }

        public Criteria andOrderCategoryGreaterThanOrEqualTo(String value) {
            addCriterion("order_category >=", value, "orderCategory");
            return (Criteria) this;
        }

        public Criteria andOrderCategoryLessThan(String value) {
            addCriterion("order_category <", value, "orderCategory");
            return (Criteria) this;
        }

        public Criteria andOrderCategoryLessThanOrEqualTo(String value) {
            addCriterion("order_category <=", value, "orderCategory");
            return (Criteria) this;
        }

        public Criteria andOrderCategoryLike(String value) {
            addCriterion("order_category like", value, "orderCategory");
            return (Criteria) this;
        }

        public Criteria andOrderCategoryNotLike(String value) {
            addCriterion("order_category not like", value, "orderCategory");
            return (Criteria) this;
        }

        public Criteria andOrderCategoryIn(List<String> values) {
            addCriterion("order_category in", values, "orderCategory");
            return (Criteria) this;
        }

        public Criteria andOrderCategoryNotIn(List<String> values) {
            addCriterion("order_category not in", values, "orderCategory");
            return (Criteria) this;
        }

        public Criteria andOrderCategoryBetween(String value1, String value2) {
            addCriterion("order_category between", value1, value2, "orderCategory");
            return (Criteria) this;
        }

        public Criteria andOrderCategoryNotBetween(String value1, String value2) {
            addCriterion("order_category not between", value1, value2, "orderCategory");
            return (Criteria) this;
        }

        public Criteria andOrderAmtIsNull() {
            addCriterion("order_amt is null");
            return (Criteria) this;
        }

        public Criteria andOrderAmtIsNotNull() {
            addCriterion("order_amt is not null");
            return (Criteria) this;
        }

        public Criteria andOrderAmtEqualTo(BigDecimal value) {
            addCriterion("order_amt =", value, "orderAmt");
            return (Criteria) this;
        }

        public Criteria andOrderAmtNotEqualTo(BigDecimal value) {
            addCriterion("order_amt <>", value, "orderAmt");
            return (Criteria) this;
        }

        public Criteria andOrderAmtGreaterThan(BigDecimal value) {
            addCriterion("order_amt >", value, "orderAmt");
            return (Criteria) this;
        }

        public Criteria andOrderAmtGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("order_amt >=", value, "orderAmt");
            return (Criteria) this;
        }

        public Criteria andOrderAmtLessThan(BigDecimal value) {
            addCriterion("order_amt <", value, "orderAmt");
            return (Criteria) this;
        }

        public Criteria andOrderAmtLessThanOrEqualTo(BigDecimal value) {
            addCriterion("order_amt <=", value, "orderAmt");
            return (Criteria) this;
        }

        public Criteria andOrderAmtIn(List<BigDecimal> values) {
            addCriterion("order_amt in", values, "orderAmt");
            return (Criteria) this;
        }

        public Criteria andOrderAmtNotIn(List<BigDecimal> values) {
            addCriterion("order_amt not in", values, "orderAmt");
            return (Criteria) this;
        }

        public Criteria andOrderAmtBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("order_amt between", value1, value2, "orderAmt");
            return (Criteria) this;
        }

        public Criteria andOrderAmtNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("order_amt not between", value1, value2, "orderAmt");
            return (Criteria) this;
        }

        public Criteria andPointAmtIsNull() {
            addCriterion("point_amt is null");
            return (Criteria) this;
        }

        public Criteria andPointAmtIsNotNull() {
            addCriterion("point_amt is not null");
            return (Criteria) this;
        }

        public Criteria andPointAmtEqualTo(BigDecimal value) {
            addCriterion("point_amt =", value, "pointAmt");
            return (Criteria) this;
        }

        public Criteria andPointAmtNotEqualTo(BigDecimal value) {
            addCriterion("point_amt <>", value, "pointAmt");
            return (Criteria) this;
        }

        public Criteria andPointAmtGreaterThan(BigDecimal value) {
            addCriterion("point_amt >", value, "pointAmt");
            return (Criteria) this;
        }

        public Criteria andPointAmtGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("point_amt >=", value, "pointAmt");
            return (Criteria) this;
        }

        public Criteria andPointAmtLessThan(BigDecimal value) {
            addCriterion("point_amt <", value, "pointAmt");
            return (Criteria) this;
        }

        public Criteria andPointAmtLessThanOrEqualTo(BigDecimal value) {
            addCriterion("point_amt <=", value, "pointAmt");
            return (Criteria) this;
        }

        public Criteria andPointAmtIn(List<BigDecimal> values) {
            addCriterion("point_amt in", values, "pointAmt");
            return (Criteria) this;
        }

        public Criteria andPointAmtNotIn(List<BigDecimal> values) {
            addCriterion("point_amt not in", values, "pointAmt");
            return (Criteria) this;
        }

        public Criteria andPointAmtBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("point_amt between", value1, value2, "pointAmt");
            return (Criteria) this;
        }

        public Criteria andPointAmtNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("point_amt not between", value1, value2, "pointAmt");
            return (Criteria) this;
        }

        public Criteria andPointTypeIsNull() {
            addCriterion("point_type is null");
            return (Criteria) this;
        }

        public Criteria andPointTypeIsNotNull() {
            addCriterion("point_type is not null");
            return (Criteria) this;
        }

        public Criteria andPointTypeEqualTo(String value) {
            addCriterion("point_type =", value, "pointType");
            return (Criteria) this;
        }

        public Criteria andPointTypeNotEqualTo(String value) {
            addCriterion("point_type <>", value, "pointType");
            return (Criteria) this;
        }

        public Criteria andPointTypeGreaterThan(String value) {
            addCriterion("point_type >", value, "pointType");
            return (Criteria) this;
        }

        public Criteria andPointTypeGreaterThanOrEqualTo(String value) {
            addCriterion("point_type >=", value, "pointType");
            return (Criteria) this;
        }

        public Criteria andPointTypeLessThan(String value) {
            addCriterion("point_type <", value, "pointType");
            return (Criteria) this;
        }

        public Criteria andPointTypeLessThanOrEqualTo(String value) {
            addCriterion("point_type <=", value, "pointType");
            return (Criteria) this;
        }

        public Criteria andPointTypeLike(String value) {
            addCriterion("point_type like", value, "pointType");
            return (Criteria) this;
        }

        public Criteria andPointTypeNotLike(String value) {
            addCriterion("point_type not like", value, "pointType");
            return (Criteria) this;
        }

        public Criteria andPointTypeIn(List<String> values) {
            addCriterion("point_type in", values, "pointType");
            return (Criteria) this;
        }

        public Criteria andPointTypeNotIn(List<String> values) {
            addCriterion("point_type not in", values, "pointType");
            return (Criteria) this;
        }

        public Criteria andPointTypeBetween(String value1, String value2) {
            addCriterion("point_type between", value1, value2, "pointType");
            return (Criteria) this;
        }

        public Criteria andPointTypeNotBetween(String value1, String value2) {
            addCriterion("point_type not between", value1, value2, "pointType");
            return (Criteria) this;
        }

        public Criteria andOrderQtyIsNull() {
            addCriterion("order_qty is null");
            return (Criteria) this;
        }

        public Criteria andOrderQtyIsNotNull() {
            addCriterion("order_qty is not null");
            return (Criteria) this;
        }

        public Criteria andOrderQtyEqualTo(Integer value) {
            addCriterion("order_qty =", value, "orderQty");
            return (Criteria) this;
        }

        public Criteria andOrderQtyNotEqualTo(Integer value) {
            addCriterion("order_qty <>", value, "orderQty");
            return (Criteria) this;
        }

        public Criteria andOrderQtyGreaterThan(Integer value) {
            addCriterion("order_qty >", value, "orderQty");
            return (Criteria) this;
        }

        public Criteria andOrderQtyGreaterThanOrEqualTo(Integer value) {
            addCriterion("order_qty >=", value, "orderQty");
            return (Criteria) this;
        }

        public Criteria andOrderQtyLessThan(Integer value) {
            addCriterion("order_qty <", value, "orderQty");
            return (Criteria) this;
        }

        public Criteria andOrderQtyLessThanOrEqualTo(Integer value) {
            addCriterion("order_qty <=", value, "orderQty");
            return (Criteria) this;
        }

        public Criteria andOrderQtyIn(List<Integer> values) {
            addCriterion("order_qty in", values, "orderQty");
            return (Criteria) this;
        }

        public Criteria andOrderQtyNotIn(List<Integer> values) {
            addCriterion("order_qty not in", values, "orderQty");
            return (Criteria) this;
        }

        public Criteria andOrderQtyBetween(Integer value1, Integer value2) {
            addCriterion("order_qty between", value1, value2, "orderQty");
            return (Criteria) this;
        }

        public Criteria andOrderQtyNotBetween(Integer value1, Integer value2) {
            addCriterion("order_qty not between", value1, value2, "orderQty");
            return (Criteria) this;
        }

        public Criteria andExpressFeeIsNull() {
            addCriterion("express_fee is null");
            return (Criteria) this;
        }

        public Criteria andExpressFeeIsNotNull() {
            addCriterion("express_fee is not null");
            return (Criteria) this;
        }

        public Criteria andExpressFeeEqualTo(BigDecimal value) {
            addCriterion("express_fee =", value, "expressFee");
            return (Criteria) this;
        }

        public Criteria andExpressFeeNotEqualTo(BigDecimal value) {
            addCriterion("express_fee <>", value, "expressFee");
            return (Criteria) this;
        }

        public Criteria andExpressFeeGreaterThan(BigDecimal value) {
            addCriterion("express_fee >", value, "expressFee");
            return (Criteria) this;
        }

        public Criteria andExpressFeeGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("express_fee >=", value, "expressFee");
            return (Criteria) this;
        }

        public Criteria andExpressFeeLessThan(BigDecimal value) {
            addCriterion("express_fee <", value, "expressFee");
            return (Criteria) this;
        }

        public Criteria andExpressFeeLessThanOrEqualTo(BigDecimal value) {
            addCriterion("express_fee <=", value, "expressFee");
            return (Criteria) this;
        }

        public Criteria andExpressFeeIn(List<BigDecimal> values) {
            addCriterion("express_fee in", values, "expressFee");
            return (Criteria) this;
        }

        public Criteria andExpressFeeNotIn(List<BigDecimal> values) {
            addCriterion("express_fee not in", values, "expressFee");
            return (Criteria) this;
        }

        public Criteria andExpressFeeBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("express_fee between", value1, value2, "expressFee");
            return (Criteria) this;
        }

        public Criteria andExpressFeeNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("express_fee not between", value1, value2, "expressFee");
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

        public Criteria andReceiveNameIsNull() {
            addCriterion("receive_name is null");
            return (Criteria) this;
        }

        public Criteria andReceiveNameIsNotNull() {
            addCriterion("receive_name is not null");
            return (Criteria) this;
        }

        public Criteria andReceiveNameEqualTo(String value) {
            addCriterion("receive_name =", value, "receiveName");
            return (Criteria) this;
        }

        public Criteria andReceiveNameNotEqualTo(String value) {
            addCriterion("receive_name <>", value, "receiveName");
            return (Criteria) this;
        }

        public Criteria andReceiveNameGreaterThan(String value) {
            addCriterion("receive_name >", value, "receiveName");
            return (Criteria) this;
        }

        public Criteria andReceiveNameGreaterThanOrEqualTo(String value) {
            addCriterion("receive_name >=", value, "receiveName");
            return (Criteria) this;
        }

        public Criteria andReceiveNameLessThan(String value) {
            addCriterion("receive_name <", value, "receiveName");
            return (Criteria) this;
        }

        public Criteria andReceiveNameLessThanOrEqualTo(String value) {
            addCriterion("receive_name <=", value, "receiveName");
            return (Criteria) this;
        }

        public Criteria andReceiveNameLike(String value) {
            addCriterion("receive_name like", value, "receiveName");
            return (Criteria) this;
        }

        public Criteria andReceiveNameNotLike(String value) {
            addCriterion("receive_name not like", value, "receiveName");
            return (Criteria) this;
        }

        public Criteria andReceiveNameIn(List<String> values) {
            addCriterion("receive_name in", values, "receiveName");
            return (Criteria) this;
        }

        public Criteria andReceiveNameNotIn(List<String> values) {
            addCriterion("receive_name not in", values, "receiveName");
            return (Criteria) this;
        }

        public Criteria andReceiveNameBetween(String value1, String value2) {
            addCriterion("receive_name between", value1, value2, "receiveName");
            return (Criteria) this;
        }

        public Criteria andReceiveNameNotBetween(String value1, String value2) {
            addCriterion("receive_name not between", value1, value2, "receiveName");
            return (Criteria) this;
        }

        public Criteria andExpressAddressIsNull() {
            addCriterion("express_address is null");
            return (Criteria) this;
        }

        public Criteria andExpressAddressIsNotNull() {
            addCriterion("express_address is not null");
            return (Criteria) this;
        }

        public Criteria andExpressAddressEqualTo(String value) {
            addCriterion("express_address =", value, "expressAddress");
            return (Criteria) this;
        }

        public Criteria andExpressAddressNotEqualTo(String value) {
            addCriterion("express_address <>", value, "expressAddress");
            return (Criteria) this;
        }

        public Criteria andExpressAddressGreaterThan(String value) {
            addCriterion("express_address >", value, "expressAddress");
            return (Criteria) this;
        }

        public Criteria andExpressAddressGreaterThanOrEqualTo(String value) {
            addCriterion("express_address >=", value, "expressAddress");
            return (Criteria) this;
        }

        public Criteria andExpressAddressLessThan(String value) {
            addCriterion("express_address <", value, "expressAddress");
            return (Criteria) this;
        }

        public Criteria andExpressAddressLessThanOrEqualTo(String value) {
            addCriterion("express_address <=", value, "expressAddress");
            return (Criteria) this;
        }

        public Criteria andExpressAddressLike(String value) {
            addCriterion("express_address like", value, "expressAddress");
            return (Criteria) this;
        }

        public Criteria andExpressAddressNotLike(String value) {
            addCriterion("express_address not like", value, "expressAddress");
            return (Criteria) this;
        }

        public Criteria andExpressAddressIn(List<String> values) {
            addCriterion("express_address in", values, "expressAddress");
            return (Criteria) this;
        }

        public Criteria andExpressAddressNotIn(List<String> values) {
            addCriterion("express_address not in", values, "expressAddress");
            return (Criteria) this;
        }

        public Criteria andExpressAddressBetween(String value1, String value2) {
            addCriterion("express_address between", value1, value2, "expressAddress");
            return (Criteria) this;
        }

        public Criteria andExpressAddressNotBetween(String value1, String value2) {
            addCriterion("express_address not between", value1, value2, "expressAddress");
            return (Criteria) this;
        }

        public Criteria andRecevivePhoneIsNull() {
            addCriterion("recevive_phone is null");
            return (Criteria) this;
        }

        public Criteria andRecevivePhoneIsNotNull() {
            addCriterion("recevive_phone is not null");
            return (Criteria) this;
        }

        public Criteria andRecevivePhoneEqualTo(String value) {
            addCriterion("recevive_phone =", value, "recevivePhone");
            return (Criteria) this;
        }

        public Criteria andRecevivePhoneNotEqualTo(String value) {
            addCriterion("recevive_phone <>", value, "recevivePhone");
            return (Criteria) this;
        }

        public Criteria andRecevivePhoneGreaterThan(String value) {
            addCriterion("recevive_phone >", value, "recevivePhone");
            return (Criteria) this;
        }

        public Criteria andRecevivePhoneGreaterThanOrEqualTo(String value) {
            addCriterion("recevive_phone >=", value, "recevivePhone");
            return (Criteria) this;
        }

        public Criteria andRecevivePhoneLessThan(String value) {
            addCriterion("recevive_phone <", value, "recevivePhone");
            return (Criteria) this;
        }

        public Criteria andRecevivePhoneLessThanOrEqualTo(String value) {
            addCriterion("recevive_phone <=", value, "recevivePhone");
            return (Criteria) this;
        }

        public Criteria andRecevivePhoneLike(String value) {
            addCriterion("recevive_phone like", value, "recevivePhone");
            return (Criteria) this;
        }

        public Criteria andRecevivePhoneNotLike(String value) {
            addCriterion("recevive_phone not like", value, "recevivePhone");
            return (Criteria) this;
        }

        public Criteria andRecevivePhoneIn(List<String> values) {
            addCriterion("recevive_phone in", values, "recevivePhone");
            return (Criteria) this;
        }

        public Criteria andRecevivePhoneNotIn(List<String> values) {
            addCriterion("recevive_phone not in", values, "recevivePhone");
            return (Criteria) this;
        }

        public Criteria andRecevivePhoneBetween(String value1, String value2) {
            addCriterion("recevive_phone between", value1, value2, "recevivePhone");
            return (Criteria) this;
        }

        public Criteria andRecevivePhoneNotBetween(String value1, String value2) {
            addCriterion("recevive_phone not between", value1, value2, "recevivePhone");
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

        public Criteria andOrderStatuesIsNull() {
            addCriterion("order_statues is null");
            return (Criteria) this;
        }

        public Criteria andOrderStatuesIsNotNull() {
            addCriterion("order_statues is not null");
            return (Criteria) this;
        }

        public Criteria andOrderStatuesEqualTo(String value) {
            addCriterion("order_statues =", value, "orderStatues");
            return (Criteria) this;
        }

        public Criteria andOrderStatuesNotEqualTo(String value) {
            addCriterion("order_statues <>", value, "orderStatues");
            return (Criteria) this;
        }

        public Criteria andOrderStatuesGreaterThan(String value) {
            addCriterion("order_statues >", value, "orderStatues");
            return (Criteria) this;
        }

        public Criteria andOrderStatuesGreaterThanOrEqualTo(String value) {
            addCriterion("order_statues >=", value, "orderStatues");
            return (Criteria) this;
        }

        public Criteria andOrderStatuesLessThan(String value) {
            addCriterion("order_statues <", value, "orderStatues");
            return (Criteria) this;
        }

        public Criteria andOrderStatuesLessThanOrEqualTo(String value) {
            addCriterion("order_statues <=", value, "orderStatues");
            return (Criteria) this;
        }

        public Criteria andOrderStatuesLike(String value) {
            addCriterion("order_statues like", value, "orderStatues");
            return (Criteria) this;
        }

        public Criteria andOrderStatuesNotLike(String value) {
            addCriterion("order_statues not like", value, "orderStatues");
            return (Criteria) this;
        }

        public Criteria andOrderStatuesIn(List<String> values) {
            addCriterion("order_statues in", values, "orderStatues");
            return (Criteria) this;
        }

        public Criteria andOrderStatuesNotIn(List<String> values) {
            addCriterion("order_statues not in", values, "orderStatues");
            return (Criteria) this;
        }

        public Criteria andOrderStatuesBetween(String value1, String value2) {
            addCriterion("order_statues between", value1, value2, "orderStatues");
            return (Criteria) this;
        }

        public Criteria andOrderStatuesNotBetween(String value1, String value2) {
            addCriterion("order_statues not between", value1, value2, "orderStatues");
            return (Criteria) this;
        }

        public Criteria andExpressNoIsNull() {
            addCriterion("express_no is null");
            return (Criteria) this;
        }

        public Criteria andExpressNoIsNotNull() {
            addCriterion("express_no is not null");
            return (Criteria) this;
        }

        public Criteria andExpressNoEqualTo(String value) {
            addCriterion("express_no =", value, "expressNo");
            return (Criteria) this;
        }

        public Criteria andExpressNoNotEqualTo(String value) {
            addCriterion("express_no <>", value, "expressNo");
            return (Criteria) this;
        }

        public Criteria andExpressNoGreaterThan(String value) {
            addCriterion("express_no >", value, "expressNo");
            return (Criteria) this;
        }

        public Criteria andExpressNoGreaterThanOrEqualTo(String value) {
            addCriterion("express_no >=", value, "expressNo");
            return (Criteria) this;
        }

        public Criteria andExpressNoLessThan(String value) {
            addCriterion("express_no <", value, "expressNo");
            return (Criteria) this;
        }

        public Criteria andExpressNoLessThanOrEqualTo(String value) {
            addCriterion("express_no <=", value, "expressNo");
            return (Criteria) this;
        }

        public Criteria andExpressNoLike(String value) {
            addCriterion("express_no like", value, "expressNo");
            return (Criteria) this;
        }

        public Criteria andExpressNoNotLike(String value) {
            addCriterion("express_no not like", value, "expressNo");
            return (Criteria) this;
        }

        public Criteria andExpressNoIn(List<String> values) {
            addCriterion("express_no in", values, "expressNo");
            return (Criteria) this;
        }

        public Criteria andExpressNoNotIn(List<String> values) {
            addCriterion("express_no not in", values, "expressNo");
            return (Criteria) this;
        }

        public Criteria andExpressNoBetween(String value1, String value2) {
            addCriterion("express_no between", value1, value2, "expressNo");
            return (Criteria) this;
        }

        public Criteria andExpressNoNotBetween(String value1, String value2) {
            addCriterion("express_no not between", value1, value2, "expressNo");
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

        public Criteria andSendbypostynIsNull() {
            addCriterion("sendByPostYN is null");
            return (Criteria) this;
        }

        public Criteria andSendbypostynIsNotNull() {
            addCriterion("sendByPostYN is not null");
            return (Criteria) this;
        }

        public Criteria andSendbypostynEqualTo(String value) {
            addCriterion("sendByPostYN =", value, "sendbypostyn");
            return (Criteria) this;
        }

        public Criteria andSendbypostynNotEqualTo(String value) {
            addCriterion("sendByPostYN <>", value, "sendbypostyn");
            return (Criteria) this;
        }

        public Criteria andSendbypostynGreaterThan(String value) {
            addCriterion("sendByPostYN >", value, "sendbypostyn");
            return (Criteria) this;
        }

        public Criteria andSendbypostynGreaterThanOrEqualTo(String value) {
            addCriterion("sendByPostYN >=", value, "sendbypostyn");
            return (Criteria) this;
        }

        public Criteria andSendbypostynLessThan(String value) {
            addCriterion("sendByPostYN <", value, "sendbypostyn");
            return (Criteria) this;
        }

        public Criteria andSendbypostynLessThanOrEqualTo(String value) {
            addCriterion("sendByPostYN <=", value, "sendbypostyn");
            return (Criteria) this;
        }

        public Criteria andSendbypostynLike(String value) {
            addCriterion("sendByPostYN like", value, "sendbypostyn");
            return (Criteria) this;
        }

        public Criteria andSendbypostynNotLike(String value) {
            addCriterion("sendByPostYN not like", value, "sendbypostyn");
            return (Criteria) this;
        }

        public Criteria andSendbypostynIn(List<String> values) {
            addCriterion("sendByPostYN in", values, "sendbypostyn");
            return (Criteria) this;
        }

        public Criteria andSendbypostynNotIn(List<String> values) {
            addCriterion("sendByPostYN not in", values, "sendbypostyn");
            return (Criteria) this;
        }

        public Criteria andSendbypostynBetween(String value1, String value2) {
            addCriterion("sendByPostYN between", value1, value2, "sendbypostyn");
            return (Criteria) this;
        }

        public Criteria andSendbypostynNotBetween(String value1, String value2) {
            addCriterion("sendByPostYN not between", value1, value2, "sendbypostyn");
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