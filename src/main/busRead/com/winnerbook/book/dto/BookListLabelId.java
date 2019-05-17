package com.winnerbook.book.dto;

import java.util.Date;

/**
 * 书单列表和书籍对应id
 * 
 * @author hxs
 * 
 */
public class BookListLabelId {
	private Integer id;

	private Integer typeId;

	private Integer labelId;

	private Date createDate;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Integer getTypeId() {
		return typeId;
	}

	public void setTypeId(Integer typeId) {
		this.typeId = typeId;
	}

	public Integer getLabelId() {
		return labelId;
	}

	public void setLabelId(Integer labelId) {
		this.labelId = labelId;
	}

	
	
	

}