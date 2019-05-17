package com.winnerbook.web.service;

import java.util.List;
import java.util.Map;

import com.winnerbook.base.common.PageDTO;
import com.winnerbook.web.dto.Banner;

public interface BannerService {

	/**
	 * 根据id查询
	 * @param parameter
	 * @return
	 */
	Banner findById(String id);
	
	/**
	 * 分页查询
	 * @param parameter
	 * @return
	 */
	PageDTO<Banner> listByPage(Map<String, Object> parameter,Integer pageIndex, Integer pageSize);
	
	/**
	 * 新增
	 */
	void insert(Banner record);

	/**
	 * 修改
	 */
	void update(Banner record);
	
    List<Map<String, Object>> getBannerList(Map<String, Object> parameter);

	
}
