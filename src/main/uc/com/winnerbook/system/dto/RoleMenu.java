package com.winnerbook.system.dto;

import java.util.Date;

/**
 * TRoleMenu entity. @author MyEclipse Persistence Tools
 */

public class RoleMenu implements java.io.Serializable {

	// Fields
	private static final long serialVersionUID = 1L;
	private Long rmId;
	private Long roleId;
	private Long menuId;
	private Date rmCreatedate;

	// Constructors

	/** default constructor */
	public RoleMenu() {
	}

	/** minimal constructor */
	public RoleMenu(Long rmId) {
		this.rmId = rmId;
	}

	/** full constructor */
	public RoleMenu(Long rmId, Long roleId, Long menuId, Date rmCreatedate) {
		this.rmId = rmId;
		this.roleId = roleId;
		this.menuId = menuId;
		this.rmCreatedate = rmCreatedate;
	}

	// Property accessors

	public Long getRmId() {
		return this.rmId;
	}

	public void setRmId(Long rmId) {
		this.rmId = rmId;
	}

	public Long getRoleId() {
		return this.roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	public Long getMenuId() {
		return this.menuId;
	}

	public void setMenuId(Long menuId) {
		this.menuId = menuId;
	}

	public Date getRmCreatedate() {
		return this.rmCreatedate;
	}

	public void setRmCreatedate(Date rmCreatedate) {
		this.rmCreatedate = rmCreatedate;
	}

}