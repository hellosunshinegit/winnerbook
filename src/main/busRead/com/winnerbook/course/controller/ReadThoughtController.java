package com.winnerbook.course.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.winnerbook.base.common.PageDTO;
import com.winnerbook.base.common.util.FileUtils;
import com.winnerbook.base.frame.controller.BaseController;
import com.winnerbook.course.dto.ReadThought;
import com.winnerbook.course.service.CourseService;
import com.winnerbook.course.service.ReadThoughtService;

/**
 * 读后感
 * @author hxs
 */
@Controller
@RequestMapping(value="/readThoughtController")
public class ReadThoughtController extends BaseController{
	
	@Autowired
	private ReadThoughtService readThoughtService;
	
	@Autowired
	private CourseService courseService;

	private static final Logger logger = LoggerFactory.getLogger(ReadThoughtController.class);
	
	//查询列表
	@RequestMapping(value="/readThoughtList.html")
	public String busInfoList(HttpServletRequest request,Model model, Integer pageIndex,Integer pageSize){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("sessionUser",getSessionUser());
		PageDTO<ReadThought> pageDTO  = readThoughtService.listByPage(map, pageIndex,10);//默认每页显示10条数据
 
		model.addAttribute("pageDTO", pageDTO);
		model.addAllAttributes(map);
		return "manage/busRead/course/readThought/readThoughtList";
	}
	
	//点击添加跳转
	@RequestMapping(value="addReadThought.html")
	public String addReadThought(ModelMap modelMap){
		Map<String,Object> map = new HashMap<>();
		map.put("sessionUser",getSessionUser());
		modelMap.put("courseList", courseService.listByPage(map));
		return "manage/busRead/course/readThought/editReadThought";
	}
	
	//添加提交
	@RequestMapping(value="addReadThoughtSubmit.html")
	public String addReadThoughtSubmit(ReadThought readThought,String type){
		readThoughtService.insert(readThought);
		return "redirect:/readThoughtController/readThoughtList.html";
	}
	
	//点击修改
	@RequestMapping(value="updateReadThought.html")
	public String updateReadThought(@RequestParam String thoughtId,ModelMap modelMap){
		modelMap.addAttribute("readThought",readThoughtService.findById(thoughtId));
		//查询需要选择的课程
		Map<String,Object> map = new HashMap<>();
		map.put("sessionUser",getSessionUser());
		modelMap.put("courseList", courseService.listByPage(map));
		return "manage/busRead/course/readThought/editReadThought";
	}
	
	//修改提交
	@RequestMapping(value="updateSubmitReadThought.html")
	public String updateSubmitReadThought(ReadThought readThought){
		readThoughtService.update(readThought);
		return "redirect:/readThoughtController/readThoughtList.html";
	}
	
	//点击删除
	@RequestMapping("deleteReadThought.html")
	public String deleteReadThought(@RequestParam String thoughtId){
		readThoughtService.deleteReadThought(thoughtId);
		return "redirect:/readThoughtController/readThoughtList.html";
	}
	
	//点击下载
	@RequestMapping("downLoadReadThought.html")
	public void downLoadReadThought(@RequestParam String thoughtId,HttpServletResponse response){
		ReadThought readThought = readThoughtService.findById(thoughtId);
		FileUtils.downloadFilename(FileUtils.getRealtyPathName(readThought.getThoughtUrl()), readThought.getThoughtFilename(), response);
	}
	
	@RequestMapping("viewReadThought.html")
	public String viewReadThought(@RequestParam String thoughtId,ModelMap modelMap){
		modelMap.addAttribute("readThought",readThoughtService.findById(thoughtId));
		//查询需要选择的课程
		Map<String,Object> map = new HashMap<>();
		map.put("sessionUser",getSessionUser());
		modelMap.put("courseList", courseService.listByPage(map));
		return "manage/busRead/course/readThought/viewReadThought";
	}
	
}
