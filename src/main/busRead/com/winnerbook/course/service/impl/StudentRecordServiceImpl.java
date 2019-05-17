package com.winnerbook.course.service.impl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.winnerbook.base.common.GlobalConfigure;
import com.winnerbook.base.common.PageDTO;
import com.winnerbook.base.frame.service.impl.BaseServiceImpl;
import com.winnerbook.course.dao.CourseDao;
import com.winnerbook.course.dao.CourseFileDao;
import com.winnerbook.course.dao.StudentRecordDao;
import com.winnerbook.course.dto.Course;
import com.winnerbook.course.dto.CourseFile;
import com.winnerbook.course.dto.StudentRecord;
import com.winnerbook.course.service.StudentRecordService;
import com.winnerbook.score.dao.ScoreRecordDao;
import com.winnerbook.score.dto.ScoreRecord;
import com.winnerbook.score.service.ScoreRecordService;
import com.winnerbook.score.service.ScoreSetService;
import com.winnerbook.system.dao.UserDao;
import com.winnerbook.system.dto.User;

@Service("studentRecordService")
public class StudentRecordServiceImpl extends BaseServiceImpl implements StudentRecordService{
	
	@Autowired
	private StudentRecordDao studentRecordDao;
	
	@Autowired
	private CourseDao courseDao;
	
	@Autowired
	private CourseFileDao courseFileDao;
	
	@Autowired
	private ScoreSetService scoreSetService;
	
	@Autowired
	private ScoreRecordService scoreRecordService;
	
	@Autowired
	private ScoreRecordDao scoreRecordDao;
	
	@Autowired
	private UserDao userDao;
	
	int scoreRecordId = 0;

	@Override
	public StudentRecord findById(String recordId) {
		Map<String, Object> parameter  = new HashMap<String, Object>();
		parameter.put("recordId", Integer.parseInt(recordId));
		return studentRecordDao.findById(parameter);
	}

	@Override
	public PageDTO<StudentRecord> listByPage(Map<String, Object> parameter,Integer pageIndex, Integer pageSize) {
		PageDTO<StudentRecord> pageDTO = new PageDTO<StudentRecord>(pageIndex, pageSize);
		parameter.put(GlobalConfigure.PAGINATION_SQL_START, pageDTO.getFirst());
		parameter.put(GlobalConfigure.PAGINATION_SQL_LIMIT, pageDTO.getPageSize());
		long rowSize = studentRecordDao.selectCount(parameter);
		List<StudentRecord> data = null;
		if (rowSize > 0) {
			data = studentRecordDao.listByPage(parameter);
		}
		pageDTO.setRowSize(rowSize);
		pageDTO.setData(data);
		return pageDTO;
	}
	
	@Override
	public int insert(Map<String, Object> map) {
		String courseId = map.get("courseId").toString();
		String time = map.get("time").toString();
		String type = map.get("type").toString();
		String recordId = map.get("recordId").toString();
		String isEnd = null!=map.get("isEnd")?map.get("isEnd").toString():"0";
		String fileId = null!=map.get("fileId")?map.get("fileId").toString():"0";
		String totalTime = null!=map.get("totalTime")?map.get("totalTime").toString():"";
		
		User sessionUser = getSessionUser();
		
		int recordId_return = addStudentRecordByVideo(sessionUser, courseId, time, type, recordId, isEnd, fileId, totalTime, "1");//sourcetype 1:pc  2:h5
		
		if(StringUtils.isNotBlank(recordId)){
			logRecord("3","学习记录修改，id："+recordId_return);
		}else{
			logRecord("2","学习记录添加，id："+recordId_return);
		}
		
		return recordId_return;
	}

	@Override
	public Map<String, Object> getStudentRecords(Map<String, Object> parameter) {
		Map<String, Object> map = new HashMap<>();
		List<Map<String, Object>> studentRecordList = studentRecordDao.getStudentRecords(parameter);
		int studentRecordCount = studentRecordDao.getStudentRecordsCount(parameter);
		map.put("studentRecordList", studentRecordList);
		map.put("studentRecordCount", studentRecordCount);
		return map;
	}

	@Override
	public int addStudentRecordByVideo(User user,String courseId,String time,String type,String recordId,String isEnd,String fileId,String totalTime,String sourceType) {
		
		Map<String, Object> parameter = new HashMap<>();
		parameter.put("courseId", Integer.parseInt(courseId));
		Course course = courseDao.findById(parameter);
		String des = "";
		
		DateFormat df = new SimpleDateFormat("HH:mm:ss");
		Date nowDate = new Date();
		String nowDateStr = df.format(nowDate);
		
		int second_total = Integer.parseInt(Math.round(Double.parseDouble(time.toString()))+"");
		
		int minute_second = second_total/60;//已观看多少分钟
		
		if(StringUtils.isNotBlank(recordId)){
			Map<String, Object> parameterRecord = new HashMap<>();
			parameterRecord.put("recordId", Integer.parseInt(recordId));
			
			String timeStr = "";
			
			int hour,minute,second;
			
			hour = second_total/3600;
			minute = (second_total - hour*3600)/60;
			second = second_total-hour*300-minute*60;
			if(hour>0){
				timeStr+=hour+"小时";
			}
			if(minute>0){
				timeStr+=minute+"分";
			}
			if(second>0){
				timeStr+=second+"秒";
			}
			
			StudentRecord studentRecord = studentRecordDao.findById(parameterRecord);
			
			des="您在"+df.format(studentRecord.getCreateDate())+"开始学习 “"+course.getTitle()+ "” 课程";
			if(timeStr.length()>0){
				des+="，已观看"+timeStr;
			}
			
			
			if(StringUtils.isNotBlank(recordId)){
				if("1".equals(isEnd)){
					des = "您在"+df.format(studentRecord.getCreateDate())+"开始学习 “"+course.getTitle()+ "” 课程，"+nowDateStr+"观看完成";
				}
				studentRecord.setLookTime(second_total+"");
				studentRecord.setIsEnd(isEnd);
				studentRecord.setRecordDes(des);
				int recordId_insert = studentRecordDao.update(studentRecord);
				
				//查询课程得分
				Map<String, Object> comment_map = scoreSetService.findByKeyName("studentVideo");
				
				if(minute_second>0){
					if(recordId_insert>0 && scoreRecordId==0){
						String remarks = "观看 “ "+course.getTitle()+" ” 课程"+minute_second+"分钟";
						scoreRecordId = scoreRecordService.insertScoreRecord_source(user, studentRecord.getRecordId(), remarks, minute_second, "1", "studentVideo");
					}else{
						//更新
						ScoreRecord scoreRecord = scoreRecordDao.findById(scoreRecordId);
						scoreRecord.setScore((null!=comment_map.get("score")?Integer.parseInt(comment_map.get("score")+""):0)*minute_second);
						String remarks = "观看 “ "+course.getTitle()+" ” 课程"+minute_second+"分钟";
						scoreRecord.setRemarks(remarks);
						scoreRecord.setUpdateDate(new Date());
						scoreRecordDao.updateScoreRecord(scoreRecord);
					}
				}
			}
			return studentRecord.getRecordId();
		}else{
			scoreRecordId=0;//证明是新点击过来的，所以清空
			//根据课程id查询课程title
			des="您在"+nowDateStr+"开始学习‘"+course.getTitle()+ "’课程";
			StudentRecord studentRecord = new StudentRecord();
			studentRecord.setCourseId(Integer.parseInt(courseId));
			studentRecord.setCourseName(StringUtils.isNotBlank(course.getTitle())?course.getTitle():"");
			
			if(StringUtils.isNotBlank(fileId)){
				//查询课程附件的title
				studentRecord.setCourseFileId(StringUtils.isNotBlank(fileId)?Integer.parseInt(fileId):0);
				CourseFile courseFile = courseFileDao.findCourseFileByFileId(Integer.parseInt(fileId));
				studentRecord.setCourseFileName(StringUtils.isNotBlank(courseFile.getFileTitle())?courseFile.getFileTitle():"");
			}
			
			studentRecord.setUserId(Integer.parseInt(user.getUserId().toString()));
			studentRecord.setUserName(user.getUserUnitName());
			studentRecord.setRecordDes(des);
			studentRecord.setIsEnd(isEnd);
			studentRecord.setRecordType(type);
			studentRecord.setRecordSource(sourceType);//1:pc  2:h5
			studentRecord.setLookTime(second_total+"");//存入的值为秒钟
			studentRecord.setTotalTime(totalTime);
			studentRecord.setCreateDate(nowDate);
			studentRecord.setCreateUserId(Integer.parseInt(user.getUserId().toString()));
			studentRecord.setCreateUserName(user.getUserUnitName());
			studentRecordDao.insert(studentRecord);
			
			return studentRecord.getRecordId();
		}
	}

}
