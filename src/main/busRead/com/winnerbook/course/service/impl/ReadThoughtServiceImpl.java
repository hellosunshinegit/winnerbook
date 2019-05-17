package com.winnerbook.course.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.winnerbook.base.common.GlobalConfigure;
import com.winnerbook.base.common.PageDTO;
import com.winnerbook.base.frame.service.impl.BaseServiceImpl;
import com.winnerbook.book.service.impl.BookListTypeServiceImpl;
import com.winnerbook.course.dao.BookListDao;
import com.winnerbook.course.dao.CourseDao;
import com.winnerbook.course.dao.ReadThoughtDao;
import com.winnerbook.course.dto.BookList;
import com.winnerbook.course.dto.Course;
import com.winnerbook.course.dto.ReadThought;
import com.winnerbook.course.service.BookListService;
import com.winnerbook.course.service.ReadThoughtService;
import com.winnerbook.score.dao.ScoreRecordDao;
import com.winnerbook.score.dao.ScoreSetDao;
import com.winnerbook.score.dto.ScoreRecord;
import com.winnerbook.score.service.ScoreRecordService;
import com.winnerbook.score.service.ScoreSetService;
import com.winnerbook.system.dto.User;

@Service("readThoughtService")
public class ReadThoughtServiceImpl extends BaseServiceImpl implements ReadThoughtService{
	
	@Autowired
	private ReadThoughtDao readThoughtDao;
	
	@Autowired
	private CourseDao courseDao;
	
	@Autowired
	private BookListDao bookListDao;
	
	@Autowired
	private BookListService bookListService;
	
	@Autowired
	private ScoreSetService scoreSetService;
	
	@Autowired
	private ScoreRecordService scoreRecordService;

	@Override
	public ReadThought findById(String thoughtId) {
		Map<String, Object> parameter  = new HashMap<String, Object>();
		parameter.put("thoughtId", Integer.parseInt(thoughtId));
		return readThoughtDao.findById(parameter);
	}

	@Override
	public PageDTO<ReadThought> listByPage(Map<String, Object> parameter,Integer pageIndex, Integer pageSize) {
		PageDTO<ReadThought> pageDTO = new PageDTO<ReadThought>(pageIndex, pageSize);
		parameter.put(GlobalConfigure.PAGINATION_SQL_START, pageDTO.getFirst());
		parameter.put(GlobalConfigure.PAGINATION_SQL_LIMIT, pageDTO.getPageSize());
		long rowSize = readThoughtDao.selectCount(parameter);
		List<ReadThought> data = null;
		if (rowSize > 0) {
			data = readThoughtDao.listByPage(parameter);
		}
		pageDTO.setRowSize(rowSize);
		pageDTO.setData(data);
		return pageDTO;
	}
	
	@Override
	public void insert(ReadThought readThought) {
				
		readThought = getReadThought(readThought, readThought.getBookListName());
		
		User sessionUser = getSessionUser();
		readThought.setCreateDate(new Date());
		readThought.setCreateUserId(Integer.parseInt(sessionUser.getUserId().toString()));
		readThought.setCreateUserName(sessionUser.getUserUnitName());
		readThought.setUserId(Integer.parseInt(sessionUser.getUserId().toString()));
		int readThougthId = readThoughtDao.insert(readThought);
		if(readThougthId>0){
			String remarks = StringUtils.isNotBlank(readThought.getThoughtTitle())?readThought.getThoughtTitle():"《"+readThought.getBookListName()+"》";
			scoreRecordService.insertScoreRecord_source(sessionUser, readThought.getThoughtId(), "发表读后感："+remarks, 1, "2", "readThought");
		}
		
		logRecord("2","读后感信息添加，id："+readThought.getThoughtId());
	}

	@Override
	public void update(ReadThought readThought) {
		ReadThought readThought2 = findById(readThought.getThoughtId().toString());
		readThought2.setThoughtDes(readThought.getThoughtDes());
		readThought2.setThoughtUrl(readThought.getThoughtUrl());
		readThought2.setThoughtFilename(readThought.getThoughtFilename());
		readThought2.setIsOpen(readThought.getIsOpen());
		readThought2.setThoughtTitle(readThought.getThoughtTitle());
		readThought2.setUpdateDate(new Date());
		
		readThought2 = getReadThought(readThought2,readThought.getBookListName());
		
		readThoughtDao.update(readThought2);
		logRecord("3","读后感信息更新，id："+readThought2.getThoughtId());
	}
	
	
	public ReadThought getReadThought(ReadThought readThought2,String bookName){
		//根据书籍名称查询对应的书籍id，如果不存在，去爬
		Map<String, Object> parameter  = new HashMap<String, Object>();
		parameter.put("bookName", bookName);
		List<BookList> bookLists = bookListDao.getBookListByName(parameter);
		if(bookLists.size()>0){
			readThought2.setBookListId(bookLists.get(0).getBookId());
			readThought2.setBookListName(bookLists.get(0).getBookName());
		}else{
			//去爬虫结果，然后insert到书籍表中
			Integer bookId = bookListService.searchBookNameInsert(bookName);
			readThought2.setBookListId(bookId);
			readThought2.setBookListName(bookName);
		}
		
		//查询书籍是否有对应的课程，如果有，则存储
		Map<String,Object> map_course = new HashMap<>();
		map_course.put("bookListId", readThought2.getBookListId());
		List<Map<String, Object>> courseListMap = courseDao.getCourseByBookListId(map_course);
		String courseIdStr = "";
		String courseNameStr = "";
		if(null!=courseListMap && courseListMap.size()>0){
			for(int i = 0;i<courseListMap.size();i++){
				if(i!=0){
					courseIdStr+=",";
					courseNameStr+=",";
				}
				courseIdStr+=null!=courseListMap.get(i).get("courseId")?courseListMap.get(i).get("courseId"):"";
				courseNameStr+=null!=courseListMap.get(i).get("title")?courseListMap.get(i).get("title"):"";
			}
		}
		readThought2.setCourseId(courseIdStr);
		readThought2.setCourseName(courseNameStr);
		return readThought2;
	}

	@Override
	public void deleteReadThought(String thoughtId) {
		Map<String, Object> parameter  = new HashMap<String, Object>();
		parameter.put("thoughtId", thoughtId);
		readThoughtDao.deleteReadThought(parameter);
		logRecord("4","读后感信息删除，id："+thoughtId);
	}

	@Override
	public Map<String, Object> getReadThoughts(Map<String, Object> parameter) {
		Map<String, Object> map = new HashMap<>();
		List<Map<String, Object>> readThoughtList = readThoughtDao.getReadThoughts(parameter);
		int readThoughtCount = readThoughtDao.getReadThoughtsCount(parameter);
		map.put("list", readThoughtList);
		map.put("count", readThoughtCount);
		return map;
	}

	@Override
	public Map<String, Object> getReadThoughtsDetail(
			Map<String, Object> parameter) {
		return readThoughtDao.getReadThoughtsDetail(parameter);
	}

	@Override
	public Map<String, Object> getReadThoughtUser(
			Map<String, Object> parameter) {
		Map<String, Object> map = new HashMap<>();
		List<Map<String, Object>> readThoughtList = readThoughtDao.getReadThoughtUser(parameter);
		int readThoughtCount = readThoughtDao.getReadThoughtUserCount(parameter);
		map.put("readThoughtList", readThoughtList);
		map.put("readThoughtCount", readThoughtCount);
		return map;
	}

}
