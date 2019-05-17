package com.winnerbook.system.dto;

import java.sql.Timestamp;
import java.util.Date;

/**
 * TMenu entity. @author MyEclipse Persistence Tools
 */

public class Menu implements java.io.Serializable {

	// Fields
	private static final long serialVersionUID = 1L;
	private Long menuId;
	private Long menuParentid;
	private String menuName;
	private String menuStatus;
	private String menuCode;
	private String menuUrl;
	private Date menuCreatedate;
	private Date menuUpdatedate;

	// Constructors

	/** default constructor */
	public Menu() {
	}

	/** minimal constructor */
	public Menu(Long menuId) {
		this.menuId = menuId;
	}

	/** full constructor */
	public Menu(Long menuId, Long menuParentid, String menuName,
			String menuStatus, String menuCode, String menuUrl,
			Date menuCreatedate, Timestamp menuUpdatedate) {
		this.menuId = menuId;
		this.menuParentid = menuParentid;
		this.menuName = menuName;
		this.menuStatus = menuStatus;
		this.menuCode = menuCode;
		this.menuUrl = menuUrl;
		this.menuCreatedate = menuCreatedate;
		this.menuUpdatedate = menuUpdatedate;
	}

	// Property accessors

	public Long getMenuId() {
		return this.menuId;
	}

	public void setMenuId(Long menuId) {
		this.menuId = menuId;
	}

	public Long getMenuParentid() {
		return this.menuParentid;
	}

	public void setMenuParentid(Long menuParentid) {
		this.menuParentid = menuParentid;
	}

	public String getMenuName() {
		return this.menuName;
	}

	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}

	public String getMenuStatus() {
		return this.menuStatus;
	}

	public void setMenuStatus(String menuStatus) {
		this.menuStatus = menuStatus;
	}

	public String getMenuCode() {
		return this.menuCode;
	}

	public void setMenuCode(String menuCode) {
		this.menuCode = menuCode;
	}

	public String getMenuUrl() {
		return this.menuUrl;
	}

	public void setMenuUrl(String menuUrl) {
		this.menuUrl = menuUrl;
	}

	public Date getMenuCreatedate() {
		return this.menuCreatedate;
	}

	public void setMenuCreatedate(Date menuCreatedate) {
		this.menuCreatedate = menuCreatedate;
	}

	public Date getMenuUpdatedate() {
		return this.menuUpdatedate;
	}

	public void setMenuUpdatedate(Date menuUpdatedate) {
		this.menuUpdatedate = menuUpdatedate;
	}

}