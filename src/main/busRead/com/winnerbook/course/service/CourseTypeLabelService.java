package com.winnerbook.course.service;

import java.util.List;
import java.util.Map;

import com.winnerbook.base.common.PageDTO;
import com.winnerbook.course.dto.CourseType;
import com.winnerbook.course.dto.CourseTypeLabel;

public interface CourseTypeLabelService {

	/**
	 * 根据id查询
	 * @param parameter
	 * @return
	 */
	CourseTypeLabel findById(String courseTypeLabelId);
	
	/**
	 * 分页查询
	 * @param parameter
	 * @return
	 */
	PageDTO<CourseTypeLabel> listByPage(Map<String, Object> parameter,Integer pageIndex, Integer pageSize);
	
	/**
	 * 新增
	 * @param courseTypeLabel
	 */
	void insert(CourseTypeLabel courseTypeLabel);

	/**
	 * 修改
	 * @param courseTypeLabel
	 */
	void update(CourseTypeLabel courseTypeLabel);
	
	List<CourseTypeLabel> findCourseTypeLabels();
	
	//web端使用
	List<Map<String, Object>> getCourseTypeLabels();
	
}
