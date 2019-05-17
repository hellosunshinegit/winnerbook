package com.winnerbook.system.dao;

import java.util.List;
import java.util.Map;
import com.winnerbook.system.dto.UcSystemLogRecord;
public interface UcSystemLogRecordDao {

	/**
	 * 新增
	 * @param ucSystemLogRecord
	 */
	void insert(UcSystemLogRecord ucSystemLogRecord);
	
	/**
	 * 查询总条数
	 * @param parameter
	 * @return
	 */
	int  selectCount(Map<String, Object> parameter);
	
	/**
	 * 分页查询
	 * @param parameter
	 * @return
	 */
	List<UcSystemLogRecord> listByPage(Map<String, Object> parameter);
	
}
