package com.winnerbook.web.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.winnerbook.base.common.JSONResponse;
import com.winnerbook.base.common.util.EncryptUtil;
import com.winnerbook.base.frame.service.impl.WebBaseServiceImpl;
import com.winnerbook.busInfo.dto.UserBusInfo;
import com.winnerbook.busInfo.service.BusInfoService;
import com.winnerbook.system.dao.UcSystemLogRecordDao;
import com.winnerbook.system.dao.UserDao;
import com.winnerbook.system.dto.User;

@Service("LoginService")
public class LoginService extends WebBaseServiceImpl{
	
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private UcSystemLogRecordDao ucSystemLogRecordDao;
	
	@Autowired
	private BusInfoService busInfoService;
	
	public JSONResponse getLoginInfo(String username,String password,String selectUser,HttpServletRequest request){
		JSONResponse result = new JSONResponse();
		String busId = request.getParameter("busId");
		
		if(StringUtils.isEmpty(username)){
			result.setMsg("请输入手机号码!");
			result.setSuccess(false);
			return result;
		}
		if(StringUtils.isEmpty(password)){
			result.setMsg("请输入密码!");
			result.setSuccess(false);
			return result;
		}
		
		User user = null;
		//判断选中的用户是否存在
		if(StringUtils.isNotBlank(selectUser)){
			//根据用户id和企业id获取唯一的用户值 
			String[] selectUserArray = selectUser.split("-");
			Map<String,Object> userLoginMap = new HashMap<>();
			userLoginMap.put("userId", selectUserArray[0]);
			userLoginMap.put("busId", selectUserArray[1]);
			user = userDao.getUserByUserBus(userLoginMap);
		}else{
			//首先查询username在系统中是否存在，
			Map<String, Object> parameter = new HashMap<String,Object>();
			parameter.put("userName", username);
			List<Map<String, Object>> users = userDao.findUserByUserName(parameter);
			
			if(null==users || users.size()==0){
				//直接进行注册，把用户归于zcdsh（demo）企业中
				user = insertDemoUser(username, password,busId);
				loginRecord(user, request);
				result.setSuccess(true);
				//根据用户id查询企业名称
				UserBusInfo userBusInfo = busInfoService.findById(user.getBelongBusUserId().toString());
				user.setBusName(userBusInfo.getBusName());
				result.setData(user);
				result.setMsg("您的手机已经完成注册，正在登录中...");
				result.setSuccess(true);
				return result;
			}
			
			if(users.size()>1){
				result.setMsg("此手机号存在多个企业中，请选择");
				result.setSuccess(false);
				result.setData(users);
				return result;
			}
			
			
			Map<String, Object> parameterUser = new HashMap<String,Object>();
			parameterUser.put("userId", users.get(0).get("userId").toString());
			user = userDao.findUserById(parameterUser);
		}
		
		String _password = EncryptUtil.hash(password);
		String _userPassword = user.getUserPassword();
		
		if(!_userPassword.equalsIgnoreCase(_password)){
			result.setMsg("您输入的用户密码不正确!");
			result.setSuccess(false);
			return result;
		}
		
		loginRecord(user, request);
		
		result.setSuccess(true);
		
		//根据用户id查询企业名称
		UserBusInfo userBusInfo = busInfoService.findById(user.getBelongBusUserId().toString());
		
		user.setBusName(userBusInfo.getBusName());
		result.setData(user);
		return result;
	}
	
	
	public User insertDemoUser(String userName,String password,String busId){
		User user = new User();
		//查询2的企业
		String busIdFind = StringUtils.isNotBlank(busId)?busId:"2";
		Map<String,Object> map_user = new HashMap<>();
		map_user.put("userId", Integer.parseInt(busIdFind));
		User user_bus = userDao.findUserById(map_user);
		
		user.setUserName(userName);
		String pwd = "";
		if(StringUtils.isNotBlank(password)){
			pwd = password;
		}else{
			if(user.getUserName().length()==11){
				pwd = user.getUserName().substring(5, 11);
			}
		}
		String unitNameNum = System.currentTimeMillis()+"";
		if(user.getUserName().length()==11){
			unitNameNum = user.getUserName().substring(5, 11);
		}
		user.setUserPassword(EncryptUtil.hash(pwd));
		user.setUserUnitName("体验用户"+unitNameNum);
		user.setUserParentId(Long.parseLong(busIdFind));
		user.setUserStatue("1");
		user.setSourceType("3");//不是企业用户，但是通过系统登录的
		user.setIsBusinessAdmin("0");//否
		user.setDepartment("");
		user.setIsDepartLeader("");
		user.setBelongBusUserId(busIdFind);
		user.setUserCreateDate(new Date());
		user.setUserCreateUserId(Long.parseLong(busIdFind));
		user.setUserCreateUserName(user_bus.getUserUnitName());
		userDao.insert(user);
		return user;
	}

}
