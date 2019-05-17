package com.winnerbook.system.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;
import com.winnerbook.base.frame.dao.BaseDAO;
import com.winnerbook.system.dao.UcSystemLogRecordDao;
import com.winnerbook.system.dto.UcSystemLogRecord;

@Repository(value="ucSystemLogRecordDao")
public class UcSystemLogRecordDaoImpl extends BaseDAO implements UcSystemLogRecordDao{
	
	private static final String UCSYSTEMLOGRECORD_MAPPER = "UcSystemLogRecordMapper.";

	@Override
	public void insert(UcSystemLogRecord ucSystemLogRecord) {
		this.sqlSession.insert(UCSYSTEMLOGRECORD_MAPPER+"insert", ucSystemLogRecord);
	}

	@Override
	public int selectCount(Map<String, Object> parameter) {
		Object obj = this.sqlSession.selectOne(UCSYSTEMLOGRECORD_MAPPER + "selectCount", parameter);
		return obj == null ? 0 : Integer.parseInt(obj.toString());
	}

	@Override
	public List<UcSystemLogRecord> listByPage(Map<String, Object> parameter) {
		return this.sqlSession.selectList(UCSYSTEMLOGRECORD_MAPPER + "listByPage", parameter);
	}

	
	
}
