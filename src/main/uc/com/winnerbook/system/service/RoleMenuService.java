package com.winnerbook.system.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.winnerbook.system.dto.RoleMenu;

public interface RoleMenuService {

	/**
	 * 根据roleId查询
	 * @param roleId
	 * @return
	 */
	public List<RoleMenu> findById(String roleId);
	
	/**
	 * 新增
	 * @param role
	 */
	void insert(HttpServletRequest request);
	
	/**
	 * 删除
	 * @param roleId
	 */
	void delete(String roleId);
	
	/**
	 * 查询该角色下是否有权限
	 * @param roleId
	 * @return
	 */
	Integer roleIsMenu(String roleId);
	
}
