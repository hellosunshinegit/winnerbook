/** 
* 2015-3-2
* UserInfoServiceImpl.java 
* author:hxs
*/ 
package com.winnerbook.system.service.impl; 

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.winnerbook.base.common.GlobalConfigure;
import com.winnerbook.base.common.JSONResponse;
import com.winnerbook.base.common.PageDTO;
import com.winnerbook.base.frame.service.impl.BaseServiceImpl;
import com.winnerbook.system.dao.UserApplyBusAdminDao;
import com.winnerbook.system.dto.User;
import com.winnerbook.system.dto.UserApplyBusAdmin;
import com.winnerbook.system.service.UserApplyBusAdminService;
import com.winnerbook.system.service.UserService;

@Service("userApplyBusAdminService")
public class UserApplyBusAdminServiceImpl extends BaseServiceImpl implements UserApplyBusAdminService{
	
	@Autowired
	private UserApplyBusAdminDao userApplyBusAdminDao;
	
	@Autowired
	private UserService userService;

	@Override
	public UserApplyBusAdmin findById(String uaId) {
		Map<String, Object> parameter = new HashMap<String,Object>();
		parameter.put("uaId", Integer.parseInt(uaId));
		return this.userApplyBusAdminDao.findById(parameter);
	}
	
	@Override
	public PageDTO<UserApplyBusAdmin> listByPage(Map<String, Object> parameter,
			Integer pageIndex, Integer pageSize) {
		PageDTO<UserApplyBusAdmin> pageDTO = new PageDTO<UserApplyBusAdmin>(pageIndex, pageSize);
		parameter.put(GlobalConfigure.PAGINATION_SQL_START, pageDTO.getFirst());
		parameter.put(GlobalConfigure.PAGINATION_SQL_LIMIT, pageDTO.getPageSize());
		long rowSize = userApplyBusAdminDao.selectCount(parameter);
		List<UserApplyBusAdmin> data = null;
		if (rowSize > 0) {
			data = userApplyBusAdminDao.listByPage(parameter);
		}
		pageDTO.setRowSize(rowSize);
		pageDTO.setData(data);
		return pageDTO;
	}
	
	//修改
	@Override
	public int update(UserApplyBusAdmin userApplyBusAdmin) {
		userApplyBusAdminDao.update(userApplyBusAdmin);
		logRecord("3","用户申请企业管理员修改，id："+userApplyBusAdmin.getUaId());
		return userApplyBusAdmin.getUaId();
	}
	//新增
	@Override
	public int insert(UserApplyBusAdmin userApplyBusAdmin) {
		userApplyBusAdminDao.insert(userApplyBusAdmin);
		return userApplyBusAdmin.getUaId();
	}

	@Override
	public Map<String, Object> getUserApplyBusAdmin(String userId) {
		Map<String, Object> parameter = new HashMap<String,Object>();
		parameter.put("userId", userId);
		List<Map<String, Object>> listMap = userApplyBusAdminDao.getUserApplyBusAdmin(parameter);
		if(listMap.size()>0){
			return listMap.get(0);
		}else{
			return null;
		}
	}

	@Override
	public JSONResponse updateSubmitUser(String json) {
		
		JSONResponse jsonResponse = new JSONResponse();
		
		JSONObject object = JSONObject.fromObject(json);
		String uaId = object.getString("uaId");
		String status = object.getString("status");
		String statusReason = object.getString("statusReason");
		
		UserApplyBusAdmin userApplyBusAdmin = findById(uaId);
		userApplyBusAdmin.setStatus(status);
		if("1".equals(status)){
			userApplyBusAdmin.setSuccessDate(new Date());
		}
		userApplyBusAdmin.setStatusReason(statusReason);
		
		User user = userService.findUserById(userApplyBusAdmin.getUserId()+"");
		
		//如果状态为审核通过，则为这个用户创建企业管理员账号
		if("1".equals(status)){
			if(!"1".equals(user.getIsBusinessAdmin())){
				
				update(userApplyBusAdmin);//更新
				
				User user_insert = new User();
				user_insert.setUserName(user.getUserName());
				user_insert.setUserUnitName(userApplyBusAdmin.getApplyBusName()+"-"+user.getUserUnitName());
				user_insert.setUserStatue("1");
				user_insert.setIsBusinessAdmin("1");
				user_insert.setSourceType("5");//通过申请成为企业管理员添加
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
			update(userApplyBusAdmin);//更新
			jsonResponse.setMsg("操作成功");
			jsonResponse.setSuccess(true);
		}
		
		return jsonResponse;
	}
	
}
