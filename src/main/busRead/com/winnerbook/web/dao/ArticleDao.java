package com.winnerbook.web.dao;

import java.util.List;
import java.util.Map;

import com.winnerbook.web.dto.Article;
import com.winnerbook.web.dto.ArticleBlock;
import com.winnerbook.web.dto.Block;

public interface ArticleDao {
	
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
	List<Article> listByPage(Map<String, Object> parameter);
	
    int insert(Article record);
    
    Map<String,Object> findById(Map<String, Object> parameter);

    int update(Article record);
    
    void updateStatus(Map<String, Object> parameter);
    
    List<Map<String, Object>> getArticles(Map<String, Object> parameter);
    
    int getArticlesCount(Map<String, Object> parameter);
    
    Map<String, Object> getArticleDetail(Map<String, Object> parameter);
        
}