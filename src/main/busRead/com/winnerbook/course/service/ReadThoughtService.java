package com.winnerbook.course.service;

import java.util.List;
import java.util.Map;

import com.winnerbook.base.common.PageDTO;
import com.winnerbook.course.dto.ReadThought;

public interface ReadThoughtService {

	/**
	 * 根据id查询
	 * @param parameter
	 * @return
	 */
	ReadThought findById(String thoughId);
	
	/**
	 * 分页查询
	 * @param parameter
	 * @return
	 */
	PageDTO<ReadThought> listByPage(Map<String, Object> parameter,Integer pageIndex, Integer pageSize);
	
	/**
	 * 新增
	 */
	void insert(ReadThought readThought);

	/**
	 * 修改
	 * @param dictionary
	 */
	void update(ReadThought readThought);
	
	void deleteReadThought(String thoughtId);
	
	Map<String, Object> getReadThoughts(Map<String, Object> parameter);
	
	Map<String, Object> getReadThoughtsDetail(Map<String, Object> parameter);
	
	Map<String, Object> getReadThoughtUser(Map<String, Object> parameter);

		
}
