package com.winnerbook.system.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.winnerbook.base.frame.service.impl.BaseServiceImpl;
import com.winnerbook.system.dao.RoleMenuDao;
import com.winnerbook.system.dto.RoleMenu;
import com.winnerbook.system.service.RoleMenuService;

@Service(value="roleMenuService")
public class RoleMenuServiceImpl extends BaseServiceImpl implements RoleMenuService{
	
	@Autowired
	private RoleMenuDao roleMenuDao;

	@Override
	public List<RoleMenu> findById(String roleId) {
		Map<String, Object> parameter = new HashMap<String, Object>();
		parameter.put("roleId", roleId);
		return roleMenuDao.findById(parameter);
	}

	@Override
	public void insert(HttpServletRequest request) {
		String roleId = request.getParameter("roleId");
		//查询是否 已经有权限，如果 有，则先删除，后添加，否则添加
		List<RoleMenu> list = findById(roleId);
		if(null!=list && list.size()>0){
			//删除
			delete(roleId);
		}
		//添加
		String[] menuIds = request.getParameterValues("menuIds");
		if(menuIds.length>0){
			for(int i = 0;i<menuIds.length;i++){
				RoleMenu roleMenu = new RoleMenu();
				roleMenu.setRoleId(Long.parseLong(roleId));
				roleMenu.setMenuId(Long.parseLong(menuIds[i]));
				roleMenu.setRmCreatedate(new Date());
				roleMenuDao.insert(roleMenu);
			}
		}
		logRecord("2","角色分配权限");
	}

	@Override
	public void delete(String roleId) {
		roleMenuDao.delete(roleId);
		logRecord("4","清楚角色下的权限,角色id："+roleId);
	}
	

	@Override
	public Integer roleIsMenu(String roleId) {
		return roleMenuDao.roleIsMenu(roleId);
	}

}
