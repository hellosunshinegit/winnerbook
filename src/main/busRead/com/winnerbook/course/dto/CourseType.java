package com.winnerbook.course.dto;

import java.io.Serializable;
import java.util.Date;

public class CourseType implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer typeId;

	private Integer typeLabelId;

	private String typeLabelName;

	private String typeName;

	private String typeImg;

	private String typeDesc;

	private String typeStatus;

	private Date createDate;

	private Integer createUserId;

	private String createUserName;

	private Date updateDate;

	private Integer typeSort;
	
	private String typeIsFree;

	public Integer getTypeId() {
		return typeId;
	}

	public void setTypeId(Integer typeId) {
		this.typeId = typeId;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName == null ? null : typeName.trim();
	}

	public String getTypeDesc() {
		return typeDesc;
	}

	public void setTypeDesc(String typeDesc) {
		this.typeDesc = typeDesc == null ? null : typeDesc.trim();
	}

	public String getTypeStatus() {
		return typeStatus;
	}

	public void setTypeStatus(String typeStatus) {
		this.typeStatus = typeStatus == null ? null : typeStatus.trim();
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

	public Integer getTypeLabelId() {
		return typeLabelId;
	}

	public void setTypeLabelId(Integer typeLabelId) {
		this.typeLabelId = typeLabelId;
	}

	public String getTypeLabelName() {
		return typeLabelName;
	}

	public void setTypeLabelName(String typeLabelName) {
		this.typeLabelName = typeLabelName;
	}

	public String getTypeImg() {
		return typeImg;
	}

	public void setTypeImg(String typeImg) {
		this.typeImg = typeImg;
	}

	public Integer getTypeSort() {
		return typeSort;
	}

	public void setTypeSort(Integer typeSort) {
		this.typeSort = typeSort;
	}

	public String getTypeIsFree() {
		return typeIsFree;
	}

	public void setTypeIsFree(String typeIsFree) {
		this.typeIsFree = typeIsFree;
	}

	
}