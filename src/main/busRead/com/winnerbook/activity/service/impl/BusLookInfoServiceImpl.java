package com.winnerbook.activity.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.winnerbook.activity.dao.ActivityDao;
import com.winnerbook.activity.dao.BusLookInfoDao;
import com.winnerbook.activity.dto.Activity;
import com.winnerbook.activity.dto.BusLookInfo;
import com.winnerbook.activity.service.ActivityService;
import com.winnerbook.activity.service.BusLookInfoService;
import com.winnerbook.base.common.GlobalConfigure;
import com.winnerbook.base.common.JSONResponse;
import com.winnerbook.base.common.PageDTO;
import com.winnerbook.base.common.util.DateTimeUtils;
import com.winnerbook.base.frame.service.impl.BaseServiceImpl;
import com.winnerbook.system.dto.User;
import com.winnerbook.system.dto.UserApplyBusAdmin;
import com.winnerbook.system.service.UserService;

@Service("busLookInfoService")
public class BusLookInfoServiceImpl extends BaseServiceImpl implements BusLookInfoService{
	
	@Autowired
	private BusLookInfoDao busLookInfoDao;
	
	@Autowired
	private UserService userService;
	
	SimpleDateFormat df_min = new SimpleDateFormat("yyyy-MM-dd HH:mm");

	@Override
	public BusLookInfo findById(String id) {
		Map<String, Object> parameter  = new HashMap<String, Object>();
		parameter.put("id", Integer.parseInt(id));
		return busLookInfoDao.findById(parameter);
	}

	@Override
	public PageDTO<BusLookInfo> listByPage(Map<String, Object> parameter,Integer pageIndex, Integer pageSize) {
		PageDTO<BusLookInfo> pageDTO = new PageDTO<BusLookInfo>(pageIndex, pageSize);
		parameter.put(GlobalConfigure.PAGINATION_SQL_START, pageDTO.getFirst());
		parameter.put(GlobalConfigure.PAGINATION_SQL_LIMIT, pageDTO.getPageSize());
		long rowSize = busLookInfoDao.selectCount(parameter);
		List<BusLookInfo> data = null;
		if (rowSize > 0) {
			data = busLookInfoDao.listByPage(parameter);
		}
		pageDTO.setRowSize(rowSize);
		pageDTO.setData(data);
		return pageDTO;
	}

	@Override
	public void updateStatus(String id, String status, String statusReson) {
		BusLookInfo busLookInfo_update = findById(id);
		busLookInfo_update.setStatus(status);
		busLookInfo_update.setStatusReason(statusReson);
		busLookInfo_update.setUpdateDate(new Date());
		busLookInfoDao.update(busLookInfo_update);
		logRecord("3","调研报告信息更新，id："+id);
	}

	@Override
	public JSONResponse examineBus(String json) {
		JSONResponse jsonResponse = new JSONResponse();
		
		JSONObject object = JSONObject.fromObject(json);
		String id = object.getString("id");
		String status = object.getString("status");
		String statusReason = object.getString("statusReason");
		
		BusLookInfo busLookInfo = findById(id);
		busLookInfo.setStatus(status);
		if("1".equals(status)){
			busLookInfo.setSuccessDate(new Date());
		}
		busLookInfo.setStatusReason(statusReason);
		busLookInfo.setUpdateDate(new Date());
		
		//查询该手机号是否已经是企业管理员
		List<Map<String, Object>> userList = userService.findUserByUserName(busLookInfo.getTelphone());
		
		//如果状态为审核通过，则为这个用户创建企业管理员账号
		if("1".equals(status)){
			if(userList.size()==0){
				
				busLookInfoDao.update(busLookInfo);
				
				User user_insert = new User();
				user_insert.setUserName(busLookInfo.getTelphone());
				user_insert.setUserUnitName(busLookInfo.getBusName());
				user_insert.setUserStatue("1");
				user_insert.setIsBusinessAdmin("1");
				user_insert.setSourceType("6");//通过企业调研成为企业管理员
				userService.insert(user_insert);
				
				//所属企业id
				userService.updateBelongBusUser(user_insert.getUserId().toString(), user_insert.getUserId().toString());
				
				jsonResponse.setSuccess(true);
				jsonResponse.setMsg("操作成功");
			}else{
				jsonResponse.setSuccess(false);
				jsonResponse.setMsg("该用户已经是企业管理员，不需要申请");
			}
		}else{
			busLookInfoDao.update(busLookInfo);
			jsonResponse.setMsg("操作成功");
			jsonResponse.setSuccess(true);
		}
		
		return jsonResponse;
	}

}
