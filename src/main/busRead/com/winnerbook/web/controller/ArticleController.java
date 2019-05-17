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
import com.winnerbook.web.dto.Article;
import com.winnerbook.web.service.ArticleService;
import com.winnerbook.web.service.ArticleTypeService;
import com.winnerbook.web.service.BlockService;
import com.winnerbook.web.service.TagsService;

/**
 * 文章信息
 * @author hxs
 */
@Controller
@RequestMapping(value="/articleController")
public class ArticleController extends BaseController{
	
	@Autowired
	private ArticleService articleService;
	
	@Autowired
	private ArticleTypeService articleTypeService;
	
	@Autowired
	private TagsService tagsService;
	
	@Autowired
	private BlockService blockService;
	
	private static final Logger logger = LoggerFactory.getLogger(ArticleController.class);
	
	//查询列表
	@RequestMapping(value="/articleList.html")
	public String busInfoList(HttpServletRequest request,Model model, Integer pageIndex,Integer pageSize){
		logger.info("文章列表.....");
		String articleTitle =request.getParameter("articleTitle");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("articleTitle", articleTitle);
		map.put("sessionUser",getSessionUser());
		PageDTO<Article> pageDTO  = articleService.listByPage(map, pageIndex,10);//默认每页显示10条数据
 
		model.addAttribute("pageDTO", pageDTO);
		model.addAllAttributes(map);
		return "manage/busRead/web/article/articleList";
	}
	
	//点击添加跳转
	@RequestMapping(value="addArticle.html")
	public String addArticle(ModelMap modelMap){
		modelMap.addAttribute("articleTypeList", articleTypeService.findArticleType());//读取文章类型
		modelMap.addAttribute("tagList", tagsService.getTagsList());//读取标签
		modelMap.addAttribute("blockList", blockService.getBlockList());//版块
		return "manage/busRead/web/article/editArticle";
	}
	
	//添加提交
	@RequestMapping(value="addSubmitArticle.html")
	public String addSubmitArticle(Article article){
		articleService.insert(article);
		return "redirect:/articleController/articleList.html";
	}
	
	//点击修改
	@RequestMapping(value="updateArticle.html")
	public String updateCourseType(@RequestParam String articleId,ModelMap modelMap){
		modelMap.addAttribute("articleTypeList", articleTypeService.findArticleType());//读取文章类型
		modelMap.addAttribute("tagList", tagsService.getTagsList());//读取标签
		modelMap.addAttribute("blockList", blockService.getBlockList());//版块
		modelMap.addAttribute("article",articleService.findById(articleId));
		return "manage/busRead/web/article/editArticle";
	}
	
	//修改提交
	@RequestMapping(value="updateSubmitArticle.html")
	public String updateSubmitArticle(Article Article){
		articleService.update(Article);
		return "redirect:/articleController/articleList.html";
	}
	
	//详情
	@RequestMapping(value="viewArticle.html")
	public String viewArticle(@RequestParam String articleId,ModelMap modelMap){
		modelMap.addAttribute("articleTypeList", articleTypeService.findArticleType());//读取文章类型
		modelMap.addAttribute("tagList", tagsService.getTagsList());//读取标签
		modelMap.addAttribute("blockList", blockService.getBlockList());//版块
		modelMap.addAttribute("article",articleService.findById(articleId));
		return "manage/busRead/web/article/viewArticle";
	}
	
	//修改状态
	@RequestMapping(value="updateStatus.html")
	public String updateStatus(@RequestParam String articleId,@RequestParam String status){
		articleService.updateStatus(articleId, status);
		return "redirect:/articleController/articleList.html";
	}
	
}
