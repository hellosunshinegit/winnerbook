package com.winnerbook.web.controller;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.winnerbook.base.common.JSONResponse;
import com.winnerbook.report.service.StudentReportService;
import com.winnerbook.web.utils.PageUtil;

@Controller
public class StudentReportH5Web {
	
	@Autowired
	private StudentReportService studentReportService;

	//点击我的学习报告 和企业学习报告
	@RequestMapping(value="getMyReports.jhtml",produces = {"application/json;charset=utf-8"})
	@ResponseBody
	public String getMyReports(String userId,String busId,String type,@RequestParam("callback") String callback){
		JSONResponse result = new JSONResponse();
		if(StringUtils.isNotBlank(userId)){
			result.setMsg("userId为必传项");
		}
		
		Map<String, Object> mapResult = new HashMap<String, Object>();
		
		//总的数据
		Map<String, Object> map_user = new HashMap<>();
		
		if("1".equals(type)){//userId  我的学习报告
			map_user.put("userId", userId);
		}else{
			map_user.put("busId", busId);
		}
		Map<String, Object> totalMap = studentReportService.getMyReport(map_user);
		if("2".equals(type)){//企业的，获取的是学习时间的平均值
			Integer studentSecond = Integer.parseInt(totalMap.get("studentSecond")+"");
			Integer userCount = Integer.parseInt(totalMap.get("userCount")+"");
			//四舍五入，保留两位小数
			totalMap.put("studentSecond", new BigDecimal((Double.parseDouble(studentSecond+"")/Double.parseDouble(userCount+""))).setScale(2, BigDecimal.ROUND_HALF_UP));
		}
		
		//最近7天的学习数据
		List<Map<String, Object>> studentMap = studentReportService.getStudentRecord7(map_user);
		List<Map<String, Object>> readThougthMap = studentReportService.getReadThought7(map_user);
		List<Map<String, Object>> courseCommentMap = studentReportService.getCourseComment7(map_user);
		
		mapResult.put("totalMap", totalMap);
		mapResult.put("studentMap", studentMap);
		mapResult.put("readThougthMap", readThougthMap);
		mapResult.put("courseCommentMap", courseCommentMap);
		
 		result.setSuccess(true);
		result.setMsg("获取我的报告成功");
		result.setData(mapResult);
		return callback+"("+JSONObject.fromObject(result)+")";
	}
	
	//获取企业的排名
	@RequestMapping(value="getBusRanks.jhtml",produces = {"application/json;charset=utf-8"})
	@ResponseBody
	public String getBusRanks(String busId,String userId,String pageIndex,@RequestParam("callback") String callback){
		JSONResponse result = new JSONResponse();
		if(!StringUtils.isNotBlank(busId)){
			result.setMsg("busId为必传项");
			return callback+"("+JSONObject.fromObject(result)+")";
		}
		if(!StringUtils.isNotBlank(userId)){
			result.setMsg("userId为必传项");
			return callback+"("+JSONObject.fromObject(result)+")";
		}
		
		Map<String, Object> mapResult = new HashMap<String, Object>();
		
		Map<String, Object> map_bus = new HashMap<>();
		Map<String, Object> busRanks = studentReportService.getBusRanks(PageUtil.getParam(map_bus, busId, pageIndex));
		
		@SuppressWarnings("unchecked")
		List<Map<String, Object>> busRankList = (List<Map<String, Object>>) busRanks.get("busRankList");
		Map<String,Object> userRank = new HashMap<>();//当前登录人的排名
		
		if(null!=busRankList){
			//获取当前登录人的排名
			for(Map<String, Object> map:busRankList){
				if(userId.equals(map.get("userId").toString())){
					userRank = map;
				}
			}
		}
		
		mapResult.put("busRank", busRanks);
		mapResult.put("userRank", userRank);
		
 		result.setSuccess(true);
		result.setMsg("获取企业排名成功");
		result.setData(mapResult);
		return callback+"("+JSONObject.fromObject(result)+")";
	}
	
	
	//所有企业的排名
	@RequestMapping(value="getAllReports.jhtml",produces = {"application/json;charset=utf-8"})
	@ResponseBody
	public String getAllReports(String pageIndex,String busId,@RequestParam("callback") String callback){
		JSONResponse result = new JSONResponse();
		
		Map<String, Object> mapResult = new HashMap<String, Object>();
				
		Map<String, Object> map_bus = new HashMap<>();
		Map<String, Object> busRanks = studentReportService.getAllBusRanks(PageUtil.getParam(map_bus, "", pageIndex));//前10的数据
		
		Map<String, Object> busRanksAll = studentReportService.getAllBusRanks(new HashMap<String,Object>());
		
		//获取当前企业排名
		Map<String, Object> currentBusMap = new HashMap<>();
		List<Map<String, Object>> allBusRankList = (List<Map<String, Object>>) busRanksAll.get("allBusRankList");
		for(Map<String, Object> busMap:allBusRankList){
			if(busId.equals(busMap.get("busId").toString())){
				currentBusMap = busMap;
			}
		}
		
		mapResult.put("busRanks", busRanks);
		mapResult.put("currentBus", currentBusMap);
		
 		result.setSuccess(true);
		result.setMsg("获取所有企业排名成功");
		result.setData(mapResult);
		return callback+"("+JSONObject.fromObject(result)+")";
	}
	
	
	
}
