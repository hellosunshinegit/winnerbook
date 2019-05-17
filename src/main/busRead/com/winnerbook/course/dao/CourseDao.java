package com.winnerbook.course.dao;

import java.util.List;
import java.util.Map;

import com.winnerbook.course.dto.Course;

public interface CourseDao {
	
	/**
	 * 查询总条数
	 * @param parameter
	 * @return
	 */
	int  selectCount(Map<String, Object> parameter);
	
	/**
	 * 分页查询
	 * @param parameter
	 * @return
	 */
	List<Course> listByPage(Map<String, Object> parameter);
	
    int insert(Course record);
    
    Course findById(Map<String, Object> parameter);

    int update(Course record);
    
    //课程推送到h5修改
    void insertCourseRelease(Map<String, Object> parameter);
    
    void updateCourseRelease(Map<String, Object> parameter);
    
    List<Map<String, Object>> getCourseRelease(Map<String, Object> parameter);

    List<Map<String, Object>> getCourseByBookListId(Map<String, Object> parameter);
    
    Map<String, Object> getCourseAdminCreate(Map<String, Object> parameter);
    
    //查询所有课程 web端
    List<Map<String, Object>> getCourses(Map<String, Object> parameter);
    int getCoursesCount(Map<String, Object> parameter);
    
    //查询主讲人
    List<Map<String, Object>> getMainGuests(Map<String, Object> parameter);
    int getMainGuestsCount(Map<String, Object> parameter);
    //视频
    List<Map<String, Object>> getVideos(Map<String, Object> parameter);
    int getVideosCount(Map<String, Object> parameter);
    
    Map<String, Object> getCourseDetail(Map<String, Object> parameter);
    List<Map<String, Object>> getCourseFile(Map<String, Object> parameter);
    
    int updateClickNum(Map<String, Object> parameter);
    

    
}