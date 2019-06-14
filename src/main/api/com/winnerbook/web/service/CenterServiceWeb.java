package com.winnerbook.web.service;

import java.util.Date;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.winnerbook.base.frame.service.impl.WebBaseServiceImpl;
import com.winnerbook.course.dao.BookListDao;
import com.winnerbook.course.dao.CourseDao;
import com.winnerbook.course.dao.ReadThoughtDao;
import com.winnerbook.course.dto.ReadThought;
import com.winnerbook.course.service.BookListService;
import com.winnerbook.course.service.ReadThoughtService;
import com.winnerbook.course.service.impl.ReadThoughtServiceImpl;
import com.winnerbook.score.service.ScoreRecordService;
import com.winnerbook.system.dto.User;
import com.winnerbook.system.dto.UserApplyBusAdmin;
import com.winnerbook.system.service.UserApplyBusAdminService;

@Service("centerServiceWeb")
public class CenterServiceWeb extends WebBaseServiceImpl{
	
	@Autowired
	private UserApplyBusAdminService userApplyBusAdminService;
	
	@Autowired
	private ReadThoughtDao readThoughtDao;
	
	@Autowired
	private ScoreRecordService scoreRecordService;
	
	@Autowired
	private BookListDao bookListDao;
	
	@Autowired
	private BookListService bookListService;
	
	@Autowired
	private CourseDao courseDao;
	
	
	public String addApplyRecord(String userId,String busId,String applyBusName,String applyBusDes){
		
		User user = getLoginUser(userId);
		
		UserApplyBusAdmin userApplyBusAdmin = new UserApplyBusAdmin();
		userApplyBusAdmin.setUserId(Integer.parseInt(userId));
		userApplyBusAdmin.setUserName(user.getUserUnitName());
		userApplyBusAdmin.setBusId(Integer.parseInt(busId));
		userApplyBusAdmin.setApplyBusName(applyBusName);
		userApplyBusAdmin.setApplyBusDes(applyBusDes);
		userApplyBusAdmin.setStatus("0");
		userApplyBusAdmin.setCreateDate(new Date());
		
		userApplyBusAdminService.insert(userApplyBusAdmin);
		
		logRecord(userId, "2", "用户id："+user.getUserId()+",用户名称："+user.getUserUnitName()+"申请成为企业管理员");
		
		return userApplyBusAdmin.getUaId()+"";
	}
	
	//添加读后感
	public int insertReadComment(Map<String, Object> param){
		
		String userId = null!=param.get("userId")?param.get("userId")+"":"";
		User user = getLoginUser(userId);
		
		ReadThought readThought = new ReadThought();
		readThought.setBookListName(null!=param.get("bookName")?param.get("bookName")+"":"");
		readThought.setThoughtDes(null!=param.get("thought")?param.get("thought")+"":"");
		readThought.setIsOpen(null!=param.get("isOpen")?param.get("isOpen")+"":"");
		
		readThought = new ReadThoughtServiceImpl().getReadThought(readThought, readThought.getBookListName(),bookListDao,bookListService,courseDao);
		
		readThought.setCreateDate(new Date());
		readThought.setCreateUserId(Integer.parseInt(user.getUserId().toString()));
		readThought.setCreateUserName(user.getUserUnitName());
		readThought.setUserId(Integer.parseInt(user.getUserId().toString()));
		
		int readThougthId = readThoughtDao.insert(readThought);
		if(readThougthId>0){
			String remarks = StringUtils.isNotBlank(readThought.getThoughtTitle())?readThought.getThoughtTitle():"《"+readThought.getBookListName()+"》";
			scoreRecordService.insertScoreRecord_source(user, readThought.getThoughtId(), "发表读后感："+remarks, 1, "2", "readThought");
		}
		
		return readThougthId;
	}
	
}
