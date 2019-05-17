package com.winnerbook.course.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.winnerbook.base.frame.dao.BaseDAO;
import com.winnerbook.course.dao.CourseTypeDao;
import com.winnerbook.course.dto.CourseType;

@Repository("courseTypeDao")
public class CourseTypeDaoImpl  extends BaseDAO implements CourseTypeDao{
	private static final String BUSINFO_MAPPER = "CourseTypeMapper.";
	
	@Override
	public int selectCount(Map<String, Object> parameter) {
		Object obj = this.sqlSession.selectOne(BUSINFO_MAPPER + "selectCount", parameter);
		return obj == null ? 0 : Integer.parseInt(obj.toString());
	}

	@Override
	public List<CourseType> listByPage(Map<String, Object> parameter) {
		return this.sqlSession.selectList(BUSINFO_MAPPER + "listByPage", parameter);
	}

	@Override
	public CourseType findById(Map<String, Object> parameter) {
		return this.sqlSession.selectOne(BUSINFO_MAPPER+"findById",parameter);
	}

	@Override
	public int insert(CourseType courseType) {
		return this.sqlSession.insert(BUSINFO_MAPPER+"insert",courseType);
	}

	@Override
	public int update(CourseType courseType) {
		return this.sqlSession.insert(BUSINFO_MAPPER+"update",courseType);
	}

	@Override
	public List<CourseType> getCourseTypeAll(Map<String, Object> parameter) {
		return this.sqlSession.selectList(BUSINFO_MAPPER+"getCourseTypeAll",parameter);
	}

}
