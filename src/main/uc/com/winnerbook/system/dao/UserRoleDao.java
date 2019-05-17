/** 
* 2015-3-6
* RoleDao.java 
* author:hanxiaoshuang
*/ 
package com.winnerbook.system.dao; 

import java.util.List;
import java.util.Map;

import com.winnerbook.system.dto.Role;
import com.winnerbook.system.dto.RoleMenu;
import com.winnerbook.system.dto.UserRole;

public interface UserRoleDao {

	/**
	 * 根据用户id查询
	 * @param parameter
	 * @return
	 */
	UserRole findById(Map<String, Object> parameter);

	/**
	 * 新增
	 * @param role
	 */
	void insert(UserRole userRole);
	/**
	 * 删除
	 * @param id
	 */
	void update(UserRole userRole);

	/**
	 * 查询所有角色
	 * @return
	 */
	List<Role> findAllRole(Map<String,Object> map);

	/**
	 * 查询是否有角色
	 * @param userId
	 * @return
	 */
	Integer roleIsUser(String userId);

	/**
	 * 查询此用户的角色
	 * @param userId
	 * @return
	 */
	List<UserRole> findById1(Map<String, Object> parameter);

	int insertBathUserRole(List<UserRole> userRoles);

	

}
