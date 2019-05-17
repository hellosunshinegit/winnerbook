/** 
* 2016-2-18
*/ 
package com.winnerbook.system.service; 

import java.util.List;
import java.util.Map;

import com.winnerbook.system.dto.ShowUser;
import com.winnerbook.system.dto.User;

public interface UserLevelService {
	/**
	 * 根据当前id
	 * @param currentUserId
	 * @return
	 */
	List<ShowUser> getUserList(String currentUserId);
	
	/**
	 * 查询当前用户的下级用户
	 * @param parameter
	 * @return
	 */
	List<User> findUserByParentId(String currentUserId);
	
	/**
	 * 根据机构查询用户
	 * @param parameter
	 * @return
	 */
	List<User> findUserByOrganId(String organId);
	/**
	 * 根据当前用户  查询他下级的所有用户
	 * @param userId
	 * @return
	 */
	List<User> findAllUserByParentId(String userId);
	/**
	 * 点击机构，查询此机构下的所有用户
	 * @param organId
	 * @return
	 */
	List<User> findAllUserByOrganId(String organId);
	/**
	 * 根据查询条件返回新的数据
	 * @param map
	 * @return
	 */
	List<User> getNewUserByParameter(List<User> userLists,Map<String,Object> map);
	
}
