package com.winnerbook.course.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.winnerbook.base.common.util.FileUtils;
import com.winnerbook.base.frame.service.impl.BaseServiceImpl;
import com.winnerbook.course.dao.CourseFileDao;
import com.winnerbook.course.dto.CourseFile;
import com.winnerbook.course.service.CourseFileService;

@Service("courseFileService")
public class CourseFileServiceImpl extends BaseServiceImpl implements CourseFileService{
	
	@Autowired
	private CourseFileDao courseFileDao;

	@Override
	public List<CourseFile> findCourseFileByCourseId(Integer courseId) {
		return courseFileDao.findCourseFileByCourseId(courseId); 
	}

	@Override
	public String delete(String courseId, String fileId) {
		try {
			//删除图片
			CourseFile courseFile = findCourseFileByFileId(Integer.parseInt(fileId));
			FileUtils.delFile(courseFile.getFileUrl());
			
			Map<String, Object> parameter  = new HashMap<String, Object>();
			parameter.put("courseId", courseId);
			parameter.put("fileId", fileId);
			courseFileDao.delete(parameter);
			return "200";
		} catch (Exception e) {
			e.printStackTrace();
			return "-1";
		}
		
	}

	@Override
	public CourseFile findCourseFileByFileId(Integer fileId) {
		return courseFileDao.findCourseFileByFileId(fileId);
	}
	
	


}
