package com.winnerbook.share.service;

import java.util.Map;
import com.winnerbook.base.common.PageDTO;
import com.winnerbook.share.dto.QrcodeRecord;

public interface QrcodeRecordService {

	int insert(QrcodeRecord record);
	
	/**
	 * 分页查询
	 * @param parameter
	 * @return
	 */
	PageDTO<QrcodeRecord> listByPage(Map<String, Object> parameter,Integer pageIndex, Integer pageSize);
	
}
