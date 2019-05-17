package com.winnerbook.web.service;

import java.util.Map;
import com.winnerbook.base.common.PageDTO;
import com.winnerbook.web.dto.News;

public interface NewsService {

	/**
	 * 根据id查询
	 * @param parameter
	 * @return
	 */
	News findById(String id);
	
	/**
	 * 分页查询
	 * @param parameter
	 * @return
	 */
	PageDTO<News> listByPage(Map<String, Object> parameter,Integer pageIndex, Integer pageSize);
	
	/**
	 * 新增
	 */
	void insert(News record);

	/**
	 * 修改
	 */
	void update(News record);
	
    
    void updateStatus(String newId,String status);
    
    //前端
    Map<String, Object> getNewsList(Map<String, Object> parameter);
    
    Map<String, Object> getNewsDetail(Map<String, Object> parameter);

	
}
