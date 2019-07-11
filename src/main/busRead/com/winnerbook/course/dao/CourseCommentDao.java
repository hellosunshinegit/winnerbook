package com.winnerbook.course.dao;

import java.util.List;
import java.util.Map;

import com.winnerbook.course.dto.CourseComment;

public interface CourseCommentDao {
	
	
	List<CourseComment> getCommentByUserId(String userId);
	int updateCommentById(Map<String, Object> parameter);
	
    //查询课程评论web端
    List<Map<String, Object>> getCourseCommnets(Map<String, Object> parameter);
    int getCourseCommnetsCount(Map<String, Object> parameter);
    
    int insertCourseComment(CourseComment courseComment);
    
}