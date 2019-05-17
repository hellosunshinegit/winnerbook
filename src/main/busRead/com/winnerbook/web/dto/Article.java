package com.winnerbook.web.dto;

import java.util.Date;

public class Article {
	private Integer articleId;

	private Integer articleTypeId;
	private String articleTypeName;

	private String articleTags;
	private String articleTagIds;

	private String articleChannel;

	private String articleTitle;

	private String articleImg;

	private String articleAuthor;

	private String articleDes;

	private String articleStatus;

	private Date createDate;

	private Integer createUserId;

	private String createUserName;

	private String articleContent;

	private Date updateDate;

	private String blockStr;

	public Integer getArticleId() {
		return articleId;
	}

	public void setArticleId(Integer articleId) {
		this.articleId = articleId;
	}

	public Integer getArticleTypeId() {
		return articleTypeId;
	}

	public void setArticleTypeId(Integer articleTypeId) {
		this.articleTypeId = articleTypeId;
	}

	public String getArticleTags() {
		return articleTags;
	}

	public void setArticleTags(String articleTags) {
		this.articleTags = articleTags == null ? null : articleTags.trim();
	}

	public String getArticleChannel() {
		return articleChannel;
	}

	public void setArticleChannel(String articleChannel) {
		this.articleChannel = articleChannel;
	}

	public String getArticleTitle() {
		return articleTitle;
	}

	public void setArticleTitle(String articleTitle) {
		this.articleTitle = articleTitle == null ? null : articleTitle.trim();
	}

	public String getArticleAuthor() {
		return articleAuthor;
	}

	public void setArticleAuthor(String articleAuthor) {
		this.articleAuthor = articleAuthor == null ? null : articleAuthor
				.trim();
	}

	public String getArticleDes() {
		return articleDes;
	}

	public void setArticleDes(String articleDes) {
		this.articleDes = articleDes == null ? null : articleDes.trim();
	}

	public String getArticleStatus() {
		return articleStatus;
	}

	public void setArticleStatus(String articleStatus) {
		this.articleStatus = articleStatus == null ? null : articleStatus
				.trim();
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

	public String getArticleContent() {
		return articleContent;
	}

	public void setArticleContent(String articleContent) {
		this.articleContent = articleContent == null ? null : articleContent
				.trim();
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public String getArticleTypeName() {
		return articleTypeName;
	}

	public void setArticleTypeName(String articleTypeName) {
		this.articleTypeName = articleTypeName;
	}

	public String getBlockStr() {
		return blockStr;
	}

	public void setBlockStr(String blockStr) {
		this.blockStr = blockStr;
	}

	public String getArticleTagIds() {
		return articleTagIds;
	}

	public void setArticleTagIds(String articleTagIds) {
		this.articleTagIds = articleTagIds;
	}

	public String getArticleImg() {
		return articleImg;
	}

	public void setArticleImg(String articleImg) {
		this.articleImg = articleImg;
	}

}