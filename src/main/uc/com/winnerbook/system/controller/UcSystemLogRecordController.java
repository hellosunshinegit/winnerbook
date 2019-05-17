package com.winnerbook.system.controller;

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
import com.winnerbook.system.dto.UcSystemLogRecord;
import com.winnerbook.system.service.UcSystemLogRecordService;

/**
 * 后台菜单控制器
 * 
 * @date 2016-2-15
 * 
 */
@Controller
@RequestMapping(value="/ucSystemLogRecordController")
public class UcSystemLogRecordController extends BaseController{
	@Autowired
	private UcSystemLogRecordService ucSystemLogRecordService;
	
	private static final Logger logger = LoggerFactory.getLogger(UcSystemLogRecordController.class);
	
	@RequestMapping(value="/logRecordList.html")
	public String logRecordList(HttpServletRequest request,Model model, Integer pageIndex,Integer pageSize){
		
		String logType =request.getParameter("logType");
		String logSourse =request.getParameter("logSourse");
		String starttime =request.getParameter("starttime");
		String endtime =request.getParameter("endtime");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("logType", logType);
		map.put("logSourse", logSourse);
		map.put("starttime", starttime);
		map.put("endtime", endtime);
		map.put("sessionUser",getSessionUser());
		PageDTO<UcSystemLogRecord> pageDTO  = ucSystemLogRecordService.listByPage(map, pageIndex,10);
 
		model.addAttribute("pageDTO", pageDTO);
		model.addAllAttributes(map);
		return "manage/uc/log/logList";
	}
	
}
