package com.winnerbook.busInfo.controller; 

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import com.winnerbook.base.frame.controller.BaseController;
import com.winnerbook.busInfo.dto.BusPayUser;
import com.winnerbook.busInfo.dto.UserBusInfo;
import com.winnerbook.busInfo.service.BusInfoService;
import com.winnerbook.busInfo.service.BusPayUserService;

/**
 * 企业通讯录 企业付费用户选择
 * 
 */
@Controller
@RequestMapping(value="/busPayUserController")
public class BusPayUserController extends BaseController{
		
	@Autowired
	private BusInfoService busInfoService;
	
	@Autowired
	private BusPayUserService busPayUserService;

	private static final Logger logger = LoggerFactory.getLogger(BusPayUserController.class);
	
	//所有的企业列表
	@RequestMapping(value="/busPay.html")
	public String  userList(Model model){
		logger.info("企业列表...");
		List<Map<String, Object>> userBusInfos = busInfoService.getBusList();
		model.addAttribute("busList", userBusInfos);
		return "manage/busRead/bus/busPay/busPay";
	}
	
	//根据企业id查询企业下的员工  source=1,2
	@RequestMapping(value="getBusEmp.html")
	@ResponseBody
	public Map<String, Object> getBusEmp(@RequestParam String busId){
		logger.info("企业员工列表...");
		Map<String, Object> map = new HashMap<String, Object>();
		UserBusInfo userBusInfo = busInfoService.findById(busId);
		List<Map<String, Object>> empList = busInfoService.getBusEmpList(busId);//获取企业下所有员工
		//获取企业下已经勾选员工
		List<BusPayUser> busPayUsers = busPayUserService.selectUserByBusId(busId);
		map.put("empList", empList);
		map.put("userBusInfo", userBusInfo);
		map.put("busPayUsers", busPayUsers);
		return map;
	}
	
	//点击保存
	@RequestMapping(value="addPayBusEmp.html")
	@ResponseBody
	public JSONResponse addPayBusEmp(@RequestBody String jsonStr){
		logger.info("添加企业员工列表...");
		return busPayUserService.addPayBusEmp(jsonStr);
	}
	
	
}

	
