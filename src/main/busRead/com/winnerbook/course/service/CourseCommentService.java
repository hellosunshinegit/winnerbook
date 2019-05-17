package com.winnerbook.course.service;

import java.util.Map;
import com.winnerbook.course.dto.CourseComment;

public interface CourseCommentService {

	//查询课程评论web端
    Map<String, Object> getCourseCommnets(Map<String, Object> parameter);
    
    int insertCourseComment(CourseComment courseComment);

	
}
