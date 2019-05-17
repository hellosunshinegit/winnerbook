package com.winnerbook.system.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.winnerbook.base.frame.dao.BaseDAO;
import com.winnerbook.system.dao.UserRoleDao;
import com.winnerbook.system.dto.Role;
import com.winnerbook.system.dto.UserRole;

@Repository(value="userRoleDao")
public class UserRoleDaoImpl extends BaseDAO implements UserRoleDao{
	
	private static final String USERROLE_MAPPER = "UserRoleMapper.";
	
	@Override
	public UserRole findById(Map<String, Object> parameter) {
		return this.sqlSession.selectOne(USERROLE_MAPPER+"findUserById",parameter);
	}

	@Override
	public void insert(UserRole userRole) {
		this.sqlSession.insert(USERROLE_MAPPER+"insert",userRole);
	}

	@Override
	public void update(UserRole userRole) {
		this.sqlSession.update(USERROLE_MAPPER+"update",userRole);
	}

	@Override
	public List<Role> findAllRole(Map<String,Object> map) {
		return this.sqlSession.selectList(USERROLE_MAPPER+"findAllRole",map);
	}

	@Override
	public Integer roleIsUser(String userId) {
		
		return this.sqlSession.selectOne(USERROLE_MAPPER+"roleIsUser", userId);
	}

	@Override
	public List<UserRole> findById1(Map<String, Object> parameter) {
		return this.sqlSession.selectList(USERROLE_MAPPER+"findUserById1",parameter);
	}

	@Override
	public int insertBathUserRole(List<UserRole> userRoles) {
		return this.sqlSession.insert(USERROLE_MAPPER+"insertBathUserRole",userRoles);
	}

	
}
