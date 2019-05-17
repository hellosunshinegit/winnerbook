package com.winnerbook.report.service.impl;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.winnerbook.base.frame.service.impl.BaseServiceImpl;
import com.winnerbook.report.dao.StudentReportDao;
import com.winnerbook.report.service.StudentReportService;

@Service("studentReportService")
public class StudentReportServiceImpl extends BaseServiceImpl implements StudentReportService{
	
	@Autowired
	private StudentReportDao studentReportDao;

	@Override
	public Map<String, Object> getMyReport(Map<String, Object> map) {
		Map<String, Object> return_map = studentReportDao.getMyReport(map);
		if(null!=return_map.get("studentSecond")){//得到的是秒钟，转换为分钟
			return_map.put("studentSecond", Integer.parseInt(new Double(return_map.get("studentSecond").toString()).intValue()/60+""));
		}
		return return_map;
	}

	@Override
	public List<Map<String, Object>> getStudentRecord7(Map<String, Object> map) {
		List<Map<String, Object>> return_map = studentReportDao.getStudentRecord7(map);
		for(Map<String, Object> map_one:return_map){
			if(null!=map_one.get("studentSecond")){
				map_one.put("studentSecond", Integer.parseInt(new Double(map_one.get("studentSecond").toString()).intValue()/60+""));//秒钟转分钟
			}
		}
		return return_map;
	}

	@Override
	public List<Map<String, Object>> getReadThought7(Map<String, Object> map) {
		return studentReportDao.getReadThought7(map);
	}

	@Override
	public List<Map<String, Object>> getCourseComment7(Map<String, Object> map) {
		return studentReportDao.getCourseComment7(map);
	}

	@Override
	public Map<String, Object> getBusRanks(Map<String, Object> map) {
		Map<String, Object> map_result = new HashMap<String, Object>();
		
		List<Map<String, Object>> busRanks = studentReportDao.getBusRanks(map);
		int busRanksCount = studentReportDao.getBusRanksCount(map);
		
		map_result.put("busRankList",busRanks);
		map_result.put("busRanksCount", busRanksCount);
		return map_result;
	}

	@Override
	public Map<String, Object> getAllBusRanks(Map<String, Object> map) {
		Map<String, Object> map_result = new HashMap<String, Object>();
		
		List<Map<String, Object>> allBusRankList = studentReportDao.getAllBusRanks(map);
		int allBusRanksCount = studentReportDao.getAllBusRanksCount(map);
		
		map_result.put("allBusRankList",allBusRankList);
		map_result.put("allBusRanksCount", allBusRanksCount);
		return map_result;
	}
	
}
