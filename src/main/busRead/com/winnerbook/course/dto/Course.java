package com.winnerbook.course.dto;

import java.io.Serializable;
import java.util.Date;

public class Course implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer courseId;

	private Integer courseTypeId;

	private String courseTypeName;

	private String title;

	private String wbTitle;
	
	private String wbImg;

	private String courseDesc;

	private String mainGuest;

	private String mainGuestPost;

	private String mainGuestIntroduce;

	private String mainGuestImg;

	private String mainGuestBaiduKnow;

	private String dialogGuest;

	private String dialogGuestPost;

	private String presenter;

	private Integer bookListId;

	private String recommendBook;

	private String recommendBookImg;

	private String recommendBookIntroduce;

	private String recommendReason;

	private String recommendReasonImg;

	private String recordingDate;

	private String mainVideoUrl;

	private String mainVideoFilename;

	private String mainVideoLink;

	private String mainVideoTime;

	private String mainAudioUrl;

	private String mainAudioFilename;

	private String courseType;

	private String courseStatus;

	private Date createDate;

	private Integer createUserId;

	private String createUserName;

	private Date updateDate;

	private String content;

	private String courseReleaseId;
	private String courseReleaseStatus;

	public Integer getCourseId() {
		return courseId;
	}

	public void setCourseId(Integer courseId) {
		this.courseId = courseId;
	}

	public Integer getCourseTypeId() {
		return courseTypeId;
	}

	public void setCourseTypeId(Integer courseTypeId) {
		this.courseTypeId = courseTypeId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title == null ? null : title.trim();
	}

	public String getCourseDesc() {
		return courseDesc;
	}

	public void setCourseDesc(String courseDesc) {
		this.courseDesc = courseDesc;
	}

	public String getMainGuest() {
		return mainGuest;
	}

	public void setMainGuest(String mainGuest) {
		this.mainGuest = mainGuest == null ? null : mainGuest.trim();
	}

	public String getMainGuestPost() {
		return mainGuestPost;
	}

	public void setMainGuestPost(String mainGuestPost) {
		this.mainGuestPost = mainGuestPost == null ? null : mainGuestPost
				.trim();
	}

	public String getDialogGuest() {
		return dialogGuest;
	}

	public void setDialogGuest(String dialogGuest) {
		this.dialogGuest = dialogGuest == null ? null : dialogGuest.trim();
	}

	public String getDialogGuestPost() {
		return dialogGuestPost;
	}

	public void setDialogGuestPost(String dialogGuestPost) {
		this.dialogGuestPost = dialogGuestPost == null ? null : dialogGuestPost
				.trim();
	}

	public String getPresenter() {
		return presenter;
	}

	public void setPresenter(String presenter) {
		this.presenter = presenter == null ? null : presenter.trim();
	}

	public String getRecordingDate() {
		return recordingDate;
	}

	public void setRecordingDate(String recordingDate) {
		this.recordingDate = recordingDate;
	}

	public String getMainVideoUrl() {
		return mainVideoUrl;
	}

	public void setMainVideoUrl(String mainVideoUrl) {
		this.mainVideoUrl = mainVideoUrl == null ? null : mainVideoUrl.trim();
	}

	public String getMainAudioUrl() {
		return mainAudioUrl;
	}

	public void setMainAudioUrl(String mainAudioUrl) {
		this.mainAudioUrl = mainAudioUrl == null ? null : mainAudioUrl.trim();
	}

	public String getCourseType() {
		return courseType;
	}

	public void setCourseType(String courseType) {
		this.courseType = courseType == null ? null : courseType.trim();
	}

	public String getCourseStatus() {
		return courseStatus;
	}

	public void setCourseStatus(String courseStatus) {
		this.courseStatus = courseStatus == null ? null : courseStatus.trim();
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

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content == null ? null : content.trim();
	}

	public String getMainGuestIntroduce() {
		return mainGuestIntroduce;
	}

	public void setMainGuestIntroduce(String mainGuestIntroduce) {
		this.mainGuestIntroduce = mainGuestIntroduce;
	}

	public String getRecommendBook() {
		return recommendBook;
	}

	public void setRecommendBook(String recommendBook) {
		this.recommendBook = recommendBook;
	}

	public String getRecommendBookIntroduce() {
		return recommendBookIntroduce;
	}

	public void setRecommendBookIntroduce(String recommendBookIntroduce) {
		this.recommendBookIntroduce = recommendBookIntroduce;
	}

	public String getRecommendReason() {
		return recommendReason;
	}

	public void setRecommendReason(String recommendReason) {
		this.recommendReason = recommendReason;
	}

	public String getMainGuestImg() {
		return mainGuestImg;
	}

	public void setMainGuestImg(String mainGuestImg) {
		this.mainGuestImg = mainGuestImg;
	}

	public String getRecommendBookImg() {
		return recommendBookImg;
	}

	public void setRecommendBookImg(String recommendBookImg) {
		this.recommendBookImg = recommendBookImg;
	}

	public String getRecommendReasonImg() {
		return recommendReasonImg;
	}

	public void setRecommendReasonImg(String recommendReasonImg) {
		this.recommendReasonImg = recommendReasonImg;
	}

	public String getMainVideoFilename() {
		return mainVideoFilename;
	}

	public void setMainVideoFilename(String mainVideoFilename) {
		this.mainVideoFilename = mainVideoFilename;
	}

	public String getMainAudioFilename() {
		return mainAudioFilename;
	}

	public void setMainAudioFilename(String mainAudioFilename) {
		this.mainAudioFilename = mainAudioFilename;
	}

	public String getCourseTypeName() {
		return courseTypeName;
	}

	public void setCourseTypeName(String courseTypeName) {
		this.courseTypeName = courseTypeName;
	}

	public Integer getBookListId() {
		return bookListId;
	}

	public void setBookListId(Integer bookListId) {
		this.bookListId = bookListId;
	}

	public String getMainVideoLink() {
		return mainVideoLink;
	}

	public void setMainVideoLink(String mainVideoLink) {
		this.mainVideoLink = mainVideoLink;
	}

	public String getMainVideoTime() {
		return mainVideoTime;
	}

	public void setMainVideoTime(String mainVideoTime) {
		this.mainVideoTime = mainVideoTime;
	}

	public String getCourseReleaseId() {
		return courseReleaseId;
	}

	public void setCourseReleaseId(String courseReleaseId) {
		this.courseReleaseId = courseReleaseId;
	}

	public String getCourseReleaseStatus() {
		return courseReleaseStatus;
	}

	public void setCourseReleaseStatus(String courseReleaseStatus) {
		this.courseReleaseStatus = courseReleaseStatus;
	}

	public String getMainGuestBaiduKnow() {
		return mainGuestBaiduKnow;
	}

	public void setMainGuestBaiduKnow(String mainGuestBaiduKnow) {
		this.mainGuestBaiduKnow = mainGuestBaiduKnow;
	}

	public String getWbTitle() {
		return wbTitle;
	}

	public void setWbTitle(String wbTitle) {
		this.wbTitle = wbTitle;
	}

	public String getWbImg() {
		return wbImg;
	}

	public void setWbImg(String wbImg) {
		this.wbImg = wbImg;
	}
	
	

}