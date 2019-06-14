/** 
 * 2015-2-4 
 * UserController.java  后台用户控制器
 * author:nql
 */ 
package com.winnerbook.system.controller; 

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.winnerbook.base.common.JSONResponse;
import com.winnerbook.base.common.util.EncryptUtil;
import com.winnerbook.base.frame.content.ThreadLocalWrapper;
import com.winnerbook.base.frame.content.UserContext;
import com.winnerbook.system.dto.UcSystemLogRecord;
import com.winnerbook.system.dto.User;
import com.winnerbook.system.service.UcSystemLogRecordService;
import com.winnerbook.system.service.UserService;

@Controller
@RequestMapping(value="/user")
public class UserLoginController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private UcSystemLogRecordService ucSystemLogRecordService;

	private static final Logger logger = LoggerFactory.getLogger(UserController.class);
	
	@RequestMapping(value="/login.html")
	public String toLogin(Model model, HttpSession session){
		return "manage/login";
	}
	
	@RequestMapping(value="/logout.html")
	public String toLogout(Model model, HttpSession session){
		session.invalidate();
		return "redirect:/user/login.html";
	}
	

	@RequestMapping(value="/dologin.html")
	@ResponseBody
	public JSONResponse userLogin(Model model,HttpSession session, @RequestParam String userName, @RequestParam String userPassword,@RequestParam String code,HttpServletRequest request){
		String selectUser = request.getParameter("selectUser");
		logger.info("==========selectUser=========="+selectUser);
 		JSONResponse json = new JSONResponse();
		try {
			if(StringUtils.isEmpty(userName)){
				json.setMsg("请输入手机号码!");
				return json;
			}
			if(StringUtils.isEmpty(userPassword)){
				json.setMsg("请输入密码!");
				return json;
			}
			if(StringUtils.isEmpty(code)){
				json.setMsg("请输入验证码!");
				return json;
			}
			User user = null;
			if(StringUtils.isNotBlank(selectUser)){
				//根据用户id和企业id获取唯一的用户值 
				String[] selectUserArray = selectUser.split("-");
				user = userService.getUserByUserBus(selectUserArray[0], selectUserArray[1]);
				return getUserLogin(userPassword, code, user, session, request,json);
			}
			
			List<Map<String, Object>> userMapList = this.userService.findUserByUserName(userName);
			
			if(null==userMapList || userMapList.size()==0){
				json.setMsg("您输入的用户名不存在!");
				return json;
			}
			
			if(userMapList.size()>1){
				json.setMsg("您登陆的手机号存在多个企业中，请选择!");
				json.setData(JSONArray.fromObject(userMapList));
				return json;
			}  
			
			user = userService.findUserById(userMapList.get(0).get("userId").toString());
			
			return getUserLogin(userPassword, code, user, session, request,json);
			
		} catch (Exception e) {
			e.printStackTrace();
			json.setMsg("数据异常，请联系管理员"+e.getMessage());
		}
		return json;
	}
	@RequestMapping(value = "/layout.html")
	public String layout(Model model, HttpSession session) {
		return "manage/layout";
	}
	@RequestMapping(value = "/welcome.html")
	public String welcome(Model model, HttpSession session) {
		return "manage/welcome";
	}
	
	public JSONResponse getUserLogin(String userPassword,String code,User user,HttpSession session,HttpServletRequest request,JSONResponse json){
		String _password = EncryptUtil.hash(userPassword);
		String _userPassword = user.getUserPassword();
		String _code = code;
		String validateCode = session.getAttribute("validateCode").toString();
		if(!_userPassword.equalsIgnoreCase(_password)){
			json.setMsg("您输入的用户密码不正确!");
			return json;
		}
		if(!_code.equals(validateCode)){
			json.setMsg("您输入的验证码不正确!");
			return json;
		}
		UserContext userContext = new UserContext();
		userContext.setUser(user);
		ThreadLocalWrapper.bind(userContext);
		session.setAttribute(ThreadLocalWrapper.USER_CONTEXT_KEY, userContext);
		json.setSuccess(true);
		json.setUrl("user/layout.html");
		//日志记录
		UcSystemLogRecord ucSystemLogRecord = new UcSystemLogRecord();
		ucSystemLogRecord.setLogCreateDate(new Date());
		ucSystemLogRecord.setLogCreateUserId(user.getUserId());
		ucSystemLogRecord.setLogCreateUserName(user.getUserUnitName()+"("+user.getUserName()+")");
		ucSystemLogRecord.setLogIp(request.getRemoteAddr());
		ucSystemLogRecord.setLogDes("用户："+user.getUserUnitName()+"("+user.getUserName()+")"+"进行登录");
		ucSystemLogRecord.setLogType("1"); 
		ucSystemLogRecord.setLogSource("1"); //pc
		ucSystemLogRecordService.insert(ucSystemLogRecord);
		return json;
	}
}
