package com.winnerbook.system.dto;

import java.util.Date;

public class UserApplyBusAdmin {

	private Integer uaId;

	private Integer userId;

	private String userName;

	private Integer busId;

	private String applyBusName;

	private String applyBusDes;

	private String status;

	private String statusReason;

	private Date createDate;

	private Date successDate;

	private Date updateDate;

	public Integer getUaId() {
		return uaId;
	}

	public void setUaId(Integer uaId) {
		this.uaId = uaId;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getApplyBusName() {
		return applyBusName;
	}

	public void setApplyBusName(String applyBusName) {
		this.applyBusName = applyBusName == null ? null : applyBusName.trim();
	}

	public String getApplyBusDes() {
		return applyBusDes;
	}

	public void setApplyBusDes(String applyBusDes) {
		this.applyBusDes = applyBusDes == null ? null : applyBusDes.trim();
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

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public Date getSuccessDate() {
		return successDate;
	}

	public void setSuccessDate(Date successDate) {
		this.successDate = successDate;
	}

	public String getStatusReason() {
		return statusReason;
	}

	public void setStatusReason(String statusReason) {
		this.statusReason = statusReason;
	}

	public Integer getBusId() {
		return busId;
	}

	public void setBusId(Integer busId) {
		this.busId = busId;
	}

}