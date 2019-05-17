package com.winnerbook.web.controller;

import java.io.UnsupportedEncodingException;
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
import com.winnerbook.course.dto.CourseComment;
import com.winnerbook.course.service.CourseCommentService;
import com.winnerbook.course.service.CourseService;
import com.winnerbook.web.service.ClickInfoService;
import com.winnerbook.web.service.CourseCommentServiceWeb;
import com.winnerbook.web.utils.ConstantWebUtils;
import com.winnerbook.web.utils.PageUtil;

@Controller
public class CourseH5Web {
	
	@Autowired
	private CourseService courseService;
	
	@Autowired
	private CourseCommentService courseCommentService;
	
	@Autowired
	private CourseCommentServiceWeb commentServiceWeb;
	
	@Autowired
	private ClickInfoService clickInfoService;
	

	//获取文章详情表
	@RequestMapping(value="getCourseDetail.jhtml",produces = {"application/json;charset=utf-8"})
	@ResponseBody
	public String getCourseDetail(@RequestParam String courseId,String busId,String userId,String type,HttpServletRequest request,@RequestParam("callback") String callback){
		JSONResponse result = new JSONResponse();
		if(!StringUtils.isNotBlank(busId)){
			busId = ConstantWebUtils.busIdDefulat;
		}
		if("2".equals(type)){//记录导师点击次数
			clickInfoService.mainGuestClick(userId, courseId, request);
		}
		
		Map<String, Object> parameter_course = new HashMap<>();
		parameter_course.put("courseId", courseId);
		parameter_course.put("userId", userId);
		courseService.updateClickNum(parameter_course);
		
		
		
		Map<String, Object> parameter = new HashMap<String, Object>();
		parameter.put("busId", busId);
		parameter.put("courseId", courseId);
 		Map<String, Object> courseDetail = courseService.getCourseDetail(parameter);
		
 		result.setSuccess(true);
		result.setMsg("获取课程详情成功");
		result.setData(courseDetail);
		return callback+"("+JSONObject.fromObject(result)+")";
	}
	
	//评论列表 点击评论  对课程进行评论
	//获取文章详情表
	@RequestMapping(value="getCourseComments.jhtml",produces = {"application/json;charset=utf-8"})
	@ResponseBody
	public String getCourseComments(String courseId,String busId,String userId,String pageIndex,@RequestParam("callback") String callback){
		JSONResponse result = new JSONResponse();
		if(!StringUtils.isNotBlank(busId)){
			busId = ConstantWebUtils.busIdDefulat;
		}
		
		Map<String, Object> parameter = new HashMap<String, Object>();
		parameter.put("courseId", courseId);
		parameter.put("userId", userId);
		Map<String, Object> courseCommentList = courseCommentService.getCourseCommnets(PageUtil.getParam(parameter, busId, pageIndex));
		
 		result.setSuccess(true);
		result.setMsg("获取评论列表成功");
		result.setData(courseCommentList);
		return callback+"("+JSONObject.fromObject(result)+")";
	}
	
	//insert
	@RequestMapping(value="addCourseComments.jhtml",produces = {"application/json;charset=utf-8"})
	@ResponseBody
	public String addCourseComments(@RequestParam String courseId,String busId,String userId,String comment,@RequestParam("callback") String callback){
		JSONResponse result = new JSONResponse();
		if(!StringUtils.isNotBlank(busId)){
			busId = ConstantWebUtils.busIdDefulat;
		}
		if(!StringUtils.isNotBlank(comment)){
			result.setMsg("评论内容不可为空");
			return callback+"("+JSONObject.fromObject(result)+")";
		}
		
		//判断是否有表情，过滤字符
		comment = comment.replaceAll("[\\ud800\\udc00-\\udbff\\udfff\\ud800-\\udfff]", "*");
		
		Map<String, Object> parameter = new HashMap<String, Object>();
		parameter.put("courseId", courseId);
		parameter.put("userId", userId);
		parameter.put("comment", comment);
		
		int courseComment = commentServiceWeb.insertComment(parameter);
		
 		result.setSuccess(true);
		result.setMsg("评论成功");
		result.setData(courseComment);
		return callback+"("+JSONObject.fromObject(result)+")";
	}
	
	
}
