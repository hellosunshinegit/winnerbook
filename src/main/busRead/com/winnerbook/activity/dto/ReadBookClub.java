package com.winnerbook.activity.dto;

import java.util.Date;

public class ReadBookClub {
	private Integer clubId;

	private String clubTitle;

	private String wbTitle;

	private String wbImg;

	private String clubImg;

	private String clubDate;

	private String clubPlace;

	private String clubMainGuest;

	private String clubMainGuestBook;

	private String clubMainGuestIntroduce;

	private String clubPresenter;

	private String clubStatus;

	private String clubDes;

	private Date createDate;

	private Integer createUserId;

	private String createUserName;

	private Date updateDate;

	private String wbCount;
	
	public Integer getClubId() {
		return clubId;
	}

	public void setClubId(Integer clubId) {
		this.clubId = clubId;
	}

	public String getClubTitle() {
		return clubTitle;
	}

	public void setClubTitle(String clubTitle) {
		this.clubTitle = clubTitle == null ? null : clubTitle.trim();
	}

	public String getClubDate() {
		return clubDate;
	}

	public void setClubDate(String clubDate) {
		this.clubDate = clubDate == null ? null : clubDate.trim();
	}

	public String getClubPlace() {
		return clubPlace;
	}

	public void setClubPlace(String clubPlace) {
		this.clubPlace = clubPlace == null ? null : clubPlace.trim();
	}

	public String getClubMainGuest() {
		return clubMainGuest;
	}

	public void setClubMainGuest(String clubMainGuest) {
		this.clubMainGuest = clubMainGuest == null ? null : clubMainGuest
				.trim();
	}

	public String getClubMainGuestBook() {
		return clubMainGuestBook;
	}

	public void setClubMainGuestBook(String clubMainGuestBook) {
		this.clubMainGuestBook = clubMainGuestBook == null ? null
				: clubMainGuestBook.trim();
	}

	public String getClubPresenter() {
		return clubPresenter;
	}

	public void setClubPresenter(String clubPresenter) {
		this.clubPresenter = clubPresenter == null ? null : clubPresenter
				.trim();
	}

	public String getClubStatus() {
		return clubStatus;
	}

	public void setClubStatus(String clubStatus) {
		this.clubStatus = clubStatus == null ? null : clubStatus.trim();
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

	public String getClubDes() {
		return clubDes;
	}

	public void setClubDes(String clubDes) {
		this.clubDes = clubDes;
	}

	public String getClubMainGuestIntroduce() {
		return clubMainGuestIntroduce;
	}

	public void setClubMainGuestIntroduce(String clubMainGuestIntroduce) {
		this.clubMainGuestIntroduce = clubMainGuestIntroduce;
	}

	public String getClubImg() {
		return clubImg;
	}

	public void setClubImg(String clubImg) {
		this.clubImg = clubImg;
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

	public String getWbCount() {
		return wbCount;
	}

	public void setWbCount(String wbCount) {
		this.wbCount = wbCount;
	}
	

}