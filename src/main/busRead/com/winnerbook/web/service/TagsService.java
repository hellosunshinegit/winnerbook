package com.winnerbook.web.service;

import java.util.List;
import java.util.Map;

import com.winnerbook.base.common.PageDTO;
import com.winnerbook.web.dto.Tags;

public interface TagsService {

	/**
	 * 根据id查询
	 * @param parameter
	 * @return
	 */
	Tags findById(String id);
	
	/**
	 * 分页查询
	 * @param parameter
	 * @return
	 */
	PageDTO<Tags> listByPage(Map<String, Object> parameter,Integer pageIndex, Integer pageSize);
	
	/**
	 * 新增
	 */
	void insert(Tags record);

	/**
	 * 修改
	 */
	void update(Tags record);
	
    List<Tags> getTagsList();
    
    int tagsCount();
	
}
