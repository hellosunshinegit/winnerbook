package com.winnerbook.web.service;

import java.util.Map;

import com.winnerbook.base.common.PageDTO;
import com.winnerbook.web.dto.Article;

public interface ArticleService {

	/**
	 * 根据id查询
	 * @param parameter
	 * @return
	 */
	Map<String,Object> findById(String id);
	
	/**
	 * 分页查询
	 * @param parameter
	 * @return
	 */
	PageDTO<Article> listByPage(Map<String, Object> parameter,Integer pageIndex, Integer pageSize);
	
	/**
	 * 新增
	 */
	void insert(Article record);

	/**
	 * 修改
	 */
	void update(Article record);
	
	void updateStatus(String articleId,String status);
	
    Map<String, Object> getArticles(Map<String, Object> parameter);
    
    Map<String, Object> getArticleDetail(Map<String, Object> parameter);

	
}
