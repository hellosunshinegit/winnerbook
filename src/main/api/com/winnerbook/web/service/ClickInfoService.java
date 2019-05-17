package com.winnerbook.web.service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.winnerbook.base.frame.service.impl.WebBaseServiceImpl;
import com.winnerbook.book.dao.BookListClickInfoDao;
import com.winnerbook.book.dao.BookListTypeClickInfoDao;
import com.winnerbook.book.dao.BookListTypeDao;
import com.winnerbook.book.dto.BookListClickInfo;
import com.winnerbook.book.dto.BookListType;
import com.winnerbook.book.dto.BookListTypeClickInfo;
import com.winnerbook.course.dao.BookListDao;
import com.winnerbook.course.dao.CourseDao;
import com.winnerbook.course.dao.CourseFileDao;
import com.winnerbook.course.dao.CourseVideoClickInfoDao;
import com.winnerbook.course.dao.MainGuestClickInfoDao;
import com.winnerbook.course.dto.BookList;
import com.winnerbook.course.dto.Course;
import com.winnerbook.course.dto.CourseFile;
import com.winnerbook.course.dto.CourseVideoClickInfo;
import com.winnerbook.course.dto.MainGuestClickInfo;
import com.winnerbook.system.dto.User;

@Service("clickInfoService")
public class ClickInfoService extends WebBaseServiceImpl{
	
	@Autowired
	private BookListTypeDao bookListTypeDao;
	
	@Autowired
	private BookListTypeClickInfoDao bookListTypeClickInfoDao;
	
	@Autowired
	private BookListDao bookListDao;
	@Autowired
	private BookListClickInfoDao bookListClickInfoDao;
	
	@Autowired
	private CourseDao courseDao;
	
	@Autowired
	private MainGuestClickInfoDao mainGuestClickInfoDao;
	
	@Autowired
	private CourseFileDao courseFileDao;
	
	@Autowired
	private CourseVideoClickInfoDao courseVideoClickInfoDao;
	

	DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	Date nowDate = new Date();
	
	//书单点击
	public void bookListTypeClick(String userId,String typeId,HttpServletRequest request){
		
		//根据typeId查询name
		Map<String, Object> map_book_list = new HashMap<String, Object>();
		map_book_list.put("id", Integer.parseInt(typeId));
		BookListType bookListType = bookListTypeDao.findById(map_book_list);
		
		String des = "";
		if(StringUtils.isNotBlank(userId)){
			User user = getLoginUser(userId);
			des = "‘"+user.getUserUnitName()+"’在"+df.format(nowDate)+"点击了‘"+bookListType.getTypeName()+"’";
		}else{
			des = "在"+df.format(nowDate)+"点击了书单：‘"+bookListType.getTypeName()+"’";
		}
		
		BookListTypeClickInfo bookListTypeClickInfo = new BookListTypeClickInfo();
		bookListTypeClickInfo.setUserId(StringUtils.isNotBlank(userId)?Integer.parseInt(userId):0);
		bookListTypeClickInfo.setBookListTypeId(StringUtils.isNotBlank(typeId)?Integer.parseInt(typeId):0);
		bookListTypeClickInfo.setBookListTypeName(null!=bookListType?bookListType.getTypeName():"");
		bookListTypeClickInfo.setIpAddres(request.getRemoteAddr());
		bookListTypeClickInfo.setInfoDes(des);
		bookListTypeClickInfo.setCreateDate(nowDate);
		bookListTypeClickInfoDao.insert(bookListTypeClickInfo);
	}
	
	//书籍点击
	public void bookListClick(String userId,String bookId,HttpServletRequest request){
		
		//根据typeId查询name
		Map<String, Object> map_book_list = new HashMap<String, Object>();
		map_book_list.put("bookId", Integer.parseInt(bookId));
		BookList bookList = bookListDao.findById(map_book_list);
		
		String des = "";
		if(StringUtils.isNotBlank(userId)){
			User user = getLoginUser(userId);
			des = "‘"+user.getUserUnitName()+"’在"+df.format(nowDate)+"点击了‘"+bookList.getBookName()+"’";
		}else{
			des = "在"+df.format(nowDate)+"点击了书籍：‘"+bookList.getBookName()+"’";
		}
		
		BookListClickInfo bookListClickInfo = new BookListClickInfo();
		bookListClickInfo.setUserId(StringUtils.isNotBlank(userId)?Integer.parseInt(userId):0);
		bookListClickInfo.setBookListId(StringUtils.isNotBlank(bookId)?Integer.parseInt(bookId):0);
		bookListClickInfo.setBookListName(null!=bookList?bookList.getBookName():"");
		bookListClickInfo.setIpAddres(request.getRemoteAddr());
		bookListClickInfo.setInfoDes(des);
		bookListClickInfo.setCreateDate(nowDate);
		bookListClickInfoDao.insert(bookListClickInfo);
	}
	
	
	//导师点击
	public void mainGuestClick(String userId,String courseId,HttpServletRequest request){
		//根据typeId查询name
		Map<String, Object> map_book_list = new HashMap<String, Object>();
		map_book_list.put("courseId", Integer.parseInt(courseId));
		Course course = courseDao.findById(map_book_list);
		
		String des = "";
		if(StringUtils.isNotBlank(userId)){
			User user = getLoginUser(userId);
			des = "‘"+user.getUserUnitName()+"’在"+df.format(nowDate)+"点击了导师：‘"+course.getMainGuest()+"’";
		}else{
			des = "在"+df.format(nowDate)+"点击了导师：‘"+course.getMainGuest()+"’";
		}
		
		MainGuestClickInfo mainGuestClickInfo = new MainGuestClickInfo();
		mainGuestClickInfo.setUserId(StringUtils.isNotBlank(userId)?Integer.parseInt(userId):0);
		mainGuestClickInfo.setCourseId(StringUtils.isNotBlank(courseId)?Integer.parseInt(courseId):0);
		mainGuestClickInfo.setMainGuest(null!=course?course.getMainGuest():"");
		mainGuestClickInfo.setIpAddres(request.getRemoteAddr());
		mainGuestClickInfo.setInfoDes(des);
		mainGuestClickInfo.setCreateDate(nowDate);
		mainGuestClickInfoDao.insert(mainGuestClickInfo);
	}
	
	//视频播放点击
	public void videoClick(String userId,String courseId,String courseFileId,String fileType,HttpServletRequest request){
		//根据typeId查询name
		Map<String, Object> map_book_list = new HashMap<String, Object>();
		map_book_list.put("courseId", Integer.parseInt(courseId));
		Course course = courseDao.findById(map_book_list);
		
		String des = "";
		if(StringUtils.isNotBlank(userId)){
			User user = getLoginUser(userId);
			des = "‘"+user.getUserUnitName()+"’在"+df.format(nowDate)+"点击了课程：‘"+course.getTitle()+"’";
		}else{
			des = "在"+df.format(nowDate)+"点击了课程：‘"+course.getTitle()+"’";
		}
		
		if(StringUtils.isNotBlank(courseFileId)){
			CourseFile courseFile = courseFileDao.findCourseFileByFileId(Integer.parseInt(courseFileId));
			des+="，小视频标题："+courseFile.getFileTitle();
		}
		
		CourseVideoClickInfo clickInfo = new CourseVideoClickInfo();
		clickInfo.setUserId(StringUtils.isNotBlank(userId)?Integer.parseInt(userId):0);
		clickInfo.setCourseId(StringUtils.isNotBlank(courseId)?Integer.parseInt(courseId):0);
		clickInfo.setCourseFileId(StringUtils.isNotBlank(courseFileId)?Integer.parseInt(courseFileId):0);
		clickInfo.setFileType(fileType);
		clickInfo.setIpAddres(request.getRemoteAddr());
		clickInfo.setInfoDes(des);
		clickInfo.setCreateDate(nowDate);
		courseVideoClickInfoDao.insert(clickInfo);
	}
	

}
