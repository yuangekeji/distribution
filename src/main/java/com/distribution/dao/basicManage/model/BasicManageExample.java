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

        public Criteria andBuyAmtIsNull() {
            addCriterion("buy_amt is null");
            return (Criteria) this;
        }

        public Criteria andBuyAmtIsNotNull() {
            addCriterion("buy_amt is not null");
            return (Criteria) this;
        }

        public Criteria andBuyAmtEqualTo(BigDecimal value) {
            addCriterion("buy_amt =", value, "buyAmt");
            return (Criteria) this;
        }

        public Criteria andBuyAmtNotEqualTo(BigDecimal value) {
            addCriterion("buy_amt <>", value, "buyAmt");
            return (Criteria) this;
        }

        public Criteria andBuyAmtGreaterThan(BigDecimal value) {
            addCriterion("buy_amt >", value, "buyAmt");
            return (Criteria) this;
        }

        public Criteria andBuyAmtGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("buy_amt >=", value, "buyAmt");
            return (Criteria) this;
        }

        public Criteria andBuyAmtLessThan(BigDecimal value) {
            addCriterion("buy_amt <", value, "buyAmt");
            return (Criteria) this;
        }

        public Criteria andBuyAmtLessThanOrEqualTo(BigDecimal value) {
            addCriterion("buy_amt <=", value, "buyAmt");
            return (Criteria) this;
        }

        public Criteria andBuyAmtIn(List<BigDecimal> values) {
            addCriterion("buy_amt in", values, "buyAmt");
            return (Criteria) this;
        }

        public Criteria andBuyAmtNotIn(List<BigDecimal> values) {
            addCriterion("buy_amt not in", values, "buyAmt");
            return (Criteria) this;
        }

        public Criteria andBuyAmtBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("buy_amt between", value1, value2, "buyAmt");
            return (Criteria) this;
        }

        public Criteria andBuyAmtNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("buy_amt not between", value1, value2, "buyAmt");
            return (Criteria) this;
        }

        public Criteria andBuyQtyIsNull() {
            addCriterion("buy_qty is null");
            return (Criteria) this;
        }

        public Criteria andBuyQtyIsNotNull() {
            addCriterion("buy_qty is not null");
            return (Criteria) this;
        }

        public Criteria andBuyQtyEqualTo(Integer value) {
            addCriterion("buy_qty =", value, "buyQty");
            return (Criteria) this;
        }

        public Criteria andBuyQtyNotEqualTo(Integer value) {
            addCriterion("buy_qty <>", value, "buyQty");
            return (Criteria) this;
        }

        public Criteria andBuyQtyGreaterThan(Integer value) {
            addCriterion("buy_qty >", value, "buyQty");
            return (Criteria) this;
        }

        public Criteria andBuyQtyGreaterThanOrEqualTo(Integer value) {
            addCriterion("buy_qty >=", value, "buyQty");
            return (Criteria) this;
        }

        public Criteria andBuyQtyLessThan(Integer value) {
            addCriterion("buy_qty <", value, "buyQty");
            return (Criteria) this;
        }

        public Criteria andBuyQtyLessThanOrEqualTo(Integer value) {
            addCriterion("buy_qty <=", value, "buyQty");
            return (Criteria) this;
        }

        public Criteria andBuyQtyIn(List<Integer> values) {
            addCriterion("buy_qty in", values, "buyQty");
            return (Criteria) this;
        }

        public Criteria andBuyQtyNotIn(List<Integer> values) {
            addCriterion("buy_qty not in", values, "buyQty");
            return (Criteria) this;
        }

        public Criteria andBuyQtyBetween(Integer value1, Integer value2) {
            addCriterion("buy_qty between", value1, value2, "buyQty");
            return (Criteria) this;
        }

        public Criteria andBuyQtyNotBetween(Integer value1, Integer value2) {
            addCriterion("buy_qty not between", value1, value2, "buyQty");
            return (Criteria) this;
        }

        public Criteria andDevidendCntIsNull() {
            addCriterion("devidend_cnt is null");
            return (Criteria) this;
        }

        public Criteria andDevidendCntIsNotNull() {
            addCriterion("devidend_cnt is not null");
            return (Criteria) this;
        }

        public Criteria andDevidendCntEqualTo(Integer value) {
            addCriterion("devidend_cnt =", value, "devidendCnt");
            return (Criteria) this;
        }

        public Criteria andDevidendCntNotEqualTo(Integer value) {
            addCriterion("devidend_cnt <>", value, "devidendCnt");
            return (Criteria) this;
        }

        public Criteria andDevidendCntGreaterThan(Integer value) {
            addCriterion("devidend_cnt >", value, "devidendCnt");
            return (Criteria) this;
        }

        public Criteria andDevidendCntGreaterThanOrEqualTo(Integer value) {
            addCriterion("devidend_cnt >=", value, "devidendCnt");
            return (Criteria) this;
        }

        public Criteria andDevidendCntLessThan(Integer value) {
            addCriterion("devidend_cnt <", value, "devidendCnt");
            return (Criteria) this;
        }

        public Criteria andDevidendCntLessThanOrEqualTo(Integer value) {
            addCriterion("devidend_cnt <=", value, "devidendCnt");
            return (Criteria) this;
        }

        public Criteria andDevidendCntIn(List<Integer> values) {
            addCriterion("devidend_cnt in", values, "devidendCnt");
            return (Criteria) this;
        }

        public Criteria andDevidendCntNotIn(List<Integer> values) {
            addCriterion("devidend_cnt not in", values, "devidendCnt");
            return (Criteria) this;
        }

        public Criteria andDevidendCntBetween(Integer value1, Integer value2) {
            addCriterion("devidend_cnt between", value1, value2, "devidendCnt");
            return (Criteria) this;
        }

        public Criteria andDevidendCntNotBetween(Integer value1, Integer value2) {
            addCriterion("devidend_cnt not between", value1, value2, "devidendCnt");
            return (Criteria) this;
        }

        public Criteria andSalesBonusPerIsNull() {
            addCriterion("sales_bonus_per is null");
            return (Criteria) this;
        }

        public Criteria andSalesBonusPerIsNotNull() {
            addCriterion("sales_bonus_per is not null");
            return (Criteria) this;
        }

        public Criteria andSalesBonusPerEqualTo(BigDecimal value) {
            addCriterion("sales_bonus_per =", value, "salesBonusPer");
            return (Criteria) this;
        }

        public Criteria andSalesBonusPerNotEqualTo(BigDecimal value) {
            addCriterion("sales_bonus_per <>", value, "salesBonusPer");
            return (Criteria) this;
        }

        public Criteria andSalesBonusPerGreaterThan(BigDecimal value) {
            addCriterion("sales_bonus_per >", value, "salesBonusPer");
            return (Criteria) this;
        }

        public Criteria andSalesBonusPerGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("sales_bonus_per >=", value, "salesBonusPer");
            return (Criteria) this;
        }

        public Criteria andSalesBonusPerLessThan(BigDecimal value) {
            addCriterion("sales_bonus_per <", value, "salesBonusPer");
            return (Criteria) this;
        }

        public Criteria andSalesBonusPerLessThanOrEqualTo(BigDecimal value) {
            addCriterion("sales_bonus_per <=", value, "salesBonusPer");
            return (Criteria) this;
        }

        public Criteria andSalesBonusPerIn(List<BigDecimal> values) {
            addCriterion("sales_bonus_per in", values, "salesBonusPer");
            return (Criteria) this;
        }

        public Criteria andSalesBonusPerNotIn(List<BigDecimal> values) {
            addCriterion("sales_bonus_per not in", values, "salesBonusPer");
            return (Criteria) this;
        }

        public Criteria andSalesBonusPerBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("sales_bonus_per between", value1, value2, "salesBonusPer");
            return (Criteria) this;
        }

        public Criteria andSalesBonusPerNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("sales_bonus_per not between", value1, value2, "salesBonusPer");
            return (Criteria) this;
        }

        public Criteria andSalesBonusPer1IsNull() {
            addCriterion("sales_bonus_per1 is null");
            return (Criteria) this;
        }

        public Criteria andSalesBonusPer1IsNotNull() {
            addCriterion("sales_bonus_per1 is not null");
            return (Criteria) this;
        }

        public Criteria andSalesBonusPer1EqualTo(BigDecimal value) {
            addCriterion("sales_bonus_per1 =", value, "salesBonusPer1");
            return (Criteria) this;
        }

        public Criteria andSalesBonusPer1NotEqualTo(BigDecimal value) {
            addCriterion("sales_bonus_per1 <>", value, "salesBonusPer1");
            return (Criteria) this;
        }

        public Criteria andSalesBonusPer1GreaterThan(BigDecimal value) {
            addCriterion("sales_bonus_per1 >", value, "salesBonusPer1");
            return (Criteria) this;
        }

        public Criteria andSalesBonusPer1GreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("sales_bonus_per1 >=", value, "salesBonusPer1");
            return (Criteria) this;
        }

        public Criteria andSalesBonusPer1LessThan(BigDecimal value) {
            addCriterion("sales_bonus_per1 <", value, "salesBonusPer1");
            return (Criteria) this;
        }

        public Criteria andSalesBonusPer1LessThanOrEqualTo(BigDecimal value) {
            addCriterion("sales_bonus_per1 <=", value, "salesBonusPer1");
            return (Criteria) this;
        }

        public Criteria andSalesBonusPer1In(List<BigDecimal> values) {
            addCriterion("sales_bonus_per1 in", values, "salesBonusPer1");
            return (Criteria) this;
        }

        public Criteria andSalesBonusPer1NotIn(List<BigDecimal> values) {
            addCriterion("sales_bonus_per1 not in", values, "salesBonusPer1");
            return (Criteria) this;
        }

        public Criteria andSalesBonusPer1Between(BigDecimal value1, BigDecimal value2) {
            addCriterion("sales_bonus_per1 between", value1, value2, "salesBonusPer1");
            return (Criteria) this;
        }

        public Criteria andSalesBonusPer1NotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("sales_bonus_per1 not between", value1, value2, "salesBonusPer1");
            return (Criteria) this;
        }

        public Criteria andSalesBonusPer2IsNull() {
            addCriterion("sales_bonus_per2 is null");
            return (Criteria) this;
        }

        public Criteria andSalesBonusPer2IsNotNull() {
            addCriterion("sales_bonus_per2 is not null");
            return (Criteria) this;
        }

        public Criteria andSalesBonusPer2EqualTo(BigDecimal value) {
            addCriterion("sales_bonus_per2 =", value, "salesBonusPer2");
            return (Criteria) this;
        }

        public Criteria andSalesBonusPer2NotEqualTo(BigDecimal value) {
            addCriterion("sales_bonus_per2 <>", value, "salesBonusPer2");
            return (Criteria) this;
        }

        public Criteria andSalesBonusPer2GreaterThan(BigDecimal value) {
            addCriterion("sales_bonus_per2 >", value, "salesBonusPer2");
            return (Criteria) this;
        }

        public Criteria andSalesBonusPer2GreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("sales_bonus_per2 >=", value, "salesBonusPer2");
            return (Criteria) this;
        }

        public Criteria andSalesBonusPer2LessThan(BigDecimal value) {
            addCriterion("sales_bonus_per2 <", value, "salesBonusPer2");
            return (Criteria) this;
        }

        public Criteria andSalesBonusPer2LessThanOrEqualTo(BigDecimal value) {
            addCriterion("sales_bonus_per2 <=", value, "salesBonusPer2");
            return (Criteria) this;
        }

        public Criteria andSalesBonusPer2In(List<BigDecimal> values) {
            addCriterion("sales_bonus_per2 in", values, "salesBonusPer2");
            return (Criteria) this;
        }

        public Criteria andSalesBonusPer2NotIn(List<BigDecimal> values) {
            addCriterion("sales_bonus_per2 not in", values, "salesBonusPer2");
            return (Criteria) this;
        }

        public Criteria andSalesBonusPer2Between(BigDecimal value1, BigDecimal value2) {
            addCriterion("sales_bonus_per2 between", value1, value2, "salesBonusPer2");
            return (Criteria) this;
        }

        public Criteria andSalesBonusPer2NotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("sales_bonus_per2 not between", value1, value2, "salesBonusPer2");
            return (Criteria) this;
        }

        public Criteria andProfitPerIsNull() {
            addCriterion("profit_per is null");
            return (Criteria) this;
        }

        public Criteria andProfitPerIsNotNull() {
            addCriterion("profit_per is not null");
            return (Criteria) this;
        }

        public Criteria andProfitPerEqualTo(BigDecimal value) {
            addCriterion("profit_per =", value, "profitPer");
            return (Criteria) this;
        }

        public Criteria andProfitPerNotEqualTo(BigDecimal value) {
            addCriterion("profit_per <>", value, "profitPer");
            return (Criteria) this;
        }

        public Criteria andProfitPerGreaterThan(BigDecimal value) {
            addCriterion("profit_per >", value, "profitPer");
            return (Criteria) this;
        }

        public Criteria andProfitPerGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("profit_per >=", value, "profitPer");
            return (Criteria) this;
        }

        public Criteria andProfitPerLessThan(BigDecimal value) {
            addCriterion("profit_per <", value, "profitPer");
            return (Criteria) this;
        }

        public Criteria andProfitPerLessThanOrEqualTo(BigDecimal value) {
            addCriterion("profit_per <=", value, "profitPer");
            return (Criteria) this;
        }

        public Criteria andProfitPerIn(List<BigDecimal> values) {
            addCriterion("profit_per in", values, "profitPer");
            return (Criteria) this;
        }

        public Criteria andProfitPerNotIn(List<BigDecimal> values) {
            addCriterion("profit_per not in", values, "profitPer");
            return (Criteria) this;
        }

        public Criteria andProfitPerBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("profit_per between", value1, value2, "profitPer");
            return (Criteria) this;
        }

        public Criteria andProfitPerNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("profit_per not between", value1, value2, "profitPer");
            return (Criteria) this;
        }

        public Criteria andDiscountIsNull() {
            addCriterion("discount is null");
            return (Criteria) this;
        }

        public Criteria andDiscountIsNotNull() {
            addCriterion("discount is not null");
            return (Criteria) this;
        }

        public Criteria andDiscountEqualTo(BigDecimal value) {
            addCriterion("discount =", value, "discount");
            return (Criteria) this;
        }

        public Criteria andDiscountNotEqualTo(BigDecimal value) {
            addCriterion("discount <>", value, "discount");
            return (Criteria) this;
        }

        public Criteria andDiscountGreaterThan(BigDecimal value) {
            addCriterion("discount >", value, "discount");
            return (Criteria) this;
        }

        public Criteria andDiscountGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("discount >=", value, "discount");
            return (Criteria) this;
        }

        public Criteria andDiscountLessThan(BigDecimal value) {
            addCriterion("discount <", value, "discount");
            return (Criteria) this;
        }

        public Criteria andDiscountLessThanOrEqualTo(BigDecimal value) {
            addCriterion("discount <=", value, "discount");
            return (Criteria) this;
        }

        public Criteria andDiscountIn(List<BigDecimal> values) {
            addCriterion("discount in", values, "discount");
            return (Criteria) this;
        }

        public Criteria andDiscountNotIn(List<BigDecimal> values) {
            addCriterion("discount not in", values, "discount");
            return (Criteria) this;
        }

        public Criteria andDiscountBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("discount between", value1, value2, "discount");
            return (Criteria) this;
        }

        public Criteria andDiscountNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("discount not between", value1, value2, "discount");
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

        public Criteria andRecommendCntIsNull() {
            addCriterion("recommend_cnt is null");
            return (Criteria) this;
        }

        public Criteria andRecommendCntIsNotNull() {
            addCriterion("recommend_cnt is not null");
            return (Criteria) this;
        }

        public Criteria andRecommendCntEqualTo(Integer value) {
            addCriterion("recommend_cnt =", value, "recommendCnt");
            return (Criteria) this;
        }

        public Criteria andRecommendCntNotEqualTo(Integer value) {
            addCriterion("recommend_cnt <>", value, "recommendCnt");
            return (Criteria) this;
        }

        public Criteria andRecommendCntGreaterThan(Integer value) {
            addCriterion("recommend_cnt >", value, "recommendCnt");
            return (Criteria) this;
        }

        public Criteria andRecommendCntGreaterThanOrEqualTo(Integer value) {
            addCriterion("recommend_cnt >=", value, "recommendCnt");
            return (Criteria) this;
        }

        public Criteria andRecommendCntLessThan(Integer value) {
            addCriterion("recommend_cnt <", value, "recommendCnt");
            return (Criteria) this;
        }

        public Criteria andRecommendCntLessThanOrEqualTo(Integer value) {
            addCriterion("recommend_cnt <=", value, "recommendCnt");
            return (Criteria) this;
        }

        public Criteria andRecommendCntIn(List<Integer> values) {
            addCriterion("recommend_cnt in", values, "recommendCnt");
            return (Criteria) this;
        }

        public Criteria andRecommendCntNotIn(List<Integer> values) {
            addCriterion("recommend_cnt not in", values, "recommendCnt");
            return (Criteria) this;
        }

        public Criteria andRecommendCntBetween(Integer value1, Integer value2) {
            addCriterion("recommend_cnt between", value1, value2, "recommendCnt");
            return (Criteria) this;
        }

        public Criteria andRecommendCntNotBetween(Integer value1, Integer value2) {
            addCriterion("recommend_cnt not between", value1, value2, "recommendCnt");
            return (Criteria) this;
        }

        public Criteria andRemainCntIsNull() {
            addCriterion("remain_cnt is null");
            return (Criteria) this;
        }

        public Criteria andRemainCntIsNotNull() {
            addCriterion("remain_cnt is not null");
            return (Criteria) this;
        }

        public Criteria andRemainCntEqualTo(Integer value) {
            addCriterion("remain_cnt =", value, "remainCnt");
            return (Criteria) this;
        }

        public Criteria andRemainCntNotEqualTo(Integer value) {
            addCriterion("remain_cnt <>", value, "remainCnt");
            return (Criteria) this;
        }

        public Criteria andRemainCntGreaterThan(Integer value) {
            addCriterion("remain_cnt >", value, "remainCnt");
            return (Criteria) this;
        }

        public Criteria andRemainCntGreaterThanOrEqualTo(Integer value) {
            addCriterion("remain_cnt >=", value, "remainCnt");
            return (Criteria) this;
        }

        public Criteria andRemainCntLessThan(Integer value) {
            addCriterion("remain_cnt <", value, "remainCnt");
            return (Criteria) this;
        }

        public Criteria andRemainCntLessThanOrEqualTo(Integer value) {
            addCriterion("remain_cnt <=", value, "remainCnt");
            return (Criteria) this;
        }

        public Criteria andRemainCntIn(List<Integer> values) {
            addCriterion("remain_cnt in", values, "remainCnt");
            return (Criteria) this;
        }

        public Criteria andRemainCntNotIn(List<Integer> values) {
            addCriterion("remain_cnt not in", values, "remainCnt");
            return (Criteria) this;
        }

        public Criteria andRemainCntBetween(Integer value1, Integer value2) {
            addCriterion("remain_cnt between", value1, value2, "remainCnt");
            return (Criteria) this;
        }

        public Criteria andRemainCntNotBetween(Integer value1, Integer value2) {
            addCriterion("remain_cnt not between", value1, value2, "remainCnt");
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