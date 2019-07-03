package com.winnerbook.web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.winnerbook.base.frame.service.impl.WebBaseServiceImpl;
import com.winnerbook.course.service.StudentRecordService;
import com.winnerbook.system.dto.User;
import com.winnerbook.system.service.UserService;

@Service("studentRecordServiceWeb")
public class StudentRecordServiceWeb extends WebBaseServiceImpl{
	
	@Autowired
	private StudentRecordService studentRecordService;
	
	@Autowired
	private UserService userService;

	public int addStudentRecord(String courseId,String time,String type,String recordId,String isEnd,String userId,String fileId,String totalTime){
		//根据userId查询用户结果
		User user = userService.findUserById(userId);
		
		int recordId_return = studentRecordService.addStudentRecordByVideo(user, courseId, time, type, recordId, isEnd, fileId, totalTime,"2");
		
		/*if(StringUtils.isNotBlank(recordId)){
			logRecord(user.getUserId()+"","2","H5-学习记录修改，id："+recordId_return);
		}else{
			logRecord(user.getUserId()+"","2","H5-学习记录添加，id："+recordId_return);
		}*/
		
		return recordId_return;
	}
	
}
