package com.winnerbook.busInfo.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

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

import com.winnerbook.base.common.PageDTO;
import com.winnerbook.base.common.util.ConstantUtils;
import com.winnerbook.base.frame.controller.BaseController;
import com.winnerbook.busInfo.dto.BusInfo;
import com.winnerbook.busInfo.dto.UserBusCourseType;
import com.winnerbook.busInfo.dto.UserBusInfo;
import com.winnerbook.busInfo.service.BusInfoService;
import com.winnerbook.busInfo.service.UserBusCourseTypeService;
import com.winnerbook.course.dto.CourseType;
import com.winnerbook.course.service.CourseTypeService;
import com.winnerbook.share.dto.Qrcode;
import com.winnerbook.system.service.UserService;

/**
 * 企业信息  企业的登录信息在用户表中，企业的详情信息在字表中 user_id为关联的标识
 * @author hxs
 */
@Controller
@RequestMapping(value="/busInfoController")
public class BusInfoController extends BaseController{
	
	@Autowired
	private BusInfoService busInfoService;
	
	@Autowired
	private CourseTypeService courseTypeService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private UserBusCourseTypeService userBusCourseTypeService;
	
	private static final Logger logger = LoggerFactory.getLogger(BusInfoController.class);
	
	//查询列表
	@RequestMapping(value="/busInfoList.html")
	public String  busInfoList(HttpServletRequest request,Model model, Integer pageIndex,Integer pageSize){
		String busName =request.getParameter("busName");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("busName", busName);
		PageDTO<UserBusInfo> pageDTO  = busInfoService.listByPage(map, pageIndex,10);//默认每页显示10条数据
 
		model.addAttribute("pageDTO", pageDTO);
		model.addAllAttributes(map);
		//如果是admin跳转list，如果是其他企业管理员，则直接跳转编辑页面
		if(getSessionUser().getIsAdmin().equals("1")){
			return "manage/busRead/bus/busInfo/busInfoList";
		}else{
			//根据登录人id查询此人的企业详情信息
			UserBusInfo userBusInfo = busInfoService.findById(getSessionUser().getUserId().toString());
			if(null==userBusInfo){
				userBusInfo = new UserBusInfo();
				userBusInfo.setUserId(getSessionUser().getUserId());
				userBusInfo.setUserUnitName(getSessionUser().getUserUnitName());
			}
			model.addAttribute("busInfo",userBusInfo);
			return "manage/busRead/bus/busInfo/editBusManageInfo";
		}
	}
	
	//点击修改
	@RequestMapping(value="updateBusInfo.html")
	public String updateBusInfo(@RequestParam String userId,ModelMap modelMap){
		//查询所有的用户信息
		//modelMap.addAttribute("busUserNameList", busInfoService.findBusUserName());
		modelMap.addAttribute("busInfo",busInfoService.findById(userId));
		return "manage/busRead/bus/busInfo/editBusInfo";
	}
	
	//修改提交
	@RequestMapping(value="updateSubmitBusInfo.html")
	public String updateSubmitBusInfo(BusInfo busInfo,HttpServletRequest request){
		busInfoService.update(busInfo,request);
		return "redirect:/busInfoController/busInfoList.html";
	}
	
	//点击查看详情
	@RequestMapping(value="viewBusInfo.html")
	public String viewBusInfo(@RequestParam String userId,ModelMap modelMap){
		modelMap.addAttribute("busInfo",busInfoService.findById(userId));
		return "manage/busRead/bus/busInfo/viewBusInfo";
	}
	
	//定制课程分类
	@RequestMapping("customCourseType.html")
	public String customCourseType(@RequestParam String userId,ModelMap modelMap){
		List<CourseType> courseTypeList = courseTypeService.getCourseTypeAll(new HashMap<String,Object>());
		modelMap.put("user", userService.findUserById(userId));
		modelMap.put("courseTypeList", courseTypeList);
		return "manage/busRead/bus/busInfo/customCourseType";
	}
	
	
	//保存用户对应的课程分类
	@RequestMapping("customCourseTypeSubmit.html")
	@ResponseBody
	public String customCourseTypeSubmit(@RequestBody String str){
		logger.info("======courseTypeIds==========="+str);
		return busInfoService.customCourseTypeSubmit(str);
	}
	
	//查询用户对应的课程分类
	@RequestMapping("getCourseType.html")
	@ResponseBody
	public List<UserBusCourseType> getCourseType(@RequestParam String userId){
		return userBusCourseTypeService.findByUserId(userId);
	}
	
	//生成手机端二维码
	@RequestMapping("getBusQrcode.html")
	@ResponseBody
	public Qrcode getBusQrcode(@RequestBody String busIdJson){
		JSONObject jsonObject = JSONObject.fromObject(busIdJson);
		Qrcode qrcode = busInfoService.getBusQrcode(jsonObject.getString("busId"));
		if(null!=qrcode){
			qrcode.setImg(ConstantUtils.BASE_PATH_URL+qrcode.getImg()); 
		}
		return qrcode;
	}

}
