package com.winnerbook.system.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.winnerbook.base.frame.dao.BaseDAO;
import com.winnerbook.system.dao.RoleDao;
import com.winnerbook.system.dto.Role;

@Repository(value="roleDao")
public class RoleDaoImpl extends BaseDAO implements RoleDao{
	
	private static final String ROLE_MAPPER = "RoleMapper.";

	@Override
	public Role findById(Map<String, Object> parameter) {
		return this.sqlSession.selectOne(ROLE_MAPPER + "findById", parameter);
	}

	@Override
	public int selectCount(Map<String, Object> parameter) {
		Object obj = this.sqlSession.selectOne(ROLE_MAPPER + "selectCount", parameter);
		return obj == null ? 0 : Integer.parseInt(obj.toString());
	}

	@Override
	public List<Role> listByPage(Map<String, Object> parameter) {
		return this.sqlSession.selectList(ROLE_MAPPER + "listByPage", parameter);
	}

	@Override
	public int insert(Role role) {
		return this.sqlSession.insert(ROLE_MAPPER + "insert", role);
	}

	@Override
	public void update(Role role) {
		this.sqlSession.update(ROLE_MAPPER+"update", role);
	}

	@Override
	public void delete(String roleId) {
		this.sqlSession.update(ROLE_MAPPER+"delete", roleId);
	}

	@Override
	public List<Role> findAllRole() {
		return this.sqlSession.selectList(ROLE_MAPPER+"findAllRole");
	}
}
