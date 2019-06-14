package com.winnerbook.course.service;

import java.util.List;
import java.util.Map;

import com.winnerbook.base.common.PageDTO;
import com.winnerbook.course.dto.Course;

public interface CourseService {

	/**
	 * 根据id查询
	 * @param parameter
	 * @return
	 */
	Course findById(String courseId);
	
	/**
	 * 分页查询
	 * @param parameter
	 * @return
	 */
	PageDTO<Course> listByPage(Map<String, Object> parameter,Integer pageIndex, Integer pageSize);
	
	/**
	 * 新增
	 * @param dictionary
	 */
	void insert(Course course);

	/**
	 * 修改
	 * @param dictionary
	 */
	void update(Course course);
	
	int courseUploadFileSubmit(String courseUploadFileSubmit);
	
	void studentCourse(String courseId);
	
	//查询课程
	List<Course> listByPage(Map<String, Object> parameter);
	
    void updateCourseChannel(String courseId, String status,String courseReleaseId);
	
	//前端展示课程列表
    Map<String, Object> getCourses(Map<String, Object> parameter);
    
    //查询主讲人
    Map<String, Object> getMainGuests(Map<String, Object> parameter);
    //视频
    Map<String, Object> getVideos(Map<String, Object> parameter);
    
    Map<String, Object> getCourseDetail(Map<String, Object> parameter);
    
    int updateClickNum(Map<String, Object> parameter);
    
    //课程超市列表
    Map<String, Object> getAdminCourses(Map<String, Object> parameter);

	
}
