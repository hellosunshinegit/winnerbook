package com.winnerbook.activity.service;

import java.util.Map;

import com.winnerbook.activity.dto.Activity;
import com.winnerbook.activity.dto.BusLookInfo;
import com.winnerbook.base.common.JSONResponse;
import com.winnerbook.base.common.PageDTO;

public interface BusLookInfoService {

	/**
	 * 根据id查询
	 * @param parameter
	 * @return
	 */
	BusLookInfo findById(String id);
	
	/**
	 * 分页查询
	 * @param parameter
	 * @return
	 */
	PageDTO<BusLookInfo> listByPage(Map<String, Object> parameter,Integer pageIndex, Integer pageSize);
	
	/**
	 * 修改
	 * @param record
	 */
	void updateStatus( String id,String status,String statusReson);
	
	//审核
	public JSONResponse examineBus(String json);
}
