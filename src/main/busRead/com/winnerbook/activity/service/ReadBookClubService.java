package com.winnerbook.activity.service;

import java.util.List;
import java.util.Map;

import com.winnerbook.activity.dto.ReadBookClub;
import com.winnerbook.base.common.PageDTO;

public interface ReadBookClubService {

	/**
	 * 根据id查询
	 * @param parameter
	 * @return
	 */
	ReadBookClub findById(String clubId);
	
	/**
	 * 分页查询
	 * @param parameter
	 * @return
	 */
	PageDTO<ReadBookClub> listByPage(Map<String, Object> parameter,Integer pageIndex, Integer pageSize);
	
	/**
	 * 新增
	 * @param dictionary
	 */
	void insert(ReadBookClub readBookClub);

	/**
	 * 修改
	 * @param dictionary
	 */
	void update(ReadBookClub readBookClub);
	
	//删除
	void delete(String clubId);
	
	//web端使用
    Map<String, Object> getBookClubs(Map<String, Object> parameter);
    Map<String, Object> getReadBookClubDetail(Map<String, Object> parameter);
	
}
