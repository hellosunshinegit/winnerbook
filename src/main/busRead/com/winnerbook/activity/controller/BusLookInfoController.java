package com.winnerbook.activity.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

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

import com.winnerbook.activity.dto.BusLookInfo;
import com.winnerbook.activity.service.BusLookInfoService;
import com.winnerbook.base.common.JSONResponse;
import com.winnerbook.base.common.PageDTO;
import com.winnerbook.base.frame.controller.BaseController;

/**
 * 调研报告信息列表
 * @author hxs
 */
@Controller
@RequestMapping(value="/busLookInfoController")
public class BusLookInfoController extends BaseController{
	
	@Autowired
	private BusLookInfoService busLookInfoService;
	
	private static final Logger logger = LoggerFactory.getLogger(BusLookInfoController.class);
	
	//查询列表
	@RequestMapping(value="/busLookInfoList.html")
	public String busLookInfoList(HttpServletRequest request,Model model, Integer pageIndex,Integer pageSize){
		logger.info("调研报告列表...");
		String status = request.getParameter("status");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("status", status);
		map.put("sessionUser",getSessionUser());
		PageDTO<BusLookInfo> pageDTO  = busLookInfoService.listByPage(map, pageIndex,10);//默认每页显示10条数据
 
		model.addAttribute("pageDTO", pageDTO);
		model.addAllAttributes(map);
		return "manage/busRead/activity/lookInfo/lookInfoList";
	}

	//点击查看
	@RequestMapping("viewLookInfo.html")
	public String viewLookInfo(@RequestParam String id,ModelMap modelMap){
		BusLookInfo busLookInfo = busLookInfoService.findById(id);
		modelMap.addAttribute("busLookInfo", busLookInfo);
		return "manage/busRead/activity/lookInfo/viewLookInfo"; 
	}
	
	//点击通过或者拒绝
	@RequestMapping(value="examineBus.html")
	@ResponseBody
	public JSONResponse examineBus(@RequestBody String json){
		return busLookInfoService.examineBus(json);
	}
	
	
}
