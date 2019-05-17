package com.winnerbook.course.dto;

import java.util.Date;

public class MainGuestClickInfo {
    private Integer id;

    private Integer userId;

    private Integer courseId;

    private String mainGuest;

    private String ipAddres;

    private String infoDes;

    private Date createDate;

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

    public String getMainGuest() {
		return mainGuest;
	}

	public void setMainGuest(String mainGuest) {
		this.mainGuest = mainGuest;
	}

	public String getIpAddres() {
        return ipAddres;
    }

    public void setIpAddres(String ipAddres) {
        this.ipAddres = ipAddres == null ? null : ipAddres.trim();
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

}