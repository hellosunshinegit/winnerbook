package com.winnerbook.system.dao.impl; 

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.winnerbook.base.frame.dao.BaseDAO;
import com.winnerbook.system.dao.UserApplyBusAdminDao;
import com.winnerbook.system.dto.UserApplyBusAdmin;

@Repository("userApplyBusAdminDao")
public class UserApplyBusAdminDaoImpl extends BaseDAO implements UserApplyBusAdminDao{
	private static final String USERAPPLYBUSADMIN_MAPPER = "UserApplyBusAdminMapper.";
	
	@Override
	public UserApplyBusAdmin findById(Map<String, Object> parameter) {
		return this.sqlSession.selectOne(USERAPPLYBUSADMIN_MAPPER + "findById", parameter);
	}

	@Override
	public int selectCount(Map<String, Object> parameter) {
		Object obj = this.sqlSession.selectOne(USERAPPLYBUSADMIN_MAPPER + "selectCount", parameter);
		return obj == null ? 0 : Integer.parseInt(obj.toString());
	}

	@Override
	public List<UserApplyBusAdmin> listByPage(Map<String, Object> parameter) {
		return this.sqlSession.selectList(USERAPPLYBUSADMIN_MAPPER + "listByPage", parameter);
	}
	
	@Override
	public void update(UserApplyBusAdmin userApplyBusAdmin) {
		this.sqlSession.update(USERAPPLYBUSADMIN_MAPPER+"update", userApplyBusAdmin);
	}
	
	@Override
	public int insert(UserApplyBusAdmin userApplyBusAdmin) {
		return this.sqlSession.insert(USERAPPLYBUSADMIN_MAPPER+"insert", userApplyBusAdmin);
	}

	@Override
	public List<Map<String, Object>> getUserApplyBusAdmin(
			Map<String, Object> parameter) {
		return this.sqlSession.selectList(USERAPPLYBUSADMIN_MAPPER+"getUserApplyBusAdmin",parameter);
	}
	

}
