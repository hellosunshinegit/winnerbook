/** 
* 2015-3-5
* RoleDao.java 
* author:hanxiaoshuang
*/ 
package com.winnerbook.system.dao; 

import java.util.List;
import java.util.Map;

import com.winnerbook.system.dto.Role;

public interface RoleDao {

	/**
	 * 根据id查询
	 * @param parameter
	 * @return
	 */
	Role findById(Map<String, Object> parameter);
	
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
	List<Role> listByPage(Map<String, Object> parameter);
	
	/**
	 * 新增
	 * @param role
	 */
	int insert(Role role);
	/**
	 * 修改
	 * @param role
	 */
	void update(Role role);
	/**
	 * 删除
	 * @param id
	 */
	void delete(String roleId);
	/**
	 * 查询所有角色，无分页的
	 * @return
	 */
	List<Role> findAllRole();

}
