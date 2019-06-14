package com.winnerbook.busInfo.dto;

import java.io.Serializable;
import java.util.Date;

public class BusInfo implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer buId;
	
	private Integer userId;

	private String busName;

	private String busLogo;

	private String busProvince;

	private String busCity;

	private String busCounty;

	private String busAddress;

	private String busIndustry;

	private String busDes;

	private Date addressCreateDate;

	private Date addressUpdateDate;

	private String busDetail;
	
	private Integer manageQrcodeId;
	
	private String busNumber;
	
	private Integer brandQrcodeId;

	private String brandDate;
	
	private String brandImg;
	
	private String brandBusName;
	
	private String mobileBusName;
	
	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getBusName() {
		return busName;
	}

	public void setBusName(String busName) {
		this.busName = busName == null ? null : busName.trim();
	}

	public String getBusLogo() {
		return busLogo;
	}

	public void setBusLogo(String busLogo) {
		this.busLogo = busLogo == null ? null : busLogo.trim();
	}

	public String getBusDes() {
		return busDes;
	}

	public void setBusDes(String busDes) {
		this.busDes = busDes == null ? null : busDes.trim();
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
		this.busDetail = busDetail == null ? null : busDetail.trim();
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

	public Integer getManageQrcodeId() {
		return manageQrcodeId;
	}

	public void setManageQrcodeId(Integer manageQrcodeId) {
		this.manageQrcodeId = manageQrcodeId;
	}

	public String getBusNumber() {
		return busNumber;
	}

	public void setBusNumber(String busNumber) {
		this.busNumber = busNumber;
	}

	public Integer getBrandQrcodeId() {
		return brandQrcodeId;
	}

	public void setBrandQrcodeId(Integer brandQrcodeId) {
		this.brandQrcodeId = brandQrcodeId;
	}

	public String getBrandDate() {
		return brandDate;
	}

	public void setBrandDate(String brandDate) {
		this.brandDate = brandDate;
	}

	public String getBrandImg() {
		return brandImg;
	}

	public void setBrandImg(String brandImg) {
		this.brandImg = brandImg;
	}

	public String getBrandBusName() {
		return brandBusName;
	}

	public void setBrandBusName(String brandBusName) {
		this.brandBusName = brandBusName;
	}

	public String getMobileBusName() {
		return mobileBusName;
	}

	public void setMobileBusName(String mobileBusName) {
		this.mobileBusName = mobileBusName;
	}
	
	

}