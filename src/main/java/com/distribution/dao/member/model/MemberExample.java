package com.distribution.dao.member.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MemberExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public MemberExample() {
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

        public Criteria andMemberPhoneIsNull() {
            addCriterion("\"member_phone\" is null");
            return (Criteria) this;
        }

        public Criteria andMemberPhoneIsNotNull() {
            addCriterion("\"member_phone\" is not null");
            return (Criteria) this;
        }

        public Criteria andMemberPhoneEqualTo(String value) {
            addCriterion("\"member_phone\" =", value, "memberPhone");
            return (Criteria) this;
        }

        public Criteria andMemberPhoneNotEqualTo(String value) {
            addCriterion("\"member_phone\" <>", value, "memberPhone");
            return (Criteria) this;
        }

        public Criteria andMemberPhoneGreaterThan(String value) {
            addCriterion("\"member_phone\" >", value, "memberPhone");
            return (Criteria) this;
        }

        public Criteria andMemberPhoneGreaterThanOrEqualTo(String value) {
            addCriterion("\"member_phone\" >=", value, "memberPhone");
            return (Criteria) this;
        }

        public Criteria andMemberPhoneLessThan(String value) {
            addCriterion("\"member_phone\" <", value, "memberPhone");
            return (Criteria) this;
        }

        public Criteria andMemberPhoneLessThanOrEqualTo(String value) {
            addCriterion("\"member_phone\" <=", value, "memberPhone");
            return (Criteria) this;
        }

        public Criteria andMemberPhoneLike(String value) {
            addCriterion("\"member_phone\" like", value, "memberPhone");
            return (Criteria) this;
        }

        public Criteria andMemberPhoneNotLike(String value) {
            addCriterion("\"member_phone\" not like", value, "memberPhone");
            return (Criteria) this;
        }

        public Criteria andMemberPhoneIn(List<String> values) {
            addCriterion("\"member_phone\" in", values, "memberPhone");
            return (Criteria) this;
        }

        public Criteria andMemberPhoneNotIn(List<String> values) {
            addCriterion("\"member_phone\" not in", values, "memberPhone");
            return (Criteria) this;
        }

        public Criteria andMemberPhoneBetween(String value1, String value2) {
            addCriterion("\"member_phone\" between", value1, value2, "memberPhone");
            return (Criteria) this;
        }

        public Criteria andMemberPhoneNotBetween(String value1, String value2) {
            addCriterion("\"member_phone\" not between", value1, value2, "memberPhone");
            return (Criteria) this;
        }

        public Criteria andLoginPasswordIsNull() {
            addCriterion("\"login_password\" is null");
            return (Criteria) this;
        }

        public Criteria andLoginPasswordIsNotNull() {
            addCriterion("\"login_password\" is not null");
            return (Criteria) this;
        }

        public Criteria andLoginPasswordEqualTo(String value) {
            addCriterion("\"login_password\" =", value, "loginPassword");
            return (Criteria) this;
        }

        public Criteria andLoginPasswordNotEqualTo(String value) {
            addCriterion("\"login_password\" <>", value, "loginPassword");
            return (Criteria) this;
        }

        public Criteria andLoginPasswordGreaterThan(String value) {
            addCriterion("\"login_password\" >", value, "loginPassword");
            return (Criteria) this;
        }

        public Criteria andLoginPasswordGreaterThanOrEqualTo(String value) {
            addCriterion("\"login_password\" >=", value, "loginPassword");
            return (Criteria) this;
        }

        public Criteria andLoginPasswordLessThan(String value) {
            addCriterion("\"login_password\" <", value, "loginPassword");
            return (Criteria) this;
        }

        public Criteria andLoginPasswordLessThanOrEqualTo(String value) {
            addCriterion("\"login_password\" <=", value, "loginPassword");
            return (Criteria) this;
        }

        public Criteria andLoginPasswordLike(String value) {
            addCriterion("\"login_password\" like", value, "loginPassword");
            return (Criteria) this;
        }

        public Criteria andLoginPasswordNotLike(String value) {
            addCriterion("\"login_password\" not like", value, "loginPassword");
            return (Criteria) this;
        }

        public Criteria andLoginPasswordIn(List<String> values) {
            addCriterion("\"login_password\" in", values, "loginPassword");
            return (Criteria) this;
        }

        public Criteria andLoginPasswordNotIn(List<String> values) {
            addCriterion("\"login_password\" not in", values, "loginPassword");
            return (Criteria) this;
        }

        public Criteria andLoginPasswordBetween(String value1, String value2) {
            addCriterion("\"login_password\" between", value1, value2, "loginPassword");
            return (Criteria) this;
        }

        public Criteria andLoginPasswordNotBetween(String value1, String value2) {
            addCriterion("\"login_password\" not between", value1, value2, "loginPassword");
            return (Criteria) this;
        }

        public Criteria andQueryPasswordIsNull() {
            addCriterion("\"query_password\" is null");
            return (Criteria) this;
        }

        public Criteria andQueryPasswordIsNotNull() {
            addCriterion("\"query_password\" is not null");
            return (Criteria) this;
        }

        public Criteria andQueryPasswordEqualTo(String value) {
            addCriterion("\"query_password\" =", value, "queryPassword");
            return (Criteria) this;
        }

        public Criteria andQueryPasswordNotEqualTo(String value) {
            addCriterion("\"query_password\" <>", value, "queryPassword");
            return (Criteria) this;
        }

        public Criteria andQueryPasswordGreaterThan(String value) {
            addCriterion("\"query_password\" >", value, "queryPassword");
            return (Criteria) this;
        }

        public Criteria andQueryPasswordGreaterThanOrEqualTo(String value) {
            addCriterion("\"query_password\" >=", value, "queryPassword");
            return (Criteria) this;
        }

        public Criteria andQueryPasswordLessThan(String value) {
            addCriterion("\"query_password\" <", value, "queryPassword");
            return (Criteria) this;
        }

        public Criteria andQueryPasswordLessThanOrEqualTo(String value) {
            addCriterion("\"query_password\" <=", value, "queryPassword");
            return (Criteria) this;
        }

        public Criteria andQueryPasswordLike(String value) {
            addCriterion("\"query_password\" like", value, "queryPassword");
            return (Criteria) this;
        }

        public Criteria andQueryPasswordNotLike(String value) {
            addCriterion("\"query_password\" not like", value, "queryPassword");
            return (Criteria) this;
        }

        public Criteria andQueryPasswordIn(List<String> values) {
            addCriterion("\"query_password\" in", values, "queryPassword");
            return (Criteria) this;
        }

        public Criteria andQueryPasswordNotIn(List<String> values) {
            addCriterion("\"query_password\" not in", values, "queryPassword");
            return (Criteria) this;
        }

        public Criteria andQueryPasswordBetween(String value1, String value2) {
            addCriterion("\"query_password\" between", value1, value2, "queryPassword");
            return (Criteria) this;
        }

        public Criteria andQueryPasswordNotBetween(String value1, String value2) {
            addCriterion("\"query_password\" not between", value1, value2, "queryPassword");
            return (Criteria) this;
        }

        public Criteria andPayPasswordIsNull() {
            addCriterion("\"pay_password\" is null");
            return (Criteria) this;
        }

        public Criteria andPayPasswordIsNotNull() {
            addCriterion("\"pay_password\" is not null");
            return (Criteria) this;
        }

        public Criteria andPayPasswordEqualTo(String value) {
            addCriterion("\"pay_password\" =", value, "payPassword");
            return (Criteria) this;
        }

        public Criteria andPayPasswordNotEqualTo(String value) {
            addCriterion("\"pay_password\" <>", value, "payPassword");
            return (Criteria) this;
        }

        public Criteria andPayPasswordGreaterThan(String value) {
            addCriterion("\"pay_password\" >", value, "payPassword");
            return (Criteria) this;
        }

        public Criteria andPayPasswordGreaterThanOrEqualTo(String value) {
            addCriterion("\"pay_password\" >=", value, "payPassword");
            return (Criteria) this;
        }

        public Criteria andPayPasswordLessThan(String value) {
            addCriterion("\"pay_password\" <", value, "payPassword");
            return (Criteria) this;
        }

        public Criteria andPayPasswordLessThanOrEqualTo(String value) {
            addCriterion("\"pay_password\" <=", value, "payPassword");
            return (Criteria) this;
        }

        public Criteria andPayPasswordLike(String value) {
            addCriterion("\"pay_password\" like", value, "payPassword");
            return (Criteria) this;
        }

        public Criteria andPayPasswordNotLike(String value) {
            addCriterion("\"pay_password\" not like", value, "payPassword");
            return (Criteria) this;
        }

        public Criteria andPayPasswordIn(List<String> values) {
            addCriterion("\"pay_password\" in", values, "payPassword");
            return (Criteria) this;
        }

        public Criteria andPayPasswordNotIn(List<String> values) {
            addCriterion("\"pay_password\" not in", values, "payPassword");
            return (Criteria) this;
        }

        public Criteria andPayPasswordBetween(String value1, String value2) {
            addCriterion("\"pay_password\" between", value1, value2, "payPassword");
            return (Criteria) this;
        }

        public Criteria andPayPasswordNotBetween(String value1, String value2) {
            addCriterion("\"pay_password\" not between", value1, value2, "payPassword");
            return (Criteria) this;
        }

        public Criteria andRecommendIdIsNull() {
            addCriterion("\"recommend_id\" is null");
            return (Criteria) this;
        }

        public Criteria andRecommendIdIsNotNull() {
            addCriterion("\"recommend_id\" is not null");
            return (Criteria) this;
        }

        public Criteria andRecommendIdEqualTo(Integer value) {
            addCriterion("\"recommend_id\" =", value, "recommendId");
            return (Criteria) this;
        }

        public Criteria andRecommendIdNotEqualTo(Integer value) {
            addCriterion("\"recommend_id\" <>", value, "recommendId");
            return (Criteria) this;
        }

        public Criteria andRecommendIdGreaterThan(Integer value) {
            addCriterion("\"recommend_id\" >", value, "recommendId");
            return (Criteria) this;
        }

        public Criteria andRecommendIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("\"recommend_id\" >=", value, "recommendId");
            return (Criteria) this;
        }

        public Criteria andRecommendIdLessThan(Integer value) {
            addCriterion("\"recommend_id\" <", value, "recommendId");
            return (Criteria) this;
        }

        public Criteria andRecommendIdLessThanOrEqualTo(Integer value) {
            addCriterion("\"recommend_id\" <=", value, "recommendId");
            return (Criteria) this;
        }

        public Criteria andRecommendIdIn(List<Integer> values) {
            addCriterion("\"recommend_id\" in", values, "recommendId");
            return (Criteria) this;
        }

        public Criteria andRecommendIdNotIn(List<Integer> values) {
            addCriterion("\"recommend_id\" not in", values, "recommendId");
            return (Criteria) this;
        }

        public Criteria andRecommendIdBetween(Integer value1, Integer value2) {
            addCriterion("\"recommend_id\" between", value1, value2, "recommendId");
            return (Criteria) this;
        }

        public Criteria andRecommendIdNotBetween(Integer value1, Integer value2) {
            addCriterion("\"recommend_id\" not between", value1, value2, "recommendId");
            return (Criteria) this;
        }

        public Criteria andNodeIdIsNull() {
            addCriterion("\"node_id\" is null");
            return (Criteria) this;
        }

        public Criteria andNodeIdIsNotNull() {
            addCriterion("\"node_id\" is not null");
            return (Criteria) this;
        }

        public Criteria andNodeIdEqualTo(Integer value) {
            addCriterion("\"node_id\" =", value, "nodeId");
            return (Criteria) this;
        }

        public Criteria andNodeIdNotEqualTo(Integer value) {
            addCriterion("\"node_id\" <>", value, "nodeId");
            return (Criteria) this;
        }

        public Criteria andNodeIdGreaterThan(Integer value) {
            addCriterion("\"node_id\" >", value, "nodeId");
            return (Criteria) this;
        }

        public Criteria andNodeIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("\"node_id\" >=", value, "nodeId");
            return (Criteria) this;
        }

        public Criteria andNodeIdLessThan(Integer value) {
            addCriterion("\"node_id\" <", value, "nodeId");
            return (Criteria) this;
        }

        public Criteria andNodeIdLessThanOrEqualTo(Integer value) {
            addCriterion("\"node_id\" <=", value, "nodeId");
            return (Criteria) this;
        }

        public Criteria andNodeIdIn(List<Integer> values) {
            addCriterion("\"node_id\" in", values, "nodeId");
            return (Criteria) this;
        }

        public Criteria andNodeIdNotIn(List<Integer> values) {
            addCriterion("\"node_id\" not in", values, "nodeId");
            return (Criteria) this;
        }

        public Criteria andNodeIdBetween(Integer value1, Integer value2) {
            addCriterion("\"node_id\" between", value1, value2, "nodeId");
            return (Criteria) this;
        }

        public Criteria andNodeIdNotBetween(Integer value1, Integer value2) {
            addCriterion("\"node_id\" not between", value1, value2, "nodeId");
            return (Criteria) this;
        }

        public Criteria andMemberNameIsNull() {
            addCriterion("\"member_name\" is null");
            return (Criteria) this;
        }

        public Criteria andMemberNameIsNotNull() {
            addCriterion("\"member_name\" is not null");
            return (Criteria) this;
        }

        public Criteria andMemberNameEqualTo(String value) {
            addCriterion("\"member_name\" =", value, "memberName");
            return (Criteria) this;
        }

        public Criteria andMemberNameNotEqualTo(String value) {
            addCriterion("\"member_name\" <>", value, "memberName");
            return (Criteria) this;
        }

        public Criteria andMemberNameGreaterThan(String value) {
            addCriterion("\"member_name\" >", value, "memberName");
            return (Criteria) this;
        }

        public Criteria andMemberNameGreaterThanOrEqualTo(String value) {
            addCriterion("\"member_name\" >=", value, "memberName");
            return (Criteria) this;
        }

        public Criteria andMemberNameLessThan(String value) {
            addCriterion("\"member_name\" <", value, "memberName");
            return (Criteria) this;
        }

        public Criteria andMemberNameLessThanOrEqualTo(String value) {
            addCriterion("\"member_name\" <=", value, "memberName");
            return (Criteria) this;
        }

        public Criteria andMemberNameLike(String value) {
            addCriterion("\"member_name\" like", value, "memberName");
            return (Criteria) this;
        }

        public Criteria andMemberNameNotLike(String value) {
            addCriterion("\"member_name\" not like", value, "memberName");
            return (Criteria) this;
        }

        public Criteria andMemberNameIn(List<String> values) {
            addCriterion("\"member_name\" in", values, "memberName");
            return (Criteria) this;
        }

        public Criteria andMemberNameNotIn(List<String> values) {
            addCriterion("\"member_name\" not in", values, "memberName");
            return (Criteria) this;
        }

        public Criteria andMemberNameBetween(String value1, String value2) {
            addCriterion("\"member_name\" between", value1, value2, "memberName");
            return (Criteria) this;
        }

        public Criteria andMemberNameNotBetween(String value1, String value2) {
            addCriterion("\"member_name\" not between", value1, value2, "memberName");
            return (Criteria) this;
        }

        public Criteria andMemberLevelIsNull() {
            addCriterion("\"member_level\" is null");
            return (Criteria) this;
        }

        public Criteria andMemberLevelIsNotNull() {
            addCriterion("\"member_level\" is not null");
            return (Criteria) this;
        }

        public Criteria andMemberLevelEqualTo(String value) {
            addCriterion("\"member_level\" =", value, "memberLevel");
            return (Criteria) this;
        }

        public Criteria andMemberLevelNotEqualTo(String value) {
            addCriterion("\"member_level\" <>", value, "memberLevel");
            return (Criteria) this;
        }

        public Criteria andMemberLevelGreaterThan(String value) {
            addCriterion("\"member_level\" >", value, "memberLevel");
            return (Criteria) this;
        }

        public Criteria andMemberLevelGreaterThanOrEqualTo(String value) {
            addCriterion("\"member_level\" >=", value, "memberLevel");
            return (Criteria) this;
        }

        public Criteria andMemberLevelLessThan(String value) {
            addCriterion("\"member_level\" <", value, "memberLevel");
            return (Criteria) this;
        }

        public Criteria andMemberLevelLessThanOrEqualTo(String value) {
            addCriterion("\"member_level\" <=", value, "memberLevel");
            return (Criteria) this;
        }

        public Criteria andMemberLevelLike(String value) {
            addCriterion("\"member_level\" like", value, "memberLevel");
            return (Criteria) this;
        }

        public Criteria andMemberLevelNotLike(String value) {
            addCriterion("\"member_level\" not like", value, "memberLevel");
            return (Criteria) this;
        }

        public Criteria andMemberLevelIn(List<String> values) {
            addCriterion("\"member_level\" in", values, "memberLevel");
            return (Criteria) this;
        }

        public Criteria andMemberLevelNotIn(List<String> values) {
            addCriterion("\"member_level\" not in", values, "memberLevel");
            return (Criteria) this;
        }

        public Criteria andMemberLevelBetween(String value1, String value2) {
            addCriterion("\"member_level\" between", value1, value2, "memberLevel");
            return (Criteria) this;
        }

        public Criteria andMemberLevelNotBetween(String value1, String value2) {
            addCriterion("\"member_level\" not between", value1, value2, "memberLevel");
            return (Criteria) this;
        }

        public Criteria andIdNumberIsNull() {
            addCriterion("\"ID_number\" is null");
            return (Criteria) this;
        }

        public Criteria andIdNumberIsNotNull() {
            addCriterion("\"ID_number\" is not null");
            return (Criteria) this;
        }

        public Criteria andIdNumberEqualTo(String value) {
            addCriterion("\"ID_number\" =", value, "idNumber");
            return (Criteria) this;
        }

        public Criteria andIdNumberNotEqualTo(String value) {
            addCriterion("\"ID_number\" <>", value, "idNumber");
            return (Criteria) this;
        }

        public Criteria andIdNumberGreaterThan(String value) {
            addCriterion("\"ID_number\" >", value, "idNumber");
            return (Criteria) this;
        }

        public Criteria andIdNumberGreaterThanOrEqualTo(String value) {
            addCriterion("\"ID_number\" >=", value, "idNumber");
            return (Criteria) this;
        }

        public Criteria andIdNumberLessThan(String value) {
            addCriterion("\"ID_number\" <", value, "idNumber");
            return (Criteria) this;
        }

        public Criteria andIdNumberLessThanOrEqualTo(String value) {
            addCriterion("\"ID_number\" <=", value, "idNumber");
            return (Criteria) this;
        }

        public Criteria andIdNumberLike(String value) {
            addCriterion("\"ID_number\" like", value, "idNumber");
            return (Criteria) this;
        }

        public Criteria andIdNumberNotLike(String value) {
            addCriterion("\"ID_number\" not like", value, "idNumber");
            return (Criteria) this;
        }

        public Criteria andIdNumberIn(List<String> values) {
            addCriterion("\"ID_number\" in", values, "idNumber");
            return (Criteria) this;
        }

        public Criteria andIdNumberNotIn(List<String> values) {
            addCriterion("\"ID_number\" not in", values, "idNumber");
            return (Criteria) this;
        }

        public Criteria andIdNumberBetween(String value1, String value2) {
            addCriterion("\"ID_number\" between", value1, value2, "idNumber");
            return (Criteria) this;
        }

        public Criteria andIdNumberNotBetween(String value1, String value2) {
            addCriterion("\"ID_number\" not between", value1, value2, "idNumber");
            return (Criteria) this;
        }

        public Criteria andExpressAddressIsNull() {
            addCriterion("\"express_address\" is null");
            return (Criteria) this;
        }

        public Criteria andExpressAddressIsNotNull() {
            addCriterion("\"express_address\" is not null");
            return (Criteria) this;
        }

        public Criteria andExpressAddressEqualTo(String value) {
            addCriterion("\"express_address\" =", value, "expressAddress");
            return (Criteria) this;
        }

        public Criteria andExpressAddressNotEqualTo(String value) {
            addCriterion("\"express_address\" <>", value, "expressAddress");
            return (Criteria) this;
        }

        public Criteria andExpressAddressGreaterThan(String value) {
            addCriterion("\"express_address\" >", value, "expressAddress");
            return (Criteria) this;
        }

        public Criteria andExpressAddressGreaterThanOrEqualTo(String value) {
            addCriterion("\"express_address\" >=", value, "expressAddress");
            return (Criteria) this;
        }

        public Criteria andExpressAddressLessThan(String value) {
            addCriterion("\"express_address\" <", value, "expressAddress");
            return (Criteria) this;
        }

        public Criteria andExpressAddressLessThanOrEqualTo(String value) {
            addCriterion("\"express_address\" <=", value, "expressAddress");
            return (Criteria) this;
        }

        public Criteria andExpressAddressLike(String value) {
            addCriterion("\"express_address\" like", value, "expressAddress");
            return (Criteria) this;
        }

        public Criteria andExpressAddressNotLike(String value) {
            addCriterion("\"express_address\" not like", value, "expressAddress");
            return (Criteria) this;
        }

        public Criteria andExpressAddressIn(List<String> values) {
            addCriterion("\"express_address\" in", values, "expressAddress");
            return (Criteria) this;
        }

        public Criteria andExpressAddressNotIn(List<String> values) {
            addCriterion("\"express_address\" not in", values, "expressAddress");
            return (Criteria) this;
        }

        public Criteria andExpressAddressBetween(String value1, String value2) {
            addCriterion("\"express_address\" between", value1, value2, "expressAddress");
            return (Criteria) this;
        }

        public Criteria andExpressAddressNotBetween(String value1, String value2) {
            addCriterion("\"express_address\" not between", value1, value2, "expressAddress");
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

        public Criteria andStatusIsNull() {
            addCriterion("\"status\" is null");
            return (Criteria) this;
        }

        public Criteria andStatusIsNotNull() {
            addCriterion("\"status\" is not null");
            return (Criteria) this;
        }

        public Criteria andStatusEqualTo(String value) {
            addCriterion("\"status\" =", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotEqualTo(String value) {
            addCriterion("\"status\" <>", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThan(String value) {
            addCriterion("\"status\" >", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThanOrEqualTo(String value) {
            addCriterion("\"status\" >=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThan(String value) {
            addCriterion("\"status\" <", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThanOrEqualTo(String value) {
            addCriterion("\"status\" <=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLike(String value) {
            addCriterion("\"status\" like", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotLike(String value) {
            addCriterion("\"status\" not like", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusIn(List<String> values) {
            addCriterion("\"status\" in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotIn(List<String> values) {
            addCriterion("\"status\" not in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusBetween(String value1, String value2) {
            addCriterion("\"status\" between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotBetween(String value1, String value2) {
            addCriterion("\"status\" not between", value1, value2, "status");
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