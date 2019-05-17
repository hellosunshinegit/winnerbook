package com.winnerbook.busInfo.dto;

import java.util.Date;

public class UserBusCourseType {
	private Integer bctId;

	private Integer userId;

	private Integer courseTypeId;

	private Date busCreateDate;

	public Integer getBctId() {
		return bctId;
	}

	public void setBctId(Integer bctId) {
		this.bctId = bctId;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Date getBusCreateDate() {
		return busCreateDate;
	}

	public void setBusCreateDate(Date busCreateDate) {
		this.busCreateDate = busCreateDate;
	}

	public Integer getCourseTypeId() {
		return courseTypeId;
	}

	public void setCourseTypeId(Integer courseTypeId) {
		this.courseTypeId = courseTypeId;
	}

}