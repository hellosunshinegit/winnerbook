package com.winnerbook.busInfo.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.winnerbook.base.frame.dao.BaseDAO;
import com.winnerbook.busInfo.dao.BusPayUserDao;
import com.winnerbook.busInfo.dto.BusPayUser;

@Repository("busPayUserDao")
public class BusPayUserDaoImpl  extends BaseDAO implements BusPayUserDao{
	private static final String BUSINFO_MAPPER = "BusPayUserMapper.";

	@Override
	public List<BusPayUser> selectUserByBusId(String busId) {
		return this.sqlSession.selectList(BUSINFO_MAPPER+"selectUserByBusId", busId);
	}

	@Override
	public int insertBath(List<BusPayUser> busPayUsers) {
		return this.sqlSession.insert(BUSINFO_MAPPER+"insertBath", busPayUsers);
	}

	@Override
	public List<Map<String, Object>> getUserPay(Map<String, Object> map) {
		return this.sqlSession.selectList(BUSINFO_MAPPER+"getUserPay",map);
	}
	
}
