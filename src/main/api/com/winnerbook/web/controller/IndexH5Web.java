package com.winnerbook.web.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.winnerbook.activity.service.ReadBookClubService;
import com.winnerbook.base.common.JSONResponse;
import com.winnerbook.book.dto.BookTypeLabel;
import com.winnerbook.book.service.BookListTypeService;
import com.winnerbook.book.service.BookTypeLabelService;
import com.winnerbook.course.service.BookListService;
import com.winnerbook.course.service.CourseService;
import com.winnerbook.course.service.CourseTypeService;
import com.winnerbook.course.service.ReadThoughtService;
import com.winnerbook.web.service.BannerService;
import com.winnerbook.web.service.BlockService;
import com.winnerbook.web.service.NewsService;
import com.winnerbook.web.utils.ConstantWebUtils;
import com.winnerbook.web.utils.PageUtil;
import com.winnerbook.web.utils.ValidateWebUtils;

//查询banner图
@Controller
public class IndexH5Web {
			
	@Autowired
	private BannerService bannerService;
	
	@Autowired
	private BlockService blockService;
	
	@Autowired
	private CourseService courseService;
	
	@Autowired
	private BookListService bookListService;
	
	@Autowired
	private ReadThoughtService readThoughtService;
	
	@Autowired
	private NewsService newsService;
	
	@Autowired
	private ReadBookClubService readBookClubService;
	
	@Autowired
	private BookTypeLabelService bookTypeLabelService;
	
	@Autowired
	private BookListTypeService bookListTypeService;
	
	@Autowired
	private CourseTypeService courseTypeService;
	
	//首页进入
	@RequestMapping(value="index_h5.jhtml",produces = {"application/json;charset=utf-8"})
	@ResponseBody
	public String index_h5(String busId,String pageIndex,@RequestParam("callback") String callback){//busId即为企业用户id(userId)  type=1h5  2:pc
		busId = ValidateWebUtils.defaultBus(busId);
		JSONResponse result = new JSONResponse();
		Map<String,Object> resultData = new HashMap<>();
		//查询banner图
		Map<String, Object> parameter  = new HashMap<String, Object>();
		parameter.put("bannerType",ConstantWebUtils.sourceType);
		parameter.put("busId",busId);
		List<Map<String,Object>> bannerList = bannerService.getBannerList(parameter);
		resultData.put("bannerList", bannerList);
		
		//最新课程...
		/*Map<String, Object> parameter_course  = new HashMap<String, Object>();
		Map<String, Object> course = courseService.getCourses(PageUtil.getParam(parameter_course, busId, pageIndex));
		resultData.put("course", course);*/
		
		result.setSuccess(true);
		result.setMsg("获取banenr图成功");
		result.setData(resultData);
		return callback+"("+JSONObject.fromObject(result)+")";
	}
	
	//查询该企业下的课程包和免费课程包
	@RequestMapping(value="getCourseTypes.jhtml",produces = {"application/json;charset=utf-8"})
	@ResponseBody
	public String getCourseTypes(String busId,@RequestParam("callback") String callback){
		JSONResponse result = new JSONResponse();
		busId = ValidateWebUtils.defaultBus(busId);
		List<Map<String, Object>> courseTypeList = courseTypeService.getCourseTypes(busId);
		
		result.setSuccess(true);
		result.setMsg("获取课程类型成功");
		result.setData(courseTypeList);
		return callback+"("+JSONObject.fromObject(result)+")";
	}
	
	//点击精选课程
	@RequestMapping(value="getCourses.jhtml",produces = {"application/json;charset=utf-8"})
	@ResponseBody
	public String getCourses(String busId,String courseTypeId,String pageIndex,@RequestParam("callback") String callback){
		JSONResponse result = new JSONResponse();
		/*if(!StringUtils.isNotBlank(courseTypeId)){
			result.setMsg("课程类型为必输项");
			return callback+"("+JSONObject.fromObject(result)+")";
		}*/
		busId = ValidateWebUtils.defaultBus(busId);
		//最新课程...
		Map<String, Object> parameter_course  = new HashMap<String, Object>();
		parameter_course.put("courseTypeId", courseTypeId);
		Map<String, Object> courseList = courseService.getCourses(PageUtil.getParam(parameter_course, busId, pageIndex));
		
		result.setSuccess(true);
		result.setMsg("获取课程表成功");
		result.setData(courseList);
		return callback+"("+JSONObject.fromObject(result)+")";
	}
	
	
	//导师列表
	@RequestMapping(value="getMainGuests.jhtml",produces = {"application/json;charset=utf-8"})
	@ResponseBody
	public String getMainGuests(String busId,String pageIndex,@RequestParam("callback") String callback){
		busId = ValidateWebUtils.defaultBus(busId);
		JSONResponse result = new JSONResponse();
		//最新课程...
		Map<String, Object> parameter_course  = new HashMap<String, Object>();
		parameter_course.put("busId",busId);
		Map<String, Object> courseList = courseService.getMainGuests(PageUtil.getParam(parameter_course, busId, pageIndex));
		
		result.setSuccess(true);
		result.setMsg("获取主讲人成功");
		result.setData(courseList);
		return callback+"("+JSONObject.fromObject(result)+")";
	}
	
	//导师的列表的详细表
	@RequestMapping(value="getMainGuestsName.jhtml",produces = {"application/json;charset=utf-8"})
	@ResponseBody
	public String getMainGuestsName(String busId,String mainGuest,String pageIndex,@RequestParam("callback") String callback){
		busId = ValidateWebUtils.defaultBus(busId);
		JSONResponse result = new JSONResponse();
		//最新课程...
		Map<String, Object> parameter_course  = new HashMap<String, Object>();
		parameter_course.put("mainGuest",mainGuest);
		Map<String, Object> courseList = courseService.getMainGuestsName(PageUtil.getParam(parameter_course, busId, pageIndex));
		
		result.setSuccess(true);
		result.setMsg("获取主讲人成功");
		result.setData(courseList);
		return callback+"("+JSONObject.fromObject(result)+")";
	}
	
	//书单首页
	@RequestMapping(value="getBooks.jhtml",produces = {"application/json;charset=utf-8"})
	@ResponseBody
	public String getBooks(String busId,String userId,String pageIndex,@RequestParam("callback") String callback){
		busId = ValidateWebUtils.defaultBus(busId);
		JSONResponse result = new JSONResponse();
		/*Map<String, Object> parameter  = new HashMap<String, Object>();
		parameter.put("userId", userId);
		Map<String, Object> bookList = bookListService.getBooks(PageUtil.getParam(parameter, busId, pageIndex));*/
		
		//获取标签和书单列表
		List<BookTypeLabel> labelList = bookTypeLabelService.getBookTypeLabelAll(null);
		
		Map<String, Object> parameter_data  = new HashMap<String, Object>();
		/*parameter_data.put("bookList", bookList);*/
		parameter_data.put("labelList", labelList);
		
		result.setSuccess(true);
		result.setMsg("获取书单信息成功");
		result.setData(parameter_data);
		return callback+"("+JSONObject.fromObject(result)+")";
	}
	
	//我的书单分页
	@RequestMapping(value="getBusBooks.jhtml",produces = {"application/json;charset=utf-8"})
	@ResponseBody
	public String getBusBooks(String busId,String userId,String pageIndex,@RequestParam("callback") String callback){
		busId = ValidateWebUtils.defaultBus(busId);
		JSONResponse result = new JSONResponse();
		Map<String, Object> parameter  = new HashMap<String, Object>();
		parameter.put("userId", userId);
		Map<String, Object> bookList = bookListService.getBooks(PageUtil.getParam(parameter, busId, pageIndex));
		
		result.setSuccess(true);
		result.setMsg("获取企业书单信息成功");
		result.setData(bookList);
		return callback+"("+JSONObject.fromObject(result)+")";
	}
	
	//视频清单
	@RequestMapping(value="getVideos.jhtml",produces = {"application/json;charset=utf-8"})
	@ResponseBody
	public String getVideos(String busId,String pageIndex,@RequestParam("callback") String callback){
		busId = ValidateWebUtils.defaultBus(busId);
		JSONResponse result = new JSONResponse();
		Map<String, Object> parameter  = new HashMap<String, Object>();
		//根据bus查询
		Map<String, Object> videoList = courseService.getVideos(PageUtil.getParam(parameter, busId, pageIndex));
		
		result.setSuccess(true);
		result.setMsg("获取小视频列表成功");
		result.setData(videoList);
		return callback+"("+JSONObject.fromObject(result)+")";
	}
	
	//读后感   企业风采  读书会活动
	@RequestMapping(value="getList.jhtml",produces = {"application/json;charset=utf-8"})
	@ResponseBody
	public String getList(@RequestParam String list_type,String busId,String pageIndex,@RequestParam("callback") String callback){
		busId = ValidateWebUtils.defaultBus(busId);
		JSONResponse result = new JSONResponse();
		Map<String, Object> parameter  = new HashMap<String, Object>();
		parameter.put("busId",busId);
		
		Map<String, Object> map = new HashMap<>();
		
		//map中的数据转换为统一的name和title
		if("1".equals(list_type)){//读后感
			map = readThoughtService.getReadThoughts(PageUtil.getParam(parameter, busId, pageIndex));
		}else if("2".equals(list_type)){//企业风采
			map = newsService.getNewsList(PageUtil.getParam(parameter, busId, pageIndex));
		}else if("3".equals(list_type)){//读书会活动
			map = readBookClubService.getBookClubs(PageUtil.getParam(parameter, busId, pageIndex));
		}
		
		result.setSuccess(true);
		result.setMsg("获取列表成功");
		result.setData(map);
		return callback+"("+JSONObject.fromObject(result)+")";
	}
	
	//点击更多以后，展示的model block块
	@RequestMapping(value="getBlocks.jhtml",produces = {"application/json;charset=utf-8"})
	@ResponseBody
	public String getBlocks(String busId,@RequestParam("callback") String callback){
		JSONResponse result = new JSONResponse();
		busId = ValidateWebUtils.defaultBus(busId);
		
		Map<String, Object> resultData  = new HashMap<String, Object>();//返回数据
		
		Map<String, Object> parameter  = new HashMap<String, Object>();
		parameter.put("busId","1");//查看的是admin的数据
		parameter.put("blockType",ConstantWebUtils.sourceType);
		//根据bus查询
		List<Map<String, Object>> blockList = blockService.getBlocks(parameter);
		resultData.put("blockList", blockList);
		
		//首页banner图
		Map<String, Object> parameter_banner  = new HashMap<String, Object>();
		parameter_banner.put("bannerType",ConstantWebUtils.sourceType);
		parameter_banner.put("busId",busId);
		List<Map<String,Object>> bannerList = bannerService.getBannerList(parameter_banner);
		resultData.put("bannerList", bannerList);
		
		result.setSuccess(true);
		result.setMsg("获取模块列表成功");
		result.setData(resultData);
		return callback+"("+JSONObject.fromObject(result)+")";
	}
	
	
	//点击详情
	//读后感   企业风采  读书会活动
	@RequestMapping(value="getDetail.jhtml",produces = {"application/json;charset=utf-8"})
	@ResponseBody
	public String getDetail(@RequestParam String list_type,String id,String busId,String pageIndex,@RequestParam("callback") String callback){
		busId = ValidateWebUtils.defaultBus(busId);
		JSONResponse result = new JSONResponse();
		Map<String, Object> parameter  = new HashMap<String, Object>();
		parameter.put("busId",busId);
		parameter.put("id",id);
		
		Map<String, Object> map = new HashMap<>();
		
		//map中的数据转换为统一的name和title
		if("1".equals(list_type)){//读后感
			map = readThoughtService.getReadThoughtsDetail(parameter);
		}else if("2".equals(list_type)){//企业风采
			map = newsService.getNewsDetail(parameter);
		}else if("3".equals(list_type)){//读书会活动
			map = readBookClubService.getReadBookClubDetail(parameter);
		}
		
		result.setSuccess(true);
		result.setMsg("获取详情成功");
		result.setData(map);
		return callback+"("+JSONObject.fromObject(result)+")";
	}
	
}
