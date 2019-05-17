package com.winnerbook.system.dto;

import java.util.Date;

/**
 * UcAddressId entity. @author MyEclipse Persistence Tools
 */

public class UcAddress implements java.io.Serializable {

	// Fields

	private static final long serialVersionUID = 1L;
	private Long addressId;
	private Long addressParentId;
	private String addressParenName;
	private String addressName;
	private String addressStatue;
	private Date addressCreateDate;
	private Date addressUpdateDate;

	// Constructors

	/** default constructor */
	public UcAddress() {
	}

	/** full constructor */
	public UcAddress(Long addressId, Long addressParentId, String addressName) {
		this.addressId = addressId;
		this.addressParentId = addressParentId;
		this.addressName = addressName;
	}

	// Property accessors

	public Long getAddressId() {
		return this.addressId;
	}

	public void setAddressId(Long addressId) {
		this.addressId = addressId;
	}

	public Long getAddressParentId() {
		return this.addressParentId;
	}

	public void setAddressParentId(Long addressParentId) {
		this.addressParentId = addressParentId;
	}

	public String getAddressName() {
		return this.addressName;
	}

	public void setAddressName(String addressName) {
		this.addressName = addressName;
	}

	public Date getAddressCreateDate() {
		return addressCreateDate;
	}

	public void setAddressCreateDate(Date addressCreateDate) {
		this.addressCreateDate = addressCreateDate;
	}

	public Date getAddressUpdateDate() {
		return addressUpdateDate;
	}

	public void setAddressUpdateDate(Date addressUpdateDate) {
		this.addressUpdateDate = addressUpdateDate;
	}

	public String getAddressStatue() {
		return addressStatue;
	}

	public void setAddressStatue(String addressStatue) {
		this.addressStatue = addressStatue;
	}

	public String getAddressParenName() {
		return addressParenName;
	}

	public void setAddressParenName(String addressParenName) {
		this.addressParenName = addressParenName;
	}
	

}