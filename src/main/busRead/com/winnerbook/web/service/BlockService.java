package com.winnerbook.web.service;

import java.util.List;
import java.util.Map;

import com.winnerbook.base.common.PageDTO;
import com.winnerbook.web.dto.Block;

public interface BlockService {

	/**
	 * 根据id查询
	 * @param parameter
	 * @return
	 */
	Block findById(String id);
	
	/**
	 * 分页查询
	 * @param parameter
	 * @return
	 */
	PageDTO<Block> listByPage(Map<String, Object> parameter,Integer pageIndex, Integer pageSize);
	
	/**
	 * 新增
	 */
	void insert(Block record);

	/**
	 * 修改
	 */
	void update(Block record);
	
    List<Block> getBlockList();
    
    //前端使用
    List<Map<String, Object>> getBlocks(Map<String, Object> parameter);

	
}
