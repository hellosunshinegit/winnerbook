package com.winnerbook.course.dto;

import java.util.Date;

public class CourseClickInfo {
    private Integer id;

    private Integer userId;

    private Integer courseId;

    private String infoDes;
    
    private String ipAddres;

    private Date createDate;

    private Integer createUserId;

    private String createUserName;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getCourseId() {
        return courseId;
    }

    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }

    public String getInfoDes() {
        return infoDes;
    }

    public void setInfoDes(String infoDes) {
        this.infoDes = infoDes == null ? null : infoDes.trim();
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

	public String getIpAddres() {
		return ipAddres;
	}

	public void setIpAddres(String ipAddres) {
		this.ipAddres = ipAddres;
	}
    
}