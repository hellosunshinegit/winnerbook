package com.winnerbook.wx.dto;

import java.util.Date;

public class WbSendInfo {
    private Integer id;

    private Integer mainId;

    private String mainTitle;

    private String mainType;

    private String wbId;

    private String wbIdstr;

    private String wbMid;

    private Date wbCreatedAt;

    private Date createTime;
    
    private Integer createUserId;
    
    private String createUserName;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getMainId() {
        return mainId;
    }

    public void setMainId(Integer mainId) {
        this.mainId = mainId;
    }

    public String getMainTitle() {
        return mainTitle;
    }

    public void setMainTitle(String mainTitle) {
        this.mainTitle = mainTitle == null ? null : mainTitle.trim();
    }

    public String getMainType() {
        return mainType;
    }

    public void setMainType(String mainType) {
        this.mainType = mainType == null ? null : mainType.trim();
    }

    public String getWbId() {
        return wbId;
    }

    public void setWbId(String wbId) {
        this.wbId = wbId == null ? null : wbId.trim();
    }

    public String getWbIdstr() {
        return wbIdstr;
    }

    public void setWbIdstr(String wbIdstr) {
        this.wbIdstr = wbIdstr == null ? null : wbIdstr.trim();
    }

    public String getWbMid() {
        return wbMid;
    }

    public void setWbMid(String wbMid) {
        this.wbMid = wbMid == null ? null : wbMid.trim();
    }

    public Date getWbCreatedAt() {
        return wbCreatedAt;
    }

    public void setWbCreatedAt(Date wbCreatedAt) {
        this.wbCreatedAt = wbCreatedAt;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
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
		this.createUserName = createUserName;
	}
    
}