package com.winnerbook.course.dto;

import java.util.Date;

public class StudentRecord {
	private Integer recordId;

	private Integer userId;

	private String userName;

	private Integer courseId;

	private String courseName;

	private Integer courseFileId;

	private String courseFileName;

	private String recordDes;

	private String isEnd;

	private String recordType;

	private String lookTime;
	
	private String recordSource;

	private String totalTime;

	private Date createDate;

	private Integer createUserId;

	private String createUserName;

	private Date updateDate;

	public Integer getRecordId() {
		return recordId;
	}

	public void setRecordId(Integer recordId) {
		this.recordId = recordId;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getCourseId() {
		return courseId;
	}

	public void setCourseId(Integer courseId) {
		this.courseId = courseId;
	}

	public String getRecordDes() {
		return recordDes;
	}

	public void setRecordDes(String recordDes) {
		this.recordDes = recordDes == null ? null : recordDes.trim();
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Integer getCreateUserId() {
		return createUserId;
	}

	public void setCreateUserId(Integer createUserId) {
		this.createUserId = createUserId;
	}

	public String getCreateUserName() {
		return createUserName;
	}

	public void setCreateUserName(String createUserName) {
		this.createUserName = createUserName == null ? null : createUserName
				.trim();
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public String getIsEnd() {
		return isEnd;
	}

	public void setIsEnd(String isEnd) {
		this.isEnd = isEnd;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public String getRecordType() {
		return recordType;
	}

	public void setRecordType(String recordType) {
		this.recordType = recordType;
	}

	public Integer getCourseFileId() {
		return courseFileId;
	}

	public void setCourseFileId(Integer courseFileId) {
		this.courseFileId = courseFileId;
	}

	public String getCourseFileName() {
		return courseFileName;
	}

	public void setCourseFileName(String courseFileName) {
		this.courseFileName = courseFileName;
	}

	public String getRecordSource() {
		return recordSource;
	}

	public void setRecordSource(String recordSource) {
		this.recordSource = recordSource;
	}

	public String getTotalTime() {
		return totalTime;
	}

	public void setTotalTime(String totalTime) {
		this.totalTime = totalTime;
	}

	public String getLookTime() {
		return lookTime;
	}

	public void setLookTime(String lookTime) {
		this.lookTime = lookTime;
	}
	
	

}