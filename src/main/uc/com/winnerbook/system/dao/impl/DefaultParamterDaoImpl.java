package com.winnerbook.system.dao.impl;

import java.util.Map;

import org.springframework.stereotype.Repository;

import com.winnerbook.base.frame.dao.BaseDAO;
import com.winnerbook.system.dao.DefaultParamterDao;
import com.winnerbook.system.dto.DefaultParamter;

@Repository(value="defaultParamterDao")
public class DefaultParamterDaoImpl extends BaseDAO implements DefaultParamterDao{
	
	private static final String DEFAULTPARAMTER_MAPPER = "DefaultParamterMapper.";

	@Override
	public DefaultParamter findDefaultParamter() {
		return this.sqlSession.selectOne(DEFAULTPARAMTER_MAPPER+"findDefaultParamter");
	}

	@Override
	public int insertDefaultBusRole(Map<String, Object> paramter) {
		return this.sqlSession.insert(DEFAULTPARAMTER_MAPPER+"insertDefaultBusRole",paramter);
	}

	@Override
	public Map<String, Object> getDefaultBusRoleByBusId(String busAdminId) {
		return this.sqlSession.selectOne(DEFAULTPARAMTER_MAPPER+"getDefaultBusRoleByBusId",busAdminId);
	}
	
	
	
}
