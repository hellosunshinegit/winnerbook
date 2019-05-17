package com.winnerbook.base.frame.service.impl;

import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import com.winnerbook.base.frame.content.ThreadLocalWrapper;
import com.winnerbook.base.frame.content.UserContext;
import com.winnerbook.system.dao.UcSystemLogRecordDao;
import com.winnerbook.system.dao.UserDao;
import com.winnerbook.system.dto.UcSystemLogRecord;
import com.winnerbook.system.dto.User;
import com.winnerbook.system.service.UserService;

@Service(value="webBaseServiceImpl")
@Transactional
public class WebBaseServiceImpl{
	
	@Autowired
	private UcSystemLogRecordDao ucSystemLogRecordDao;
	
	@Autowired
	private UserDao userDao;
	
	
	@Autowired
	private UserService userService;
	
	/**
	 * 日志记录
	 * @param type  类型  1 登录  2添加  3 更新   4 删除
	 * @param message 内容
	 */
	public void logRecord(String userId,String type,String message){
		HttpServletRequest req =((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
		
		UcSystemLogRecord ucSystemLogRecord = new UcSystemLogRecord();
		ucSystemLogRecord.setLogDes(message);
		ucSystemLogRecord.setLogIp(req.getRemoteAddr());
		ucSystemLogRecord.setLogType(type);
		ucSystemLogRecord.setLogSource("2");//来源 h5
		
		//根据userId查询用户结果
		User user = userService.findUserById(userId);
		
		if(null!=user){
			ucSystemLogRecord.setLogCreateUserId(user.getUserId());
			ucSystemLogRecord.setLogCreateUserName(user.getUserUnitName()+"("+user.getUserName()+")");
		}
		ucSystemLogRecord.setLogCreateDate(new Date());
		ucSystemLogRecordDao.insert(ucSystemLogRecord);
	}
	
	public User getLoginUser(String  userId){
		User user = userService.findUserById(userId);
		return user;
	}

	public User getAdmin(){
		return userDao.getAdmin().get(0);
	}
	
	public void loginRecord(User user,HttpServletRequest request){
		//登录日志记录
		UcSystemLogRecord ucSystemLogRecord = new UcSystemLogRecord();
		ucSystemLogRecord.setLogCreateDate(new Date());
		ucSystemLogRecord.setLogCreateUserId(user.getUserId());
		ucSystemLogRecord.setLogCreateUserName(user.getUserUnitName()+"("+user.getUserName()+")");
		ucSystemLogRecord.setLogIp(request.getRemoteAddr());
		ucSystemLogRecord.setLogDes("H5-用户："+user.getUserUnitName()+"("+user.getUserName()+")"+"进行登录");
		ucSystemLogRecord.setLogType("1"); 
		ucSystemLogRecord.setLogSource("2");//1 pc  2h5
		ucSystemLogRecordDao.insert(ucSystemLogRecord);
	}
}
