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
import com.winnerbook.base.frame.service.BaseService;
import com.winnerbook.system.dao.UcSystemLogRecordDao;
import com.winnerbook.system.dao.UserDao;
import com.winnerbook.system.dto.UcSystemLogRecord;
import com.winnerbook.system.dto.User;

@Service(value="baseService")
@Transactional
public class BaseServiceImpl implements BaseService{
	
	@Autowired
	private UcSystemLogRecordDao ucSystemLogRecordDao;
	
	@Autowired
	private UserDao userDao;
	
	/**
	 * 日志记录
	 * @param type  类型  1 登录  2添加  3 更新   4 删除
	 * @param message 内容
	 */
	public void logRecord(String type,String message){
		HttpServletRequest req =((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
		UserContext userContext = (UserContext) req.getSession().getAttribute(ThreadLocalWrapper.USER_CONTEXT_KEY);
		User user = userContext.getUser();
		UcSystemLogRecord ucSystemLogRecord = new UcSystemLogRecord();
		ucSystemLogRecord.setLogDes(message);
		ucSystemLogRecord.setLogIp(req.getRemoteAddr());
		ucSystemLogRecord.setLogType(type);
		ucSystemLogRecord.setLogSource("1");//来源 pc
		if(null!=user){
			ucSystemLogRecord.setLogCreateUserId(user.getUserId());
			ucSystemLogRecord.setLogCreateUserName(user.getUserUnitName()+"("+user.getUserName()+")");
		}
		ucSystemLogRecord.setLogCreateDate(new Date());
		ucSystemLogRecordDao.insert(ucSystemLogRecord);
	}
	
	public User getSessionUser(){
		HttpServletRequest req =((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
		UserContext userContext = (UserContext) req.getSession().getAttribute(ThreadLocalWrapper.USER_CONTEXT_KEY);
		return userContext.getUser();
	}

	public User getAdmin(){
		return userDao.getAdmin().get(0);
	}
}
