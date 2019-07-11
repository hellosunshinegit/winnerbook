package com.winnerbook.busInfo.service.impl;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.winnerbook.base.common.JSONResponse;
import com.winnerbook.base.frame.service.impl.BaseServiceImpl;
import com.winnerbook.busInfo.dao.BusInfoDao;
import com.winnerbook.busInfo.dao.BusPayUserDao;
import com.winnerbook.busInfo.dto.BusPayUser;
import com.winnerbook.busInfo.dto.UserBusInfo;
import com.winnerbook.busInfo.service.BusInfoService;
import com.winnerbook.busInfo.service.BusPayUserService;
import com.winnerbook.system.dto.User;

@Service("busPayUserService")
public class BusPayUserServiceImpl extends BaseServiceImpl implements BusPayUserService{
	
	@Autowired
	private BusPayUserDao busPayUserDao;
	
	@Autowired
	private BusInfoDao busInfoDao;
	
	@Autowired
	private BusInfoService busInfoService;

	@Override
	public List<BusPayUser> selectUserByBusId(String busId) {
		return busPayUserDao.selectUserByBusId(busId);
	}

	@Override
	public JSONResponse addPayBusEmp(String jsonStr) {
		JSONResponse jsonResponse = new JSONResponse();
		
		User user = getSessionUser();
		
		JSONObject jsonObject = JSONObject.fromObject(jsonStr);
		String busId = jsonObject.getString("busId");
		String userIds = jsonObject.getString("userIds");
		
		if(!StringUtils.isNotBlank(busId)){
			jsonResponse.setMsg("企业不可为空");
			return jsonResponse;
		}
		if(!StringUtils.isNotBlank(userIds)){
			jsonResponse.setMsg("所选员工不可为空");
			return jsonResponse;
		}
		
		//判断选择的人数是否超上限
		UserBusInfo userBusInfo = busInfoService.findById(busId);
		//获取企业下已经勾选员工
		List<BusPayUser> busPayUsers = busPayUserDao.selectUserByBusId(busId);
		
		String[] userIdsArray = userIds.split(","); 
		if((userIdsArray.length+busPayUsers.size())>userBusInfo.getEmpUseNum()){
			jsonResponse.setMsg("所选员工数额已经超过上限，请重新选择");
			return jsonResponse;
		}
		
		List<BusPayUser> busPayUsers_insert = new ArrayList<>();
		
		for(String userId:userIdsArray){
			BusPayUser busPayUser = new BusPayUser();
			busPayUser.setBusId(Integer.parseInt(busId));
			busPayUser.setUserId(Integer.parseInt(userId));
			busPayUser.setCreateDate(new Date());
			busPayUser.setCreateUserId(Integer.parseInt(user.getUserId()+""));
			busPayUser.setCreateUserName(user.getUserUnitName());
			busPayUsers_insert.add(busPayUser);
		}
		
		if(busPayUsers_insert.size()>0){
			busPayUserDao.insertBath(busPayUsers_insert);
			jsonResponse.setSuccess(true);
		}
		
		return jsonResponse;
	}

}
