package com.winnerbook.course.service;

import java.util.List;

import com.winnerbook.course.dto.CourseFile;

public interface CourseFileService {

	List<CourseFile> findCourseFileByCourseId(Integer courseId);
	
	String delete(String courseId,String fileId);
	
	CourseFile findCourseFileByFileId(Integer fileId);
	
}
