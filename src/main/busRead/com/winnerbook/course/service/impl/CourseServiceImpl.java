package com.winnerbook.course.service.impl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.winnerbook.base.common.GlobalConfigure;
import com.winnerbook.base.common.PageDTO;
import com.winnerbook.base.frame.service.impl.BaseServiceImpl;
import com.winnerbook.busInfo.dao.BusPayUserDao;
import com.winnerbook.course.dao.BookListDao;
import com.winnerbook.course.dao.CourseClickInfoDao;
import com.winnerbook.course.dao.CourseDao;
import com.winnerbook.course.dao.CourseFileDao;
import com.winnerbook.course.dao.CourseTypeDao;
import com.winnerbook.course.dao.StudentRecordDao;
import com.winnerbook.course.dto.BookList;
import com.winnerbook.course.dto.Course;
import com.winnerbook.course.dto.CourseClickInfo;
import com.winnerbook.course.dto.CourseFile;
import com.winnerbook.course.dto.CourseType;
import com.winnerbook.course.dto.CourseTypeId;
import com.winnerbook.course.dto.StudentRecord;
import com.winnerbook.course.service.CourseService;
import com.winnerbook.system.dao.UserDao;
import com.winnerbook.system.dto.User;
import com.winnerbook.system.service.UserService;

@Service("courseService")
public class CourseServiceImpl extends BaseServiceImpl implements CourseService{
	
	@Autowired
	private CourseDao courseDao;
	
	@Autowired
	private CourseTypeDao courseTypeDao;
	
	@Autowired
	private CourseFileDao courseFileDao;
	
	@Autowired
	private StudentRecordDao studentRecordDao;
	
	@Autowired
	private BookListDao bookListDao;
	
	@Autowired
	private CourseClickInfoDao clickInfoDao;
	
	@Autowired
	private	UserService userService;
	
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private BusPayUserDao busPayUserDao;

	@Override
	public Course findById(String courseId) {
		Map<String, Object> parameter  = new HashMap<String, Object>();
		parameter.put("courseId", Integer.parseInt(courseId));
		Course course = courseDao.findById(parameter);
		return course;
	}

	@Override
	public PageDTO<Course> listByPage(Map<String, Object> parameter,Integer pageIndex, Integer pageSize) {
		PageDTO<Course> pageDTO = new PageDTO<Course>(pageIndex, pageSize);
		parameter.put(GlobalConfigure.PAGINATION_SQL_START, pageDTO.getFirst());
		parameter.put(GlobalConfigure.PAGINATION_SQL_LIMIT, pageDTO.getPageSize());
		
		//根据当前登录人查询此登录人推送的课程
		Map<String,Object> sessionMap = new HashMap<>();
		sessionMap.put("sessonUserId", getSessionUser().getUserId());
		List<Map<String,Object>> courseReleaseList = courseDao.getCourseRelease(sessionMap);
		Map<String,Object> courseReleaseMap = new HashMap<>();
		for(Map<String,Object> map:courseReleaseList){
			courseReleaseMap.put(map.get("courseId").toString(), map.get("courseReleaseId")+"_"+map.get("courseReleaseStatus"));
		}
		
		
		long rowSize = courseDao.selectCount(parameter);
		List<Course> data = null;
		if (rowSize > 0) {
			data = courseDao.listByPage(parameter);
			for(Course course:data){
				if(null!=courseReleaseMap.get(course.getCourseId().toString())){
					String[] str = courseReleaseMap.get(course.getCourseId().toString()).toString().split("_");
					course.setCourseReleaseId(str[0]);
					course.setCourseReleaseStatus(str[1]);
				}else{
					course.setCourseReleaseId("0");
					course.setCourseReleaseStatus("0");
				}
			}
		}
		
		pageDTO.setRowSize(rowSize);
		pageDTO.setData(data);
		return pageDTO;
	}
	
	@Override
	public void insert(Course course) {
		
		if(null==course.getCourseSort()){
			course.setCourseSort(0);
		}
		
		User userone = getSessionUser();
		course.setCourseStatus("1");//启用
		course.setCreateDate(new Date());
		course.setCreateUserId(Integer.parseInt(userone.getUserId().toString()));
		course.setCreateUserName(userone.getUserUnitName());
		//通过推荐书目的id得到name
		if(null!=course.getBookListId() && StringUtils.isNotBlank(course.getBookListId().toString())){
			Map<String,Object> map = new HashMap<>();
			map.put("bookId", course.getBookListId());
			BookList bookList = bookListDao.findById(map);
			course.setRecommendBook(StringUtils.isNotBlank(bookList.getBookName())?bookList.getBookName():"");
		}
		//设置课程类型name
		if(StringUtils.isNotBlank(course.getCourseTypeIds())){
			String typeName = "";
			//查询课程类型，并且返回对应的name
			HashMap<String, Object> map_type = new HashMap<>();
			map_type.put("typeIds", course.getCourseTypeIds());
			List<Map<String, Object>> map_type_name = courseTypeDao.getCourseTypeInfoByIds(map_type);
			for(int i = 0;i<map_type_name.size();i++){
				if(i!=0){
					typeName+=",";
				}
				typeName += map_type_name.get(i).get("typeName");
			}
			course.setCourseTypeName(typeName);
		}
		
		courseDao.insert(course);
		
		//课程分类  课程包insert
		if(StringUtils.isNotBlank(course.getCourseTypeIds())){
			String courseTypeIds = course.getCourseTypeIds();
			String[] courseTypeIdArray = courseTypeIds.split(",");
			
			List<CourseTypeId> courseTypeIdsArray = new LinkedList<>();
			for(String courseTypeId:courseTypeIdArray){
				CourseTypeId courseTypeId_pojo = new CourseTypeId();
				courseTypeId_pojo.setCourseId(course.getCourseId());
				courseTypeId_pojo.setCourseTypeId(Integer.parseInt(courseTypeId));
				courseTypeIdsArray.add(courseTypeId_pojo);
			}
			courseDao.insertCourseTypeId(courseTypeIdsArray);
		}
		
		logRecord("2","课程信息添加，id："+course.getTitle());
	}

	@Override
	public void update(Course course) {
		course.setUpdateDate(new Date());
		//通过推荐书目的id得到name
		if(null!=course.getBookListId() &&  StringUtils.isNotBlank(course.getBookListId().toString())){
			Map<String,Object> map = new HashMap<>();
			map.put("bookId", course.getBookListId());
			BookList bookList = bookListDao.findById(map);
			if(null!=bookList){
				course.setRecommendBook(StringUtils.isNotBlank(bookList.getBookName())?bookList.getBookName():"");
			}
		}
		//设置课程类型name
		if(StringUtils.isNotBlank(course.getCourseTypeIds())){
			String typeName = "";
			//查询课程类型，并且返回对应的name
			HashMap<String, Object> map_type = new HashMap<>();
			map_type.put("typeIds", course.getCourseTypeIds());
			List<Map<String, Object>> map_type_name = courseTypeDao.getCourseTypeInfoByIds(map_type);
			for(int i = 0;i<map_type_name.size();i++){
				if(i!=0){
					typeName+=",";
				}
				typeName += map_type_name.get(i).get("typeName");
			}
			course.setCourseTypeName(typeName);
		}
		
		courseDao.update(course);
		
		//课程分类  课程包insert
		if(StringUtils.isNotBlank(course.getCourseTypeIds())){
			//先删除，后insert
			courseDao.deleteCourseTypeId(course.getCourseId());
			
			String courseTypeIds = course.getCourseTypeIds();
			String[] courseTypeIdArray = courseTypeIds.split(",");
			
			List<CourseTypeId> courseTypeIdsArray = new LinkedList<>();
			for(String courseTypeId:courseTypeIdArray){
				CourseTypeId courseTypeId_pojo = new CourseTypeId();
				courseTypeId_pojo.setCourseId(course.getCourseId());
				courseTypeId_pojo.setCourseTypeId(Integer.parseInt(courseTypeId));
				courseTypeId_pojo.setCreateDate(new Date());
				courseTypeIdsArray.add(courseTypeId_pojo);
			}
			courseDao.insertCourseTypeId(courseTypeIdsArray);
		}
		
		logRecord("3","课程信息更新，id："+course.getCourseId());
	}

	@Override
	public int courseUploadFileSubmit(String courseFileJson) {
		try {
			JSONObject object = JSONObject.fromObject(courseFileJson);
			String courseId = StringUtils.isNotBlank(object.getString("courseId"))?object.getString("courseId"):"0";
			
			List<CourseFile> listCourseFiles = new LinkedList<>();
		
			JSONArray videoArray = object.getJSONArray("videoList");
			if(null!=videoArray && videoArray.size()>0){
				for(int i = 0;i<videoArray.size();i++){
					JSONObject jsonObject = (JSONObject) videoArray.get(i);
					listCourseFiles.add(getCourseFile(courseId,jsonObject));
				}
			}
			
			JSONArray audioArray = object.getJSONArray("audioList");
			if(null!=audioArray && audioArray.size()>0){
				for(int i = 0;i<audioArray.size();i++){
					JSONObject jsonObject = (JSONObject) audioArray.get(i);
					listCourseFiles.add(getCourseFile(courseId,jsonObject));
				}
			}
			
			JSONArray fileArray = object.getJSONArray("fileList");
			if(null!=fileArray && fileArray.size()>0){
				for(int i = 0;i<fileArray.size();i++){
					JSONObject jsonObject = (JSONObject) fileArray.get(i);
					listCourseFiles.add(getCourseFile(courseId,jsonObject));
				}
			}
			
			Map<String, Object> map = new HashMap<>();
			map.put("courseId", courseId);
			map.put("courseFileList", listCourseFiles);
			courseFileDao.insert(map);
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
	}
	
	public CourseFile getCourseFile(String courseId,JSONObject jsonObject){
		User session = getSessionUser();
		CourseFile courseFile = new CourseFile();
		courseFile.setCourseId(Integer.parseInt(courseId));
		courseFile.setFileTitle(StringUtils.isNotBlank(jsonObject.getString("fileTitle"))?jsonObject.getString("fileTitle"):"");
		courseFile.setFileName(StringUtils.isNotBlank(jsonObject.getString("fileName"))?jsonObject.getString("fileName"):"");
		courseFile.setFileUrl(StringUtils.isNotBlank(jsonObject.getString("fileUrl"))?jsonObject.getString("fileUrl"):"");
		courseFile.setFileType(StringUtils.isNotBlank(jsonObject.getString("fileType"))?jsonObject.getString("fileType"):"0");
		courseFile.setCreateDate(new Date());
		courseFile.setCreateUserId(Integer.parseInt(session.getUserId().toString()));
		courseFile.setCreateUserName(session.getUserUnitName());
		return courseFile;
	}

	
	public void studentCourse(String courseId){
		User user = getSessionUser();
		
		StudentRecord studentRecord = new StudentRecord();
		studentRecord.setCourseId(Integer.parseInt(courseId));
		studentRecord.setUserId(Integer.parseInt(user.getUserId().toString()));
		studentRecord.setCreateDate(new Date());
		studentRecord.setCreateUserId(Integer.parseInt(user.getUserId().toString()));
		studentRecord.setCreateUserName(user.getUserUnitName());
		studentRecord.setRecordDes("开始学习");
		
		studentRecordDao.insert(studentRecord);
	}

	@Override
	public List<Course> listByPage(Map<String, Object> parameter) {
		return courseDao.listByPage(parameter);
	}

	@Override
	public void updateCourseChannel(String courseId, String restatus,String courseReleaseId) {
		User user = getSessionUser();
		Map<String, Object> parameter  = new HashMap<String, Object>();
		parameter.put("courseId", courseId);
		parameter.put("userId", user.getUserId());
		if(StringUtils.isNotBlank(courseReleaseId) && !courseReleaseId.equals("0")){
			//更新
			parameter.put("courseReleaseId", courseReleaseId);
			parameter.put("status", restatus);
			courseDao.updateCourseRelease(parameter);
		}else{
			parameter.put("status", "1");
			parameter.put("createDate", new Date());
			parameter.put("createUserId", user.getUserId());
			parameter.put("createUserName", user.getUserUnitName());
			
			courseDao.insertCourseRelease(parameter);
		}
	}

	@Override
	public Map<String, Object> getCourses(Map<String, Object> parameter) {
		Map<String, Object> map = new HashMap<>();
		List<Map<String, Object>> courseList = new ArrayList<>();
		int courseCount = 0;
		//查询课程类型是免费还是付费
		String courseTypeId = parameter.get("courseTypeId")+"";
		Map<String, Object> freeCourseType = courseTypeDao.getFreeCouresType();
		if(courseTypeId.equals(freeCourseType.get("typeId")+"")){
			courseList = courseDao.getFreeCourses(parameter);
			courseCount = courseDao.getFreeCoursesCount(parameter);
		}else{
			courseList = courseDao.getCourses(parameter);
			courseCount = courseDao.getCoursesCount(parameter);
		}
		
		map.put("courseList", courseList);
		map.put("courseCount", courseCount);
		return map;
	}

	@Override
	public Map<String, Object> getMainGuests(Map<String, Object> parameter) {
		Map<String, Object> map = new HashMap<>();
		List<Map<String, Object>> mainGuests = courseDao.getMainGuests(parameter);
		int mainGuestCount = courseDao.getMainGuestsCount(parameter);
		map.put("mainGuestsList", mainGuests);
		map.put("mainGuestsCount", mainGuestCount);
		return map;
	}
	
	@Override
	public Map<String, Object> getVideos(Map<String, Object> parameter) {
		Map<String, Object> map = new HashMap<>();
		List<Map<String, Object>> videoList =  courseDao.getVideos(parameter);
		int videoCount = courseDao.getVideosCount(parameter);
		map.put("videoList", videoList);
		map.put("videoCount", videoCount);
		return map;
	}

	@Override
	public Map<String, Object> getCourseDetail(Map<String, Object> parameter) {
		Map<String, Object> map = new HashMap<>();
		
		Map<String,Object> couresMap = courseDao.getCourseDetail(parameter);
		
		//判断此课程是否在购买的课程包中，如果不在，则在点击观看主视频的时候，提示需要购买才可以看  courseTypeIds
		if(StringUtils.isNotBlank(parameter.get("busId")+"")){
			String busId = parameter.get("busId")+"";
			
			String courseTypeIds = couresMap.get("courseTypeIds")+"";
			
			//首先判断是不是免费课程，如果是免费课程，直接是0，可以直接观看
			Map<String, Object> freeCourseType = courseTypeDao.getFreeCouresType();
			if(null!=freeCourseType.get("typeId") && courseTypeIds.equals(freeCourseType.get("typeId")+"")){
				couresMap.put("isBuy", "0");//免费课程，可以直接看
			}else{
				//必须要登录
				if(StringUtils.isNotBlank(parameter.get("userId")+"")){
					//查询企业id对应购买的课程
					Map<String, Object> map_courseType = new HashMap<>();
					map_courseType.put("busId", busId);
					List<Map<String, Object>> courseTypeList = courseTypeDao.findBusCourseType(map_courseType);
					Map<String, Object> bus_courseType = new HashMap<>();
					for(Map<String, Object> courseType:courseTypeList){
						bus_courseType.put(courseType.get("courseTypeId")+"",courseType.get("courseTypeId")+"");
					}
					int isLook = 0;
					if(StringUtils.isNotBlank(courseTypeIds)){
						String[] courseTypeIdsArray = courseTypeIds.split(",");
						for(int j = 0;j<courseTypeIdsArray.length;j++){ //比较该企业是否买了这个课程对应的课程包
							if(null!=bus_courseType.get(courseTypeIdsArray[j]+"")){
								isLook++;
							}
						}
					}
					
					//查询该企业对应的登录用户是否是该企业的用户，如果不是，则不可以查看
					Map<String, Object> userMap = userDao.isBelongBus(parameter);
					if(null!=userMap && isLook>0){//证明企业购买了课程（isLook>0） 并且登录的人也是此企业的人
						//判断该客户是否在企业员工授权观看视频权限之中
						HashMap<String, Object> map_userPay = new HashMap<>();
						map_userPay.put("busId", busId);
						map_userPay.put("userId", parameter.get("userId")+"");
						List<Map<String, Object>> user_pay_list = busPayUserDao.getUserPay(map_userPay);
						if(user_pay_list.size()>0){
							couresMap.put("isBuy", "0");//已购买
						}else{
							couresMap.put("isBuy", "1");//已购买
						}
					}else{
						couresMap.put("isBuy", "1");//未购买
					}
				}
			}
		}
		
		map.put("courseInfo", couresMap);
		map.put("courseFile", courseDao.getCourseFile(parameter));
		return map;
	}
	@Override
	public int updateClickNum(Map<String, Object> parameter) {
		String courseId = null!=parameter.get("courseId")?parameter.get("courseId").toString():"";
		String userId = null!=parameter.get("userId")?parameter.get("userId").toString():"";
		Date date = new Date();
		
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		//查询课程名称
		Course course = findById(courseId);
		String des = "";
		
		CourseClickInfo clickInfo = new CourseClickInfo();
		if(StringUtils.isNotBlank(userId)){
			User user = userService.findUserById(userId);
			des+="‘"+user.getUserUnitName()+"’";
			clickInfo.setUserId(Integer.parseInt(userId)); 
			clickInfo.setCreateUserId(Integer.parseInt(userId));
			clickInfo.setCreateUserName(user.getUserUnitName());
		}
		
		des+="在"+df.format(date)+"点击了‘"+course.getTitle()+"’课程";
		//记录点击次数详情
		clickInfo.setCourseId(Integer.parseInt(parameter.get("courseId").toString())); 
		clickInfo.setInfoDes(des);
		clickInfo.setCreateDate(date);
		
		HttpServletRequest req =((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
		clickInfo.setIpAddres(req.getRemoteAddr());
		
		clickInfoDao.insert(clickInfo);
		
		//更新课程点击次数
		return courseDao.updateClickNum(parameter);
	}

	@Override
	public Map<String, Object> getAdminCourses(Map<String, Object> parameter) {
		Map<String, Object> map = new HashMap<>();
		List<Map<String, Object>> courseAdminList = courseDao.getAdminCourses(parameter);
		int courseCount = courseDao.getAdminCoursesCount(parameter);
		//需要过滤已经买过的视频，如果未买过的视频，则需要标记
		//根据busId查询
		/*Map<String, Object> course_map = new HashMap<>();
		course_map.put("courseTypeId", parameter.get("courseTypeId"));
		course_map.put("busId", parameter.get("busId"));
 		List<Map<String, Object>> busCourseList = courseDao.getCourses(course_map);
		Map<Object, Object> busCourseMap = new HashMap<>();
		for(Map<String, Object> busCourse:busCourseList){
			busCourseMap.put(busCourse.get("courseId"), busCourse.get("courseId"));
		}
		
		for(Map<String, Object> adminCourse:courseAdminList){
			if(null!=busCourseMap.get(adminCourse.get("courseId"))){//证明是买过的课程
				adminCourse.put("isBuy", "0");
			}else{
				adminCourse.put("isBuy", "1");//需要买的课程
			}
		}*/
		
		List<CourseType> courseTypes = courseTypeDao.getCourseTypeAdmin(parameter);
		Map<Object, Object> courseTypeMap = new HashMap<>();
		for(CourseType courseType:courseTypes){
			courseTypeMap.put(courseType.getTypeId(), courseType.getTypeName());
		}
		if(null!=courseTypeMap.get(Integer.parseInt(parameter.get("courseTypeId")+""))){
			map.put("courseTypeName", courseTypeMap.get(Integer.parseInt(parameter.get("courseTypeId")+"")));
		}
		
		map.put("courseAdminList", courseAdminList);
		map.put("courseAdminCount", courseCount);
		return map;
	}
}
