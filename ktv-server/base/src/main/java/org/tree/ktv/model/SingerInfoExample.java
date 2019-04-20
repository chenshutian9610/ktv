package org.tree.ktv.model;

import java.util.ArrayList;
import java.util.List;
import org.tree.commons.support.mapper.Example;

public class SingerInfoExample implements Example<SingerInfo> {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public SingerInfoExample() {
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

        public Criteria andSingerIdIsNull() {
            addCriterion("singer_id is null");
            return (Criteria) this;
        }

        public Criteria andSingerIdIsNotNull() {
            addCriterion("singer_id is not null");
            return (Criteria) this;
        }

        public Criteria andSingerIdEqualTo(Long value) {
            addCriterion("singer_id =", value, "singerId");
            return (Criteria) this;
        }

        public Criteria andSingerIdNotEqualTo(Long value) {
            addCriterion("singer_id <>", value, "singerId");
            return (Criteria) this;
        }

        public Criteria andSingerIdGreaterThan(Long value) {
            addCriterion("singer_id >", value, "singerId");
            return (Criteria) this;
        }

        public Criteria andSingerIdGreaterThanOrEqualTo(Long value) {
            addCriterion("singer_id >=", value, "singerId");
            return (Criteria) this;
        }

        public Criteria andSingerIdLessThan(Long value) {
            addCriterion("singer_id <", value, "singerId");
            return (Criteria) this;
        }

        public Criteria andSingerIdLessThanOrEqualTo(Long value) {
            addCriterion("singer_id <=", value, "singerId");
            return (Criteria) this;
        }

        public Criteria andSingerIdIn(List<Long> values) {
            addCriterion("singer_id in", values, "singerId");
            return (Criteria) this;
        }

        public Criteria andSingerIdNotIn(List<Long> values) {
            addCriterion("singer_id not in", values, "singerId");
            return (Criteria) this;
        }

        public Criteria andSingerIdBetween(Long value1, Long value2) {
            addCriterion("singer_id between", value1, value2, "singerId");
            return (Criteria) this;
        }

        public Criteria andSingerIdNotBetween(Long value1, Long value2) {
            addCriterion("singer_id not between", value1, value2, "singerId");
            return (Criteria) this;
        }

        public Criteria andSingerNameIsNull() {
            addCriterion("singer_name is null");
            return (Criteria) this;
        }

        public Criteria andSingerNameIsNotNull() {
            addCriterion("singer_name is not null");
            return (Criteria) this;
        }

        public Criteria andSingerNameEqualTo(String value) {
            addCriterion("singer_name =", value, "singerName");
            return (Criteria) this;
        }

        public Criteria andSingerNameNotEqualTo(String value) {
            addCriterion("singer_name <>", value, "singerName");
            return (Criteria) this;
        }

        public Criteria andSingerNameGreaterThan(String value) {
            addCriterion("singer_name >", value, "singerName");
            return (Criteria) this;
        }

        public Criteria andSingerNameGreaterThanOrEqualTo(String value) {
            addCriterion("singer_name >=", value, "singerName");
            return (Criteria) this;
        }

        public Criteria andSingerNameLessThan(String value) {
            addCriterion("singer_name <", value, "singerName");
            return (Criteria) this;
        }

        public Criteria andSingerNameLessThanOrEqualTo(String value) {
            addCriterion("singer_name <=", value, "singerName");
            return (Criteria) this;
        }

        public Criteria andSingerNameLike(String value) {
            addCriterion("singer_name like", value, "singerName");
            return (Criteria) this;
        }

        public Criteria andSingerNameNotLike(String value) {
            addCriterion("singer_name not like", value, "singerName");
            return (Criteria) this;
        }

        public Criteria andSingerNameIn(List<String> values) {
            addCriterion("singer_name in", values, "singerName");
            return (Criteria) this;
        }

        public Criteria andSingerNameNotIn(List<String> values) {
            addCriterion("singer_name not in", values, "singerName");
            return (Criteria) this;
        }

        public Criteria andSingerNameBetween(String value1, String value2) {
            addCriterion("singer_name between", value1, value2, "singerName");
            return (Criteria) this;
        }

        public Criteria andSingerNameNotBetween(String value1, String value2) {
            addCriterion("singer_name not between", value1, value2, "singerName");
            return (Criteria) this;
        }

        public Criteria andSingerSexIsNull() {
            addCriterion("singer_sex is null");
            return (Criteria) this;
        }

        public Criteria andSingerSexIsNotNull() {
            addCriterion("singer_sex is not null");
            return (Criteria) this;
        }

        public Criteria andSingerSexEqualTo(Byte value) {
            addCriterion("singer_sex =", value, "singerSex");
            return (Criteria) this;
        }

        public Criteria andSingerSexNotEqualTo(Byte value) {
            addCriterion("singer_sex <>", value, "singerSex");
            return (Criteria) this;
        }

        public Criteria andSingerSexGreaterThan(Byte value) {
            addCriterion("singer_sex >", value, "singerSex");
            return (Criteria) this;
        }

        public Criteria andSingerSexGreaterThanOrEqualTo(Byte value) {
            addCriterion("singer_sex >=", value, "singerSex");
            return (Criteria) this;
        }

        public Criteria andSingerSexLessThan(Byte value) {
            addCriterion("singer_sex <", value, "singerSex");
            return (Criteria) this;
        }

        public Criteria andSingerSexLessThanOrEqualTo(Byte value) {
            addCriterion("singer_sex <=", value, "singerSex");
            return (Criteria) this;
        }

        public Criteria andSingerSexIn(List<Byte> values) {
            addCriterion("singer_sex in", values, "singerSex");
            return (Criteria) this;
        }

        public Criteria andSingerSexNotIn(List<Byte> values) {
            addCriterion("singer_sex not in", values, "singerSex");
            return (Criteria) this;
        }

        public Criteria andSingerSexBetween(Byte value1, Byte value2) {
            addCriterion("singer_sex between", value1, value2, "singerSex");
            return (Criteria) this;
        }

        public Criteria andSingerSexNotBetween(Byte value1, Byte value2) {
            addCriterion("singer_sex not between", value1, value2, "singerSex");
            return (Criteria) this;
        }

        public Criteria andSingerNationalityIsNull() {
            addCriterion("singer_nationality is null");
            return (Criteria) this;
        }

        public Criteria andSingerNationalityIsNotNull() {
            addCriterion("singer_nationality is not null");
            return (Criteria) this;
        }

        public Criteria andSingerNationalityEqualTo(String value) {
            addCriterion("singer_nationality =", value, "singerNationality");
            return (Criteria) this;
        }

        public Criteria andSingerNationalityNotEqualTo(String value) {
            addCriterion("singer_nationality <>", value, "singerNationality");
            return (Criteria) this;
        }

        public Criteria andSingerNationalityGreaterThan(String value) {
            addCriterion("singer_nationality >", value, "singerNationality");
            return (Criteria) this;
        }

        public Criteria andSingerNationalityGreaterThanOrEqualTo(String value) {
            addCriterion("singer_nationality >=", value, "singerNationality");
            return (Criteria) this;
        }

        public Criteria andSingerNationalityLessThan(String value) {
            addCriterion("singer_nationality <", value, "singerNationality");
            return (Criteria) this;
        }

        public Criteria andSingerNationalityLessThanOrEqualTo(String value) {
            addCriterion("singer_nationality <=", value, "singerNationality");
            return (Criteria) this;
        }

        public Criteria andSingerNationalityLike(String value) {
            addCriterion("singer_nationality like", value, "singerNationality");
            return (Criteria) this;
        }

        public Criteria andSingerNationalityNotLike(String value) {
            addCriterion("singer_nationality not like", value, "singerNationality");
            return (Criteria) this;
        }

        public Criteria andSingerNationalityIn(List<String> values) {
            addCriterion("singer_nationality in", values, "singerNationality");
            return (Criteria) this;
        }

        public Criteria andSingerNationalityNotIn(List<String> values) {
            addCriterion("singer_nationality not in", values, "singerNationality");
            return (Criteria) this;
        }

        public Criteria andSingerNationalityBetween(String value1, String value2) {
            addCriterion("singer_nationality between", value1, value2, "singerNationality");
            return (Criteria) this;
        }

        public Criteria andSingerNationalityNotBetween(String value1, String value2) {
            addCriterion("singer_nationality not between", value1, value2, "singerNationality");
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