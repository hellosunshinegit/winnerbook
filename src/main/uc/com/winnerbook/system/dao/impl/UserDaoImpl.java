/** 
* 2016-2-15
* UserTestDaoImpl.java 
* author:hxs
*/ 
package com.winnerbook.system.dao.impl; 

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Repository;

import com.winnerbook.base.frame.dao.BaseDAO;
import com.winnerbook.system.dao.UserDao;
import com.winnerbook.system.dto.Role;
import com.winnerbook.system.dto.User;

@Repository("userDao")
public class UserDaoImpl extends BaseDAO implements UserDao{
	private static final String USER_MAPPER = "UserMapper.";
	
	@Override
	public List<Map<String, Object>> findUserByUserName(Map<String, Object> parameter) {
		return this.sqlSession.selectList(USER_MAPPER + "findUserByUserName", parameter);
	}

	@Override
	public User findUserById(Map<String, Object> parameter) {
		return this.sqlSession.selectOne(USER_MAPPER + "findUserById", parameter);
	}

	@Override
	public int selectCount(Map<String, Object> parameter) {
		Object obj = this.sqlSession.selectOne(USER_MAPPER + "selectCount", parameter);
		return obj == null ? 0 : Integer.parseInt(obj.toString());
	}

	@Override
	public List<User> listByPage(Map<String, Object> parameter) {
		return this.sqlSession.selectList(USER_MAPPER + "listByPage", parameter);
	}

	@Override
	public void delete(String userId) {
		this.sqlSession.update(USER_MAPPER+"delete", userId);
	}
	
	@Override
	public void update(User user) {
		this.sqlSession.update(USER_MAPPER+"update", user);
	}
	
	@Override
	public int insert(User user) {
		return this.sqlSession.insert(USER_MAPPER+"insert", user);
	}
	
	@Override
	public void updateUserPassword(String userId,String userPassword) {
		this.sqlSession.update(USER_MAPPER+"updateUserPassword", userPassword);
	}
	
	@Override
	public void userRole(Role role) {
		this.sqlSession.update(USER_MAPPER+"userRole", role);	
	}

	@Override
	public List<User> findUserByUcOrganIds(Map<String, Object> parameter) {
		return this.sqlSession.selectList(USER_MAPPER+"findUserByUcOrganIds", parameter);
	}

	@Override
	public void resetPassword(User user) {
		this.sqlSession.update(USER_MAPPER+"resetPassword", user);
		
	}

	@Override
	public List<User> checkUserName(String userId,String userName) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("userId", userId);
		map.put("userName", userName);
		
		//根据用户id查询所属企业，判断相同企业不重复
		Map<String, Object> map_user  = new HashMap<>();
		map_user.put("userId", userId);
 		User user = findUserById(map_user);
 		
 		if(StringUtils.isNotBlank(user.getBelongBusUserId())){
 			map.put("belongBusUserId", user.getBelongBusUserId());
 		}
		return this.sqlSession.selectList(USER_MAPPER+"checkUserName", map);	
	}

	@Override
	public int importUser(User user) {
		return this.sqlSession.insert(USER_MAPPER+"importUser",user);
	}

	@Override
	public List<User> getAdmin() {
		return this.sqlSession.selectList(USER_MAPPER+"getAdmin");
	}

	@Override
	public User getBusinessAdmin(Map<String, Object> parameter) {
		return this.sqlSession.selectOne(USER_MAPPER+"getBusinessAdmin",parameter);
	}

	@Override
	public void updateBelongBusUser(Map<String, Object> parameter) {
		this.sqlSession.update(USER_MAPPER+"updateBelongBusUser",parameter);
	}

	@Override
	public List<Map<String, Object>> getUserByBusinessId(
			Map<String, Object> parameter) {
		return this.sqlSession.selectList(USER_MAPPER+"getUserByBusinessId",parameter);
	}

	@Override
	public User getUserByUserBus(Map<String, Object> parameter) {
		return this.sqlSession.selectOne(USER_MAPPER+"getUserByUserBus",parameter);
	}

	@Override
	public User isExistsByBusId(Map<String, Object> parameter) {
		return this.sqlSession.selectOne(USER_MAPPER+"isExistsByBusId",parameter);
	}

	@Override
	public Map<String, Object> isBelongBus(Map<String, Object> parameter) {
		return this.sqlSession.selectOne(USER_MAPPER+"isBelongBus",parameter);
	}

}
