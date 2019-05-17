package com.winnerbook.book.dto;

import java.util.Date;

public class BookListClickInfo {
    private Integer id;

    private Integer userId;

    private Integer bookListId;

    private String bookListName;

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

    public Integer getBookListId() {
        return bookListId;
    }

    public void setBookListId(Integer bookListId) {
        this.bookListId = bookListId;
    }

    public String getBookListName() {
        return bookListName;
    }

    public void setBookListName(String bookListName) {
        this.bookListName = bookListName == null ? null : bookListName.trim();
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

