/** 
* 2016-2-15
* UserTestService.java 
*/ 
package com.winnerbook.system.service; 

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.winnerbook.base.common.PageDTO;
import com.winnerbook.share.dto.Qrcode;
import com.winnerbook.system.dto.Role;
import com.winnerbook.system.dto.User;

public interface UserService {
	
	/**
	 * 根据name查询
	 * @param userName
	 * @return
	 */
	List<Map<String, Object>> findUserByUserName(String userName);
	
	/**
	 * 根据id查询
	 * @param userId
	 * @return
	 */
	User findUserById(String userId);
	
	/**
	 * 分页查询
	 * @param parameter
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 */
	PageDTO<User> listByPage(Map<String, Object> parameter,Integer pageIndex, Integer pageSize);
	
	/**
	 * 删除
	 * @param id
	 */
	void delete(String id);
	
	/**
	 * 修改
	 * @param 
	 */
	void update(User user);
	
	/**
	 * 新增
	 * @param user
	 */
	int insert(User user);

	/**
	 * 修改密码提交
	 * @param 
	 */
	Map<String,Object> updateUserPassword(String userPassword);

	/**
	 * 角色修改
	 * @param 
	 */
	void userRole(Role role);
	/**
	 * 用户选择机构
	 * @param request
	 */
	void organSubmit(HttpServletRequest request);

	/**
	 * 重置密码
	 * @param user
	 */
	void resetPassword(User user);

	/**
	 * 判断用户名重复否
	 * @param user
	 */
	Map<String,Integer> checkUserName(String userId,String userName);
	
	//导入用户
	Map<String,Object> importUser(HttpServletRequest request);
	
	User getAdmin();
	
	void updateBelongBusUser(String userId,String belongBusUserId);

	String userCourseTypes();
	
	User getUserByUserBus(String userId,String busId);
	
	User isExistsByBusId(String busId,String phone);
	
	Qrcode getBusQrcode(String busId);
	
	//权限转让员工下拉选择
	List<User> getBusEmp(String busId);
	//点击权限转让提交
	String busAdminTransferSubmit(String userId,String selectUserId);
}
