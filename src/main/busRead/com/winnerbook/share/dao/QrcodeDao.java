package com.winnerbook.share.dao;

import java.util.List;
import java.util.Map;
import com.winnerbook.share.dto.Qrcode;

public interface QrcodeDao {

	int selectCount(Map<String, Object> parameter);

	List<Qrcode> listByPage(Map<String, Object> parameter);

	int insert(Qrcode record);
	
	Qrcode findById(Map<String, Object> parameter);

	int update(Qrcode record);
	
	int updateQrcodeUrl(Map<String, Object> parameter);
	int updateScanCount(Map<String, Object> parameter);
	
	List<Qrcode> getQrcodeByBusId(Map<String, Object> parameter);
	
}