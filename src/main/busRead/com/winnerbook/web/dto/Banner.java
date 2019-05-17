package com.winnerbook.web.dto;

import java.util.Date;

public class Banner {
    private Integer bannerId;

    private String bannerTitle;

    private String bannerUrl;

    private String bannerType;

    private String bannerClickUrl;
    
    private String bannerStatus;

    private Date createDate;

    private Integer createUserId;

    private String createUserName;

    private Date updateDate;
    
    public Integer getBannerId() {
        return bannerId;
    }

    public void setBannerId(Integer bannerId) {
        this.bannerId = bannerId;
    }

    public String getBannerTitle() {
        return bannerTitle;
    }

    public void setBannerTitle(String bannerTitle) {
        this.bannerTitle = bannerTitle == null ? null : bannerTitle.trim();
    }

    public String getBannerUrl() {
        return bannerUrl;
    }

    public void setBannerUrl(String bannerUrl) {
        this.bannerUrl = bannerUrl == null ? null : bannerUrl.trim();
    }

    public String getBannerType() {
        return bannerType;
    }

    public void setBannerType(String bannerType) {
        this.bannerType = bannerType == null ? null : bannerType.trim();
    }

    public String getBannerStatus() {
        return bannerStatus;
    }

    public void setBannerStatus(String bannerStatus) {
        this.bannerStatus = bannerStatus == null ? null : bannerStatus.trim();
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Integer getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(Integer createUserId) {
        this.createUserId = createUserId;
    }

    public String getCreateUserName() {
        return createUserName;
    }

    public void setCreateUserName(String createUserName) {
        this.createUserName = createUserName == null ? null : createUserName.trim();
    }

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public String getBannerClickUrl() {
		return bannerClickUrl;
	}

	public void setBannerClickUrl(String bannerClickUrl) {
		this.bannerClickUrl = bannerClickUrl;
	}
	
	
    
    
}