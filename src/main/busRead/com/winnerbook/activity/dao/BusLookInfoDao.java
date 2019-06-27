package com.winnerbook.activity.dao;

import java.util.List;
import java.util.Map;
import com.winnerbook.activity.dto.BusLookInfo;

public interface BusLookInfoDao {
	
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
	List<BusLookInfo> listByPage(Map<String, Object> parameter);
	
	BusLookInfo findById(Map<String, Object> parameter);

    int insert(BusLookInfo record);
    
    int update(BusLookInfo record);
    
}