package com.winnerbook.system.service;

import java.util.List;
import java.util.Map;
import com.winnerbook.base.common.PageDTO;
import com.winnerbook.system.dto.UcAddress;

public interface UcAddressService {

	/**
	 * 根据id查询
	 * @param id
	 * @return
	 */
	UcAddress findById(String id);
	
	/**
	 * 分页查询
	 * @param parameter
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 */
	PageDTO<UcAddress> listByPage(Map<String, Object> parameter,Integer pageIndex, Integer pageSize);
	
	/**
	 * 新增
	 * @param ucAddress
	 */
	void insert(UcAddress ucAddress);
	
	/**
	 * 修改
	 * @param ucAddress
	 */
	void update(UcAddress ucAddress);
	
	/**
	 * 删除
	 * @param id
	 */
	void delete(String id);
	
	/**
	 * 查询地址一级数据
	 * @return
	 */
	List<UcAddress> findAddressOneLevel();
	/**
	 * 查询下级数据
	 * @param parameter
	 * @return
	 */
	List<UcAddress> findAddressByParentId(String addressId);
	
}
