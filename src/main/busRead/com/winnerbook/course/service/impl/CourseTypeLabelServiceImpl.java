package com.winnerbook.course.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.winnerbook.base.common.GlobalConfigure;
import com.winnerbook.base.common.PageDTO;
import com.winnerbook.base.frame.service.impl.BaseServiceImpl;
import com.winnerbook.course.dao.CourseTypeLabelDao;
import com.winnerbook.course.dto.CourseTypeLabel;
import com.winnerbook.course.service.CourseTypeLabelService;
import com.winnerbook.system.dto.User;

@Service("courseTypeLabelService")
public class CourseTypeLabelServiceImpl extends BaseServiceImpl implements CourseTypeLabelService{
	
	@Autowired
	private CourseTypeLabelDao courseTypeLabelDao;

	@Override
	public CourseTypeLabel findById(String courseTypeLabelId) {
		Map<String, Object> parameter  = new HashMap<String, Object>();
		parameter.put("id", Integer.parseInt(courseTypeLabelId));
		return courseTypeLabelDao.findById(parameter);
	}

	@Override
	public PageDTO<CourseTypeLabel> listByPage(Map<String, Object> parameter,Integer pageIndex, Integer pageSize) {
		PageDTO<CourseTypeLabel> pageDTO = new PageDTO<CourseTypeLabel>(pageIndex, pageSize);
		parameter.put(GlobalConfigure.PAGINATION_SQL_START, pageDTO.getFirst());
		parameter.put(GlobalConfigure.PAGINATION_SQL_LIMIT, pageDTO.getPageSize());
		long rowSize = courseTypeLabelDao.selectCount(parameter);
		List<CourseTypeLabel> data = null;
		if (rowSize > 0) {
			data = courseTypeLabelDao.listByPage(parameter);
		}
		pageDTO.setRowSize(rowSize);
		pageDTO.setData(data);
		return pageDTO;
	}
	
	@Override
	public void insert(CourseTypeLabel courseTypeLabel) {
		User sessionUser = getSessionUser();
		courseTypeLabel.setCreateDate(new Date());
		courseTypeLabel.setCreateUserId(Integer.parseInt(sessionUser.getUserId().toString()));
		courseTypeLabel.setCreateUserName(sessionUser.getUserUnitName());
		if(!StringUtils.isNotBlank(courseTypeLabel.getSort()+"")){
			courseTypeLabel.setSort(0);
		}
		courseTypeLabelDao.insert(courseTypeLabel);
		logRecord("2","课程类型标签信息添加，id："+courseTypeLabel.getId());
	}

	@Override
	public void update(CourseTypeLabel courseTypeLabel) {
		courseTypeLabel.setUpdateDate(new Date());
		courseTypeLabelDao.update(courseTypeLabel);
		logRecord("3","课程类型信息更新，id："+courseTypeLabel.getId());
	}

	@Override
	public List<Map<String, Object>> getCourseTypeLabels() {
		Map<String, Object> hashmap = new HashMap<>();
		return courseTypeLabelDao.getCourseTypeLabels(hashmap);
	}

	@Override
	public List<CourseTypeLabel> findCourseTypeLabels() {
		return courseTypeLabelDao.findCourseTypeLabels();
	}

}
