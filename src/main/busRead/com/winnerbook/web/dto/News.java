package com.winnerbook.web.dto;

import java.util.Date;

public class News {
	private Integer newId;

	private String newTitle;

	private String wbTitle;

	private String wbImg;

	private String newImg;

	private String newAuthor;

	private String newChannel;

	private String newDes;

	private String newStatus;

	private Date createDate;

	private Integer createUserId;

	private String createUserName;

	private Date updateDate;

	private String newContent;

	public Integer getNewId() {
		return newId;
	}

	public void setNewId(Integer newId) {
		this.newId = newId;
	}

	public String getNewTitle() {
		return newTitle;
	}

	public void setNewTitle(String newTitle) {
		this.newTitle = newTitle == null ? null : newTitle.trim();
	}

	public String getNewAuthor() {
		return newAuthor;
	}

	public void setNewAuthor(String newAuthor) {
		this.newAuthor = newAuthor == null ? null : newAuthor.trim();
	}

	public String getNewChannel() {
		return newChannel;
	}

	public void setNewChannel(String newChannel) {
		this.newChannel = newChannel == null ? null : newChannel.trim();
	}

	public String getNewDes() {
		return newDes;
	}

	public void setNewDes(String newDes) {
		this.newDes = newDes == null ? null : newDes.trim();
	}

	public String getNewStatus() {
		return newStatus;
	}

	public void setNewStatus(String newStatus) {
		this.newStatus = newStatus == null ? null : newStatus.trim();
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

	public String getNewContent() {
		return newContent;
	}

	public void setNewContent(String newContent) {
		this.newContent = newContent == null ? null : newContent.trim();
	}

	public String getNewImg() {
		return newImg;
	}

	public void setNewImg(String newImg) {
		this.newImg = newImg;
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