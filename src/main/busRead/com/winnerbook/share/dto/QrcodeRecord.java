package com.winnerbook.share.dto;

import java.util.Date;

public class QrcodeRecord {
    private Integer id;

    private Integer qrcodeId;

    private String ip;

    private String browserType;
    
    private String mobileDevice;

    private Date createDate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getQrcodeId() {
        return qrcodeId;
    }

    public void setQrcodeId(Integer qrcodeId) {
        this.qrcodeId = qrcodeId;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip == null ? null : ip.trim();
    }

    public String getBrowserType() {
        return browserType;
    }

    public void setBrowserType(String browserType) {
        this.browserType = browserType == null ? null : browserType.trim();
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

	public String getMobileDevice() {
		return mobileDevice;
	}

	public void setMobileDevice(String mobileDevice) {
		this.mobileDevice = mobileDevice;
	}
    
    
    
}