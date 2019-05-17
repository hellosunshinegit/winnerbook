package com.winnerbook.book.dto;

import java.util.Date;

public class BookListTypeClickInfo {
    private Integer id;

    private Integer userId;

    private Integer bookListTypeId;

    private String bookListTypeName;

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

    public Integer getBookListTypeId() {
        return bookListTypeId;
    }

    public void setBookListTypeId(Integer bookListTypeId) {
        this.bookListTypeId = bookListTypeId;
    }

    public String getBookListTypeName() {
        return bookListTypeName;
    }

    public void setBookListTypeName(String bookListTypeName) {
        this.bookListTypeName = bookListTypeName == null ? null : bookListTypeName.trim();
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