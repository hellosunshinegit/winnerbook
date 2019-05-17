package com.winnerbook.course.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.winnerbook.base.common.PageDTO;
import com.winnerbook.base.frame.controller.BaseController;
import com.winnerbook.course.dto.StudentRecord;
import com.winnerbook.course.service.StudentRecordService;

/**
 * 学习记录
 * @author hxs
 */
@Controller
@RequestMapping(value="/studentRecordController")
public class StudentRecordController extends BaseController{
	
	@Autowired
	private StudentRecordService studentRecordService;

	private static final Logger logger = LoggerFactory.getLogger(StudentRecordController.class);
	
	//查询列表
	@RequestMapping(value="/studentRecordList.html")
	public String busInfoList(HttpServletRequest request,Model model, Integer pageIndex,Integer pageSize){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userName", request.getParameter("userName"));
		map.put("courseName", request.getParameter("courseName"));
		map.put("sessionUser",getSessionUser());
		PageDTO<StudentRecord> pageDTO  = studentRecordService.listByPage(map, pageIndex,10);//默认每页显示10条数据
 
		model.addAttribute("pageDTO", pageDTO);
		model.addAllAttributes(map);
		return "manage/busRead/course/studentRecord/studentRecordList";
	}
	
	@RequestMapping("startStudent.html")
	@ResponseBody
	public Map<String, Object> startStudent(@RequestBody String param){
		Map<String, Object> map = new HashMap<>();
		
		JSONObject paramJson = JSONObject.fromObject(param);
		String courseId = paramJson.getString("courseId");
		String time = paramJson.getString("time");
		String recordId = null!=paramJson.get("recordId")?paramJson.getString("recordId"):"";
		String type = paramJson.getString("type");
		String isEnd = paramJson.getString("isEnd");
		String fileId = null!=paramJson.get("fileId")?paramJson.getString("fileId"):"";
		String totalTime = null!=paramJson.get("totalTime")?paramJson.getString("totalTime"):"";
		try {
			Map<String, Object> map_insert = new HashMap<String, Object>();
			map_insert.put("courseId", courseId);
			map_insert.put("time", time);
			map_insert.put("type", type);
			map_insert.put("recordId", recordId);
			map_insert.put("isEnd", isEnd);
			map_insert.put("fileId", fileId);
			map_insert.put("totalTime", totalTime);
			
			int insertRecordId = studentRecordService.insert(map_insert);
			map.put("recordId", insertRecordId);
			map.put("code", "200");
		} catch (Exception e) {
			e.getMessage();
			map.put("code", "-1");
		}
		return map;
	}
	
}
