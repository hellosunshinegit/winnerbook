package com.winnerbook.course.dto;

import java.util.Date;

public class ReadThought {
	private Integer thoughtId;

	private Integer userId;

	private String courseId;

	private String courseName;

	private String thoughtUrl;

	private String thoughtFilename;

	private Integer bookListId;

	private String bookListName;

	private String isOpen;

	private Date createDate;

	private Integer createUserId;

	private String createUserName;

	private String thoughtDes;

	private Date updateDate;

	private String thoughtTitle;

	public Integer getThoughtId() {
		return thoughtId;
	}

	public void setThoughtId(Integer thoughtId) {
		this.thoughtId = thoughtId;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getCourseId() {
		return courseId;
	}

	public void setCourseId(String courseId) {
		this.courseId = courseId;
	}

	public String getThoughtUrl() {
		return thoughtUrl;
	}

	public void setThoughtUrl(String thoughtUrl) {
		this.thoughtUrl = thoughtUrl == null ? null : thoughtUrl.trim();
	}

	public Date getCreateDate() {
		return createDate;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
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

	public String getThoughtDes() {
		return thoughtDes;
	}

	public void setThoughtDes(String thoughtDes) {
		this.thoughtDes = thoughtDes == null ? null : thoughtDes.trim();
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public String getThoughtFilename() {
		return thoughtFilename;
	}

	public void setThoughtFilename(String thoughtFilename) {
		this.thoughtFilename = thoughtFilename;
	}

	public String getIsOpen() {
		return isOpen;
	}

	public void setIsOpen(String isOpen) {
		this.isOpen = isOpen;
	}

	public Integer getBookListId() {
		return bookListId;
	}

	public void setBookListId(Integer bookListId) {
		this.bookListId = bookListId;
	}

	public String getBookListName() {
		return bookListName;
	}

	public void setBookListName(String bookListName) {
		this.bookListName = bookListName;
	}

	public String getThoughtTitle() {
		return thoughtTitle;
	}

	public void setThoughtTitle(String thoughtTitle) {
		this.thoughtTitle = thoughtTitle;
	}

}