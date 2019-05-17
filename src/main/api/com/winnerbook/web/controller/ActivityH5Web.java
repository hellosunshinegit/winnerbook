package com.winnerbook.web.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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

import com.winnerbook.activity.service.ActivityService;
import com.winnerbook.activity.service.ActivitySignupService;
import com.winnerbook.base.common.JSONResponse;
import com.winnerbook.base.common.util.DateTimeUtils;
import com.winnerbook.web.service.ActivityWebService;
import com.winnerbook.web.utils.ConstantWebUtils;
import com.winnerbook.web.utils.PageUtil;
@Controller
public class ActivityH5Web {
	
	@Autowired
	private ActivityService activityService;
	
	@Autowired
	private ActivityWebService activityWebService;
	
	@Autowired
	private ActivitySignupService activitySignupService;

	//活动列表
	@RequestMapping(value="getActivitys.jhtml",produces = {"application/json;charset=utf-8"})
	@ResponseBody
	public String getActivitys(String busId,String pageIndex,@RequestParam("callback") String callback){
		JSONResponse result = new JSONResponse();
		if(!StringUtils.isNotBlank(busId)){
			busId = ConstantWebUtils.busIdDefulat;
		}
		
		Map<String, Object> parameter = new HashMap<String, Object>();
 		Map<String, Object> article = activityService.getActivitys(PageUtil.getParam(parameter, busId, pageIndex));
		
 		result.setSuccess(true);
		result.setMsg("获取活动列表成功");
		result.setData(article);
		return callback+"("+JSONObject.fromObject(result)+")";
	}
	
	//获取活动详情表
	@RequestMapping(value="getActivityDetail.jhtml",produces = {"application/json;charset=utf-8"})
	@ResponseBody
	public String getActivityDetail(@RequestParam String id,@RequestParam("callback") String callback){
		JSONResponse result = new JSONResponse();
		
		Map<String, Object> parameter = new HashMap<String, Object>();
		parameter.put("id", id);
 		Map<String, Object> article = activityService.getActivityDetail(parameter);
 		if(null!=article.get("startDate") && StringUtils.isNotBlank(article.get("startDate").toString())){
 			article.put("week", DateTimeUtils.dateToWeek(article.get("startDate").toString()));
 			SimpleDateFormat df_min = new SimpleDateFormat("yyyy-MM-dd HH:mm");
 			Date startDate;
			try {
				startDate = df_min.parse(article.get("startDate").toString()+" "+article.get("startDateTime").toString());
				long min = DateTimeUtils.getDiffMinute(startDate);
				if(min>=0){
					article.put("isInvalid", "1");//1 已失效
				}else{
					article.put("isInvalid", "0");//0 正常
				}
				
				//转换开始事件和结束时间格式化
				SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
				SimpleDateFormat sdf = new SimpleDateFormat("MM/dd");
				article.put("startDate", sdf.format(df.parse(article.get("startDate").toString())));
				article.put("endDate", sdf.format(df.parse(article.get("endDate").toString())));
				
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
 		result.setSuccess(true);
		result.setMsg("获取活动详情成功");
		result.setData(article);
		return callback+"("+JSONObject.fromObject(result)+")";
	}
	
	
	//点击报名提交
	@RequestMapping(value="activitySignUpSubmit.jhtml",produces = {"application/json;charset=utf-8"})
	@ResponseBody
	public String activitySignUpSubmit(String userName,String phone,String activityId,String busId,HttpServletRequest request,@RequestParam("callback") String callback){
		JSONResponse result = new JSONResponse();
		result = activityWebService.activitySignup(userName, phone, activityId, busId, request);
		return callback+"("+JSONObject.fromObject(result)+")";
	}
	
	//我的报名列表
	@RequestMapping(value="activitySignUps.jhtml",produces = {"application/json;charset=utf-8"})
	@ResponseBody
	public String getActivitys(String busId,String userId,String pageIndex,@RequestParam("callback") String callback){
		JSONResponse result = new JSONResponse();
		if(!StringUtils.isNotBlank(busId)){
			busId = ConstantWebUtils.busIdDefulat;
		}
		
		Map<String, Object> parameter = new HashMap<String, Object>();
		parameter.put("userId", userId);
 		Map<String, Object> signup = activitySignupService.getActivitySignups(PageUtil.getParam(parameter, busId, pageIndex));
		
 		result.setSuccess(true);
		result.setMsg("获取活动报名列表成功");
		result.setData(signup);
		return callback+"("+JSONObject.fromObject(result)+")";
	}
	
}
