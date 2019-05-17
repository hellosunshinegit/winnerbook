package com.winnerbook.system.dto;

import java.sql.Timestamp;
import java.util.Date;

/**
 * TUser entity. @author MyEclipse Persistence Tools
 */

public class User implements java.io.Serializable {

	// Fields
	private static final long serialVersionUID = 1L;
	private Long userId;
	private Long userParentId;
	private String userName;
	private String userPassword;
	private String userPassword1;// 确认密码
	private String userUnitName;
	private Integer userLoginCount;
	private Date userLastLoginDate;
	private String userStatue;
	private String isBusinessAdmin;
	private String sourceType;
	private String isAdmin;
	private String department;
	private String isDepartLeader;
	private String isCompanyLeader;
	private Date userCreateDate;
	private Long userCreateUserId;
	private String userCreateUserName;
	private Date userUpdateDate;
	private Long userUpdateUserId;
	private String userUpdateUserName;
	private String adminId;
	private String belongBusUserId;
	private String busName;

	// Constructors

	/** default constructor */
	public User() {
	}

	// Property accessors

	public Long getUserId() {
		return this.userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getUserParentId() {
		return this.userParentId;
	}

	public void setUserParentId(Long userParentId) {
		this.userParentId = userParentId;
	}

	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserPassword() {
		return this.userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public String getUserPassword1() {
		return this.userPassword1;
	}

	public void setUserPassword1(String userPassword1) {
		this.userPassword1 = userPassword1;
	}

	public String getUserUnitName() {
		return userUnitName;
	}

	public void setUserUnitName(String userUnitName) {
		this.userUnitName = userUnitName;
	}

	public Integer getUserLoginCount() {
		return this.userLoginCount;
	}

	public void setUserLoginCount(Integer userLoginCount) {
		this.userLoginCount = userLoginCount;
	}

	public void setUserCreateDate(Timestamp userCreateDate) {
		this.userCreateDate = userCreateDate;
	}

	public Long getUserCreateUserId() {
		return this.userCreateUserId;
	}

	public void setUserCreateUserId(Long userCreateUserId) {
		this.userCreateUserId = userCreateUserId;
	}

	public String getUserCreateUserName() {
		return this.userCreateUserName;
	}

	public void setUserCreateUserName(String userCreateUserName) {
		this.userCreateUserName = userCreateUserName;
	}

	public Date getUserLastLoginDate() {
		return userLastLoginDate;
	}

	public void setUserLastLoginDate(Date userLastLoginDate) {
		this.userLastLoginDate = userLastLoginDate;
	}

	public Date getUserCreateDate() {
		return userCreateDate;
	}

	public void setUserCreateDate(Date userCreateDate) {
		this.userCreateDate = userCreateDate;
	}

	public Date getUserUpdateDate() {
		return userUpdateDate;
	}

	public void setUserUpdateDate(Date userUpdateDate) {
		this.userUpdateDate = userUpdateDate;
	}

	public Long getUserUpdateUserId() {
		return this.userUpdateUserId;
	}

	public void setUserUpdateUserId(Long userUpdateUserId) {
		this.userUpdateUserId = userUpdateUserId;
	}

	public String getUserUpdateUserName() {
		return this.userUpdateUserName;
	}

	public void setUserUpdateUserName(String userUpdateUserName) {
		this.userUpdateUserName = userUpdateUserName;
	}

	public String getUserStatue() {
		return userStatue;
	}

	public void setUserStatue(String userStatue) {
		this.userStatue = userStatue;
	}

	public String getIsBusinessAdmin() {
		return isBusinessAdmin;
	}

	public void setIsBusinessAdmin(String isBusinessAdmin) {
		this.isBusinessAdmin = isBusinessAdmin;
	}

	public String getSourceType() {
		return sourceType;
	}

	public void setSourceType(String sourceType) {
		this.sourceType = sourceType;
	}

	public String getIsAdmin() {
		return isAdmin;
	}

	public void setIsAdmin(String isAdmin) {
		this.isAdmin = isAdmin;
	}

	public String getAdminId() {
		return adminId;
	}

	public void setAdminId(String adminId) {
		this.adminId = adminId;
	}

	public String getBelongBusUserId() {
		return belongBusUserId;
	}

	public void setBelongBusUserId(String belongBusUserId) {
		this.belongBusUserId = belongBusUserId;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getIsDepartLeader() {
		return isDepartLeader;
	}

	public void setIsDepartLeader(String isDepartLeader) {
		this.isDepartLeader = isDepartLeader;
	}

	public String getIsCompanyLeader() {
		return isCompanyLeader;
	}

	public void setIsCompanyLeader(String isCompanyLeader) {
		this.isCompanyLeader = isCompanyLeader;
	}

	public String getBusName() {
		return busName;
	}

	public void setBusName(String busName) {
		this.busName = busName;
	}
	
	
	
	

}