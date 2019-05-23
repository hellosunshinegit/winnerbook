package com.winnerbook.share.service;

import java.util.Map;
import com.winnerbook.base.common.PageDTO;
import com.winnerbook.share.dto.Qrcode;

public interface QrcodeService {

	/**
	 * 根据id查询
	 * @param parameter
	 * @return
	 */
	Qrcode findById(String id);
	
	/**
	 * 分页查询
	 * @param parameter
	 * @return
	 */
	PageDTO<Qrcode> listByPage(Map<String, Object> parameter,Integer pageIndex, Integer pageSize);
	
	/**
	 * 新增
	 */
	int insert(Qrcode record);
	
	/**
	 * 修改
	 * @param dictionary
	 */
	void update(Qrcode record);
	
	//根据企业id查询二维码
	Qrcode getQrcodeByBusId(String busId);
	

		
}
