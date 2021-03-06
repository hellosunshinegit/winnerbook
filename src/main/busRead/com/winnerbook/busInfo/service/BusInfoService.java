package com.winnerbook.busInfo.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.winnerbook.base.common.PageDTO;
import com.winnerbook.busInfo.dto.BusInfo;
import com.winnerbook.busInfo.dto.BusPayUser;
import com.winnerbook.busInfo.dto.UserBusInfo;
import com.winnerbook.share.dto.Qrcode;
import com.winnerbook.system.dto.User;

public interface BusInfoService {

	/**
	 * 根据id查询
	 * @param parameter
	 * @return
	 */
	UserBusInfo findById(String userId);
	
	/**
	 * 分页查询
	 * @param parameter
	 * @return
	 */
	PageDTO<UserBusInfo> listByPage(Map<String, Object> parameter,Integer pageIndex, Integer pageSize);

	/**
	 * 修改
	 * @param dictionary
	 */
	void update(UserBusInfo busInfo,HttpServletRequest request);
	
	void updateSubmitBusInfoBus(UserBusInfo busInfo);
	
	//查询企业管理员用户
	List<User> findBusUserName();
	
	int findBusInfoById(String userId);
	
	String customCourseTypeSubmit(String courseTypeIds);
	
	Qrcode getBusQrcode(String busId);
	
	Qrcode getBusBrandQrcode(String busId);
	
	void uploadBrandImg(String busId,String brandType,HttpServletResponse response);

	String getGenerateCode();
	
	List<Map<String, Object>> getBusSendWbList(String type,String busId);
	
	//查询所有企业
	List<Map<String, Object>> getBusList();
	
	List<Map<String, Object>> getBusEmpList(String busId);
	
	List<BusPayUser> selectUserByBusId(String busId);
}
