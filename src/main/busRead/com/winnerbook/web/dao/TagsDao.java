package com.winnerbook.web.dao;

import java.util.List;
import java.util.Map;
import com.winnerbook.web.dto.Tags;

public interface TagsDao {
	
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
	List<Tags> listByPage(Map<String, Object> parameter);
	
    int insert(Tags record);
    
    Tags findById(Map<String, Object> parameter);

    int update(Tags record);
    
    List<Tags> getTagsList(Map<String, Object> parameter);
    
    int tagsCount(Map<String, Object> parameter);
    
}