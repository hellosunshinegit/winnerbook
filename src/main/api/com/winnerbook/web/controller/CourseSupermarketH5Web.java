package com.winnerbook.web.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.winnerbook.base.common.JSONResponse;
import com.winnerbook.course.service.CourseService;
import com.winnerbook.course.service.CourseTypeLabelService;
import com.winnerbook.course.service.CourseTypeService;
import com.winnerbook.web.utils.PageUtil;
import com.winnerbook.web.utils.ValidateWebUtils;

//课程超市   获取的是admin的数据
@Controller
public class CourseSupermarketH5Web {
	
	@Autowired
	private CourseTypeLabelService courseTypeLabelService;
	
	@Autowired
	private CourseTypeService courseTypeService;
	
	@Autowired
	private CourseService courseService;
	
	//获取课程类型标签
	@RequestMapping(value="getCourseTypeLabels.jhtml",produces = {"application/json;charset=utf-8"})
	@ResponseBody
	public String getCourseDetail(@RequestParam("callback") String callback){
		JSONResponse result = new JSONResponse();
		
		List<Map<String, Object>> typeLabels = courseTypeLabelService.getCourseTypeLabels();
		
 		result.setSuccess(true);
		result.setMsg("获取课程类型标签成功");
		result.setData(typeLabels);
		return callback+"("+JSONObject.fromObject(result)+")";
	}
	
	//查询admin的课程类型 和是否要购买的课程
	@RequestMapping(value="getAdminCourseTypes.jhtml",produces = {"application/json;charset=utf-8"})
	@ResponseBody
	public String getAdminCourseTypes(String busId,String typeLabelId,@RequestParam("callback") String callback){
		JSONResponse result = new JSONResponse();
		if(!StringUtils.isNotBlank(typeLabelId)){
			result.setMsg("typeLabelId为必传项");
			return callback+"("+JSONObject.fromObject(result)+")";
		}
		busId = ValidateWebUtils.defaultBus(busId);
		List<Map<String, Object>> courseTypeList = courseTypeService.getAdminCourseTypes(busId,typeLabelId);
		
		result.setSuccess(true);
		result.setMsg("获取admin课程类型成功");
		result.setData(courseTypeList);
		return callback+"("+JSONObject.fromObject(result)+")";
	}
	
	//点击精选课程
	@RequestMapping(value="getAdminCourses.jhtml",produces = {"application/json;charset=utf-8"})
	@ResponseBody
	public String getAdminCourses(String busId,String courseTypeId,String pageIndex,@RequestParam("callback") String callback){
		JSONResponse result = new JSONResponse();
		//最新课程...
		Map<String, Object> parameter_course  = new HashMap<String, Object>();
		parameter_course.put("courseTypeId", courseTypeId);
		Map<String, Object> courseList = courseService.getAdminCourses(PageUtil.getParam(parameter_course, ValidateWebUtils.defaultBus(busId), pageIndex));
		
		result.setSuccess(true);
		result.setMsg("获取admin课程表成功");
		result.setData(courseList);
		return callback+"("+JSONObject.fromObject(result)+")";
	}
	
}
