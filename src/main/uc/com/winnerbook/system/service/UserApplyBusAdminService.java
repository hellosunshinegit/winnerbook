/** 
* 2016-2-15
* UserTestService.java 
*/ 
package com.winnerbook.system.service; 

import java.util.Map;

import com.winnerbook.base.common.JSONResponse;
import com.winnerbook.base.common.PageDTO;
import com.winnerbook.system.dto.UserApplyBusAdmin;

public interface UserApplyBusAdminService {
	
	/**
	 * 根据id查询
	 * @param userId
	 * @return
	 */
	UserApplyBusAdmin findById(String uaId);
	
	/**
	 * 分页查询
	 * @param parameter
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 */
	PageDTO<UserApplyBusAdmin> listByPage(Map<String, Object> parameter,Integer pageIndex, Integer pageSize);
	
	int update(UserApplyBusAdmin userApplyBusAdmin);
	
	JSONResponse updateSubmitUser(String json);
	
	int insert(UserApplyBusAdmin userApplyBusAdmin);
	
	Map<String, Object> getUserApplyBusAdmin(String userId);
	
}
