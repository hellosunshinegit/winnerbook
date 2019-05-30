package com.winnerbook.web.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.winnerbook.base.common.JSONResponse;
import com.winnerbook.course.service.ReadThoughtService;
import com.winnerbook.course.service.StudentRecordService;
import com.winnerbook.system.service.UserApplyBusAdminService;
import com.winnerbook.web.service.CenterServiceWeb;
import com.winnerbook.web.service.ClickInfoService;
import com.winnerbook.web.service.StudentRecordServiceWeb;
import com.winnerbook.web.utils.PageUtil;

@Controller
public class CenterH5Web {
	
	@Autowired
	private ReadThoughtService readThoughtService;
	
	@Autowired
	private StudentRecordService studentRecordService;
	
	@Autowired
	private StudentRecordServiceWeb studentRecordServiceWeb;
	
	@Autowired
	private ClickInfoService clickInfoService;
	
	@Autowired
	private CenterServiceWeb centerServiceWeb;
	
	@Autowired
	private UserApplyBusAdminService userApplyBusAdminService;

	//获取我的读后感
	@RequestMapping(value="getReadThoughtUsers.jhtml",produces = {"application/json;charset=utf-8"})
	@ResponseBody
	public String getArticles(@RequestParam String userId,String busId,String pageIndex,@RequestParam("callback") String callback){
		JSONResponse result = new JSONResponse();
		
		if(!StringUtils.isNotBlank(userId)){
			result.setMsg("请登录...");
			return callback+"("+JSONObject.fromObject(result)+")";
 		}
		
		Map<String, Object> parameter = new HashMap<String, Object>();
		parameter.put("userId", userId);
 		Map<String, Object> readThought = readThoughtService.getReadThoughtUser(PageUtil.getParam(parameter, busId, pageIndex));
		
 		result.setSuccess(true);
		result.setMsg("获取我的读后感成功");
		result.setData(readThought);
		return callback+"("+JSONObject.fromObject(result)+")";
	}
	
	//获取学习记录
	@RequestMapping(value="getStudentRecords.jhtml",produces = {"application/json;charset=utf-8"})
	@ResponseBody
	public String getStudentRecords(@RequestParam String userId,String busId,String pageIndex,@RequestParam("callback") String callback){
		JSONResponse result = new JSONResponse();
		
		if(!StringUtils.isNotBlank(userId)){
			result.setMsg("请登录...");
			return callback+"("+JSONObject.fromObject(result)+")";
 		}
		
		Map<String, Object> parameter = new HashMap<String, Object>();
		parameter.put("userId", userId);
 		Map<String, Object> studentRecord = studentRecordService.getStudentRecords(PageUtil.getParam(parameter, busId, pageIndex));
		
 		result.setSuccess(true);
		result.setMsg("获取我的学习记录成功");
		result.setData(studentRecord);
		return callback+"("+JSONObject.fromObject(result)+")";
	}
	
	
	//记录学习时长
	@RequestMapping(value="addStudentRecord.jhtml",produces = {"application/json;charset=utf-8"})
	@ResponseBody
	public String addStudentRecord(@RequestParam String courseId,@RequestParam String time,String recordId,String type,String isEnd,String userId,String fileId,String totalTime,String clickStart,HttpServletRequest request,@RequestParam("callback") String callback){
		JSONResponse result = new JSONResponse();
		if(!StringUtils.isNotBlank(userId)){
			result.setMsg("请登录...");
			return callback+"("+JSONObject.fromObject(result)+")";
 		}
		if(StringUtils.isNotBlank(clickStart) && "1".equals(clickStart)){//记录点击的视频记录
			clickInfoService.videoClick(userId, courseId, fileId, type, request);
		}
		
		int insertRecordId = studentRecordServiceWeb.addStudentRecord(courseId, time, type, recordId,isEnd,userId,fileId,totalTime);
		Map<String,Object> map = new HashMap<>();
		map.put("recordId", insertRecordId);
		result.setData(map);
		result.setSuccess(true);
		return callback+"("+JSONObject.fromObject(result)+")";
	}
	
	//请求是否已经申请了成为企业管理员
	@RequestMapping(value="isApplyBusAdmin.jhtml",produces = {"application/json;charset=utf-8"})
	@ResponseBody
	public String isApplyBusAdmin(@RequestParam String userId,String busId,@RequestParam("callback") String callback){
		JSONResponse result = new JSONResponse();
		if(!StringUtils.isNotBlank(userId)){
			result.setMsg("请登录...");
			return callback+"("+JSONObject.fromObject(result)+")";
 		}
		
		Map<String, Object> busAdminMap = userApplyBusAdminService.getUserApplyBusAdmin(userId);
		result.setData(busAdminMap);
		result.setSuccess(true);
		return callback+"("+JSONObject.fromObject(result)+")";
	}
	
	//申请成为企业管理员
	@RequestMapping(value="applyBusAdmin.jhtml",produces = {"application/json;charset=utf-8"})
	@ResponseBody
	public String applyBusAdmin(@RequestParam String userId,String busId,String applyBusName,String applyBusDes,@RequestParam("callback") String callback){
		JSONResponse result = new JSONResponse();
		if(!StringUtils.isNotBlank(userId)){
			result.setMsg("请登录...");
			return callback+"("+JSONObject.fromObject(result)+")";
 		}
		
		String applyId = centerServiceWeb.addApplyRecord(userId, busId, applyBusName, applyBusDes);
		
		result.setData(applyId);
		result.setSuccess(true);
		return callback+"("+JSONObject.fromObject(result)+")";
	}
	
}
