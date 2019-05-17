package com.winnerbook.course.dao.impl;

import org.springframework.stereotype.Repository;
import com.winnerbook.base.frame.dao.BaseDAO;
import com.winnerbook.course.dao.CourseClickInfoDao;
import com.winnerbook.course.dto.CourseClickInfo;

@Repository("courseClickInfoDao")
public class CourseClickInfoDaoImpl  extends BaseDAO implements CourseClickInfoDao{
	private static final String COURSE_MAPPER = "CourseClickInfoMapper.";
	
	@Override
	public int insert(CourseClickInfo courseClickInfo) {
		return this.sqlSession.insert(COURSE_MAPPER+"insert",courseClickInfo);
	}
	
}
