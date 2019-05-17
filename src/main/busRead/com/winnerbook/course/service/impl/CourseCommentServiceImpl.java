package com.winnerbook.course.service.impl;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.winnerbook.base.frame.service.impl.BaseServiceImpl;
import com.winnerbook.course.dao.CourseCommentDao;
import com.winnerbook.course.dto.CourseComment;
import com.winnerbook.course.service.CourseCommentService;

@Service("courseCommentService")
public class CourseCommentServiceImpl extends BaseServiceImpl implements CourseCommentService{
	
	@Autowired
	private CourseCommentDao courseCommentDao;

	@Override
	public Map<String, Object> getCourseCommnets(
			Map<String, Object> parameter) {
		Map<String, Object> map = new HashMap<>();
		List<Map<String, Object>> courseList = courseCommentDao.getCourseCommnets(parameter);
		int courseCount = courseCommentDao.getCourseCommnetsCount(parameter);
		map.put("courseCommentList", courseList);
		map.put("courseCommentCount", courseCount);
		return map;
	}

	@Override
	public int insertCourseComment(CourseComment courseComment) {
		return courseCommentDao.insertCourseComment(courseComment);
	}
	
	
}
