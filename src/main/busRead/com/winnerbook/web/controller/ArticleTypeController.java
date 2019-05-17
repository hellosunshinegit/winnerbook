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
import com.winnerbook.web.dto.ArticleType;
import com.winnerbook.web.service.ArticleTypeService;

/**
 * 文章类型信息
 * @author hxs
 */
@Controller
@RequestMapping(value="/articleTypeController")
public class ArticleTypeController extends BaseController{
	
	@Autowired
	private ArticleTypeService articleTypeService;
	
	private static final Logger logger = LoggerFactory.getLogger(ArticleTypeController.class);
	
	//查询列表
	@RequestMapping(value="/articleTypeList.html")
	public String busInfoList(HttpServletRequest request,Model model, Integer pageIndex,Integer pageSize){
		logger.info("文章类型列表.....");
		String typeName =request.getParameter("typeName");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("typeName", typeName);
		map.put("sessionUser",getSessionUser());
		PageDTO<ArticleType> pageDTO  = articleTypeService.listByPage(map, pageIndex,10);//默认每页显示10条数据
 
		model.addAttribute("pageDTO", pageDTO);
		model.addAllAttributes(map);
		return "manage/busRead/web/articleType/articleTypeList";
	}
	
	//点击添加跳转
	@RequestMapping(value="addArticleType.html")
	public String addArticle(ModelMap modelMap){
		return "manage/busRead/web/articleType/editArticleType";
	}
	
	//添加提交
	@RequestMapping(value="addSubmitArticle.html")
	public String addSubmitArticle(ArticleType articleType){
		articleTypeService.insert(articleType);
		return "redirect:/articleTypeController/articleTypeList.html";
	}
	
	//点击修改
	@RequestMapping(value="updateArticle.html")
	public String updateCourseType(@RequestParam String typeId,ModelMap modelMap){
		modelMap.addAttribute("articleType",articleTypeService.findById(typeId));
		return "manage/busRead/web/articleType/editArticleType";
	}
	
	//修改提交
	@RequestMapping(value="updateSubmitArticleType.html")
	public String updateSubmitArticle(ArticleType articleType){
		articleTypeService.update(articleType);
		return "redirect:/articleTypeController/articleTypeList.html";
	}
	
}
