package com.winnerbook.share.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.winnerbook.base.frame.dao.BaseDAO;
import com.winnerbook.share.dao.QrcodeDao;
import com.winnerbook.share.dto.Qrcode;

@Repository("qrcodeDao")
public class QrcodeDaoImpl  extends BaseDAO implements QrcodeDao{
	private static final String QRCODE_MAPPER = "QrcodeMapper.";
	
	@Override
	public int selectCount(Map<String, Object> parameter) {
		Object obj = this.sqlSession.selectOne(QRCODE_MAPPER + "selectCount", parameter);
		return obj == null ? 0 : Integer.parseInt(obj.toString());
	}

	@Override
	public List<Qrcode> listByPage(Map<String, Object> parameter) {
		return this.sqlSession.selectList(QRCODE_MAPPER + "listByPage", parameter);
	}

	@Override
	public Qrcode findById(Map<String, Object> parameter) {
		return this.sqlSession.selectOne(QRCODE_MAPPER+"findById",parameter);
	}

	@Override
	public int insert(Qrcode record) {
		return this.sqlSession.insert(QRCODE_MAPPER+"insert",record);
	}

	@Override
	public int update(Qrcode record) {
		return this.sqlSession.insert(QRCODE_MAPPER+"update",record);
	}

	@Override
	public int updateQrcodeUrl(Map<String, Object> parameter) {
		return this.sqlSession.update(QRCODE_MAPPER+"updateQrcodeUrl",parameter);
	}

	@Override
	public int updateScanCount(Map<String, Object> parameter) {
		return this.sqlSession.update(QRCODE_MAPPER+"updateScanCount",parameter);
	}

	@Override
	public List<Qrcode> getQrcodeByBusId(Map<String, Object> parameter) {
		return this.sqlSession.selectList(QRCODE_MAPPER+"getQrcodeByBusId",parameter);
	}

}
