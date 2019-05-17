package com.winnerbook.course.service;

import java.util.Map;

import com.winnerbook.base.common.PageDTO;
import com.winnerbook.course.dto.StudentRecord;
import com.winnerbook.system.dto.User;

public interface StudentRecordService {

	/**
	 * 根据id查询
	 * @param parameter
	 * @return
	 */
	StudentRecord findById(String recordId);
	
	/**
	 * 分页查询
	 * @param parameter
	 * @return
	 */
	PageDTO<StudentRecord> listByPage(Map<String, Object> parameter,Integer pageIndex, Integer pageSize);
	
	/**
	 * 新增
	 */
	int insert(Map<String, Object> map);
	
	//web
	Map<String, Object> getStudentRecords(Map<String, Object> parameter);

	//时时获取观看时间记录
	int addStudentRecordByVideo(User user,String courseId,String time,String type,String recordId,String isEnd,String fileId,String totalTime,String sourceType);
	
		
}
