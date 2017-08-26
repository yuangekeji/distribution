package com.distribution.dao.memberLevel.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class MemberLevelExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public MemberLevelExample() {
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

        public Criteria andLevelCategoryIsNull() {
            addCriterion("level_category is null");
            return (Criteria) this;
        }

        public Criteria andLevelCategoryIsNotNull() {
            addCriterion("level_category is not null");
            return (Criteria) this;
        }

        public Criteria andLevelCategoryEqualTo(Integer value) {
            addCriterion("level_category =", value, "levelCategory");
            return (Criteria) this;
        }

        public Criteria andLevelCategoryNotEqualTo(Integer value) {
            addCriterion("level_category <>", value, "levelCategory");
            return (Criteria) this;
        }

        public Criteria andLevelCategoryGreaterThan(Integer value) {
            addCriterion("level_category >", value, "levelCategory");
            return (Criteria) this;
        }

        public Criteria andLevelCategoryGreaterThanOrEqualTo(Integer value) {
            addCriterion("level_category >=", value, "levelCategory");
            return (Criteria) this;
        }

        public Criteria andLevelCategoryLessThan(Integer value) {
            addCriterion("level_category <", value, "levelCategory");
            return (Criteria) this;
        }

        public Criteria andLevelCategoryLessThanOrEqualTo(Integer value) {
            addCriterion("level_category <=", value, "levelCategory");
            return (Criteria) this;
        }

        public Criteria andLevelCategoryIn(List<Integer> values) {
            addCriterion("level_category in", values, "levelCategory");
            return (Criteria) this;
        }

        public Criteria andLevelCategoryNotIn(List<Integer> values) {
            addCriterion("level_category not in", values, "levelCategory");
            return (Criteria) this;
        }

        public Criteria andLevelCategoryBetween(Integer value1, Integer value2) {
            addCriterion("level_category between", value1, value2, "levelCategory");
            return (Criteria) this;
        }

        public Criteria andLevelCategoryNotBetween(Integer value1, Integer value2) {
            addCriterion("level_category not between", value1, value2, "levelCategory");
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