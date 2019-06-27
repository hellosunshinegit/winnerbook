/** 
* 2016-2-15
* UserTestDao.java 
* author:hxs
*/ 
package com.winnerbook.system.dao; 

import java.util.List;
import java.util.Map;

import com.winnerbook.system.dto.Role;
import com.winnerbook.system.dto.User;

public interface UserDao {
	
	/**
	 * 根据name查询
	 * @param parameter
	 * @return
	 */
	List<Map<String, Object>> findUserByUserName(Map<String, Object> parameter);
	
	/**
	 * 根据id查询
	 * @param parameter
	 * @return
	 */
	User findUserById(Map<String, Object> parameter);
	
	
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
	List<User> listByPage(Map<String, Object> parameter);
	
	/**
	 * 删除
	 * @param userId
	 */
	void delete(String userId);
	
	/**
	 * 修改
	 * @param user
	 */
	void update(User user);
	
	/**
	 * 新增
	 * @param user
	 */
	int insert(User user);
	
	/**
	 * 修改用户密码提交
	 * @param user
	 */
	void updateUserPassword(String userId,String userPassword);

	/**
	 * 角色修改
	 * @param role
	 */
	void userRole(Role role);
	/**
	 * 根据organId 查询此机构下是否有用户
	 * @return
	 */
	List<User> findUserByUcOrganIds(Map<String, Object> parameter);

	/**
	 * 重置密码
	 * @param user
	 */
	void resetPassword(User user);

	/**
	 * 判断用户名重复否
	 * @return
	 */
	List<User> checkUserName(String userId,String userName);
	
	int importUser(User user);
	
	List<User> getAdmin();
	
	User getBusinessAdmin(Map<String, Object> parameter);
	
	void updateBelongBusUser(Map<String,Object> parameter);
	
	List<Map<String,Object>> getUserByBusinessId(Map<String,Object> parameter);
	
	User getUserByUserBus(Map<String,Object> parameter);
	
	User isExistsByBusId(Map<String,Object> parameter);
	
	//查询该用户是否属于这个企业的员工，排除体验用户
	Map<String,Object> isBelongBus(Map<String,Object> parameter);
}
