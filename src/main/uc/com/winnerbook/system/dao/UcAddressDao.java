package com.winnerbook.system.dao;

import java.util.List;
import java.util.Map;
import com.winnerbook.system.dto.UcAddress;

public interface UcAddressDao {
	/**
	 * 根据id查询
	 * @param parameter
	 * @return
	 */
	UcAddress findById(Map<String, Object> parameter);
	
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
	List<UcAddress> listByPage(Map<String, Object> parameter);

	/**
	 * 新增
	 * @param ucAddress
	 */
	void insert(UcAddress ucAddress);
	/**
	 * 修改
	 * @param menu
	 */
	void update(UcAddress ucAddress);
	/**
	 * 删除
	 * @param addressId
	 */
	void delete(String addressId);
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
	List<UcAddress> findAddressByParentId(Map<String, Object> parameter);
	
}
