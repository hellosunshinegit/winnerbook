package com.winnerbook.web.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.winnerbook.base.common.JSONResponse;
import com.winnerbook.web.service.LoginService;

@Controller
public class LoginH5Web {
	
	@Autowired
	private LoginService loginService;

	//登录
	@RequestMapping(value="getLogin.jhtml",produces = {"application/json;charset=utf-8"})
	@ResponseBody
	public String getLogin(@RequestParam String username,@RequestParam String password,String selectUser,HttpServletRequest request,@RequestParam("callback") String callback){
		JSONResponse result = new JSONResponse();
		
		Map<String, Object> parameter = new HashMap<String, Object>();
		parameter.put("username", username);
		parameter.put("password", password);
 		try {
 			result = loginService.getLoginInfo(username, password,selectUser,request);
		} catch (Exception e) {
			result.setSuccess(false);
			result.setMsg("登录信息获取失败");
			e.getStackTrace();
		}
		
		return callback+"("+JSONObject.fromObject(result)+")";
	}
	
}
