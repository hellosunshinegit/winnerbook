package com.winnerbook.system.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.winnerbook.base.common.GlobalConfigure;
import com.winnerbook.base.common.PageDTO;
import com.winnerbook.base.frame.service.impl.BaseServiceImpl;
import com.winnerbook.system.dao.MenuDao;
import com.winnerbook.system.dto.Menu;
import com.winnerbook.system.service.MenuService;

@Service(value = "menuService")
public class MenuServiceImpl extends BaseServiceImpl implements MenuService {

	@Autowired
	private MenuDao menuDao;
	
	@Autowired
	private HttpServletRequest request;

	@Override
	public Menu findById(String id) {
		Map<String, Object> parameter = new HashMap<String, Object>();
		parameter.put("menuId", id);
		return menuDao.findById(parameter);
	}

	@Override
	public PageDTO<Menu> listByPage(Map<String, Object> parameter,
			Integer pageIndex, Integer pageSize) {
		PageDTO<Menu> pageDTO = new PageDTO<Menu>(pageIndex, pageSize);
		parameter.put(GlobalConfigure.PAGINATION_SQL_START, pageDTO.getFirst());
		parameter.put(GlobalConfigure.PAGINATION_SQL_LIMIT, pageDTO.getPageSize());
		long rowSize = menuDao.selectCount(parameter);
		List<Menu> data = null;
		if (rowSize > 0) {
			data = menuDao.listByPage(parameter);
		}
		pageDTO.setRowSize(rowSize);
		pageDTO.setData(data);
		return pageDTO;
	}

	@Override
	public void insert(Menu menu) {
		if(null==menu.getMenuParentid() || "".equals(menu.getMenuParentid())){
			menu.setMenuParentid(0l);
		}
		menu.setMenuCreatedate(new Date());
		menuDao.insert(menu);
		logRecord("2","菜单模块添加，id:"+menu.getMenuId());
	}

	@Override
	public void update(Menu menu) {
		if(null==menu.getMenuParentid() || "".equals(menu.getMenuParentid())){
			menu.setMenuParentid(0l);
		}
		menu.setMenuUpdatedate(new Date());
		menuDao.update(menu);
		logRecord("3","菜单模块更新，id:"+menu.getMenuId());
	}

	@Override
	public void delete(String id) {
		logRecord("4","菜单模块删除，id:"+id);
		menuDao.delete(id);
	}

	@Override
	public List<Menu> findMenuByCode() {
		return menuDao.findMenuByCode();
	}

	@Override
	public List<Menu> findAllMenue() {
		return menuDao.findAllMenue();
	}

	@Override
	public List<Menu> queryMenuByUserId(String userId) {
		Map<String, Object> parameter = new HashMap<String, Object>();
		parameter.put("userId", userId);
		return menuDao.queryMenuByUserId(parameter);
	}

	@Override
	public List<Menu> queryMenuByParentUserId(String userId, String parentid) {
		Map<String, Object> parameter = new HashMap<String, Object>();
		parameter.put("userId", userId);
		parameter.put("parentid", parentid);
		return menuDao.queryMenuByParentUserId(parameter);
	}

	@Override
	public List<Menu> queryAllMenuByParentId(String parentid) {
		Map<String, Object> parameter = new HashMap<String, Object>();
		parameter.put("parentid", parentid);
		return menuDao.queryAllMenuByParentId(parameter);
	}

	@Override
	public List<Menu> findMenueByUserId(String userId) {
		Map<String, Object> parameter = new HashMap<String, Object>();
		parameter.put("userId", userId);
		return menuDao.findMenueByUserId(parameter);
	}

}
