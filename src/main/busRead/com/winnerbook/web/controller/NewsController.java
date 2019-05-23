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

import com.winnerbook.base.common.PageDTO;
import com.winnerbook.base.frame.controller.BaseController;
import com.winnerbook.web.dto.News;
import com.winnerbook.web.service.NewsService;
import com.winnerbook.wx.service.WxInfoService;

/**
 * 宣传图信息
 * @author hxs
 */
@Controller
@RequestMapping(value="/newsController")
public class NewsController extends BaseController{
	
	@Autowired
	private NewsService newsService;
	
	@Autowired
	private WxInfoService wxInfoService;
	
	private static final Logger logger = LoggerFactory.getLogger(NewsController.class);
	
	//查询列表
	@RequestMapping(value="/newList.html")
	public String bannerList(HttpServletRequest request,Model model, Integer pageIndex,Integer pageSize){
		logger.info("news列表.....");
		String newTitle =request.getParameter("newTitle");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("newTitle", newTitle);
		map.put("sessionUser",getSessionUser());
		PageDTO<News> pageDTO  = newsService.listByPage(map, pageIndex,10);//默认每页显示10条数据
 
		model.addAttribute("pageDTO", pageDTO);
		//获取微博appid
		map.put("wxInfo", wxInfoService.getWxInfo("2"));
		model.addAllAttributes(map);
		return "manage/busRead/web/news/newsList";
	}
	
	//点击添加跳转
	@RequestMapping(value="addNews.html")
	public String addNews(ModelMap modelMap){
		return "manage/busRead/web/news/editNews";
	}
	
	//添加提交
	@RequestMapping(value="addSubmitNews.html")
	public String addSubmitNews(News news){
		newsService.insert(news);
		return "redirect:/newsController/newList.html";
	}
	
	//点击修改
	@RequestMapping(value="updateNews.html")
	public String updateNews(@RequestParam String newId,ModelMap modelMap){
		modelMap.addAttribute("news",newsService.findById(newId));
		return "manage/busRead/web/news/editNews";
	}
	
	//修改提交
	@RequestMapping(value="updateSubmitNews.html")
	public String updateSubmitNews(News news){
		newsService.update(news);
		return "redirect:/newsController/newList.html";
	}
	
	//点击修改
	@RequestMapping(value="viewNews.html")
	public String viewNews(@RequestParam String newId,ModelMap modelMap){
		modelMap.addAttribute("news",newsService.findById(newId));
		return "manage/busRead/web/news/viewNews";
	}
	
	@RequestMapping(value="updateStatus.html")
	public String updateStatus(@RequestParam String newId,@RequestParam String status){
		newsService.updateStatus(newId,status);
		return "redirect:/newsController/newList.html";
	}
	
	
	
}
