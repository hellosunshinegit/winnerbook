package com.winnerbook.course.dao;

import java.util.List;
import java.util.Map;
import com.winnerbook.course.dto.CourseTypeLabel;

public interface CourseTypeLabelDao {
	
	int  selectCount(Map<String, Object> parameter);
	
	List<CourseTypeLabel> listByPage(Map<String, Object> parameter);
	
    int insert(CourseTypeLabel record);
    
    CourseTypeLabel findById(Map<String, Object> parameter);

    int update(CourseTypeLabel record);
    
    List<CourseTypeLabel> findCourseTypeLabels();
    
    //web端使用
    List<Map<String, Object>> getCourseTypeLabels(Map<String, Object> parameter);
}