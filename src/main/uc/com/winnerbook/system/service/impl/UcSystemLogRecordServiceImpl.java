package com.winnerbook.system.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.winnerbook.base.common.GlobalConfigure;
import com.winnerbook.base.common.PageDTO;
import com.winnerbook.system.dao.UcSystemLogRecordDao;
import com.winnerbook.system.dto.UcSystemLogRecord;
import com.winnerbook.system.service.UcSystemLogRecordService;

@Service(value = "ucSystemLogRecordService")
public class UcSystemLogRecordServiceImpl implements UcSystemLogRecordService {

	@Autowired
	private UcSystemLogRecordDao ucSystemLogRecordDao;

	@Override
	public void insert(UcSystemLogRecord ucSystemLogRecord) {
		
		ucSystemLogRecord.setLogCreateDate(new Date());
		ucSystemLogRecordDao.insert(ucSystemLogRecord);
	}

	@Override
	public PageDTO<UcSystemLogRecord> listByPage(Map<String, Object> parameter,
			Integer pageIndex, Integer pageSize) {
		PageDTO<UcSystemLogRecord> pageDTO = new PageDTO<UcSystemLogRecord>(pageIndex, pageSize);
		parameter.put(GlobalConfigure.PAGINATION_SQL_START, pageDTO.getFirst());
		parameter.put(GlobalConfigure.PAGINATION_SQL_LIMIT, pageDTO.getPageSize());
		long rowSize = ucSystemLogRecordDao.selectCount(parameter);
		List<UcSystemLogRecord> data = null;
		if (rowSize > 0) {
			data = ucSystemLogRecordDao.listByPage(parameter);
		}
		pageDTO.setRowSize(rowSize);
		pageDTO.setData(data);
		return pageDTO;
	}

}
