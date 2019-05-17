package com.winnerbook.system.service;


import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.winnerbook.system.dto.Role;
import com.winnerbook.system.dto.UserRole;

public interface UserRoleService {

	/**
	 * 根据userId查询
	 * @param roleId
	 * @return
	 */
	public UserRole findById(String userId);
	
	/**
	 * 新增
	 * @param userRole
	 */
	void insert(UserRole userRole);
	
	/**
	 * 更新
	 * @param userRole
	 */
	void update(UserRole userRole);

	/**
	 * 查询所有角色
	 * @return
	 */
	List<Role> findAllRole(Map<String,Object> map);
	
	/**
	 * 新增
	 * @param role
	 */
	void insert1(HttpServletRequest request);
	
	

	/**
	 * 查询是否分配了角色
	 * @param userId
	 * @return
	 */
	Integer roleIsUser(String userId);
	
	/**
	 * 查询此用户的角色
	 * @param userId
	 * @return
	 */
	List<UserRole> findById1(String userId);
}
