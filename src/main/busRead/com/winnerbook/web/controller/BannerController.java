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
import com.winnerbook.web.dto.Banner;
import com.winnerbook.web.service.BannerService;

/**
 * 宣传图信息
 * @author hxs
 */
@Controller
@RequestMapping(value="/bannerController")
public class BannerController extends BaseController{
	
	@Autowired
	private BannerService bannerService;
	
	private static final Logger logger = LoggerFactory.getLogger(BannerController.class);
	
	//查询列表
	@RequestMapping(value="/bannerList.html")
	public String bannerList(HttpServletRequest request,Model model, Integer pageIndex,Integer pageSize){
		logger.info("banner列表.....");
		String bannerTitle =request.getParameter("bannerTitle");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("bannerTitle", bannerTitle);
		map.put("sessionUser",getSessionUser());
		PageDTO<Banner> pageDTO  = bannerService.listByPage(map, pageIndex,10);//默认每页显示10条数据
 
		model.addAttribute("pageDTO", pageDTO);
		model.addAllAttributes(map);
		return "manage/busRead/web/banner/bannerList";
	}
	
	//点击添加跳转
	@RequestMapping(value="addBanner.html")
	public String addBanner(ModelMap modelMap){
		return "manage/busRead/web/banner/editBanner";
	}
	
	//添加提交
	@RequestMapping(value="addSubmitBanner.html")
	public String addSubmitBanner(Banner banner){
		bannerService.insert(banner);
		return "redirect:/bannerController/bannerList.html";
	}
	
	//点击修改
	@RequestMapping(value="updateBanner.html")
	public String updateBanner(@RequestParam String bannerId,ModelMap modelMap){
		modelMap.addAttribute("banner",bannerService.findById(bannerId));
		return "manage/busRead/web/banner/editBanner";
	}
	
	//修改提交
	@RequestMapping(value="updateSubmitBanner.html")
	public String updateSubmitBanner(Banner banner){
		bannerService.update(banner);
		return "redirect:/bannerController/bannerList.html";
	}
	
}
