package com.winnerbook.web.dao;

import java.util.List;
import java.util.Map;
import com.winnerbook.web.dto.ArticleType;

public interface ArticleTypeDao {
	
	/**
	 * 查询总条数
	 * @param parameter
	 * @return
	 */
	int  selectCount(Map<String, Object> parameter);
	
	/**
	 * 分页查询
	 * @param parameter
	 * @return
	 */
	List<ArticleType> listByPage(Map<String, Object> parameter);
	
    int insert(ArticleType record);
    
    ArticleType findById(Map<String, Object> parameter);

    int update(ArticleType record);
    
    List<ArticleType> findArticleType(Map<String, Object> parameter);
    
}