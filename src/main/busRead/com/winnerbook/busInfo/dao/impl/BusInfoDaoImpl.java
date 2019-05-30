package com.winnerbook.busInfo.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.winnerbook.base.frame.dao.BaseDAO;
import com.winnerbook.busInfo.dao.BusInfoDao;
import com.winnerbook.busInfo.dto.UserBusInfo;
import com.winnerbook.system.dto.User;

@Repository("busInfoDao")
public class BusInfoDaoImpl  extends BaseDAO implements BusInfoDao{
private static final String BUSINFO_MAPPER = "BusInfoMapper.";
	
	@Override
	public int selectCount(Map<String, Object> parameter) {
		Object obj = this.sqlSession.selectOne(BUSINFO_MAPPER + "selectCount", parameter);
		return obj == null ? 0 : Integer.parseInt(obj.toString());
	}

	@Override
	public List<UserBusInfo> listByPage(Map<String, Object> parameter) {
		return this.sqlSession.selectList(BUSINFO_MAPPER + "listByPage", parameter);
	}

	@Override
	public UserBusInfo findById(Map<String, Object> parameter) {
		return this.sqlSession.selectOne(BUSINFO_MAPPER+"findBusInfoByuserId",parameter);
	}

	@Override
	public void insert(UserBusInfo busInfo) {
		this.sqlSession.insert(BUSINFO_MAPPER+"insert",busInfo);
	}

	@Override
	public void update(UserBusInfo busInfo) {
		this.sqlSession.insert(BUSINFO_MAPPER+"update",busInfo);
	}

	@Override
	public List<User> findBusUserName() {
		// TODO Auto-generated method stub
		return this.sqlSession.selectList(BUSINFO_MAPPER+"findBusUserName");
	}

	@Override
	public int findBusInfoById(String userId) {
		Object obj = this.sqlSession.selectOne(BUSINFO_MAPPER+"findBusInfoById",userId);
		return obj == null ? 0 : Integer.parseInt(obj.toString());
	}

	@Override
	public int getNumber(String busNumber) {
		Object obj = this.sqlSession.selectOne(BUSINFO_MAPPER+"getNumber",busNumber);
		return obj == null ? 0 : Integer.parseInt(obj.toString());
	}
}
