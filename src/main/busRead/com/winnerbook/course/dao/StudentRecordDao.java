package com.winnerbook.course.dao;

import java.util.List;
import java.util.Map;
import com.winnerbook.course.dto.StudentRecord;

public interface StudentRecordDao {

	/**
	 * 查询总条数
	 * 
	 * @param parameter
	 * @return
	 */
	int selectCount(Map<String, Object> parameter);

	/**
	 * 分页查询
	 * 
	 * @param parameter
	 * @return
	 */
	List<StudentRecord> listByPage(Map<String, Object> parameter);

	int insert(StudentRecord record);

	StudentRecord findById(Map<String, Object> parameter);
	
	int update(StudentRecord record);
	
	//web
	List<Map<String, Object>> getStudentRecords(Map<String, Object> parameter);
	int getStudentRecordsCount(Map<String, Object> parameter);

}