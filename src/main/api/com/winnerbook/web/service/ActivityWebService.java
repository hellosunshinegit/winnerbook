package com.winnerbook.web.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.winnerbook.activity.dto.Activity;
import com.winnerbook.activity.dto.ActivitySignup;
import com.winnerbook.activity.service.ActivityService;
import com.winnerbook.activity.service.ActivitySignupService;
import com.winnerbook.base.common.JSONResponse;
import com.winnerbook.base.common.util.EncryptUtil;
import com.winnerbook.base.frame.service.impl.WebBaseServiceImpl;
import com.winnerbook.busInfo.dto.UserBusInfo;
import com.winnerbook.busInfo.service.BusInfoService;
import com.winnerbook.system.dao.UserDao;
import com.winnerbook.system.dto.User;
import com.winnerbook.system.service.UserService;

@Service("activityWebService")
public class ActivityWebService extends WebBaseServiceImpl{
	
	@Autowired
	private ActivitySignupService activitySignupService;
	
	@Autowired
	private ActivityService activityService;
	
	@Autowired
	private BusInfoService busInfoService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private UserDao userDao;
	
	//活动报名
	public JSONResponse activitySignup(String userName,String phone,String activityId,String busId,HttpServletRequest request){
		JSONResponse result = new JSONResponse();

		User user_bus = userService.findUserById(busId);
		
		//根据手机号查询此用户在改企业下是否注册，如果没注册，则先注册，然后在报名
		User user = userService.isExistsByBusId(busId, phone);
		String userInsertId = "";//用户注册在h5有两个地方，一个是登陆的时候如果数据库中没有，则注册。还有就是报名的时候默认给注册
		if(null==user){
			//给用户注册到busId企业下
			String pwd = "";
			if(StringUtils.isNotBlank(phone)){
				//截取后6位作为手机号默认登录密码
				if(phone.length()==11){
					pwd = phone.substring(5, 11);
				}
			}
			
			User userInsert = new User();
			userInsert.setUserParentId(Long.parseLong(busId));
			userInsert.setUserName(phone);
			userInsert.setUserPassword(EncryptUtil.hash(pwd));
			userInsert.setUserUnitName(userName);
			userInsert.setUserStatue("1");
			userInsert.setIsBusinessAdmin("0");//不是企业管理员
			userInsert.setSourceType("4");//通过报名注册的用户
			userInsert.setIsAdmin("0");
			userInsert.setBelongBusUserId(busId);
			userInsert.setUserCreateDate(new Date());
			userInsert.setUserCreateUserName(user_bus.getUserUnitName());
			userInsert.setUserCreateUserId(Long.parseLong(busId));
			
			userDao.insert(userInsert);
			userInsertId = userInsert.getUserId()+"";
			user = userInsert;
		}else{
			userInsertId = user.getUserId()+"";
		}
		
		
		//查询是否已经报名，如果已经报名，则提示不能重复报名
		List<Map<String, Object>> singupList = activitySignupService.isRepeatSingup(busId, userInsertId, activityId);
		if(null!=singupList && singupList.size()>0){
			//证明已经报名，返回
			result.setMsg("此活动您已经报名，不需要重复报名哦");
			return result;
		}
		
		//根据活动id查询活动name
	    Activity activity = activityService.findById(activityId);
		
		ActivitySignup activitySignup = new ActivitySignup();
		activitySignup.setBusId(Integer.parseInt(busId));
		activitySignup.setUserId(Integer.parseInt(userInsertId));
		activitySignup.setActivityId(Integer.parseInt(activityId));
		activitySignup.setActivityTitle(StringUtils.isNotBlank(activity.getTitle())?activity.getTitle():"");
		activitySignup.setUserName(userName);
		activitySignup.setPhone(phone);
		activitySignup.setCreateDate(new Date());
		activitySignup.setCreateUserId(Integer.parseInt(user.getUserId()+""));
		activitySignup.setCreateUserName(user.getUserUnitName());
		
		activitySignupService.insert(activitySignup);
		
		loginRecord(user, request);
		
		result.setSuccess(true);
		//根据用户id查询企业名称
		UserBusInfo userBusInfo = busInfoService.findById(user.getBelongBusUserId().toString());
		user.setBusName(userBusInfo.getBusName());
		result.setData(user);
		return result;
	}
	

}
