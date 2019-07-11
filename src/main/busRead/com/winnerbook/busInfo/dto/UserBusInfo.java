package com.winnerbook.busInfo.dto;

import java.io.Serializable;
import java.util.Date;

public class UserBusInfo implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer buId;
	
	private Integer userId;

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

	private Date busCreateDate;

	private Date busUpdateDate;
	
	private Integer manageQrcodeId;
	
	private String busNumber;
	
	private Integer brandQrcodeId;

	private String brandDate;
	
	private String brandDateChinese;
	
	private String brandImg;
	
	private String brandBusName;
	
	private String mobileBusName;
	
	private Integer sendWbCount;
	
	private Integer empUseNum;
	
	private String brandImgRegion;//20190711
	private String brandImgProvince;
	private String brandImgCountry;
	
	private String isGenerateApp;
	
	
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

	public Date getBusCreateDate() {
		return busCreateDate;
	}

	public void setBusCreateDate(Date busCreateDate) {
		this.busCreateDate = busCreateDate;
	}

	public Date getBusUpdateDate() {
		return busUpdateDate;
	}

	public void setBusUpdateDate(Date busUpdateDate) {
		this.busUpdateDate = busUpdateDate;
	}

	public String getBrandDate() {
		return brandDate;
	}

	public void setBrandDate(String brandDate) {
		this.brandDate = brandDate;
	}

	public String getBrandDateChinese() {
		return brandDateChinese;
	}

	public void setBrandDateChinese(String brandDateChinese) {
		this.brandDateChinese = brandDateChinese;
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

	public Integer getSendWbCount() {
		return sendWbCount;
	}

	public void setSendWbCount(Integer sendWbCount) {
		this.sendWbCount = sendWbCount;
	}

	public Integer getEmpUseNum() {
		return empUseNum;
	}

	public void setEmpUseNum(Integer empUseNum) {
		this.empUseNum = empUseNum;
	}

	public String getBrandImgRegion() {
		return brandImgRegion;
	}

	public void setBrandImgRegion(String brandImgRegion) {
		this.brandImgRegion = brandImgRegion;
	}

	public String getBrandImgProvince() {
		return brandImgProvince;
	}

	public void setBrandImgProvince(String brandImgProvince) {
		this.brandImgProvince = brandImgProvince;
	}

	public String getBrandImgCountry() {
		return brandImgCountry;
	}

	public void setBrandImgCountry(String brandImgCountry) {
		this.brandImgCountry = brandImgCountry;
	}

	public String getIsGenerateApp() {
		return isGenerateApp;
	}

	public void setIsGenerateApp(String isGenerateApp) {
		this.isGenerateApp = isGenerateApp;
	}
	
	
}
