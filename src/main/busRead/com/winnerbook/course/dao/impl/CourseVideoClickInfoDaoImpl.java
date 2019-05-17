package com.winnerbook.course.dao.impl;

import org.springframework.stereotype.Repository;
import com.winnerbook.base.frame.dao.BaseDAO;
import com.winnerbook.course.dao.CourseVideoClickInfoDao;
import com.winnerbook.course.dto.CourseVideoClickInfo;

@Repository("courseVideoClickInfoDao")
public class CourseVideoClickInfoDaoImpl  extends BaseDAO implements CourseVideoClickInfoDao{
	private static final String COURSEVIDEOCLICKINFO_MAPPER = "CourseVideoClickInfoMapper.";
	
	@Override
	public int insert(CourseVideoClickInfo courseVideoClickInfo) {
		return this.sqlSession.insert(COURSEVIDEOCLICKINFO_MAPPER+"insert",courseVideoClickInfo);
	}

}
