package com.winnerbook.system.dao;

import java.util.List;
import java.util.Map;

import com.winnerbook.system.dto.Menu;

public interface MenuDao {
	/**
	 * 根据id查询
	 * @param parameter
	 * @return
	 */
	Menu findById(Map<String, Object> parameter);
	
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
	List<Menu> listByPage(Map<String, Object> parameter);

	/**
	 * 新增
	 * @param menu
	 */
	void insert(Menu menu);
	/**
	 * 修改
	 * @param menu
	 */
	void update(Menu menu);
	/**
	 * 删除
	 * @param menuId
	 */
	void delete(String menuId);
	/**
	 * 查询一级和耳机菜单
	 * @return
	 */
	List<Menu> findMenuByCode();
	/**
	 * 查询所有菜单
	 * @return
	 */
	List<Menu> findAllMenue();
	/**
	 * 根据用户id查询权限
	 * @param userId
	 * @return
	 */
	List<Menu> queryMenuByUserId(Map<String, Object> parameter);
	/**
	 * 根据parent获取值
	 * @param userId
	 * @param parentid
	 * @return
	 */
	List<Menu> queryMenuByParentUserId(Map<String, Object> parameter);
	
	/**
	 * 根据parent获取值
	 * @param userId
	 * @param parentid
	 * @return
	 */
	List<Menu> queryAllMenuByParentId(Map<String, Object> parameter);
	
	List<Menu> findMenueByUserId(Map<String, Object> parameter);
	
	
}
