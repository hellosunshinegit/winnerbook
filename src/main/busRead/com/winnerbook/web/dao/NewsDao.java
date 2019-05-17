package com.winnerbook.web.dao;

import java.util.List;
import java.util.Map;

import com.winnerbook.web.dto.News;

public interface NewsDao {
	
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
	List<News> listByPage(Map<String, Object> parameter);
	
    int insert(News record);
    
    News findById(Map<String, Object> parameter);

    int update(News record);
    
    void updateStatus(Map<String, Object> parameter);
    
    //前端使用
    List<Map<String, Object>> getNewsList(Map<String, Object> parameter);
    int getNewsCount(Map<String, Object> parameter);
    Map<String, Object> getNewsDetail(Map<String, Object> parameter);

}