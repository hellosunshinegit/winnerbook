package com.winnerbook.course.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.winnerbook.base.frame.dao.BaseDAO;
import com.winnerbook.course.dao.CourseTypeDao;
import com.winnerbook.course.dto.CourseType;

@Repository("courseTypeDao")
public class CourseTypeDaoImpl  extends BaseDAO implements CourseTypeDao{
	private static final String COURSETYPE_MAPPER = "CourseTypeMapper.";
	
	@Override
	public int selectCount(Map<String, Object> parameter) {
		Object obj = this.sqlSession.selectOne(COURSETYPE_MAPPER + "selectCount", parameter);
		return obj == null ? 0 : Integer.parseInt(obj.toString());
	}

	@Override
	public List<CourseType> listByPage(Map<String, Object> parameter) {
		return this.sqlSession.selectList(COURSETYPE_MAPPER + "listByPage", parameter);
	}

	@Override
	public CourseType findById(Map<String, Object> parameter) {
		return this.sqlSession.selectOne(COURSETYPE_MAPPER+"findById",parameter);
	}

	@Override
	public int insert(CourseType courseType) {
		return this.sqlSession.insert(COURSETYPE_MAPPER+"insert",courseType);
	}

	@Override
	public int update(CourseType courseType) {
		return this.sqlSession.insert(COURSETYPE_MAPPER+"update",courseType);
	}

	@Override
	public List<CourseType> getCourseTypeAll(Map<String, Object> parameter) {
		return this.sqlSession.selectList(COURSETYPE_MAPPER+"getCourseTypeAll",parameter);
	}

	@Override
	public List<Map<String, Object>> getCourseTypes(Map<String, Object> parameter) {
		return this.sqlSession.selectList(COURSETYPE_MAPPER+"getCourseTypes",parameter);
	}

	@Override
	public List<Map<String, Object>> getAdminCourseTypes(
			Map<String, Object> parameter) {
		return this.sqlSession.selectList(COURSETYPE_MAPPER+"getAdminCourseTypes",parameter);
	}

	@Override
	public List<CourseType> getCourseTypeAdmin(Map<String, Object> parameter) {
		return this.sqlSession.selectList(COURSETYPE_MAPPER+"getCourseTypeAdmin",parameter);
	}

	@Override
	public List<Map<String, Object>> getCourseTypeInfoByIds(
			Map<String, Object> parameter) {
		return this.sqlSession.selectList(COURSETYPE_MAPPER+"getCourseTypeInfoByIds",parameter);
	}

	@Override
	public List<CourseType> getCourseTypeAllSelect(Map<String, Object> parameter) {
		return this.sqlSession.selectList(COURSETYPE_MAPPER+"getCourseTypeAllSelect",parameter);	}

	@Override
	public List<Map<String, Object>> findBusCourseType(
			Map<String, Object> parameter) {
		return this.sqlSession.selectList(COURSETYPE_MAPPER+"findBusCourseType",parameter);
	}

	@Override
	public Map<String, Object> getFreeCouresType() {
		return this.sqlSession.selectOne(COURSETYPE_MAPPER+"getFreeCouresType");
	}

	@Override
	public List<Map<String, Object>> getCoursePackage(
			Map<String, Object> parameter) {
		return this.sqlSession.selectList(COURSETYPE_MAPPER+"getCoursePackage",parameter);
	}

}
