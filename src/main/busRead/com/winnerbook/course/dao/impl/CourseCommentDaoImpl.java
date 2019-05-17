package com.winnerbook.course.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.winnerbook.base.frame.dao.BaseDAO;
import com.winnerbook.course.dao.CourseCommentDao;
import com.winnerbook.course.dto.CourseComment;

@Repository("courseCommentDao")
public class CourseCommentDaoImpl  extends BaseDAO implements CourseCommentDao{
	private static final String COURSECOMMENT_MAPPER = "CourseCommentMapper.";
	
	
	@Override
	public List<Map<String, Object>> getCourseCommnets(Map<String, Object> parameter) {
		return this.sqlSession.selectList(COURSECOMMENT_MAPPER+"getCourseCommnets",parameter);
	}

	@Override
	public int getCourseCommnetsCount(Map<String, Object> parameter) {
		Object obj = this.sqlSession.selectOne(COURSECOMMENT_MAPPER + "getCourseCommnetsCount", parameter);
		return obj == null ? 0 : Integer.parseInt(obj.toString());
	}

	@Override
	public int insertCourseComment(CourseComment comment) {
		return this.sqlSession.insert(COURSECOMMENT_MAPPER+"insertCourseComment",comment);
	}

}
