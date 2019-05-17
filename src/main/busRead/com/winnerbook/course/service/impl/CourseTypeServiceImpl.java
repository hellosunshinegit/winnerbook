package com.winnerbook.course.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.winnerbook.base.common.GlobalConfigure;
import com.winnerbook.base.common.PageDTO;
import com.winnerbook.base.frame.service.impl.BaseServiceImpl;
import com.winnerbook.course.dao.CourseTypeDao;
import com.winnerbook.course.dto.CourseType;
import com.winnerbook.course.service.CourseTypeService;
import com.winnerbook.system.dto.User;

@Service("courseTypeService")
public class CourseTypeServiceImpl extends BaseServiceImpl implements CourseTypeService{
	
	@Autowired
	private CourseTypeDao courseTypeDao;

	@Override
	public CourseType findById(String courseTypeId) {
		Map<String, Object> parameter  = new HashMap<String, Object>();
		parameter.put("typeId", Integer.parseInt(courseTypeId));
		return courseTypeDao.findById(parameter);
	}

	@Override
	public PageDTO<CourseType> listByPage(Map<String, Object> parameter,Integer pageIndex, Integer pageSize) {
		PageDTO<CourseType> pageDTO = new PageDTO<CourseType>(pageIndex, pageSize);
		parameter.put(GlobalConfigure.PAGINATION_SQL_START, pageDTO.getFirst());
		parameter.put(GlobalConfigure.PAGINATION_SQL_LIMIT, pageDTO.getPageSize());
		long rowSize = courseTypeDao.selectCount(parameter);
		List<CourseType> data = null;
		if (rowSize > 0) {
			data = courseTypeDao.listByPage(parameter);
		}
		pageDTO.setRowSize(rowSize);
		pageDTO.setData(data);
		return pageDTO;
	}
	
	@Override
	public void insert(CourseType courseType) {
		User sessionUser = getSessionUser();
		courseType.setCreateDate(new Date());
		courseType.setCreateUserId(Integer.parseInt(sessionUser.getUserId().toString()));
		courseType.setCreateUserName(sessionUser.getUserUnitName());
		courseTypeDao.insert(courseType);
		logRecord("2","课程类型信息添加，id："+courseType.getTypeName());
	}

	@Override
	public void update(CourseType courseType) {
		courseType.setUpdateDate(new Date());
		courseTypeDao.update(courseType);
		logRecord("3","课程类型信息更新，id："+courseType.getTypeId());
	}

	@Override
	public List<CourseType> getCourseTypeAll(Map<String, Object> parameter) {
		return courseTypeDao.getCourseTypeAll(parameter);
	}


}
