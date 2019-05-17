package com.winnerbook.busInfo.dto;

import java.io.Serializable;
import java.util.Date;

public class UserBusInfo implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer buId;
	private Long userId;

	private String userName;

	private String userUnitName;

	private String busName;

	private String busLogo;

	private String busProvince;

	private String busCity;

	private String busCounty;

	private String busAddress;

	private String busIndustry;

	private String busDes;

	private String busDetail;

	private Date addressCreateDate;

	private Date addressUpdateDate;

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserUnitName() {
		return userUnitName;
	}

	public void setUserUnitName(String userUnitName) {
		this.userUnitName = userUnitName;
	}

	public String getBusName() {
		return busName;
	}

	public void setBusName(String busName) {
		this.busName = busName;
	}

	public String getBusLogo() {
		return busLogo;
	}

	public void setBusLogo(String busLogo) {
		this.busLogo = busLogo;
	}

	public String getBusDes() {
		return busDes;
	}

	public void setBusDes(String busDes) {
		this.busDes = busDes;
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

	public String getBusDetail() {
		return busDetail;
	}

	public void setBusDetail(String busDetail) {
		this.busDetail = busDetail;
	}

	public Integer getBuId() {
		return buId;
	}

	public void setBuId(Integer buId) {
		this.buId = buId;
	}

	public String getBusProvince() {
		return busProvince;
	}

	public void setBusProvince(String busProvince) {
		this.busProvince = busProvince;
	}

	public String getBusCity() {
		return busCity;
	}

	public void setBusCity(String busCity) {
		this.busCity = busCity;
	}

	public String getBusCounty() {
		return busCounty;
	}

	public void setBusCounty(String busCounty) {
		this.busCounty = busCounty;
	}

	public String getBusAddress() {
		return busAddress;
	}

	public void setBusAddress(String busAddress) {
		this.busAddress = busAddress;
	}

	public String getBusIndustry() {
		return busIndustry;
	}

	public void setBusIndustry(String busIndustry) {
		this.busIndustry = busIndustry;
	}

}
