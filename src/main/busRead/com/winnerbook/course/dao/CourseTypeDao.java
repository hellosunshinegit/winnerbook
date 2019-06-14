package com.winnerbook.course.dao;

import java.util.List;
import java.util.Map;

import com.winnerbook.course.dto.CourseType;

public interface CourseTypeDao {
	
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
	List<CourseType> listByPage(Map<String, Object> parameter);
	
    int insert(CourseType record);
    
    CourseType findById(Map<String, Object> parameter);

    int update(CourseType record);
    
    List<CourseType> getCourseTypeAll(Map<String, Object> parameter);
    
    List<CourseType> getCourseTypeAdmin(Map<String, Object> parameter);
    List<CourseType> getCourseTypeAllSelect(Map<String, Object> parameter);
    
    List<Map<String, Object>> getCourseTypeInfoByIds(Map<String, Object> parameter);
    
    //web
    List<Map<String, Object>> getCourseTypes(Map<String, Object> parameter);
    
    List<Map<String, Object>> getAdminCourseTypes(Map<String, Object> parameter);
    
    Map<String, Object> getFreeCouresType();
    
    List<Map<String, Object>> findBusCourseType(Map<String, Object> parameter);
}