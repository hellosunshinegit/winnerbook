/** 
* 2019-5-29
* author:hxs
*/ 
package com.winnerbook.system.dao; 

import java.util.List;
import java.util.Map;

import com.winnerbook.system.dto.UserApplyBusAdmin;

public interface UserApplyBusAdminDao {
	
	/**
	 * 根据id查询
	 * @param parameter
	 * @return
	 */
	UserApplyBusAdmin findById(Map<String, Object> parameter);
	
	
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
	List<UserApplyBusAdmin> listByPage(Map<String, Object> parameter);
	
	/**
	 * 修改
	 * @param user
	 */
	void update(UserApplyBusAdmin userApplyBusAdmin);
	
	//web 申请成为企业管理员
	int insert(UserApplyBusAdmin userApplyBusAdmin);
	List<Map<String, Object>> getUserApplyBusAdmin(Map<String, Object> parameter);
}
