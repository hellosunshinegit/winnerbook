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

import com.winnerbook.activity.dto.ReadBookClub;
import com.winnerbook.activity.service.ReadBookClubService;
import com.winnerbook.base.common.PageDTO;
import com.winnerbook.base.frame.controller.BaseController;
import com.winnerbook.wx.service.WxInfoService;

/**
 * 读书会展示信息
 * @author hxs
 */
@Controller
@RequestMapping(value="/readBookClubController")
public class ReadBookClubController extends BaseController{
	
	@Autowired
	private ReadBookClubService readBookClubService;
	
	@Autowired
	private WxInfoService wxInfoService;
	
	
	private static final Logger logger = LoggerFactory.getLogger(ReadBookClubController.class);
	
	//查询列表
	@RequestMapping(value="/readBookClubList.html")
	public String busInfoList(HttpServletRequest request,Model model, Integer pageIndex,Integer pageSize){
		logger.info("读书会列表...");
		String clubTitle =request.getParameter("clubTitle");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("clubTitle", clubTitle);
		map.put("sessionUser",getSessionUser());
		PageDTO<ReadBookClub> pageDTO  = readBookClubService.listByPage(map, pageIndex,10);//默认每页显示10条数据
 
		model.addAttribute("pageDTO", pageDTO);
		//获取微博appid
		map.put("wxInfo", wxInfoService.getWxInfo("2"));
		model.addAllAttributes(map);
		return "manage/busRead/activity/readBookClubList";
	}
	
	//点击添加跳转
	@RequestMapping(value="addreadBookClub.html")
	public String addreadBookClub(ModelMap modelMap){
		return "manage/busRead/activity/editReadBookClub";
	}
	
	//添加提交
	@RequestMapping(value="addSubmitReadBookClub.html")
	public String addSubmitreadBookClub(ReadBookClub readBookClub){
		readBookClubService.insert(readBookClub);
		return "redirect:/readBookClubController/readBookClubList.html";
	}
	
	//点击修改
	@RequestMapping(value="updateReadBookClub.html")
	public String updateCourseType(@RequestParam String clubId,ModelMap modelMap){
		modelMap.addAttribute("readBookClub",readBookClubService.findById(clubId));
		return "manage/busRead/activity/editReadBookClub";
	}
	
	//修改提交
	@RequestMapping(value="updateSubmitReadBookClub.html")
	public String updateSubmitreadBookClub(ReadBookClub readBookClub){
		readBookClubService.update(readBookClub);
		return "redirect:/readBookClubController/readBookClubList.html";
	}
	
	//点击删除
	@RequestMapping(value="deleteReadBookClub.html")
	public String deleteReadBookClub(@RequestParam String clubId){
		readBookClubService.delete(clubId);
		return "redirect:/readBookClubController/readBookClubList.html";
	}
	
	//详情
	@RequestMapping(value="viewReadBookClub.html")
	public String viewReadBookClub(@RequestParam String clubId,ModelMap modelMap){
		modelMap.addAttribute("readBookClub",readBookClubService.findById(clubId));
		return "manage/busRead/activity/viewReadBookClub";
	}
	
	
}
