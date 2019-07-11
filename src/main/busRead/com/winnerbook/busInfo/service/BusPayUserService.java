package com.winnerbook.busInfo.service;

import java.util.List;

import com.winnerbook.base.common.JSONResponse;
import com.winnerbook.busInfo.dto.BusPayUser;

public interface BusPayUserService {

	List<BusPayUser> selectUserByBusId(String busId);
	
	JSONResponse addPayBusEmp(String jsonStr);
}
