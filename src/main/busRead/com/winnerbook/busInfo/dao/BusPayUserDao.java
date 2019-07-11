package com.winnerbook.busInfo.dao;

import java.util.List;
import java.util.Map;

import com.winnerbook.busInfo.dto.BusPayUser;

public interface BusPayUserDao{
	
	List<BusPayUser> selectUserByBusId(String busId);
	
	int insertBath(List<BusPayUser> busPayUsers);
	
	List<Map<String, Object>> getUserPay(Map<String, Object> map);

}
