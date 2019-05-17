package com.winnerbook.busInfo.controller; 

import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import com.winnerbook.base.common.PageDTO;
import com.winnerbook.base.frame.controller.BaseController;
import com.winnerbook.system.dto.User;
import com.winnerbook.system.service.UserService;

/**
 * 企业通讯录 查询的就是user表
 * 
 */
@Controller
@RequestMapping(value="/busEmpController")
public class BusEmpController extends BaseController{
		
	@Autowired
	private UserService userService;

	private static final Logger logger = LoggerFactory.getLogger(BusEmpController.class);
	
	@RequestMapping(value="/userEmpList.html")
	public String  userList(HttpServletRequest request,Model model, Integer pageIndex,Integer pageSize){
		logger.info("企业员工列表...");
		String userName =request.getParameter("userName");
		String userStatue =request.getParameter("userStatue");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userName", userName);
		map.put("userStatue", userStatue);
		map.put("sessionUser",getSessionUser());
		PageDTO<User> pageDTO  = userService.listByPage(map, pageIndex,10);
 
		model.addAttribute("pageDTO", pageDTO);
		model.addAllAttributes(map);
		return "manage/busRead/bus/busEmp/busEmpList";
	}
}

	
