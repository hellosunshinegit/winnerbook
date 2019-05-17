package com.winnerbook.course.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.winnerbook.base.frame.dao.BaseDAO;
import com.winnerbook.course.dao.CourseFileDao;
import com.winnerbook.course.dto.CourseFile;

@Repository("courseFileDao")
public class CourseFileDaoImpl  extends BaseDAO implements CourseFileDao{
	private static final String COURSEFILE_MAPPER = "CourseFileMapper.";
	
	@Override
	public List<CourseFile> findCourseFileByCourseId(Integer courseId) {
		return this.sqlSession.selectList(COURSEFILE_MAPPER + "findCourseFileByCourseId", courseId);
	}

	@Override
	public int insert(Map<String, Object> parameter) {
		return this.sqlSession.insert(COURSEFILE_MAPPER+"insert",parameter);
	}

	@Override
	public void delete(Map<String, Object> parameter) {
		this.sqlSession.delete(COURSEFILE_MAPPER+"deleteByFileId",parameter);
	}

	@Override
	public CourseFile findCourseFileByFileId(Integer fileId) {
		return this.sqlSession.selectOne(COURSEFILE_MAPPER + "findCourseFileByFileId", fileId);
	}

}
