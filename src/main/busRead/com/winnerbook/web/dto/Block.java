package com.winnerbook.web.dto;

import java.util.Date;

public class Block {
    private Integer blockId;

    private String blockName;

    private String blockImgUrl;

    private String blockType;

    private String blockStatus;

    private Date createDate;

    private Integer createUserId;

    private String createUserName;
    
    private Date updateDate;

    public Integer getBlockId() {
        return blockId;
    }

    public void setBlockId(Integer blockId) {
        this.blockId = blockId;
    }

    public String getBlockName() {
        return blockName;
    }

    public void setBlockName(String blockName) {
        this.blockName = blockName == null ? null : blockName.trim();
    }

    public String getBlockImgUrl() {
        return blockImgUrl;
    }

    public void setBlockImgUrl(String blockImgUrl) {
        this.blockImgUrl = blockImgUrl == null ? null : blockImgUrl.trim();
    }

    public String getBlockType() {
        return blockType;
    }

    public void setBlockType(String blockType) {
        this.blockType = blockType == null ? null : blockType.trim();
    }

    public String getBlockStatus() {
        return blockStatus;
    }

    public void setBlockStatus(String blockStatus) {
        this.blockStatus = blockStatus == null ? null : blockStatus.trim();
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
    
    
}