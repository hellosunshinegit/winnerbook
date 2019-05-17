package com.winnerbook.report.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.winnerbook.base.frame.dao.BaseDAO;
import com.winnerbook.report.dao.StudentReportDao;

@Repository("studentReportDao")
public class StudentReportDaoImpl  extends BaseDAO implements StudentReportDao{
	private static final String STUDENTREPORT_MAPPER = "StudentReportMapper.";

	@Override
	public Map<String, Object> getMyReport(Map<String, Object> map) {
		return this.sqlSession.selectOne(STUDENTREPORT_MAPPER+"getMyReport",map);
	}

	@Override
	public List<Map<String, Object>> getStudentRecord7(Map<String, Object> map) {
		return this.sqlSession.selectList(STUDENTREPORT_MAPPER+"getStudentRecord7",map);
	}

	@Override
	public List<Map<String, Object>> getReadThought7(Map<String, Object> map) {
		return this.sqlSession.selectList(STUDENTREPORT_MAPPER+"getReadThought7",map);
	}

	@Override
	public List<Map<String, Object>> getCourseComment7(Map<String, Object> map) {
		return this.sqlSession.selectList(STUDENTREPORT_MAPPER+"getCourseComment7",map);
	}

	@Override
	public List<Map<String, Object>> getBusRanks(Map<String, Object> map) {
		return this.sqlSession.selectList(STUDENTREPORT_MAPPER+"getBusRanks",map);
	}

	@Override
	public int getBusRanksCount(Map<String, Object> map) {
		Object obj = this.sqlSession.selectOne(STUDENTREPORT_MAPPER+"getBusRanksCount",map);
		return obj == null ? 0 : Integer.parseInt(obj.toString());
	}

	@Override
	public List<Map<String, Object>> getAllBusRanks(Map<String, Object> map) {
		return this.sqlSession.selectList(STUDENTREPORT_MAPPER+"getAllBusRanks",map);
	}

	@Override
	public int getAllBusRanksCount(Map<String, Object> map) {
		Object obj = this.sqlSession.selectOne(STUDENTREPORT_MAPPER+"getAllBusRanksCount",map);
		return obj == null ? 0 : Integer.parseInt(obj.toString());
	}
	
}
