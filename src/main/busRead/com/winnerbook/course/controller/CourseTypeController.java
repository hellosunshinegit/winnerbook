package com.winnerbook.course.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.winnerbook.base.common.PageDTO;
import com.winnerbook.base.frame.controller.BaseController;
import com.winnerbook.course.dto.CourseType;
import com.winnerbook.course.service.CourseTypeService;
import com.winnerbook.system.service.UserService;

/**
 * 课程类型
 * @author hxs
 */
@Controller
@RequestMapping(value="/courseTypeController")
public class CourseTypeController extends BaseController{
	
	@Autowired
	private CourseTypeService courseTypeService;
	
	@Autowired
	private UserService userService;
	
	private static final Logger logger = LoggerFactory.getLogger(CourseTypeController.class);
	
	//查询列表
	@RequestMapping(value="/courseTypeList.html")
	public String busInfoList(HttpServletRequest request,Model model, Integer pageIndex,Integer pageSize){
		logger.info("courseTypeList....");
		String typeName =request.getParameter("typeName");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("typeName", typeName);
		map.put("sessionUser",getSessionUser());
		PageDTO<CourseType> pageDTO  = courseTypeService.listByPage(map, pageIndex,10);//默认每页显示10条数据
 
		model.addAttribute("pageDTO", pageDTO);
		model.addAllAttributes(map);
		return "manage/busRead/course/courseType/courseTypeList";
	}
	
	//点击添加跳转
	@RequestMapping(value="addCourseType.html")
	public String addCourseType(ModelMap modelMap){
		return "manage/busRead/course/courseType/editCourseType";
	}
	
	//添加提交
	@RequestMapping(value="addSubmitCourseType.html")
	public String addSubmitCourseType(CourseType courseType){
		courseTypeService.insert(courseType);
		return "redirect:/courseTypeController/courseTypeList.html";
	}
	
	//点击修改
	@RequestMapping(value="updateCourseType.html")
	public String updateCourseType(@RequestParam String typeId,ModelMap modelMap){
		modelMap.addAttribute("courseType",courseTypeService.findById(typeId));
		return "manage/busRead/course/courseType/editCourseType";
	}
	
	//修改提交
	@RequestMapping(value="updateSubmitCourseType.html")
	public String updateSubmitCourseType(CourseType courseType){
		courseTypeService.update(courseType);
		return "redirect:/courseTypeController/courseTypeList.html";
	}
}
