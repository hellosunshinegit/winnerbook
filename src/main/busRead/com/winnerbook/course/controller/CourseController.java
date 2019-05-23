package com.winnerbook.course.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

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
import com.winnerbook.base.common.util.FileUtils;
import com.winnerbook.base.frame.controller.BaseController;
import com.winnerbook.course.dto.Course;
import com.winnerbook.course.dto.CourseFile;
import com.winnerbook.course.service.BookListService;
import com.winnerbook.course.service.CourseFileService;
import com.winnerbook.course.service.CourseService;
import com.winnerbook.course.service.CourseTypeService;
import com.winnerbook.wx.service.WxInfoService;

/**
 * 课程
 * @author hxs
 */
@Controller
@RequestMapping(value="/courseController")
public class CourseController extends BaseController{
	
	@Autowired
	private CourseService courseService;
	
	@Autowired
	private CourseTypeService courseTypeService;
	
	@Autowired
	private CourseFileService courseFileService;
	
	@Autowired
	private BookListService bookListService;
	
	@Autowired
	private WxInfoService wxInfoService;
	
	private static final Logger logger = LoggerFactory.getLogger(CourseController.class);
	
	//查询列表
	@RequestMapping(value="/courseList.html")
	public String busInfoList(HttpServletRequest request,Model model, Integer pageIndex,Integer pageSize){
		String title =request.getParameter("title");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("title", title);
		//如果不是admin和企业管理员，则是企业用户，那么需要查询企业用户对应的企业管理员id
		
		map.put("sessionUser",getSessionUser());
		PageDTO<Course> pageDTO  = courseService.listByPage(map, pageIndex,10);//默认每页显示10条数据
 
		model.addAttribute("pageDTO", pageDTO);
		//获取微博appid
		map.put("wxInfo", wxInfoService.getWxInfo("2"));
		model.addAllAttributes(map);
		return "manage/busRead/course/course/courseList";
	}
	
	//点击添加跳转
	@RequestMapping(value="addCourse.html")
	public String addCourse(ModelMap modelMap){
		//读取课程类型
		HashMap<String,Object> map = new HashMap<>();
		map.put("sessionUser",getSessionUser());
		modelMap.addAttribute("courseTypeList",courseTypeService.getCourseTypeAll(map));
		//查询书单
		modelMap.addAttribute("bookList",bookListService.getBookListAll(map));
		return "manage/busRead/course/course/editCourse";
	}
	
	//添加提交
	@RequestMapping(value="addSubmitCourse.html")
	public String addSubmitCourse(Course course){
		courseService.insert(course);
		return "redirect:/courseController/courseList.html";
	}
	
	//点击修改
	@RequestMapping(value="updateCourse.html")
	public String updateCourse(@RequestParam String courseId,ModelMap modelMap){
		modelMap.addAttribute("course",courseService.findById(courseId));
		HashMap<String,Object> map = new HashMap<>();
		map.put("sessionUser",getSessionUser());
		modelMap.addAttribute("courseTypeList",courseTypeService.getCourseTypeAll(map));
		//查询书单
		modelMap.addAttribute("bookList",bookListService.getBookListAll(map));
		return "manage/busRead/course/course/editCourse";
	}
	
	//修改提交
	@RequestMapping(value="updateSubmitCourse.html")
	public String updateSubmitCourse(Course course,HttpServletRequest request){
		courseService.update(course);
		return "redirect:/courseController/courseList.html";
	}
	
	//点击上传资料
	@RequestMapping(value="courseUploadFile.html")
	public String courseUploadFile(@RequestParam String courseId,ModelMap modelMap){
		modelMap.put("courseId", courseId);
		//查询已经上传的数据
		modelMap.put("courseFileList", courseFileService.findCourseFileByCourseId(Integer.parseInt(courseId)));
		return "manage/busRead/course/course/courseUploadFile";
	}
	
	//上传资料提交
	@RequestMapping("courseUploadFileSubmit.html")
	@ResponseBody
	public String courseUploadFileSubmit(@RequestBody String courseFileJson){
		logger.info("===courseFileJson======="+courseFileJson);
		int result = courseService.courseUploadFileSubmit(courseFileJson);
		if(result==1){
			return "200";
		}else{
			return "-1";
		}
	}
	
	@RequestMapping(value="deleteFile.html")
	@ResponseBody
	public String deleteFile(@RequestParam String courseId,@RequestParam String fileId){
		 return courseFileService.delete(courseId, fileId);
	}
	
	//查看详情
	@RequestMapping(value="viewCourse.html")
	public String viewCourse(@RequestParam String courseId,ModelMap modelMap){
		modelMap.addAttribute("course",courseService.findById(courseId));
		HashMap<String,Object> map = new HashMap<>();
		map.put("sessionUser",getSessionUser());
		modelMap.addAttribute("courseTypeList",courseTypeService.getCourseTypeAll(map));
		modelMap.put("courseFileList", courseFileService.findCourseFileByCourseId(Integer.parseInt(courseId)));
		return "manage/busRead/course/course/viewCourse";
	}
	
	//修改状态
	@RequestMapping("updateCourseStatus.html")
	public String updateCourseStatus(@RequestParam String courseId,@RequestParam String coursStatus){
		Course course = courseService.findById(courseId);
		course.setCourseStatus(coursStatus);
		courseService.update(course);
		return "redirect:/courseController/courseList.html";
	}
	
	//开始学习
	@RequestMapping("studentCourse.html")
	public String studentCourse(@RequestParam String courseId,ModelMap modelMap){
		modelMap.addAttribute("course",courseService.findById(courseId));
		HashMap<String,Object> map = new HashMap<>();
		map.put("sessionUser",getSessionUser());
		modelMap.addAttribute("courseTypeList",courseTypeService.getCourseTypeAll(map));
		List<CourseFile> courseFiles = courseFileService.findCourseFileByCourseId(Integer.parseInt(courseId));
		modelMap.put("courseFileList",courseFiles);
		modelMap.put("jsonCourseFiles", JSONArray.fromObject(courseFiles));
		return "manage/busRead/course/course/studentCourse";
	}
	
	//写读后感
	@RequestMapping("addReadThought.html")
	public String addReadThought(@RequestParam String courseId,ModelMap modelMap){
		modelMap.put("courseId", courseId);
		return "manage/busRead/course/readThought/editReadThought";
	}
	
	//点击下载上传附件的文件
	@RequestMapping("downLoadCourseFile.html")
	public void downLoadReadThought(@RequestParam String fileId,HttpServletResponse response){
		CourseFile courseFile = courseFileService.findCourseFileByFileId(Integer.parseInt(fileId));
		FileUtils.downloadFilename(FileUtils.getRealtyPathName(courseFile.getFileUrl()), courseFile.getFileName(), response);
	}
	
	//是否推送到h5
	@RequestMapping("updateCourseChannel.html")
	public String updateCourseChannel(@RequestParam String courseId,@RequestParam String courseReleaseStatus,String courseReleaseId){
		courseService.updateCourseChannel(courseId, courseReleaseStatus,courseReleaseId);
		return "redirect:/courseController/courseList.html";
	}
	
}
