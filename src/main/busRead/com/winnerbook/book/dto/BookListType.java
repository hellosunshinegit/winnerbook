package com.winnerbook.book.dto;

import java.util.Date;

/**
 * 书单列表 对书籍列表的分类
 * 
 * @author hxs
 * 
 */
public class BookListType {
	private Integer id;

	private String typeName;

	private String wbTitle;

	private String wbImg;

	private String typeLabel;

	private String labelIds;

	private String status;

	private String typeImg;

	private String typeDes;

	private Date createDate;

	private Integer createUserId;

	private String createUserName;

	private Date updateDate;

	private Integer typeSort;

	private String wbCount;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName == null ? null : typeName.trim();
	}

	public String getTypeLabel() {
		return typeLabel;
	}

	public void setTypeLabel(String typeLabel) {
		this.typeLabel = typeLabel == null ? null : typeLabel.trim();
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status == null ? null : status.trim();
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

	public String getTypeDes() {
		return typeDes;
	}

	public void setTypeDes(String typeDes) {
		this.typeDes = typeDes;
	}

	public String getLabelIds() {
		return labelIds;
	}

	public void setLabelIds(String labelIds) {
		this.labelIds = labelIds;
	}

	public String getTypeImg() {
		return typeImg;
	}

	public void setTypeImg(String typeImg) {
		this.typeImg = typeImg;
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

	public Integer getTypeSort() {
		return typeSort;
	}

	public void setTypeSort(Integer typeSort) {
		this.typeSort = typeSort;
	}

	public String getWbCount() {
		return wbCount;
	}

	public void setWbCount(String wbCount) {
		this.wbCount = wbCount;
	}

}