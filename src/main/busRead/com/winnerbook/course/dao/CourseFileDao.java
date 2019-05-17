package com.winnerbook.course.dao;

import java.util.List;
import java.util.Map;
import com.winnerbook.course.dto.CourseFile;

public interface CourseFileDao {

	List<CourseFile> findCourseFileByCourseId(Integer courseId);
	
    int insert(Map<String, Object> parameter);
    
    void delete(Map<String, Object> parameter);
    
    CourseFile findCourseFileByFileId(Integer fileId);
   
}