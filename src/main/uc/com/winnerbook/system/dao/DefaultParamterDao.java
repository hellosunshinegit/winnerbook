/** 
* 2015-3-6
* RoleDao.java 
* author:hanxiaoshuang
*/ 
package com.winnerbook.system.dao; 
import java.util.Map;

import com.winnerbook.system.dto.DefaultParamter;

public interface DefaultParamterDao {
	
	DefaultParamter findDefaultParamter();
	
	int insertDefaultBusRole(Map<String, Object> paramter);
	
	Map<String,Object> getDefaultBusRoleByBusId(String busAdminId);
	
}
