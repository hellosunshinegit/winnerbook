package com.winnerbook.system.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.winnerbook.base.frame.dao.BaseDAO;
import com.winnerbook.system.dao.MenuDao;
import com.winnerbook.system.dto.Menu;

@Repository(value="menuDao")
public class MenuDaoImpl extends BaseDAO implements MenuDao{
	
	private static final String MENU_MAPPER = "MenuMapper.";

	@Override
	public Menu findById(Map<String, Object> parameter) {
		return this.sqlSession.selectOne(MENU_MAPPER + "findById", parameter);
	}

	@Override
	public int selectCount(Map<String, Object> parameter) {
		Object obj = this.sqlSession.selectOne(MENU_MAPPER + "selectCount", parameter);
		return obj == null ? 0 : Integer.parseInt(obj.toString());
	}

	@Override
	public List<Menu> listByPage(Map<String, Object> parameter) {
		return this.sqlSession.selectList(MENU_MAPPER + "listByPage", parameter);
	}

	@Override
	public void insert(Menu menu) {
		this.sqlSession.insert(MENU_MAPPER + "insert", menu);
	}

	@Override
	public void update(Menu menu) {
		this.sqlSession.update(MENU_MAPPER+"update", menu);
	}

	@Override
	public void delete(String menuId) {
		this.sqlSession.update(MENU_MAPPER+"delete", menuId);
	}

	@Override
	public List<Menu> findMenuByCode() {
		return this.sqlSession.selectList(MENU_MAPPER+"findMenuByCode");
	}

	@Override
	public List<Menu> findAllMenue() {
		return this.sqlSession.selectList(MENU_MAPPER+"findAllMenu");
	}

	@Override
	public List<Menu> queryMenuByUserId(Map<String, Object> parameter) {
		return this.sqlSession.selectList(MENU_MAPPER+"queryMenuByUserId", parameter);
	}

	@Override
	public List<Menu> queryMenuByParentUserId(Map<String, Object> parameter) {
		return this.sqlSession.selectList(MENU_MAPPER+"queryMenuByParentUserId", parameter);
	}

	@Override
	public List<Menu> queryAllMenuByParentId(Map<String, Object> parameter) {
		return this.sqlSession.selectList(MENU_MAPPER+"queryAllMenu", parameter);
	}

	@Override
	public List<Menu> findMenueByUserId(Map<String, Object> parameter) {
		return this.sqlSession.selectList(MENU_MAPPER+"findMenueByUserId",parameter);
	}

}
