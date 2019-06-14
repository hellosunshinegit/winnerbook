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
import com.winnerbook.course.dao.CourseTypeDao;
import com.winnerbook.course.dao.CourseTypeLabelDao;
import com.winnerbook.course.dto.CourseType;
import com.winnerbook.course.dto.CourseTypeLabel;
import com.winnerbook.course.service.CourseTypeService;
import com.winnerbook.system.dto.User;

@Service("courseTypeService")
public class CourseTypeServiceImpl extends BaseServiceImpl implements CourseTypeService{
	
	@Autowired
	private CourseTypeDao courseTypeDao;
	
	@Autowired
	private CourseTypeLabelDao courseTypeLabelDao;

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
			List<CourseTypeLabel> courseTypeLabels = courseTypeLabelDao.findCourseTypeLabels();
			Map<Integer, Object> typeLabelMap = new HashMap<>();
			for(CourseTypeLabel courseTypeLabel:courseTypeLabels){
				if(StringUtils.isNotBlank(courseTypeLabel.getId()+"")){
					typeLabelMap.put(courseTypeLabel.getId(), courseTypeLabel.getName());
				}
			}
			
			for(CourseType courseType:data){
				if(null!=typeLabelMap.get(courseType.getTypeLabelId())){
					courseType.setTypeLabelName(typeLabelMap.get(courseType.getTypeLabelId())+"");
				}
			}
			
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
		
		if(null==courseType.getTypeSort()){
			courseType.setTypeSort(0);
		}
		
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

	@Override
	public List<Map<String, Object>> getCourseTypes(String busId) {
		Map<String, Object> parameter  = new HashMap<String, Object>();
		parameter.put("busId", busId);
		List<Map<String, Object>> busCourse = courseTypeDao.getCourseTypes(parameter);
		Map<String, Object> freeCourseType = courseTypeDao.getFreeCouresType();
		busCourse.add(freeCourseType);
		return busCourse;
	}

	@Override
	public List<Map<String, Object>> getAdminCourseTypes(String busId,String typeLabelId) {
		Map<String, Object> parameter  = new HashMap<String, Object>();
		parameter.put("typeLabelId", typeLabelId);
		List<Map<String, Object>> typeLists = courseTypeDao.getAdminCourseTypes(parameter);
		
		//根据busId查询该企业有哪些是已经购买的，哪些是需要付费的
		Map<String, Object> course_typeMap_param = new HashMap<>();
		course_typeMap_param.put("busId", busId);
		List<Map<String, Object>> coursetypeList = courseTypeDao.findBusCourseType(course_typeMap_param);
		
		Map<String,Object> course_typeMap = new HashMap<>();
		for(Map<String, Object> map:coursetypeList){
			course_typeMap.put(map.get("courseTypeId")+"", map.get("courseTypeId"));
		}
		
		
		for(Map<String, Object> type:typeLists){
			if(null!=course_typeMap.get(type.get("typeId")+"")){
				type.put("isBuy", "0");//不需要购买
			}else{
				type.put("isBuy", "1");//需要购买
			}
		}
		
		return typeLists;
	}

	@Override
	public List<CourseType> getCourseTypeAdmin(Map<String, Object> parameter) {
		return courseTypeDao.getCourseTypeAdmin(parameter);
	}

	@Override
	public List<CourseType> getCourseTypeAllSelect() {
		Map<String, Object> parameter  = new HashMap<String, Object>();
		parameter.put("sessionUser", getSessionUser());
		return courseTypeDao.getCourseTypeAllSelect(parameter);
	}


}
