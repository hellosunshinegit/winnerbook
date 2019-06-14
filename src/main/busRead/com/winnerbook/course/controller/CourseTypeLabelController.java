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
import com.winnerbook.course.dto.CourseTypeLabel;
import com.winnerbook.course.service.CourseTypeLabelService;
import com.winnerbook.system.service.UserService;

/**
 * 课程类型标签
 * @author hxs
 */
@Controller
@RequestMapping(value="/courseTypeLabelController")
public class CourseTypeLabelController extends BaseController{
	
	@Autowired
	private CourseTypeLabelService courseTypeLabelService;
	
	@Autowired
	private UserService userService;
	
	private static final Logger logger = LoggerFactory.getLogger(CourseTypeLabelController.class);
	
	//查询列表
	@RequestMapping(value="/courseTypeLabelList.html")
	public String courseTypeLabelList(HttpServletRequest request,Model model, Integer pageIndex,Integer pageSize){
		logger.info("courseTypeLabelList....");
		String name =request.getParameter("name");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("name", name);
		PageDTO<CourseTypeLabel> pageDTO  = courseTypeLabelService.listByPage(map, pageIndex,10);//默认每页显示10条数据
 
		model.addAttribute("pageDTO", pageDTO);
		model.addAllAttributes(map);
		return "manage/busRead/course/courseTypeLabel/courseTypeLabelList";
	}
	
	//点击添加跳转
	@RequestMapping(value="addCourseTypeLabel.html")
	public String addCourseTypeLabel(ModelMap modelMap){
		return "manage/busRead/course/courseTypeLabel/editCourseTypeLabel";
	}
	
	//添加提交
	@RequestMapping(value="addSubmitCourseTypeLabel.html")
	public String addSubmitCourseTypeLabel(CourseTypeLabel courseTypeLabel){
		courseTypeLabelService.insert(courseTypeLabel);
		return "redirect:/courseTypeLabelController/courseTypeLabelList.html";
	}
	
	//点击修改
	@RequestMapping(value="updateCourseTypeLabel.html")
	public String updateCourseType(@RequestParam String id,ModelMap modelMap){
		modelMap.addAttribute("courseTypeLabel",courseTypeLabelService.findById(id));
		return "manage/busRead/course/courseTypeLabel/editCourseTypeLabel";
	}
	
	//修改提交
	@RequestMapping(value="updateSubmitCourseTypeLabel.html")
	public String updateSubmitCourseTypeLabel(CourseTypeLabel courseTypeLabel){
		courseTypeLabelService.update(courseTypeLabel);
		return "redirect:/courseTypeLabelController/courseTypeLabelList.html";
	}
}
