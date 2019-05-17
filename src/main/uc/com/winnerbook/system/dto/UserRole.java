package com.winnerbook.system.dto;

import java.util.Date;

/**
 * TUserRole entity. @author MyEclipse Persistence Tools
 */

public class UserRole implements java.io.Serializable {

	// Fields
	private static final long serialVersionUID = 1L;
	private Long urId;
	private Long userId;
	private Long roleId;
	private Date urCreatedate;
	private Date urUpdatedate;

	// Constructors

	/** default constructor */
	public UserRole() {
	}

	/** minimal constructor */
	public UserRole(Long urId) {
		this.urId = urId;
	}

	/** full constructor */
	public UserRole(Long urId, Long userId, Long roleId,
			Date urCreatedate, Date urUpdatedate) {
		this.urId = urId;
		this.userId = userId;
		this.roleId = roleId;
		this.urCreatedate = urCreatedate;
		this.urUpdatedate = urUpdatedate;
	}

	// Property accessors

	public Long getUrId() {
		return this.urId;
	}

	public void setUrId(Long urId) {
		this.urId = urId;
	}

	public Long getUserId() {
		return this.userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getRoleId() {
		return this.roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	public Date getUrCreatedate() {
		return this.urCreatedate;
	}

	public void setUrCreatedate(Date urCreatedate) {
		this.urCreatedate = urCreatedate;
	}

	public Date getUrUpdatedate() {
		return this.urUpdatedate;
	}

	public void setUrUpdatedate(Date urUpdatedate) {
		this.urUpdatedate = urUpdatedate;
	}

}