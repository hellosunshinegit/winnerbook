package com.winnerbook.share.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.winnerbook.base.common.GlobalConfigure;
import com.winnerbook.base.common.PageDTO;
import com.winnerbook.base.frame.service.impl.BaseServiceImpl;
import com.winnerbook.share.dao.QrcodeRecordDao;
import com.winnerbook.share.dto.QrcodeRecord;
import com.winnerbook.share.service.QrcodeRecordService;

@Service("qrcodeRecordService")
public class QrcodeRecordServiceImpl extends BaseServiceImpl implements QrcodeRecordService{
	
	@Autowired
	private QrcodeRecordDao qrcodeRecordDao;
	
	@Override
	public int insert(QrcodeRecord qrcodeRecord) {
		qrcodeRecord.setCreateDate(new Date());
		logRecord("2","二维码扫描记录新增，："+qrcodeRecord.getIp());
		return qrcodeRecordDao.insert(qrcodeRecord);
	}

	@Override
	public PageDTO<QrcodeRecord> listByPage(Map<String, Object> parameter,
			Integer pageIndex, Integer pageSize) {
		PageDTO<QrcodeRecord> pageDTO = new PageDTO<QrcodeRecord>(pageIndex, pageSize);
		parameter.put(GlobalConfigure.PAGINATION_SQL_START, pageDTO.getFirst());
		parameter.put(GlobalConfigure.PAGINATION_SQL_LIMIT, pageDTO.getPageSize());
		long rowSize = qrcodeRecordDao.selectCount(parameter);
		List<QrcodeRecord> data = null;
		if (rowSize > 0) {
			data = qrcodeRecordDao.listByPage(parameter);
		}
		pageDTO.setRowSize(rowSize);
		pageDTO.setData(data);
		return pageDTO;
	}
	
}
