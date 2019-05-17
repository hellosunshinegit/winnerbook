package com.winnerbook.web.controller;

import java.util.HashMap;
import java.util.Map;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.winnerbook.base.common.JSONResponse;
import com.winnerbook.web.service.ArticleService;
import com.winnerbook.web.utils.ConstantWebUtils;
import com.winnerbook.web.utils.PageUtil;
@Controller
public class ArticleH5Web {
	
	@Autowired
	private ArticleService articleService;

	//点击模板 block，进入到文件列表
	@RequestMapping(value="getArticles.jhtml",produces = {"application/json;charset=utf-8"})
	@ResponseBody
	public String getArticles(String blockId,String busId,String pageIndex,@RequestParam("callback") String callback){
		JSONResponse result = new JSONResponse();
		if(!StringUtils.isNotBlank(busId)){
			busId = ConstantWebUtils.busIdDefulat;
		}
		
		Map<String, Object> parameter = new HashMap<String, Object>();
		parameter.put("blockId", blockId);
 		Map<String, Object> article = articleService.getArticles(PageUtil.getParam(parameter, "1", pageIndex));//读取amdin添加的数据
		
 		result.setSuccess(true);
		result.setMsg("获取文章列表成功");
		result.setData(article);
		return callback+"("+JSONObject.fromObject(result)+")";
	}
	
	//获取文章详情表
	@RequestMapping(value="getArticleDetail.jhtml",produces = {"application/json;charset=utf-8"})
	@ResponseBody
	public String getArticles(@RequestParam String articleId,String busId,@RequestParam("callback") String callback){
		JSONResponse result = new JSONResponse();
		if(!StringUtils.isNotBlank(busId)){
			busId = ConstantWebUtils.busIdDefulat;
		}
		
		Map<String, Object> parameter = new HashMap<String, Object>();
		parameter.put("busId", "1");
		parameter.put("articleId", articleId);
 		Map<String, Object> article = articleService.getArticleDetail(parameter);
		
 		result.setSuccess(true);
		result.setMsg("获取文章详情成功");
		result.setData(article);
		return callback+"("+JSONObject.fromObject(result)+")";
	}
	
}
