package com.winnerbook.course.dao;

import java.util.List;
import java.util.Map;
import com.winnerbook.course.dto.ReadThought;

public interface ReadThoughtDao {

	/**
	 * 查询总条数
	 * 
	 * @param parameter
	 * @return
	 */
	int selectCount(Map<String, Object> parameter);

	/**
	 * 分页查询
	 * 
	 * @param parameter
	 * @return
	 */
	List<ReadThought> listByPage(Map<String, Object> parameter);

	int insert(ReadThought record);

	ReadThought findById(Map<String, Object> parameter);

	int update(ReadThought record);
	
	void deleteReadThought(Map<String, Object> parameter);
	
	List<ReadThought> getReadThoughtByUserId(String userId);
	int updateReadThoughtById(Map<String, Object> parameter);
	
	//首页读后感列表 web
	List<Map<String, Object>> getReadThoughts(Map<String, Object> parameter);
	int getReadThoughtsCount(Map<String, Object> parameter);
	//读后感详情
	Map<String, Object> getReadThoughtsDetail(Map<String, Object> parameter);
	//个人读后感
	List<Map<String, Object>> getReadThoughtUser(Map<String, Object> parameter);
	int getReadThoughtUserCount(Map<String, Object> parameter);
}