package org.tree.ktv.model;

import java.util.ArrayList;
import java.util.List;
import org.tree.commons.support.mapper.Example;

public class SongInfoExample implements Example<SongInfo> {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public SongInfoExample() {
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

        public Criteria andSongIdIsNull() {
            addCriterion("song_id is null");
            return (Criteria) this;
        }

        public Criteria andSongIdIsNotNull() {
            addCriterion("song_id is not null");
            return (Criteria) this;
        }

        public Criteria andSongIdEqualTo(Long value) {
            addCriterion("song_id =", value, "songId");
            return (Criteria) this;
        }

        public Criteria andSongIdNotEqualTo(Long value) {
            addCriterion("song_id <>", value, "songId");
            return (Criteria) this;
        }

        public Criteria andSongIdGreaterThan(Long value) {
            addCriterion("song_id >", value, "songId");
            return (Criteria) this;
        }

        public Criteria andSongIdGreaterThanOrEqualTo(Long value) {
            addCriterion("song_id >=", value, "songId");
            return (Criteria) this;
        }

        public Criteria andSongIdLessThan(Long value) {
            addCriterion("song_id <", value, "songId");
            return (Criteria) this;
        }

        public Criteria andSongIdLessThanOrEqualTo(Long value) {
            addCriterion("song_id <=", value, "songId");
            return (Criteria) this;
        }

        public Criteria andSongIdIn(List<Long> values) {
            addCriterion("song_id in", values, "songId");
            return (Criteria) this;
        }

        public Criteria andSongIdNotIn(List<Long> values) {
            addCriterion("song_id not in", values, "songId");
            return (Criteria) this;
        }

        public Criteria andSongIdBetween(Long value1, Long value2) {
            addCriterion("song_id between", value1, value2, "songId");
            return (Criteria) this;
        }

        public Criteria andSongIdNotBetween(Long value1, Long value2) {
            addCriterion("song_id not between", value1, value2, "songId");
            return (Criteria) this;
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

        public Criteria andSongNameIsNull() {
            addCriterion("song_name is null");
            return (Criteria) this;
        }

        public Criteria andSongNameIsNotNull() {
            addCriterion("song_name is not null");
            return (Criteria) this;
        }

        public Criteria andSongNameEqualTo(String value) {
            addCriterion("song_name =", value, "songName");
            return (Criteria) this;
        }

        public Criteria andSongNameNotEqualTo(String value) {
            addCriterion("song_name <>", value, "songName");
            return (Criteria) this;
        }

        public Criteria andSongNameGreaterThan(String value) {
            addCriterion("song_name >", value, "songName");
            return (Criteria) this;
        }

        public Criteria andSongNameGreaterThanOrEqualTo(String value) {
            addCriterion("song_name >=", value, "songName");
            return (Criteria) this;
        }

        public Criteria andSongNameLessThan(String value) {
            addCriterion("song_name <", value, "songName");
            return (Criteria) this;
        }

        public Criteria andSongNameLessThanOrEqualTo(String value) {
            addCriterion("song_name <=", value, "songName");
            return (Criteria) this;
        }

        public Criteria andSongNameLike(String value) {
            addCriterion("song_name like", value, "songName");
            return (Criteria) this;
        }

        public Criteria andSongNameNotLike(String value) {
            addCriterion("song_name not like", value, "songName");
            return (Criteria) this;
        }

        public Criteria andSongNameIn(List<String> values) {
            addCriterion("song_name in", values, "songName");
            return (Criteria) this;
        }

        public Criteria andSongNameNotIn(List<String> values) {
            addCriterion("song_name not in", values, "songName");
            return (Criteria) this;
        }

        public Criteria andSongNameBetween(String value1, String value2) {
            addCriterion("song_name between", value1, value2, "songName");
            return (Criteria) this;
        }

        public Criteria andSongNameNotBetween(String value1, String value2) {
            addCriterion("song_name not between", value1, value2, "songName");
            return (Criteria) this;
        }

        public Criteria andSongNameAbbrIsNull() {
            addCriterion("song_name_abbr is null");
            return (Criteria) this;
        }

        public Criteria andSongNameAbbrIsNotNull() {
            addCriterion("song_name_abbr is not null");
            return (Criteria) this;
        }

        public Criteria andSongNameAbbrEqualTo(String value) {
            addCriterion("song_name_abbr =", value, "songNameAbbr");
            return (Criteria) this;
        }

        public Criteria andSongNameAbbrNotEqualTo(String value) {
            addCriterion("song_name_abbr <>", value, "songNameAbbr");
            return (Criteria) this;
        }

        public Criteria andSongNameAbbrGreaterThan(String value) {
            addCriterion("song_name_abbr >", value, "songNameAbbr");
            return (Criteria) this;
        }

        public Criteria andSongNameAbbrGreaterThanOrEqualTo(String value) {
            addCriterion("song_name_abbr >=", value, "songNameAbbr");
            return (Criteria) this;
        }

        public Criteria andSongNameAbbrLessThan(String value) {
            addCriterion("song_name_abbr <", value, "songNameAbbr");
            return (Criteria) this;
        }

        public Criteria andSongNameAbbrLessThanOrEqualTo(String value) {
            addCriterion("song_name_abbr <=", value, "songNameAbbr");
            return (Criteria) this;
        }

        public Criteria andSongNameAbbrLike(String value) {
            addCriterion("song_name_abbr like", value, "songNameAbbr");
            return (Criteria) this;
        }

        public Criteria andSongNameAbbrNotLike(String value) {
            addCriterion("song_name_abbr not like", value, "songNameAbbr");
            return (Criteria) this;
        }

        public Criteria andSongNameAbbrIn(List<String> values) {
            addCriterion("song_name_abbr in", values, "songNameAbbr");
            return (Criteria) this;
        }

        public Criteria andSongNameAbbrNotIn(List<String> values) {
            addCriterion("song_name_abbr not in", values, "songNameAbbr");
            return (Criteria) this;
        }

        public Criteria andSongNameAbbrBetween(String value1, String value2) {
            addCriterion("song_name_abbr between", value1, value2, "songNameAbbr");
            return (Criteria) this;
        }

        public Criteria andSongNameAbbrNotBetween(String value1, String value2) {
            addCriterion("song_name_abbr not between", value1, value2, "songNameAbbr");
            return (Criteria) this;
        }

        public Criteria andSongLanguageIsNull() {
            addCriterion("song_language is null");
            return (Criteria) this;
        }

        public Criteria andSongLanguageIsNotNull() {
            addCriterion("song_language is not null");
            return (Criteria) this;
        }

        public Criteria andSongLanguageEqualTo(String value) {
            addCriterion("song_language =", value, "songLanguage");
            return (Criteria) this;
        }

        public Criteria andSongLanguageNotEqualTo(String value) {
            addCriterion("song_language <>", value, "songLanguage");
            return (Criteria) this;
        }

        public Criteria andSongLanguageGreaterThan(String value) {
            addCriterion("song_language >", value, "songLanguage");
            return (Criteria) this;
        }

        public Criteria andSongLanguageGreaterThanOrEqualTo(String value) {
            addCriterion("song_language >=", value, "songLanguage");
            return (Criteria) this;
        }

        public Criteria andSongLanguageLessThan(String value) {
            addCriterion("song_language <", value, "songLanguage");
            return (Criteria) this;
        }

        public Criteria andSongLanguageLessThanOrEqualTo(String value) {
            addCriterion("song_language <=", value, "songLanguage");
            return (Criteria) this;
        }

        public Criteria andSongLanguageLike(String value) {
            addCriterion("song_language like", value, "songLanguage");
            return (Criteria) this;
        }

        public Criteria andSongLanguageNotLike(String value) {
            addCriterion("song_language not like", value, "songLanguage");
            return (Criteria) this;
        }

        public Criteria andSongLanguageIn(List<String> values) {
            addCriterion("song_language in", values, "songLanguage");
            return (Criteria) this;
        }

        public Criteria andSongLanguageNotIn(List<String> values) {
            addCriterion("song_language not in", values, "songLanguage");
            return (Criteria) this;
        }

        public Criteria andSongLanguageBetween(String value1, String value2) {
            addCriterion("song_language between", value1, value2, "songLanguage");
            return (Criteria) this;
        }

        public Criteria andSongLanguageNotBetween(String value1, String value2) {
            addCriterion("song_language not between", value1, value2, "songLanguage");
            return (Criteria) this;
        }

        public Criteria andSongFileNameIsNull() {
            addCriterion("song_file_name is null");
            return (Criteria) this;
        }

        public Criteria andSongFileNameIsNotNull() {
            addCriterion("song_file_name is not null");
            return (Criteria) this;
        }

        public Criteria andSongFileNameEqualTo(String value) {
            addCriterion("song_file_name =", value, "songFileName");
            return (Criteria) this;
        }

        public Criteria andSongFileNameNotEqualTo(String value) {
            addCriterion("song_file_name <>", value, "songFileName");
            return (Criteria) this;
        }

        public Criteria andSongFileNameGreaterThan(String value) {
            addCriterion("song_file_name >", value, "songFileName");
            return (Criteria) this;
        }

        public Criteria andSongFileNameGreaterThanOrEqualTo(String value) {
            addCriterion("song_file_name >=", value, "songFileName");
            return (Criteria) this;
        }

        public Criteria andSongFileNameLessThan(String value) {
            addCriterion("song_file_name <", value, "songFileName");
            return (Criteria) this;
        }

        public Criteria andSongFileNameLessThanOrEqualTo(String value) {
            addCriterion("song_file_name <=", value, "songFileName");
            return (Criteria) this;
        }

        public Criteria andSongFileNameLike(String value) {
            addCriterion("song_file_name like", value, "songFileName");
            return (Criteria) this;
        }

        public Criteria andSongFileNameNotLike(String value) {
            addCriterion("song_file_name not like", value, "songFileName");
            return (Criteria) this;
        }

        public Criteria andSongFileNameIn(List<String> values) {
            addCriterion("song_file_name in", values, "songFileName");
            return (Criteria) this;
        }

        public Criteria andSongFileNameNotIn(List<String> values) {
            addCriterion("song_file_name not in", values, "songFileName");
            return (Criteria) this;
        }

        public Criteria andSongFileNameBetween(String value1, String value2) {
            addCriterion("song_file_name between", value1, value2, "songFileName");
            return (Criteria) this;
        }

        public Criteria andSongFileNameNotBetween(String value1, String value2) {
            addCriterion("song_file_name not between", value1, value2, "songFileName");
            return (Criteria) this;
        }

        public Criteria andDurationIsNull() {
            addCriterion("duration is null");
            return (Criteria) this;
        }

        public Criteria andDurationIsNotNull() {
            addCriterion("duration is not null");
            return (Criteria) this;
        }

        public Criteria andDurationEqualTo(String value) {
            addCriterion("duration =", value, "duration");
            return (Criteria) this;
        }

        public Criteria andDurationNotEqualTo(String value) {
            addCriterion("duration <>", value, "duration");
            return (Criteria) this;
        }

        public Criteria andDurationGreaterThan(String value) {
            addCriterion("duration >", value, "duration");
            return (Criteria) this;
        }

        public Criteria andDurationGreaterThanOrEqualTo(String value) {
            addCriterion("duration >=", value, "duration");
            return (Criteria) this;
        }

        public Criteria andDurationLessThan(String value) {
            addCriterion("duration <", value, "duration");
            return (Criteria) this;
        }

        public Criteria andDurationLessThanOrEqualTo(String value) {
            addCriterion("duration <=", value, "duration");
            return (Criteria) this;
        }

        public Criteria andDurationLike(String value) {
            addCriterion("duration like", value, "duration");
            return (Criteria) this;
        }

        public Criteria andDurationNotLike(String value) {
            addCriterion("duration not like", value, "duration");
            return (Criteria) this;
        }

        public Criteria andDurationIn(List<String> values) {
            addCriterion("duration in", values, "duration");
            return (Criteria) this;
        }

        public Criteria andDurationNotIn(List<String> values) {
            addCriterion("duration not in", values, "duration");
            return (Criteria) this;
        }

        public Criteria andDurationBetween(String value1, String value2) {
            addCriterion("duration between", value1, value2, "duration");
            return (Criteria) this;
        }

        public Criteria andDurationNotBetween(String value1, String value2) {
            addCriterion("duration not between", value1, value2, "duration");
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