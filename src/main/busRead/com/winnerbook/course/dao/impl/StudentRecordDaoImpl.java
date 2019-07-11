package com.winnerbook.course.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.winnerbook.base.frame.dao.BaseDAO;
import com.winnerbook.course.dao.StudentRecordDao;
import com.winnerbook.course.dto.StudentRecord;

@Repository("studentRecordDao")
public class StudentRecordDaoImpl  extends BaseDAO implements StudentRecordDao{
	private static final String STUDENTRECORD_MAPPER = "StudentRecordMapper.";
	
	@Override
	public int selectCount(Map<String, Object> parameter) {
		Object obj = this.sqlSession.selectOne(STUDENTRECORD_MAPPER + "selectCount", parameter);
		return obj == null ? 0 : Integer.parseInt(obj.toString());
	}

	@Override
	public List<StudentRecord> listByPage(Map<String, Object> parameter) {
		return this.sqlSession.selectList(STUDENTRECORD_MAPPER + "listByPage", parameter);
	}

	@Override
	public StudentRecord findById(Map<String, Object> parameter) {
		return this.sqlSession.selectOne(STUDENTRECORD_MAPPER+"findById",parameter);
	}

	@Override
	public int insert(StudentRecord studentRecord) {
		return this.sqlSession.insert(STUDENTRECORD_MAPPER+"insert",studentRecord);
	}

	@Override
	public int update(StudentRecord record) {
		return this.sqlSession.update(STUDENTRECORD_MAPPER+"update",record);
	}

	@Override
	public List<Map<String, Object>> getStudentRecords(
			Map<String, Object> parameter) {
		return this.sqlSession.selectList(STUDENTRECORD_MAPPER+"getStudentRecords",parameter);
	}

	@Override
	public int getStudentRecordsCount(Map<String, Object> parameter) {
		Object obj = this.sqlSession.selectOne(STUDENTRECORD_MAPPER + "getStudentRecordsCount", parameter);
		return obj == null ? 0 : Integer.parseInt(obj.toString());
	}

	@Override
	public List<StudentRecord> getRecordByUserId(String userId) {
		return this.sqlSession.selectList(STUDENTRECORD_MAPPER+"getRecordByUserId",userId);
	}

	@Override
	public int updateRecordById(Map<String, Object> parameter) {
		return this.sqlSession.update(STUDENTRECORD_MAPPER+"updateRecordById", parameter);
	}


}
