package com.winnerbook.web.controller;

import java.util.HashMap;
import java.util.Map;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.winnerbook.activity.service.FeedbackService;
import com.winnerbook.base.common.JSONResponse;
import com.winnerbook.web.service.FeedbackServiceWeb;
import com.winnerbook.web.utils.ConstantWebUtils;
import com.winnerbook.web.utils.PageUtil;

@Controller
public class FeedbackH5Web {
	
	@Autowired
	private FeedbackService feedbackService;
	
	@Autowired
	private FeedbackServiceWeb feedbackServiceWeb;

	//获取反馈
	@RequestMapping(value="getFeedbacks.jhtml",produces = {"application/json;charset=utf-8"})
	@ResponseBody
	public String getFeedback(String busId,String userId,String pageIndex,@RequestParam("callback") String callback){
		JSONResponse result = new JSONResponse();
		if(!StringUtils.isNotBlank(busId)){
			busId = ConstantWebUtils.busIdDefulat;
		}
		
		Map<String, Object> parameter = new HashMap<String, Object>();
		parameter.put("userId", userId);
		Map<String, Object> feedbackList = feedbackService.getFeedbacks(PageUtil.getParam(parameter, busId, pageIndex));
		
 		result.setSuccess(true);
		result.setMsg("获取反馈列表成功");
		result.setData(feedbackList);
		return callback+"("+JSONObject.fromObject(result)+")";
	}
	
	//insert
	@RequestMapping(value="addFeedback.jhtml",produces = {"application/json;charset=utf-8"})
	@ResponseBody
	public String addCourseComments(@RequestParam String userId,String busId,String remarks,@RequestParam("callback") String callback){
		JSONResponse result = new JSONResponse();
		if(!StringUtils.isNotBlank(busId)){
			busId = ConstantWebUtils.busIdDefulat;
		}
		if(!StringUtils.isNotBlank(remarks)){
			result.setMsg("反馈内容不可为空");
			return callback+"("+JSONObject.fromObject(result)+")";
		}
		
		//判断是否有表情，过滤字符
		remarks = remarks.replaceAll("[\\ud800\\udc00-\\udbff\\udfff\\ud800-\\udfff]", "*");
		
		Map<String, Object> parameter = new HashMap<String, Object>();
		parameter.put("userId", userId);
		parameter.put("remarks", remarks);
		
		int feedback = feedbackServiceWeb.insertFeedback(parameter);
		
 		result.setSuccess(true);
		result.setMsg("评论成功");
		result.setData(feedback);
		return callback+"("+JSONObject.fromObject(result)+")";
	}
	
	
}
