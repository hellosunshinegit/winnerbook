package com.winnerbook.web.controller;

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
import org.springframework.web.bind.annotation.ResponseBody;

import com.winnerbook.base.common.PageDTO;
import com.winnerbook.base.frame.controller.BaseController;
import com.winnerbook.web.dto.Tags;
import com.winnerbook.web.service.TagsService;

/**
 * 标签信息
 * @author hxs
 */
@Controller
@RequestMapping(value="/tagsController")
public class TagsController extends BaseController{
	
	@Autowired
	private TagsService tagsService;
	
	private static final Logger logger = LoggerFactory.getLogger(TagsController.class);
	
	//查询列表
	@RequestMapping(value="/tagsList.html")
	public String tagsList(HttpServletRequest request,Model model, Integer pageIndex,Integer pageSize){
		logger.info("标签列表.....");
		String tagName =request.getParameter("tagName");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("tagName", tagName);
		map.put("sessionUser",getSessionUser());
		PageDTO<Tags> pageDTO  = tagsService.listByPage(map, pageIndex,10);//默认每页显示10条数据
 
		model.addAttribute("pageDTO", pageDTO);
		model.addAllAttributes(map);
		return "manage/busRead/web/tags/tagsList";
	}
	
	//点击添加跳转
	@RequestMapping(value="addTags.html")
	public String addTags(ModelMap modelMap){
		return "manage/busRead/web/tags/editTags";
	}
	
	//添加提交
	@RequestMapping(value="addSubmitTags.html")
	public String addSubmitTags(Tags tags){
		tagsService.insert(tags);
		return "redirect:/tagsController/tagsList.html";
	}
	
	//点击修改
	@RequestMapping(value="updateTags.html")
	public String updateTags(@RequestParam String tagId,ModelMap modelMap){
		modelMap.addAttribute("tags",tagsService.findById(tagId));
		return "manage/busRead/web/tags/editTags";
	}
	
	//修改提交
	@RequestMapping(value="updateSubmitTags.html")
	public String updateSubmitTags(Tags tags){
		tagsService.update(tags);
		return "redirect:/tagsController/tagsList.html";
	}
	
	//查询改企业输入tag的数量
	@RequestMapping("tagsCount.html")
	@ResponseBody
	public Integer tagsCount(){
		return tagsService.tagsCount();
	}
	
}
