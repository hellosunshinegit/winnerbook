package com.winnerbook.system.controller; 

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.winnerbook.base.common.JSONResponse;
import com.winnerbook.base.common.PageDTO;
import com.winnerbook.base.frame.controller.BaseController;
import com.winnerbook.system.dto.UserApplyBusAdmin;
import com.winnerbook.system.service.UserApplyBusAdminService;

/**
 * 用户申请成为企业管理员列表
 */
@Controller
@RequestMapping(value="/userApplyBusAdminController")
public class UserApplyBusAdminController extends BaseController{
	private static final Logger logger = LoggerFactory.getLogger(UserApplyBusAdminController.class);
		
	@Autowired
	private UserApplyBusAdminService userApplyBusAdminService;
	
	
	@RequestMapping(value="/userApplyList.html")
	public String  userList(HttpServletRequest request,Model model, Integer pageIndex,Integer pageSize){
		
		String userName =request.getParameter("userName");
		String status =request.getParameter("status");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userName", userName);
		map.put("status", status);
		PageDTO<UserApplyBusAdmin> pageDTO  = userApplyBusAdminService.listByPage(map, pageIndex,10);
 
		model.addAttribute("pageDTO", pageDTO);
		model.addAllAttributes(map);
		return "manage/uc/applyBusAdmin/applyBusAdminList";
	}
	
	//查看详情
	@RequestMapping("viewApplyBusAdmin.html")
	public String viewApplyBusAdmin(@RequestParam String uaId,ModelMap modelMap){
		modelMap.put("applyBusAdmin", userApplyBusAdminService.findById(uaId));
		return "manage/uc/applyBusAdmin/viewApplyBusAdmin";
	}
	
	//修改提交
	@RequestMapping(value="updateSubmitUser.html")
	@ResponseBody
	public JSONResponse updateSubmitUser(@RequestBody String json){
		return userApplyBusAdminService.updateSubmitUser(json);
	}
	
}

	
