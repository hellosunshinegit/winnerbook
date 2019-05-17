package com.winnerbook.web.service;

import java.util.List;
import java.util.Map;

import com.winnerbook.base.common.PageDTO;
import com.winnerbook.web.dto.ArticleType;

public interface ArticleTypeService {

	/**
	 * 根据id查询
	 * @param parameter
	 * @return
	 */
	ArticleType findById(String id);
	
	/**
	 * 分页查询
	 * @param parameter
	 * @return
	 */
	PageDTO<ArticleType> listByPage(Map<String, Object> parameter,Integer pageIndex, Integer pageSize);
	
	/**
	 * 新增
	 */
	void insert(ArticleType record);

	/**
	 * 修改
	 */
	void update(ArticleType record);
	
    List<ArticleType> findArticleType();
	
}
