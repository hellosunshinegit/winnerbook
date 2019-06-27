package com.winnerbook.activity.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.winnerbook.activity.dao.BusLookInfoDao;
import com.winnerbook.activity.dto.BusLookInfo;
import com.winnerbook.base.frame.dao.BaseDAO;

@Repository("busLookInfoDao")
public class BusLookInfoDaoImpl  extends BaseDAO implements BusLookInfoDao{
	private static final String BUSLOOKINFO_MAPPER = "BusLookInfoMapper.";
	
	@Override
	public int selectCount(Map<String, Object> parameter) {
		Object obj = this.sqlSession.selectOne(BUSLOOKINFO_MAPPER + "selectCount", parameter);
		return obj == null ? 0 : Integer.parseInt(obj.toString());
	}

	@Override
	public List<BusLookInfo> listByPage(Map<String, Object> parameter) {
		return this.sqlSession.selectList(BUSLOOKINFO_MAPPER + "listByPage", parameter);
	}

	@Override
	public int update(BusLookInfo record) {
		return this.sqlSession.update(BUSLOOKINFO_MAPPER+"update",record);
	}

	@Override
	public int insert(BusLookInfo record) {
		return this.sqlSession.insert(BUSLOOKINFO_MAPPER+"insert",record);
	}

	@Override
	public BusLookInfo findById(Map<String, Object> parameter) {
		return this.sqlSession.selectOne(BUSLOOKINFO_MAPPER+"findById",parameter);
	}

}
