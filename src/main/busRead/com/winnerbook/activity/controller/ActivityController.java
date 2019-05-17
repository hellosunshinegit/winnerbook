package com.winnerbook.activity.controller;

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
import com.winnerbook.activity.dto.Activity;
import com.winnerbook.activity.dto.ActivitySignup;
import com.winnerbook.activity.service.ActivityService;
import com.winnerbook.activity.service.ActivitySignupService;
import com.winnerbook.base.common.PageDTO;
import com.winnerbook.base.frame.controller.BaseController;

/**
 * 读书会展示信息
 * @author hxs
 */
@Controller
@RequestMapping(value="/activityController")
public class ActivityController extends BaseController{
	
	@Autowired
	private ActivityService activityService;
	
	@Autowired
	private ActivitySignupService activitySignupService;
	
	private static final Logger logger = LoggerFactory.getLogger(ActivityController.class);
	
	//查询列表
	@RequestMapping(value="/activityList.html")
	public String busInfoList(HttpServletRequest request,Model model, Integer pageIndex,Integer pageSize){
		logger.info("活动列表...");
		String title =request.getParameter("title");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("title", title);
		map.put("sessionUser",getSessionUser());
		PageDTO<Activity> pageDTO  = activityService.listByPage(map, pageIndex,10);//默认每页显示10条数据
 
		model.addAttribute("pageDTO", pageDTO);
		model.addAllAttributes(map);
		return "manage/busRead/activity/activity/activityList";
	}
	
	//点击添加跳转
	@RequestMapping(value="addActivity.html")
	public String addreadBookClub(ModelMap modelMap){
		return "manage/busRead/activity/activity/editActivity";
	}
	
	//添加提交
	@RequestMapping(value="addSubmitActivity.html")
	public String addSubmitreadBookClub(Activity activity){
		activityService.insert(activity);
		return "redirect:/activityController/activityList.html";
	}
	
	//点击修改
	@RequestMapping(value="updateActivity.html")
	public String updateCourseType(@RequestParam String id,ModelMap modelMap){
		modelMap.addAttribute("activity",activityService.findById(id));
		return "manage/busRead/activity/activity/editActivity";
	}
	
	//修改提交
	@RequestMapping(value="updateActivitySubmit.html")
	public String updateSubmitreadBookClub(Activity activity){
		activityService.update(activity);
		return "redirect:/activityController/activityList.html";
	}
	
	//详情
	@RequestMapping(value="viewActivity.html")
	public String viewReadBookClub(@RequestParam String id,ModelMap modelMap){
		modelMap.addAttribute("activity",activityService.findById(id));
		return "manage/busRead/activity/activity/viewActivity";
	}
	
	
	//查看报名情况
	@RequestMapping(value="singupActivityList.html")
	public String singupActivityList(HttpServletRequest request,Model model, Integer pageIndex,Integer pageSize){
		logger.info("singupActivityList....");
		String activityId = request.getParameter("activityId");
		String userName = request.getParameter("userName");
		String phone = request.getParameter("phone");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("activityId", activityId);
		map.put("userName", userName);
		map.put("phone", phone);
		PageDTO<ActivitySignup> pageDTO  = activitySignupService.listByPage(map, pageIndex,10);//默认每页显示10条数据
 
		model.addAttribute("pageDTO", pageDTO);
		model.addAllAttributes(map);
		model.addAttribute("activity",activityService.findById(activityId));
		return "manage/busRead/activity/activity/activitySignupList";
	}
	
	
}
