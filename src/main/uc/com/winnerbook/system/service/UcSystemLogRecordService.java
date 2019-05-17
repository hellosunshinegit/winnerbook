package com.winnerbook.system.service;

import java.util.Map;
import com.winnerbook.base.common.PageDTO;
import com.winnerbook.system.dto.UcSystemLogRecord;

public interface UcSystemLogRecordService {

	/**
	/**
	 * 新增
	 * @param ucSystemLogRecord
	 */
	void insert(UcSystemLogRecord ucSystemLogRecord);
	
	/**
	 * 分页查询
	 * @param parameter
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 */
	PageDTO<UcSystemLogRecord> listByPage(Map<String, Object> parameter,Integer pageIndex, Integer pageSize);
	
}
