package com.winnerbook.course.dto;

import java.io.Serializable;
import java.util.Date;

public class BookListSearchLog implements Serializable{
    private Integer id;

    private String bookName;
    
    private String bookTitle;

    private String bookAuthor;

    private String bookPublishers;

    private String bookPublicationDate;

    private String openBook;

    private String bookPaper;

    private String bookPack;

    private String isSuit;

    private String bookIsbn;

    private String bookClass;

    private String bookImg;

    private String bookUrl;

    private Date createDate;

    private Integer createUserId;

    private String createUserName;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName == null ? null : bookName.trim();
    }

    public String getBookAuthor() {
        return bookAuthor;
    }

    public void setBookAuthor(String bookAuthor) {
        this.bookAuthor = bookAuthor == null ? null : bookAuthor.trim();
    }

    public String getBookPublishers() {
        return bookPublishers;
    }

    public void setBookPublishers(String bookPublishers) {
        this.bookPublishers = bookPublishers == null ? null : bookPublishers.trim();
    }

    public String getBookPublicationDate() {
        return bookPublicationDate;
    }

    public void setBookPublicationDate(String bookPublicationDate) {
        this.bookPublicationDate = bookPublicationDate == null ? null : bookPublicationDate.trim();
    }

    public String getOpenBook() {
        return openBook;
    }

    public void setOpenBook(String openBook) {
        this.openBook = openBook == null ? null : openBook.trim();
    }

    public String getBookPaper() {
        return bookPaper;
    }

    public void setBookPaper(String bookPaper) {
        this.bookPaper = bookPaper == null ? null : bookPaper.trim();
    }

    public String getBookPack() {
        return bookPack;
    }

    public void setBookPack(String bookPack) {
        this.bookPack = bookPack == null ? null : bookPack.trim();
    }

    public String getIsSuit() {
        return isSuit;
    }

    public void setIsSuit(String isSuit) {
        this.isSuit = isSuit == null ? null : isSuit.trim();
    }

    public String getBookIsbn() {
        return bookIsbn;
    }

    public void setBookIsbn(String bookIsbn) {
        this.bookIsbn = bookIsbn == null ? null : bookIsbn.trim();
    }

    public String getBookClass() {
        return bookClass;
    }

    public void setBookClass(String bookClass) {
        this.bookClass = bookClass == null ? null : bookClass.trim();
    }

    public String getBookImg() {
        return bookImg;
    }

    public void setBookImg(String bookImg) {
        this.bookImg = bookImg == null ? null : bookImg.trim();
    }

    public String getBookUrl() {
        return bookUrl;
    }

    public void setBookUrl(String bookUrl) {
        this.bookUrl = bookUrl == null ? null : bookUrl.trim();
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

	public String getBookTitle() {
		return bookTitle;
	}

	public void setBookTitle(String bookTitle) {
		this.bookTitle = bookTitle;
	}
    
    
}