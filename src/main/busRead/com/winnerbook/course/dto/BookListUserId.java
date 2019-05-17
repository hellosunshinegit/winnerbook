package com.winnerbook.course.dto;

import java.io.Serializable;
import java.util.Date;

/**
 * 不同的用户可能推荐了相同的书籍
 * @author hxs
 * 
 */
public class BookListUserId implements Serializable{
	private Integer bluId;

    private Integer bookListId;

    private Integer userId;

    private String bookName;

    private String bookSearchName;

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

    private String bookContentDes;

    private String bookAuthorDes;

    private String bookStatus;

    private Date createDate;

    private Integer createUserId;

    private String createUserName;

    private Date updateDate;

	public Integer getBluId() {
		return bluId;
	}

	public void setBluId(Integer bluId) {
		this.bluId = bluId;
	}

	public Integer getBookListId() {
		return bookListId;
	}

	public void setBookListId(Integer bookListId) {
		this.bookListId = bookListId;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getBookName() {
		return bookName;
	}

	public void setBookName(String bookName) {
		this.bookName = bookName;
	}

	public String getBookSearchName() {
		return bookSearchName;
	}

	public void setBookSearchName(String bookSearchName) {
		this.bookSearchName = bookSearchName;
	}

	public String getBookAuthor() {
		return bookAuthor;
	}

	public void setBookAuthor(String bookAuthor) {
		this.bookAuthor = bookAuthor;
	}

	public String getBookPublishers() {
		return bookPublishers;
	}

	public void setBookPublishers(String bookPublishers) {
		this.bookPublishers = bookPublishers;
	}

	public String getBookPublicationDate() {
		return bookPublicationDate;
	}

	public void setBookPublicationDate(String bookPublicationDate) {
		this.bookPublicationDate = bookPublicationDate;
	}

	public String getOpenBook() {
		return openBook;
	}

	public void setOpenBook(String openBook) {
		this.openBook = openBook;
	}

	public String getBookPaper() {
		return bookPaper;
	}

	public void setBookPaper(String bookPaper) {
		this.bookPaper = bookPaper;
	}

	public String getBookPack() {
		return bookPack;
	}

	public void setBookPack(String bookPack) {
		this.bookPack = bookPack;
	}

	public String getIsSuit() {
		return isSuit;
	}

	public void setIsSuit(String isSuit) {
		this.isSuit = isSuit;
	}

	public String getBookIsbn() {
		return bookIsbn;
	}

	public void setBookIsbn(String bookIsbn) {
		this.bookIsbn = bookIsbn;
	}

	public String getBookClass() {
		return bookClass;
	}

	public void setBookClass(String bookClass) {
		this.bookClass = bookClass;
	}

	public String getBookImg() {
		return bookImg;
	}

	public void setBookImg(String bookImg) {
		this.bookImg = bookImg;
	}

	public String getBookUrl() {
		return bookUrl;
	}

	public void setBookUrl(String bookUrl) {
		this.bookUrl = bookUrl;
	}

	public String getBookContentDes() {
		return bookContentDes;
	}

	public void setBookContentDes(String bookContentDes) {
		this.bookContentDes = bookContentDes;
	}

	public String getBookAuthorDes() {
		return bookAuthorDes;
	}

	public void setBookAuthorDes(String bookAuthorDes) {
		this.bookAuthorDes = bookAuthorDes;
	}

	public String getBookStatus() {
		return bookStatus;
	}

	public void setBookStatus(String bookStatus) {
		this.bookStatus = bookStatus;
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
		this.createUserName = createUserName;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	
}