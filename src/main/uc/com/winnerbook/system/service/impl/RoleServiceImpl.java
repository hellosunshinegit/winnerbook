package com.winnerbook.system.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.winnerbook.base.common.GlobalConfigure;
import com.winnerbook.base.common.PageDTO;
import com.winnerbook.base.frame.service.impl.BaseServiceImpl;
import com.winnerbook.system.dao.RoleDao;
import com.winnerbook.system.dto.Role;
import com.winnerbook.system.dto.User;
import com.winnerbook.system.service.RoleService;

@Service(value = "roleService")
public class RoleServiceImpl extends BaseServiceImpl implements RoleService {

	@Autowired
	private RoleDao roleDao;

	@Override
	public Role findById(String id) {
		Map<String, Object> parameter = new HashMap<String, Object>();
		parameter.put("roleId", id);
		return roleDao.findById(parameter);
	}

	@Override
	public PageDTO<Role> listByPage(Map<String, Object> parameter,
			Integer pageIndex, Integer pageSize) {
		PageDTO<Role> pageDTO = new PageDTO<Role>(pageIndex, pageSize);
		parameter.put(GlobalConfigure.PAGINATION_SQL_START, pageDTO.getFirst());
		parameter.put(GlobalConfigure.PAGINATION_SQL_LIMIT, pageDTO.getPageSize());
		long rowSize = roleDao.selectCount(parameter);
		List<Role> data = null;
		if (rowSize > 0) {
			data = roleDao.listByPage(parameter);
		}
		pageDTO.setRowSize(rowSize);
		pageDTO.setData(data);
		return pageDTO;
	}

	@Override
	public void insert(Role role) {
		User userone = getSessionUser();
		role.setRoleCreatedate(new Date());
		role.setRoleCreateUserId(userone.getUserId());
		role.setRoleCreateUserName(userone.getUserUnitName());
		roleDao.insert(role);
		logRecord("2","角色添加，id:"+role.getRoleId());
		
	}

	@Override
	public void update(Role role) {
		role.setRoleUpdatedate(new Date());
		roleDao.update(role);
		logRecord("3","角色更新，id:"+role.getRoleId());
	}

	@Override
	public void delete(String id) {
		roleDao.delete(id);
		logRecord("4","角色删除，id:"+id);
	}

	@Override
	public List<Role> findAllRole() {
		return roleDao.findAllRole();
	}

}
