package com.winnerbook.share.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.winnerbook.base.frame.dao.BaseDAO;
import com.winnerbook.share.dao.QrcodeRecordDao;
import com.winnerbook.share.dto.QrcodeRecord;

@Repository("qrcodeRecordDao")
public class QrcodeRecordDaoImpl  extends BaseDAO implements QrcodeRecordDao{
	private static final String QRCODE_MAPPER = "QrcodeRecordMapper.";
	
	@Override
	public int insert(QrcodeRecord record) {
		return this.sqlSession.insert(QRCODE_MAPPER+"insert",record);
	}

	@Override
	public int selectCount(Map<String, Object> parameter) {
		Object obj = this.sqlSession.selectOne(QRCODE_MAPPER + "selectCount", parameter);
		return obj == null ? 0 : Integer.parseInt(obj.toString());
	}

	@Override
	public List<QrcodeRecord> listByPage(Map<String, Object> parameter) {
		return this.sqlSession.selectList(QRCODE_MAPPER + "listByPage", parameter);
	}

}
