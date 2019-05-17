package com.winnerbook.system.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.winnerbook.base.frame.service.impl.BaseServiceImpl;
import com.winnerbook.system.dao.UserRoleDao;
import com.winnerbook.system.dto.Role;
import com.winnerbook.system.dto.RoleMenu;
import com.winnerbook.system.dto.UserRole;
import com.winnerbook.system.service.UserRoleService;

@Service(value="userRoleService")
public class UserRoleServiceImpl extends BaseServiceImpl implements UserRoleService{
	
	@Autowired
	private UserRoleDao userRoleDao;

	@Override
	public UserRole findById(String userId) {
		Map<String, Object> parameter = new HashMap<String, Object>();
		parameter.put("userId", userId);
		return userRoleDao.findById(parameter);
	}
	
	@Override
	public void insert(UserRole userRole) {
		userRoleDao.insert(userRole);
		logRecord("2","角色新增，id："+userRole.getUserId());
	}

	@Override
	public void update(UserRole userRole) {
		userRole.setUrUpdatedate(new Date());
		userRoleDao.update(userRole);
		logRecord("3","角色修改，id："+userRole.getUserId());
	}

	@Override
	public List<Role> findAllRole(Map<String,Object> map) {
		return userRoleDao.findAllRole(map);
	}

	@Override
	public void insert1(HttpServletRequest request) {
		
		String userId = request.getParameter("userId");
		//查询是否 已经有角色，如果 有，则先删除，后添加，否则添加
		UserRole userRole = findById(userId);//查询原来的角色
		String[] roleId = request.getParameterValues("roleId");//修改后的角色id
	
		if(null == userRole){
			userRole = new UserRole();
			userRole.setUserId(Long.parseLong(userId));
			userRole.setUrCreatedate(new Date());
			userRole.setRoleId(Long.parseLong(roleId[0]));
			userRoleDao.insert(userRole);
		}else{
			userRole.setRoleId(Long.parseLong(roleId[0]));
			userRole.setUrUpdatedate(new Date());
			update(userRole);
		}
	}

	@Override
	public Integer roleIsUser(String userId) {
		return userRoleDao.roleIsUser(userId);
	}
	
	@Override
	public List<UserRole> findById1(String userId) {
		Map<String, Object> parameter = new HashMap<String, Object>();
		parameter.put("userId", userId);
		return userRoleDao.findById1(parameter);
	}
}
