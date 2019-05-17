package com.winnerbook.share.dao;

import java.util.List;
import java.util.Map;
import com.winnerbook.share.dto.QrcodeRecord;

public interface QrcodeRecordDao {
	
	int selectCount(Map<String, Object> parameter);

	List<QrcodeRecord> listByPage(Map<String, Object> parameter);

	int insert(QrcodeRecord record);
	
}