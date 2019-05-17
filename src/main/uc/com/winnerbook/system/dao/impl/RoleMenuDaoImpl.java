package com.winnerbook.system.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.winnerbook.base.frame.dao.BaseDAO;
import com.winnerbook.system.dao.RoleMenuDao;
import com.winnerbook.system.dto.RoleMenu;

@Repository(value="roleMenuDao")
public class RoleMenuDaoImpl extends BaseDAO implements RoleMenuDao{
	
	private static final String ROLEMENU_MAPPER = "RoleMenuMapper.";

	@Override
	public List<RoleMenu> findById(Map<String, Object> parameter) {
		return this.sqlSession.selectList(ROLEMENU_MAPPER+"findRoleById", parameter);
	}

	@Override
	public void insert(RoleMenu roleMenu) {
		this.sqlSession.insert(ROLEMENU_MAPPER+"insert",roleMenu);
	}

	@Override
	public void delete(String roleId) {
		this.sqlSession.delete(ROLEMENU_MAPPER+"deleteByRoleId", roleId);
	}
	
	@Override
	public Integer roleIsMenu(String roleId) {
		return this.sqlSession.selectOne(ROLEMENU_MAPPER+"roleIsMenu", roleId);
	}

	@Override
	public int insertBathRoleMenu(List<RoleMenu> roleMenus) {
		return this.sqlSession.insert(ROLEMENU_MAPPER+"insertBathRoleMenu",roleMenus);
	}

}
