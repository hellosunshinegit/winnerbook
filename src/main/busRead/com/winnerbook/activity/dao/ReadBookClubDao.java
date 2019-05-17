package com.winnerbook.activity.dao;

import java.util.List;
import java.util.Map;
import com.winnerbook.activity.dto.ReadBookClub;

public interface ReadBookClubDao {
	
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
	List<ReadBookClub> listByPage(Map<String, Object> parameter);
	
    int insert(ReadBookClub record);
    
    ReadBookClub findById(Map<String, Object> parameter);

    int update(ReadBookClub record);
    
    void delete(Map<String, Object> parameter);
    
    List<Map<String, Object>> getBookClubs(Map<String, Object> parameter);
    int getBookClubsCount(Map<String, Object> parameter);
    Map<String, Object> getReadBookClubDetail(Map<String, Object> parameter);
    
}