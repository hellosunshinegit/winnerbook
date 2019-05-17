package com.winnerbook.system.dao.impl;

import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Repository;
import com.winnerbook.base.frame.dao.BaseDAO;
import com.winnerbook.system.dao.UcAddressDao;
import com.winnerbook.system.dto.UcAddress;

@Repository(value="ucAddressDao")
public class UcAddressDaoImpl extends BaseDAO implements UcAddressDao{
	
	private static final String UCADDRESS_MAPPER = "UcAddressMapper.";

	@Override
	public UcAddress findById(Map<String, Object> parameter) {
		return this.sqlSession.selectOne(UCADDRESS_MAPPER + "findById", parameter);
	}

	@Override
	public int selectCount(Map<String, Object> parameter) {
		Object obj = this.sqlSession.selectOne(UCADDRESS_MAPPER + "selectCount", parameter);
		return obj == null ? 0 : Integer.parseInt(obj.toString());
	}

	@Override
	public List<UcAddress> listByPage(Map<String, Object> parameter) {
		return this.sqlSession.selectList(UCADDRESS_MAPPER + "listByPage", parameter);
	}

	@Override
	public void insert(UcAddress ucAddress) {
		this.sqlSession.insert(UCADDRESS_MAPPER + "insert", ucAddress);
	}

	@Override
	public void update(UcAddress ucAddress) {
		this.sqlSession.update(UCADDRESS_MAPPER+"update", ucAddress);
	}

	@Override
	public void delete(String addressId) {
		this.sqlSession.update(UCADDRESS_MAPPER+"delete", addressId);
	}

	@Override
	public List<UcAddress> findAddressOneLevel() {
		return this.sqlSession.selectList(UCADDRESS_MAPPER+"findAddressOneLevel");
	}

	@Override
	public List<UcAddress> findAddressByParentId(Map<String, Object> parameter) {
		return this.sqlSession.selectList(UCADDRESS_MAPPER+"findAddressByParentId",parameter);
	}

}
