package com.winnerbook.course.service;

import java.util.List;
import java.util.Map;

import com.winnerbook.base.common.PageDTO;
import com.winnerbook.course.dto.CourseType;

public interface CourseTypeService {

	/**
	 * 根据id查询
	 * @param parameter
	 * @return
	 */
	CourseType findById(String courseTypeId);
	
	/**
	 * 分页查询
	 * @param parameter
	 * @return
	 */
	PageDTO<CourseType> listByPage(Map<String, Object> parameter,Integer pageIndex, Integer pageSize);
	
	/**
	 * 新增
	 * @param dictionary
	 */
	void insert(CourseType courseType);

	/**
	 * 修改
	 * @param dictionary
	 */
	void update(CourseType courseType);
	
	List<CourseType> getCourseTypeAll(Map<String, Object> parameter);
	
}
