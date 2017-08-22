package com.distribution.dao.goods.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class GoodsExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public GoodsExample() {
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

        public Criteria andGoodsNameIsNull() {
            addCriterion("\"goods_name\" is null");
            return (Criteria) this;
        }

        public Criteria andGoodsNameIsNotNull() {
            addCriterion("\"goods_name\" is not null");
            return (Criteria) this;
        }

        public Criteria andGoodsNameEqualTo(String value) {
            addCriterion("\"goods_name\" =", value, "goodsName");
            return (Criteria) this;
        }

        public Criteria andGoodsNameNotEqualTo(String value) {
            addCriterion("\"goods_name\" <>", value, "goodsName");
            return (Criteria) this;
        }

        public Criteria andGoodsNameGreaterThan(String value) {
            addCriterion("\"goods_name\" >", value, "goodsName");
            return (Criteria) this;
        }

        public Criteria andGoodsNameGreaterThanOrEqualTo(String value) {
            addCriterion("\"goods_name\" >=", value, "goodsName");
            return (Criteria) this;
        }

        public Criteria andGoodsNameLessThan(String value) {
            addCriterion("\"goods_name\" <", value, "goodsName");
            return (Criteria) this;
        }

        public Criteria andGoodsNameLessThanOrEqualTo(String value) {
            addCriterion("\"goods_name\" <=", value, "goodsName");
            return (Criteria) this;
        }

        public Criteria andGoodsNameLike(String value) {
            addCriterion("\"goods_name\" like", value, "goodsName");
            return (Criteria) this;
        }

        public Criteria andGoodsNameNotLike(String value) {
            addCriterion("\"goods_name\" not like", value, "goodsName");
            return (Criteria) this;
        }

        public Criteria andGoodsNameIn(List<String> values) {
            addCriterion("\"goods_name\" in", values, "goodsName");
            return (Criteria) this;
        }

        public Criteria andGoodsNameNotIn(List<String> values) {
            addCriterion("\"goods_name\" not in", values, "goodsName");
            return (Criteria) this;
        }

        public Criteria andGoodsNameBetween(String value1, String value2) {
            addCriterion("\"goods_name\" between", value1, value2, "goodsName");
            return (Criteria) this;
        }

        public Criteria andGoodsNameNotBetween(String value1, String value2) {
            addCriterion("\"goods_name\" not between", value1, value2, "goodsName");
            return (Criteria) this;
        }

        public Criteria andGoodsNumIsNull() {
            addCriterion("\"goods_num\" is null");
            return (Criteria) this;
        }

        public Criteria andGoodsNumIsNotNull() {
            addCriterion("\"goods_num\" is not null");
            return (Criteria) this;
        }

        public Criteria andGoodsNumEqualTo(Integer value) {
            addCriterion("\"goods_num\" =", value, "goodsNum");
            return (Criteria) this;
        }

        public Criteria andGoodsNumNotEqualTo(Integer value) {
            addCriterion("\"goods_num\" <>", value, "goodsNum");
            return (Criteria) this;
        }

        public Criteria andGoodsNumGreaterThan(Integer value) {
            addCriterion("\"goods_num\" >", value, "goodsNum");
            return (Criteria) this;
        }

        public Criteria andGoodsNumGreaterThanOrEqualTo(Integer value) {
            addCriterion("\"goods_num\" >=", value, "goodsNum");
            return (Criteria) this;
        }

        public Criteria andGoodsNumLessThan(Integer value) {
            addCriterion("\"goods_num\" <", value, "goodsNum");
            return (Criteria) this;
        }

        public Criteria andGoodsNumLessThanOrEqualTo(Integer value) {
            addCriterion("\"goods_num\" <=", value, "goodsNum");
            return (Criteria) this;
        }

        public Criteria andGoodsNumIn(List<Integer> values) {
            addCriterion("\"goods_num\" in", values, "goodsNum");
            return (Criteria) this;
        }

        public Criteria andGoodsNumNotIn(List<Integer> values) {
            addCriterion("\"goods_num\" not in", values, "goodsNum");
            return (Criteria) this;
        }

        public Criteria andGoodsNumBetween(Integer value1, Integer value2) {
            addCriterion("\"goods_num\" between", value1, value2, "goodsNum");
            return (Criteria) this;
        }

        public Criteria andGoodsNumNotBetween(Integer value1, Integer value2) {
            addCriterion("\"goods_num\" not between", value1, value2, "goodsNum");
            return (Criteria) this;
        }

        public Criteria andGoodsPriceIsNull() {
            addCriterion("\"goods_price\" is null");
            return (Criteria) this;
        }

        public Criteria andGoodsPriceIsNotNull() {
            addCriterion("\"goods_price\" is not null");
            return (Criteria) this;
        }

        public Criteria andGoodsPriceEqualTo(BigDecimal value) {
            addCriterion("\"goods_price\" =", value, "goodsPrice");
            return (Criteria) this;
        }

        public Criteria andGoodsPriceNotEqualTo(BigDecimal value) {
            addCriterion("\"goods_price\" <>", value, "goodsPrice");
            return (Criteria) this;
        }

        public Criteria andGoodsPriceGreaterThan(BigDecimal value) {
            addCriterion("\"goods_price\" >", value, "goodsPrice");
            return (Criteria) this;
        }

        public Criteria andGoodsPriceGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("\"goods_price\" >=", value, "goodsPrice");
            return (Criteria) this;
        }

        public Criteria andGoodsPriceLessThan(BigDecimal value) {
            addCriterion("\"goods_price\" <", value, "goodsPrice");
            return (Criteria) this;
        }

        public Criteria andGoodsPriceLessThanOrEqualTo(BigDecimal value) {
            addCriterion("\"goods_price\" <=", value, "goodsPrice");
            return (Criteria) this;
        }

        public Criteria andGoodsPriceIn(List<BigDecimal> values) {
            addCriterion("\"goods_price\" in", values, "goodsPrice");
            return (Criteria) this;
        }

        public Criteria andGoodsPriceNotIn(List<BigDecimal> values) {
            addCriterion("\"goods_price\" not in", values, "goodsPrice");
            return (Criteria) this;
        }

        public Criteria andGoodsPriceBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("\"goods_price\" between", value1, value2, "goodsPrice");
            return (Criteria) this;
        }

        public Criteria andGoodsPriceNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("\"goods_price\" not between", value1, value2, "goodsPrice");
            return (Criteria) this;
        }

        public Criteria andListTimeIsNull() {
            addCriterion("\"list_time\" is null");
            return (Criteria) this;
        }

        public Criteria andListTimeIsNotNull() {
            addCriterion("\"list_time\" is not null");
            return (Criteria) this;
        }

        public Criteria andListTimeEqualTo(Date value) {
            addCriterion("\"list_time\" =", value, "listTime");
            return (Criteria) this;
        }

        public Criteria andListTimeNotEqualTo(Date value) {
            addCriterion("\"list_time\" <>", value, "listTime");
            return (Criteria) this;
        }

        public Criteria andListTimeGreaterThan(Date value) {
            addCriterion("\"list_time\" >", value, "listTime");
            return (Criteria) this;
        }

        public Criteria andListTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("\"list_time\" >=", value, "listTime");
            return (Criteria) this;
        }

        public Criteria andListTimeLessThan(Date value) {
            addCriterion("\"list_time\" <", value, "listTime");
            return (Criteria) this;
        }

        public Criteria andListTimeLessThanOrEqualTo(Date value) {
            addCriterion("\"list_time\" <=", value, "listTime");
            return (Criteria) this;
        }

        public Criteria andListTimeIn(List<Date> values) {
            addCriterion("\"list_time\" in", values, "listTime");
            return (Criteria) this;
        }

        public Criteria andListTimeNotIn(List<Date> values) {
            addCriterion("\"list_time\" not in", values, "listTime");
            return (Criteria) this;
        }

        public Criteria andListTimeBetween(Date value1, Date value2) {
            addCriterion("\"list_time\" between", value1, value2, "listTime");
            return (Criteria) this;
        }

        public Criteria andListTimeNotBetween(Date value1, Date value2) {
            addCriterion("\"list_time\" not between", value1, value2, "listTime");
            return (Criteria) this;
        }

        public Criteria andGoodsTypeIsNull() {
            addCriterion("\"goods_type\" is null");
            return (Criteria) this;
        }

        public Criteria andGoodsTypeIsNotNull() {
            addCriterion("\"goods_type\" is not null");
            return (Criteria) this;
        }

        public Criteria andGoodsTypeEqualTo(String value) {
            addCriterion("\"goods_type\" =", value, "goodsType");
            return (Criteria) this;
        }

        public Criteria andGoodsTypeNotEqualTo(String value) {
            addCriterion("\"goods_type\" <>", value, "goodsType");
            return (Criteria) this;
        }

        public Criteria andGoodsTypeGreaterThan(String value) {
            addCriterion("\"goods_type\" >", value, "goodsType");
            return (Criteria) this;
        }

        public Criteria andGoodsTypeGreaterThanOrEqualTo(String value) {
            addCriterion("\"goods_type\" >=", value, "goodsType");
            return (Criteria) this;
        }

        public Criteria andGoodsTypeLessThan(String value) {
            addCriterion("\"goods_type\" <", value, "goodsType");
            return (Criteria) this;
        }

        public Criteria andGoodsTypeLessThanOrEqualTo(String value) {
            addCriterion("\"goods_type\" <=", value, "goodsType");
            return (Criteria) this;
        }

        public Criteria andGoodsTypeLike(String value) {
            addCriterion("\"goods_type\" like", value, "goodsType");
            return (Criteria) this;
        }

        public Criteria andGoodsTypeNotLike(String value) {
            addCriterion("\"goods_type\" not like", value, "goodsType");
            return (Criteria) this;
        }

        public Criteria andGoodsTypeIn(List<String> values) {
            addCriterion("\"goods_type\" in", values, "goodsType");
            return (Criteria) this;
        }

        public Criteria andGoodsTypeNotIn(List<String> values) {
            addCriterion("\"goods_type\" not in", values, "goodsType");
            return (Criteria) this;
        }

        public Criteria andGoodsTypeBetween(String value1, String value2) {
            addCriterion("\"goods_type\" between", value1, value2, "goodsType");
            return (Criteria) this;
        }

        public Criteria andGoodsTypeNotBetween(String value1, String value2) {
            addCriterion("\"goods_type\" not between", value1, value2, "goodsType");
            return (Criteria) this;
        }

        public Criteria andSpecificationsIsNull() {
            addCriterion("\"specifications\" is null");
            return (Criteria) this;
        }

        public Criteria andSpecificationsIsNotNull() {
            addCriterion("\"specifications\" is not null");
            return (Criteria) this;
        }

        public Criteria andSpecificationsEqualTo(String value) {
            addCriterion("\"specifications\" =", value, "specifications");
            return (Criteria) this;
        }

        public Criteria andSpecificationsNotEqualTo(String value) {
            addCriterion("\"specifications\" <>", value, "specifications");
            return (Criteria) this;
        }

        public Criteria andSpecificationsGreaterThan(String value) {
            addCriterion("\"specifications\" >", value, "specifications");
            return (Criteria) this;
        }

        public Criteria andSpecificationsGreaterThanOrEqualTo(String value) {
            addCriterion("\"specifications\" >=", value, "specifications");
            return (Criteria) this;
        }

        public Criteria andSpecificationsLessThan(String value) {
            addCriterion("\"specifications\" <", value, "specifications");
            return (Criteria) this;
        }

        public Criteria andSpecificationsLessThanOrEqualTo(String value) {
            addCriterion("\"specifications\" <=", value, "specifications");
            return (Criteria) this;
        }

        public Criteria andSpecificationsLike(String value) {
            addCriterion("\"specifications\" like", value, "specifications");
            return (Criteria) this;
        }

        public Criteria andSpecificationsNotLike(String value) {
            addCriterion("\"specifications\" not like", value, "specifications");
            return (Criteria) this;
        }

        public Criteria andSpecificationsIn(List<String> values) {
            addCriterion("\"specifications\" in", values, "specifications");
            return (Criteria) this;
        }

        public Criteria andSpecificationsNotIn(List<String> values) {
            addCriterion("\"specifications\" not in", values, "specifications");
            return (Criteria) this;
        }

        public Criteria andSpecificationsBetween(String value1, String value2) {
            addCriterion("\"specifications\" between", value1, value2, "specifications");
            return (Criteria) this;
        }

        public Criteria andSpecificationsNotBetween(String value1, String value2) {
            addCriterion("\"specifications\" not between", value1, value2, "specifications");
            return (Criteria) this;
        }

        public Criteria andNetContentIsNull() {
            addCriterion("\"net_content\" is null");
            return (Criteria) this;
        }

        public Criteria andNetContentIsNotNull() {
            addCriterion("\"net_content\" is not null");
            return (Criteria) this;
        }

        public Criteria andNetContentEqualTo(String value) {
            addCriterion("\"net_content\" =", value, "netContent");
            return (Criteria) this;
        }

        public Criteria andNetContentNotEqualTo(String value) {
            addCriterion("\"net_content\" <>", value, "netContent");
            return (Criteria) this;
        }

        public Criteria andNetContentGreaterThan(String value) {
            addCriterion("\"net_content\" >", value, "netContent");
            return (Criteria) this;
        }

        public Criteria andNetContentGreaterThanOrEqualTo(String value) {
            addCriterion("\"net_content\" >=", value, "netContent");
            return (Criteria) this;
        }

        public Criteria andNetContentLessThan(String value) {
            addCriterion("\"net_content\" <", value, "netContent");
            return (Criteria) this;
        }

        public Criteria andNetContentLessThanOrEqualTo(String value) {
            addCriterion("\"net_content\" <=", value, "netContent");
            return (Criteria) this;
        }

        public Criteria andNetContentLike(String value) {
            addCriterion("\"net_content\" like", value, "netContent");
            return (Criteria) this;
        }

        public Criteria andNetContentNotLike(String value) {
            addCriterion("\"net_content\" not like", value, "netContent");
            return (Criteria) this;
        }

        public Criteria andNetContentIn(List<String> values) {
            addCriterion("\"net_content\" in", values, "netContent");
            return (Criteria) this;
        }

        public Criteria andNetContentNotIn(List<String> values) {
            addCriterion("\"net_content\" not in", values, "netContent");
            return (Criteria) this;
        }

        public Criteria andNetContentBetween(String value1, String value2) {
            addCriterion("\"net_content\" between", value1, value2, "netContent");
            return (Criteria) this;
        }

        public Criteria andNetContentNotBetween(String value1, String value2) {
            addCriterion("\"net_content\" not between", value1, value2, "netContent");
            return (Criteria) this;
        }

        public Criteria andProductStandardIsNull() {
            addCriterion("\"product_standard\" is null");
            return (Criteria) this;
        }

        public Criteria andProductStandardIsNotNull() {
            addCriterion("\"product_standard\" is not null");
            return (Criteria) this;
        }

        public Criteria andProductStandardEqualTo(String value) {
            addCriterion("\"product_standard\" =", value, "productStandard");
            return (Criteria) this;
        }

        public Criteria andProductStandardNotEqualTo(String value) {
            addCriterion("\"product_standard\" <>", value, "productStandard");
            return (Criteria) this;
        }

        public Criteria andProductStandardGreaterThan(String value) {
            addCriterion("\"product_standard\" >", value, "productStandard");
            return (Criteria) this;
        }

        public Criteria andProductStandardGreaterThanOrEqualTo(String value) {
            addCriterion("\"product_standard\" >=", value, "productStandard");
            return (Criteria) this;
        }

        public Criteria andProductStandardLessThan(String value) {
            addCriterion("\"product_standard\" <", value, "productStandard");
            return (Criteria) this;
        }

        public Criteria andProductStandardLessThanOrEqualTo(String value) {
            addCriterion("\"product_standard\" <=", value, "productStandard");
            return (Criteria) this;
        }

        public Criteria andProductStandardLike(String value) {
            addCriterion("\"product_standard\" like", value, "productStandard");
            return (Criteria) this;
        }

        public Criteria andProductStandardNotLike(String value) {
            addCriterion("\"product_standard\" not like", value, "productStandard");
            return (Criteria) this;
        }

        public Criteria andProductStandardIn(List<String> values) {
            addCriterion("\"product_standard\" in", values, "productStandard");
            return (Criteria) this;
        }

        public Criteria andProductStandardNotIn(List<String> values) {
            addCriterion("\"product_standard\" not in", values, "productStandard");
            return (Criteria) this;
        }

        public Criteria andProductStandardBetween(String value1, String value2) {
            addCriterion("\"product_standard\" between", value1, value2, "productStandard");
            return (Criteria) this;
        }

        public Criteria andProductStandardNotBetween(String value1, String value2) {
            addCriterion("\"product_standard\" not between", value1, value2, "productStandard");
            return (Criteria) this;
        }

        public Criteria andLicenseKeyIsNull() {
            addCriterion("\"license_key\" is null");
            return (Criteria) this;
        }

        public Criteria andLicenseKeyIsNotNull() {
            addCriterion("\"license_key\" is not null");
            return (Criteria) this;
        }

        public Criteria andLicenseKeyEqualTo(String value) {
            addCriterion("\"license_key\" =", value, "licenseKey");
            return (Criteria) this;
        }

        public Criteria andLicenseKeyNotEqualTo(String value) {
            addCriterion("\"license_key\" <>", value, "licenseKey");
            return (Criteria) this;
        }

        public Criteria andLicenseKeyGreaterThan(String value) {
            addCriterion("\"license_key\" >", value, "licenseKey");
            return (Criteria) this;
        }

        public Criteria andLicenseKeyGreaterThanOrEqualTo(String value) {
            addCriterion("\"license_key\" >=", value, "licenseKey");
            return (Criteria) this;
        }

        public Criteria andLicenseKeyLessThan(String value) {
            addCriterion("\"license_key\" <", value, "licenseKey");
            return (Criteria) this;
        }

        public Criteria andLicenseKeyLessThanOrEqualTo(String value) {
            addCriterion("\"license_key\" <=", value, "licenseKey");
            return (Criteria) this;
        }

        public Criteria andLicenseKeyLike(String value) {
            addCriterion("\"license_key\" like", value, "licenseKey");
            return (Criteria) this;
        }

        public Criteria andLicenseKeyNotLike(String value) {
            addCriterion("\"license_key\" not like", value, "licenseKey");
            return (Criteria) this;
        }

        public Criteria andLicenseKeyIn(List<String> values) {
            addCriterion("\"license_key\" in", values, "licenseKey");
            return (Criteria) this;
        }

        public Criteria andLicenseKeyNotIn(List<String> values) {
            addCriterion("\"license_key\" not in", values, "licenseKey");
            return (Criteria) this;
        }

        public Criteria andLicenseKeyBetween(String value1, String value2) {
            addCriterion("\"license_key\" between", value1, value2, "licenseKey");
            return (Criteria) this;
        }

        public Criteria andLicenseKeyNotBetween(String value1, String value2) {
            addCriterion("\"license_key\" not between", value1, value2, "licenseKey");
            return (Criteria) this;
        }

        public Criteria andStorageConditionsIsNull() {
            addCriterion("\"storage_conditions\" is null");
            return (Criteria) this;
        }

        public Criteria andStorageConditionsIsNotNull() {
            addCriterion("\"storage_conditions\" is not null");
            return (Criteria) this;
        }

        public Criteria andStorageConditionsEqualTo(String value) {
            addCriterion("\"storage_conditions\" =", value, "storageConditions");
            return (Criteria) this;
        }

        public Criteria andStorageConditionsNotEqualTo(String value) {
            addCriterion("\"storage_conditions\" <>", value, "storageConditions");
            return (Criteria) this;
        }

        public Criteria andStorageConditionsGreaterThan(String value) {
            addCriterion("\"storage_conditions\" >", value, "storageConditions");
            return (Criteria) this;
        }

        public Criteria andStorageConditionsGreaterThanOrEqualTo(String value) {
            addCriterion("\"storage_conditions\" >=", value, "storageConditions");
            return (Criteria) this;
        }

        public Criteria andStorageConditionsLessThan(String value) {
            addCriterion("\"storage_conditions\" <", value, "storageConditions");
            return (Criteria) this;
        }

        public Criteria andStorageConditionsLessThanOrEqualTo(String value) {
            addCriterion("\"storage_conditions\" <=", value, "storageConditions");
            return (Criteria) this;
        }

        public Criteria andStorageConditionsLike(String value) {
            addCriterion("\"storage_conditions\" like", value, "storageConditions");
            return (Criteria) this;
        }

        public Criteria andStorageConditionsNotLike(String value) {
            addCriterion("\"storage_conditions\" not like", value, "storageConditions");
            return (Criteria) this;
        }

        public Criteria andStorageConditionsIn(List<String> values) {
            addCriterion("\"storage_conditions\" in", values, "storageConditions");
            return (Criteria) this;
        }

        public Criteria andStorageConditionsNotIn(List<String> values) {
            addCriterion("\"storage_conditions\" not in", values, "storageConditions");
            return (Criteria) this;
        }

        public Criteria andStorageConditionsBetween(String value1, String value2) {
            addCriterion("\"storage_conditions\" between", value1, value2, "storageConditions");
            return (Criteria) this;
        }

        public Criteria andStorageConditionsNotBetween(String value1, String value2) {
            addCriterion("\"storage_conditions\" not between", value1, value2, "storageConditions");
            return (Criteria) this;
        }

        public Criteria andQualityGuaranteePeriodIsNull() {
            addCriterion("\"quality_guarantee_period\" is null");
            return (Criteria) this;
        }

        public Criteria andQualityGuaranteePeriodIsNotNull() {
            addCriterion("\"quality_guarantee_period\" is not null");
            return (Criteria) this;
        }

        public Criteria andQualityGuaranteePeriodEqualTo(String value) {
            addCriterion("\"quality_guarantee_period\" =", value, "qualityGuaranteePeriod");
            return (Criteria) this;
        }

        public Criteria andQualityGuaranteePeriodNotEqualTo(String value) {
            addCriterion("\"quality_guarantee_period\" <>", value, "qualityGuaranteePeriod");
            return (Criteria) this;
        }

        public Criteria andQualityGuaranteePeriodGreaterThan(String value) {
            addCriterion("\"quality_guarantee_period\" >", value, "qualityGuaranteePeriod");
            return (Criteria) this;
        }

        public Criteria andQualityGuaranteePeriodGreaterThanOrEqualTo(String value) {
            addCriterion("\"quality_guarantee_period\" >=", value, "qualityGuaranteePeriod");
            return (Criteria) this;
        }

        public Criteria andQualityGuaranteePeriodLessThan(String value) {
            addCriterion("\"quality_guarantee_period\" <", value, "qualityGuaranteePeriod");
            return (Criteria) this;
        }

        public Criteria andQualityGuaranteePeriodLessThanOrEqualTo(String value) {
            addCriterion("\"quality_guarantee_period\" <=", value, "qualityGuaranteePeriod");
            return (Criteria) this;
        }

        public Criteria andQualityGuaranteePeriodLike(String value) {
            addCriterion("\"quality_guarantee_period\" like", value, "qualityGuaranteePeriod");
            return (Criteria) this;
        }

        public Criteria andQualityGuaranteePeriodNotLike(String value) {
            addCriterion("\"quality_guarantee_period\" not like", value, "qualityGuaranteePeriod");
            return (Criteria) this;
        }

        public Criteria andQualityGuaranteePeriodIn(List<String> values) {
            addCriterion("\"quality_guarantee_period\" in", values, "qualityGuaranteePeriod");
            return (Criteria) this;
        }

        public Criteria andQualityGuaranteePeriodNotIn(List<String> values) {
            addCriterion("\"quality_guarantee_period\" not in", values, "qualityGuaranteePeriod");
            return (Criteria) this;
        }

        public Criteria andQualityGuaranteePeriodBetween(String value1, String value2) {
            addCriterion("\"quality_guarantee_period\" between", value1, value2, "qualityGuaranteePeriod");
            return (Criteria) this;
        }

        public Criteria andQualityGuaranteePeriodNotBetween(String value1, String value2) {
            addCriterion("\"quality_guarantee_period\" not between", value1, value2, "qualityGuaranteePeriod");
            return (Criteria) this;
        }

        public Criteria andEdibleMethodIsNull() {
            addCriterion("\"edible_method\" is null");
            return (Criteria) this;
        }

        public Criteria andEdibleMethodIsNotNull() {
            addCriterion("\"edible_method\" is not null");
            return (Criteria) this;
        }

        public Criteria andEdibleMethodEqualTo(String value) {
            addCriterion("\"edible_method\" =", value, "edibleMethod");
            return (Criteria) this;
        }

        public Criteria andEdibleMethodNotEqualTo(String value) {
            addCriterion("\"edible_method\" <>", value, "edibleMethod");
            return (Criteria) this;
        }

        public Criteria andEdibleMethodGreaterThan(String value) {
            addCriterion("\"edible_method\" >", value, "edibleMethod");
            return (Criteria) this;
        }

        public Criteria andEdibleMethodGreaterThanOrEqualTo(String value) {
            addCriterion("\"edible_method\" >=", value, "edibleMethod");
            return (Criteria) this;
        }

        public Criteria andEdibleMethodLessThan(String value) {
            addCriterion("\"edible_method\" <", value, "edibleMethod");
            return (Criteria) this;
        }

        public Criteria andEdibleMethodLessThanOrEqualTo(String value) {
            addCriterion("\"edible_method\" <=", value, "edibleMethod");
            return (Criteria) this;
        }

        public Criteria andEdibleMethodLike(String value) {
            addCriterion("\"edible_method\" like", value, "edibleMethod");
            return (Criteria) this;
        }

        public Criteria andEdibleMethodNotLike(String value) {
            addCriterion("\"edible_method\" not like", value, "edibleMethod");
            return (Criteria) this;
        }

        public Criteria andEdibleMethodIn(List<String> values) {
            addCriterion("\"edible_method\" in", values, "edibleMethod");
            return (Criteria) this;
        }

        public Criteria andEdibleMethodNotIn(List<String> values) {
            addCriterion("\"edible_method\" not in", values, "edibleMethod");
            return (Criteria) this;
        }

        public Criteria andEdibleMethodBetween(String value1, String value2) {
            addCriterion("\"edible_method\" between", value1, value2, "edibleMethod");
            return (Criteria) this;
        }

        public Criteria andEdibleMethodNotBetween(String value1, String value2) {
            addCriterion("\"edible_method\" not between", value1, value2, "edibleMethod");
            return (Criteria) this;
        }

        public Criteria andMattersNeedingAttentionIsNull() {
            addCriterion("\"matters_needing_attention\" is null");
            return (Criteria) this;
        }

        public Criteria andMattersNeedingAttentionIsNotNull() {
            addCriterion("\"matters_needing_attention\" is not null");
            return (Criteria) this;
        }

        public Criteria andMattersNeedingAttentionEqualTo(String value) {
            addCriterion("\"matters_needing_attention\" =", value, "mattersNeedingAttention");
            return (Criteria) this;
        }

        public Criteria andMattersNeedingAttentionNotEqualTo(String value) {
            addCriterion("\"matters_needing_attention\" <>", value, "mattersNeedingAttention");
            return (Criteria) this;
        }

        public Criteria andMattersNeedingAttentionGreaterThan(String value) {
            addCriterion("\"matters_needing_attention\" >", value, "mattersNeedingAttention");
            return (Criteria) this;
        }

        public Criteria andMattersNeedingAttentionGreaterThanOrEqualTo(String value) {
            addCriterion("\"matters_needing_attention\" >=", value, "mattersNeedingAttention");
            return (Criteria) this;
        }

        public Criteria andMattersNeedingAttentionLessThan(String value) {
            addCriterion("\"matters_needing_attention\" <", value, "mattersNeedingAttention");
            return (Criteria) this;
        }

        public Criteria andMattersNeedingAttentionLessThanOrEqualTo(String value) {
            addCriterion("\"matters_needing_attention\" <=", value, "mattersNeedingAttention");
            return (Criteria) this;
        }

        public Criteria andMattersNeedingAttentionLike(String value) {
            addCriterion("\"matters_needing_attention\" like", value, "mattersNeedingAttention");
            return (Criteria) this;
        }

        public Criteria andMattersNeedingAttentionNotLike(String value) {
            addCriterion("\"matters_needing_attention\" not like", value, "mattersNeedingAttention");
            return (Criteria) this;
        }

        public Criteria andMattersNeedingAttentionIn(List<String> values) {
            addCriterion("\"matters_needing_attention\" in", values, "mattersNeedingAttention");
            return (Criteria) this;
        }

        public Criteria andMattersNeedingAttentionNotIn(List<String> values) {
            addCriterion("\"matters_needing_attention\" not in", values, "mattersNeedingAttention");
            return (Criteria) this;
        }

        public Criteria andMattersNeedingAttentionBetween(String value1, String value2) {
            addCriterion("\"matters_needing_attention\" between", value1, value2, "mattersNeedingAttention");
            return (Criteria) this;
        }

        public Criteria andMattersNeedingAttentionNotBetween(String value1, String value2) {
            addCriterion("\"matters_needing_attention\" not between", value1, value2, "mattersNeedingAttention");
            return (Criteria) this;
        }

        public Criteria andManufacturerIsNull() {
            addCriterion("\"manufacturer\" is null");
            return (Criteria) this;
        }

        public Criteria andManufacturerIsNotNull() {
            addCriterion("\"manufacturer\" is not null");
            return (Criteria) this;
        }

        public Criteria andManufacturerEqualTo(String value) {
            addCriterion("\"manufacturer\" =", value, "manufacturer");
            return (Criteria) this;
        }

        public Criteria andManufacturerNotEqualTo(String value) {
            addCriterion("\"manufacturer\" <>", value, "manufacturer");
            return (Criteria) this;
        }

        public Criteria andManufacturerGreaterThan(String value) {
            addCriterion("\"manufacturer\" >", value, "manufacturer");
            return (Criteria) this;
        }

        public Criteria andManufacturerGreaterThanOrEqualTo(String value) {
            addCriterion("\"manufacturer\" >=", value, "manufacturer");
            return (Criteria) this;
        }

        public Criteria andManufacturerLessThan(String value) {
            addCriterion("\"manufacturer\" <", value, "manufacturer");
            return (Criteria) this;
        }

        public Criteria andManufacturerLessThanOrEqualTo(String value) {
            addCriterion("\"manufacturer\" <=", value, "manufacturer");
            return (Criteria) this;
        }

        public Criteria andManufacturerLike(String value) {
            addCriterion("\"manufacturer\" like", value, "manufacturer");
            return (Criteria) this;
        }

        public Criteria andManufacturerNotLike(String value) {
            addCriterion("\"manufacturer\" not like", value, "manufacturer");
            return (Criteria) this;
        }

        public Criteria andManufacturerIn(List<String> values) {
            addCriterion("\"manufacturer\" in", values, "manufacturer");
            return (Criteria) this;
        }

        public Criteria andManufacturerNotIn(List<String> values) {
            addCriterion("\"manufacturer\" not in", values, "manufacturer");
            return (Criteria) this;
        }

        public Criteria andManufacturerBetween(String value1, String value2) {
            addCriterion("\"manufacturer\" between", value1, value2, "manufacturer");
            return (Criteria) this;
        }

        public Criteria andManufacturerNotBetween(String value1, String value2) {
            addCriterion("\"manufacturer\" not between", value1, value2, "manufacturer");
            return (Criteria) this;
        }

        public Criteria andAddressIsNull() {
            addCriterion("\"address\" is null");
            return (Criteria) this;
        }

        public Criteria andAddressIsNotNull() {
            addCriterion("\"address\" is not null");
            return (Criteria) this;
        }

        public Criteria andAddressEqualTo(String value) {
            addCriterion("\"address\" =", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressNotEqualTo(String value) {
            addCriterion("\"address\" <>", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressGreaterThan(String value) {
            addCriterion("\"address\" >", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressGreaterThanOrEqualTo(String value) {
            addCriterion("\"address\" >=", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressLessThan(String value) {
            addCriterion("\"address\" <", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressLessThanOrEqualTo(String value) {
            addCriterion("\"address\" <=", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressLike(String value) {
            addCriterion("\"address\" like", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressNotLike(String value) {
            addCriterion("\"address\" not like", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressIn(List<String> values) {
            addCriterion("\"address\" in", values, "address");
            return (Criteria) this;
        }

        public Criteria andAddressNotIn(List<String> values) {
            addCriterion("\"address\" not in", values, "address");
            return (Criteria) this;
        }

        public Criteria andAddressBetween(String value1, String value2) {
            addCriterion("\"address\" between", value1, value2, "address");
            return (Criteria) this;
        }

        public Criteria andAddressNotBetween(String value1, String value2) {
            addCriterion("\"address\" not between", value1, value2, "address");
            return (Criteria) this;
        }

        public Criteria andPlaceOfOriginIsNull() {
            addCriterion("\"place_of_origin\" is null");
            return (Criteria) this;
        }

        public Criteria andPlaceOfOriginIsNotNull() {
            addCriterion("\"place_of_origin\" is not null");
            return (Criteria) this;
        }

        public Criteria andPlaceOfOriginEqualTo(String value) {
            addCriterion("\"place_of_origin\" =", value, "placeOfOrigin");
            return (Criteria) this;
        }

        public Criteria andPlaceOfOriginNotEqualTo(String value) {
            addCriterion("\"place_of_origin\" <>", value, "placeOfOrigin");
            return (Criteria) this;
        }

        public Criteria andPlaceOfOriginGreaterThan(String value) {
            addCriterion("\"place_of_origin\" >", value, "placeOfOrigin");
            return (Criteria) this;
        }

        public Criteria andPlaceOfOriginGreaterThanOrEqualTo(String value) {
            addCriterion("\"place_of_origin\" >=", value, "placeOfOrigin");
            return (Criteria) this;
        }

        public Criteria andPlaceOfOriginLessThan(String value) {
            addCriterion("\"place_of_origin\" <", value, "placeOfOrigin");
            return (Criteria) this;
        }

        public Criteria andPlaceOfOriginLessThanOrEqualTo(String value) {
            addCriterion("\"place_of_origin\" <=", value, "placeOfOrigin");
            return (Criteria) this;
        }

        public Criteria andPlaceOfOriginLike(String value) {
            addCriterion("\"place_of_origin\" like", value, "placeOfOrigin");
            return (Criteria) this;
        }

        public Criteria andPlaceOfOriginNotLike(String value) {
            addCriterion("\"place_of_origin\" not like", value, "placeOfOrigin");
            return (Criteria) this;
        }

        public Criteria andPlaceOfOriginIn(List<String> values) {
            addCriterion("\"place_of_origin\" in", values, "placeOfOrigin");
            return (Criteria) this;
        }

        public Criteria andPlaceOfOriginNotIn(List<String> values) {
            addCriterion("\"place_of_origin\" not in", values, "placeOfOrigin");
            return (Criteria) this;
        }

        public Criteria andPlaceOfOriginBetween(String value1, String value2) {
            addCriterion("\"place_of_origin\" between", value1, value2, "placeOfOrigin");
            return (Criteria) this;
        }

        public Criteria andPlaceOfOriginNotBetween(String value1, String value2) {
            addCriterion("\"place_of_origin\" not between", value1, value2, "placeOfOrigin");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNull() {
            addCriterion("\"create_time\" is null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNotNull() {
            addCriterion("\"create_time\" is not null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeEqualTo(Date value) {
            addCriterion("\"create_time\" =", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotEqualTo(Date value) {
            addCriterion("\"create_time\" <>", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThan(Date value) {
            addCriterion("\"create_time\" >", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("\"create_time\" >=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThan(Date value) {
            addCriterion("\"create_time\" <", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThanOrEqualTo(Date value) {
            addCriterion("\"create_time\" <=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIn(List<Date> values) {
            addCriterion("\"create_time\" in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotIn(List<Date> values) {
            addCriterion("\"create_time\" not in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeBetween(Date value1, Date value2) {
            addCriterion("\"create_time\" between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotBetween(Date value1, Date value2) {
            addCriterion("\"create_time\" not between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andDeleteFlagIsNull() {
            addCriterion("\"delete_flag\" is null");
            return (Criteria) this;
        }

        public Criteria andDeleteFlagIsNotNull() {
            addCriterion("\"delete_flag\" is not null");
            return (Criteria) this;
        }

        public Criteria andDeleteFlagEqualTo(String value) {
            addCriterion("\"delete_flag\" =", value, "deleteFlag");
            return (Criteria) this;
        }

        public Criteria andDeleteFlagNotEqualTo(String value) {
            addCriterion("\"delete_flag\" <>", value, "deleteFlag");
            return (Criteria) this;
        }

        public Criteria andDeleteFlagGreaterThan(String value) {
            addCriterion("\"delete_flag\" >", value, "deleteFlag");
            return (Criteria) this;
        }

        public Criteria andDeleteFlagGreaterThanOrEqualTo(String value) {
            addCriterion("\"delete_flag\" >=", value, "deleteFlag");
            return (Criteria) this;
        }

        public Criteria andDeleteFlagLessThan(String value) {
            addCriterion("\"delete_flag\" <", value, "deleteFlag");
            return (Criteria) this;
        }

        public Criteria andDeleteFlagLessThanOrEqualTo(String value) {
            addCriterion("\"delete_flag\" <=", value, "deleteFlag");
            return (Criteria) this;
        }

        public Criteria andDeleteFlagLike(String value) {
            addCriterion("\"delete_flag\" like", value, "deleteFlag");
            return (Criteria) this;
        }

        public Criteria andDeleteFlagNotLike(String value) {
            addCriterion("\"delete_flag\" not like", value, "deleteFlag");
            return (Criteria) this;
        }

        public Criteria andDeleteFlagIn(List<String> values) {
            addCriterion("\"delete_flag\" in", values, "deleteFlag");
            return (Criteria) this;
        }

        public Criteria andDeleteFlagNotIn(List<String> values) {
            addCriterion("\"delete_flag\" not in", values, "deleteFlag");
            return (Criteria) this;
        }

        public Criteria andDeleteFlagBetween(String value1, String value2) {
            addCriterion("\"delete_flag\" between", value1, value2, "deleteFlag");
            return (Criteria) this;
        }

        public Criteria andDeleteFlagNotBetween(String value1, String value2) {
            addCriterion("\"delete_flag\" not between", value1, value2, "deleteFlag");
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