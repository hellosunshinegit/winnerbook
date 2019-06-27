package com.winnerbook.web.controller;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.winnerbook.base.common.JSONResponse;
import com.winnerbook.course.service.CourseTypeService;
import com.winnerbook.system.dto.Dictionary;
import com.winnerbook.system.service.DictionaryService;
import com.winnerbook.web.service.LookInfoWebService;

@Controller
public class LookInfoH5Web {
	
	@Autowired
	private CourseTypeService courseTypeService;
	
	@Autowired
	private LookInfoWebService lookInfoWebService;
	
	@Autowired
	private DictionaryService dictionaryService;
	
	//企业调研
	@RequestMapping(value="getCourseType.jhtml",produces = {"application/json;charset=utf-8"})
	@ResponseBody
	public String getActivitys(@RequestParam("callback") String callback){
		JSONResponse result = new JSONResponse();
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		//查询课程类型
		List<Map<String,Object>> listMap = courseTypeService.getCoursePackage();
		//查询读书计划
		List<Dictionary> dictionaries = dictionaryService.getDictionaries("READ_BOOK_PLAN");
		
		resultMap.put("listMap", listMap);
		resultMap.put("readBookPlanDicList", dictionaries);
		
 		result.setSuccess(true);
		result.setMsg("获取调研before数据成功");
		result.setData(resultMap);
		return callback+"("+JSONObject.fromObject(result)+")";
	}
	
	//提交调研
	@RequestMapping(value="addLookInfo.jhtml",produces = {"application/json;charset=utf-8"})
	@ResponseBody
	public String addLookInfo(HttpServletRequest request,@RequestParam("callback") String callback){
		JSONResponse result = lookInfoWebService.addLookInfo(request);
		return callback+"("+JSONObject.fromObject(result)+")";
	}
	
}
