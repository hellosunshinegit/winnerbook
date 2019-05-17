package com.winnerbook.web.dao;

import java.util.List;
import java.util.Map;
import com.winnerbook.web.dto.Banner;

public interface BannerDao {
	
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
	List<Banner> listByPage(Map<String, Object> parameter);
	
    int insert(Banner record);
    
    Banner findById(Map<String, Object> parameter);

    int update(Banner record);
    
    List<Map<String, Object>> getBannerList(Map<String, Object> parameter);
    
}