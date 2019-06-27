package com.winnerbook.activity.dto;

import java.util.Date;

/*企业调查报告*/
public class BusLookInfo {
	private Integer id;

	private String busName;

	private String publicNum;

	private String homepageUrl;

	private String empNum;

	private String companyAddress;

	private String companyIndustry;

	private String bookListName;

	private String courseList;

	private String readBookPlan;

	private String mustReadNum;

	private String selectReadNum;

	private String isReadThought;

	private String isReadClud;

	private String longReadClud;

	private String isShareWb;

	private String isReadBook;

	private String isWriteBook;

	private String isShareBook;

	private String telphone;

	private String isUseBookYun;

	private String status;

	private String statusReason;

	private Date createDate;

	private Date updateDate;
	
	private Date successDate;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getPublicNum() {
		return publicNum;
	}

	public void setPublicNum(String publicNum) {
		this.publicNum = publicNum == null ? null : publicNum.trim();
	}

	public String getHomepageUrl() {
		return homepageUrl;
	}

	public void setHomepageUrl(String homepageUrl) {
		this.homepageUrl = homepageUrl == null ? null : homepageUrl.trim();
	}

	public String getEmpNum() {
		return empNum;
	}

	public void setEmpNum(String empNum) {
		this.empNum = empNum == null ? null : empNum.trim();
	}

	public String getCompanyAddress() {
		return companyAddress;
	}

	public void setCompanyAddress(String companyAddress) {
		this.companyAddress = companyAddress;
	}

	public String getCompanyIndustry() {
		return companyIndustry;
	}

	public void setCompanyIndustry(String companyIndustry) {
		this.companyIndustry = companyIndustry == null ? null : companyIndustry
				.trim();
	}

	public String getBookListName() {
		return bookListName;
	}

	public void setBookListName(String bookListName) {
		this.bookListName = bookListName == null ? null : bookListName.trim();
	}

	public String getCourseList() {
		return courseList;
	}

	public void setCourseList(String courseList) {
		this.courseList = courseList == null ? null : courseList.trim();
	}

	public String getReadBookPlan() {
		return readBookPlan;
	}

	public void setReadBookPlan(String readBookPlan) {
		this.readBookPlan = readBookPlan == null ? null : readBookPlan.trim();
	}

	public String getMustReadNum() {
		return mustReadNum;
	}

	public void setMustReadNum(String mustReadNum) {
		this.mustReadNum = mustReadNum == null ? null : mustReadNum.trim();
	}

	public String getSelectReadNum() {
		return selectReadNum;
	}

	public void setSelectReadNum(String selectReadNum) {
		this.selectReadNum = selectReadNum == null ? null : selectReadNum
				.trim();
	}

	public String getIsReadThought() {
		return isReadThought;
	}

	public void setIsReadThought(String isReadThought) {
		this.isReadThought = isReadThought == null ? null : isReadThought
				.trim();
	}

	public String getIsReadClud() {
		return isReadClud;
	}

	public void setIsReadClud(String isReadClud) {
		this.isReadClud = isReadClud == null ? null : isReadClud.trim();
	}

	public String getLongReadClud() {
		return longReadClud;
	}

	public void setLongReadClud(String longReadClud) {
		this.longReadClud = longReadClud == null ? null : longReadClud.trim();
	}

	public String getIsShareWb() {
		return isShareWb;
	}

	public void setIsShareWb(String isShareWb) {
		this.isShareWb = isShareWb == null ? null : isShareWb.trim();
	}

	public String getIsReadBook() {
		return isReadBook;
	}

	public void setIsReadBook(String isReadBook) {
		this.isReadBook = isReadBook == null ? null : isReadBook.trim();
	}

	public String getIsWriteBook() {
		return isWriteBook;
	}

	public void setIsWriteBook(String isWriteBook) {
		this.isWriteBook = isWriteBook == null ? null : isWriteBook.trim();
	}

	public String getIsShareBook() {
		return isShareBook;
	}

	public void setIsShareBook(String isShareBook) {
		this.isShareBook = isShareBook == null ? null : isShareBook.trim();
	}

	public String getTelphone() {
		return telphone;
	}

	public void setTelphone(String telphone) {
		this.telphone = telphone == null ? null : telphone.trim();
	}

	public String getIsUseBookYun() {
		return isUseBookYun;
	}

	public void setIsUseBookYun(String isUseBookYun) {
		this.isUseBookYun = isUseBookYun == null ? null : isUseBookYun.trim();
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status == null ? null : status.trim();
	}

	public String getStatusReason() {
		return statusReason;
	}

	public void setStatusReason(String statusReason) {
		this.statusReason = statusReason == null ? null : statusReason.trim();
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public String getBusName() {
		return busName;
	}

	public void setBusName(String busName) {
		this.busName = busName;
	}

	public Date getSuccessDate() {
		return successDate;
	}

	public void setSuccessDate(Date successDate) {
		this.successDate = successDate;
	}

	
}