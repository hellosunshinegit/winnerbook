package com.winnerbook.report.dao;

import java.util.List;
import java.util.Map;

public interface StudentReportDao {
	
	//我的报告
    Map<String, Object> getMyReport(Map<String, Object> map);
    
    //最近7天学习时长
    List<Map<String, Object>> getStudentRecord7(Map<String, Object> map); 
    
    //最近7天读后感
    List<Map<String, Object>> getReadThought7(Map<String, Object> map); 
    
    //最近7天的评论
    List<Map<String, Object>> getCourseComment7(Map<String, Object> map); 
    
    //获取企业里的个人排名
    List<Map<String, Object>> getBusRanks(Map<String, Object> map);
    int getBusRanksCount(Map<String, Object> map);
    
    //获取所有企业的排名
    List<Map<String, Object>> getAllBusRanks(Map<String, Object> map);
    int getAllBusRanksCount(Map<String, Object> map);
}