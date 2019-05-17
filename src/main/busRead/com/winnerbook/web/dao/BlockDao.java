package com.winnerbook.web.dao;

import java.util.List;
import java.util.Map;

import com.winnerbook.web.dto.Block;

public interface BlockDao {
	
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
	List<Block> listByPage(Map<String, Object> parameter);
	
    int insert(Block record);
    
    Block findById(Map<String, Object> parameter);

    int update(Block record);
    
    List<Block> getBlockList(Map<String, Object> parameter);
    
    List<Map<String, Object>> getBlocks(Map<String, Object> parameter);
}