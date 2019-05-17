package com.winnerbook.system.service;

import java.util.List;
import java.util.Map;

import com.winnerbook.base.common.PageDTO;
import com.winnerbook.system.dto.Role;
import com.winnerbook.system.dto.User;

public interface RoleService {

	/**
	 * 根据id查询
	 * @param id
	 * @return
	 */
	Role findById(String id);
	
	/**
	 * 分页查询
	 * @param parameter
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 */
	PageDTO<Role> listByPage(Map<String, Object> parameter,Integer pageIndex, Integer pageSize);
	
	/**
	 * 新增
	 * @param role
	 */
	void insert(Role role);
	
	/**
	 * 修改
	 * @param dictionary
	 */
	void update(Role role);
	
	/**
	 * 删除
	 * @param id
	 */
	void delete(String id);
	
	/**
	 * 查询所有角色，无分页的
	 * @return
	 */
	List<Role> findAllRole();
	
}
