/** 
* 2015-3-6
* RoleDao.java 
* author:hanxiaoshuang
*/ 
package com.winnerbook.system.dao; 

import java.util.List;
import java.util.Map;

import com.winnerbook.system.dto.RoleMenu;

public interface RoleMenuDao {

	/**
	 * 根据id查询
	 * @param parameter
	 * @return
	 */
	List<RoleMenu> findById(Map<String, Object> parameter);

	/**
	 * 新增
	 * @param role
	 */
	void insert(RoleMenu roleMenu);
	/**
	 * 删除
	 * @param id
	 */
	void delete(String roleId);
	/**
	 * 查询该角色下是否有权限
	 * @param roleId
	 * @return
	 */
	Integer roleIsMenu(String roleId);
	
	int insertBathRoleMenu(List<RoleMenu> roleMenus);
	

}
