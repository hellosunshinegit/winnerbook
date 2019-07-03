package com.winnerbook.web.service;
import java.util.Date;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.winnerbook.base.frame.service.impl.WebBaseServiceImpl;
import com.winnerbook.course.dao.CourseCommentDao;
import com.winnerbook.course.dao.CourseDao;
import com.winnerbook.course.dto.Course;
import com.winnerbook.course.dto.CourseComment;
import com.winnerbook.course.service.CourseService;
import com.winnerbook.score.dao.ScoreRecordDao;
import com.winnerbook.score.dao.ScoreSetDao;
import com.winnerbook.score.dto.ScoreRecord;
import com.winnerbook.score.service.ScoreRecordService;
import com.winnerbook.score.service.ScoreSetService;
import com.winnerbook.system.dto.User;
import com.winnerbook.system.service.UserService;

@Service("courseCommentServiceWeb")
public class CourseCommentServiceWeb extends WebBaseServiceImpl{
	
	@Autowired
	private CourseCommentDao courseCommentDao;
	
	@Autowired
	private ScoreSetService scoreSetService;
	
	@Autowired
	private ScoreRecordService scoreRecordService;
	
	@Autowired
	private CourseService courseService;
	
	@Autowired
	private UserService userService;
	
	public int insertComment(Map<String,Object> map){
		
		String userId = null!=map.get("userId")?map.get("userId").toString():"";
		User user = getLoginUser(userId);
		
		Integer courseId = null!=map.get("courseId")?Integer.parseInt(map.get("courseId")+""):0;
		
		Integer userId_integer = null!=map.get("userId")?Integer.parseInt(map.get("userId")+""):0;
		
		CourseComment courseComment = new CourseComment();
		courseComment.setCourseId(courseId);
		courseComment.setUserId(userId_integer);
		courseComment.setComment(null!=map.get("comment")?map.get("comment").toString():"");
		courseComment.setStatus("1");
		courseComment.setCreateDate(new Date());
		courseComment.setCreateUserName(user.getUserUnitName());
		courseComment.setCreateUserId(Integer.parseInt(userId));
		
		int commentId = courseCommentDao.insertCourseComment(courseComment);
		if(commentId>0){
			//根据课程id查询课程名称
			Course course = courseService.findById(courseId+"");
			String  remarks = "评论了 “"+course.getTitle()+"” 课程";
			scoreRecordService.insertScoreRecord_source(user, courseComment.getId(), remarks, 1, "3", "comment");
		}
		
		return commentId;
		
	}
}
