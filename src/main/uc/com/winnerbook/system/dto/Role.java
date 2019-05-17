package com.winnerbook.system.dto;

import java.util.Date;

/**
 * TRole entity. @author MyEclipse Persistence Tools
 */

public class Role implements java.io.Serializable {

	// Fields
	private static final long serialVersionUID = 1L;
	private Long roleId;
	private Long userId;
	private String roleName;
	private String roleDesc;
	private String roleStatus;
	private Date roleCreatedate;
	private Long roleCreateUserId;
	private String roleCreateUserName;
	private Date roleUpdatedate;

	// Constructors

	/** default constructor */
	public Role() {
	}

	/** minimal constructor */
	public Role(Long roleId) {
		this.roleId = roleId;
	}

	// Property accessors

	public Long getRoleId() {
		return this.roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	public String getRoleName() {
		return this.roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getRoleDesc() {
		return this.roleDesc;
	}

	public void setRoleDesc(String roleDesc) {
		this.roleDesc = roleDesc;
	}

	public String getRoleStatus() {
		return this.roleStatus;
	}

	public void setRoleStatus(String roleStatus) {
		this.roleStatus = roleStatus;
	}

	public Date getRoleCreatedate() {
		return this.roleCreatedate;
	}

	public void setRoleCreatedate(Date roleCreatedate) {
		this.roleCreatedate = roleCreatedate;
	}

	public Date getRoleUpdatedate() {
		return this.roleUpdatedate;
	}

	public void setRoleUpdatedate(Date roleUpdatedate) {
		this.roleUpdatedate = roleUpdatedate;
	}

	public Long getRoleCreateUserId() {
		return roleCreateUserId;
	}

	public void setRoleCreateUserId(Long roleCreateUserId) {
		this.roleCreateUserId = roleCreateUserId;
	}

	public String getRoleCreateUserName() {
		return roleCreateUserName;
	}

	public void setRoleCreateUserName(String roleCreateUserName) {
		this.roleCreateUserName = roleCreateUserName;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}
	
	
	
	
}