package com.winnerbook.course.dao.impl;

import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Repository;
import com.winnerbook.base.frame.dao.BaseDAO;
import com.winnerbook.course.dao.CourseTypeLabelDao;
import com.winnerbook.course.dto.CourseTypeLabel;

@Repository("courseTypeLabelDao")
public class CourseTypeLabelDaoImpl  extends BaseDAO implements CourseTypeLabelDao{
	private static final String COURSETYPELABEL_MAPPER = "CourseTypeLabelMapper.";
	
	@Override
	public int selectCount(Map<String, Object> parameter) {
		Object obj = this.sqlSession.selectOne(COURSETYPELABEL_MAPPER + "selectCount", parameter);
		return obj == null ? 0 : Integer.parseInt(obj.toString());
	}

	@Override
	public List<CourseTypeLabel> listByPage(Map<String, Object> parameter) {
		return this.sqlSession.selectList(COURSETYPELABEL_MAPPER + "listByPage", parameter);
	}

	@Override
	public CourseTypeLabel findById(Map<String, Object> parameter) {
		return this.sqlSession.selectOne(COURSETYPELABEL_MAPPER+"findById",parameter);
	}

	@Override
	public int insert(CourseTypeLabel courseTypeLabel) {
		return this.sqlSession.insert(COURSETYPELABEL_MAPPER+"insert",courseTypeLabel);
	}

	@Override
	public int update(CourseTypeLabel courseTypeLabel) {
		return this.sqlSession.insert(COURSETYPELABEL_MAPPER+"update",courseTypeLabel);
	}

	@Override
	public List<Map<String, Object>> getCourseTypeLabels(Map<String, Object> parameter) {
		return this.sqlSession.selectList(COURSETYPELABEL_MAPPER+"getCourseTypeLabels",parameter);
	}

	@Override
	public List<CourseTypeLabel> findCourseTypeLabels() {
		return this.sqlSession.selectList(COURSETYPELABEL_MAPPER+"findCourseTypeLabels");
	}

}
