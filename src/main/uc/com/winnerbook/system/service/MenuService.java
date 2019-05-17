package com.winnerbook.system.service;

import java.util.List;
import java.util.Map;

import com.winnerbook.base.common.PageDTO;
import com.winnerbook.system.dto.Menu;

public interface MenuService {

	/**
	 * 根据id查询
	 * @param id
	 * @return
	 */
	Menu findById(String id);
	
	/**
	 * 分页查询
	 * @param parameter
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 */
	PageDTO<Menu> listByPage(Map<String, Object> parameter,Integer pageIndex, Integer pageSize);
	
	/**
	 * 新增
	 * @param menu
	 */
	void insert(Menu menu);
	
	/**
	 * 修改
	 * @param role
	 */
	void update(Menu menu);
	
	/**
	 * 删除
	 * @param id
	 */
	void delete(String id);
	
	/**
	 * 查询所有的菜单权限
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
	List<Menu> queryMenuByUserId(String userId);
	
	/**
	 * 根据parent获取值
	 * @param userId
	 * @param parentid
	 * @return
	 */
	List<Menu> queryMenuByParentUserId(String userId,String parentid);
	/**
	 *  根据parent获取值
	 * @param parentid
	 * @return
	 */
	List<Menu> queryAllMenuByParentId(String parentid);
	
	List<Menu> findMenueByUserId(String userId);
	
}
