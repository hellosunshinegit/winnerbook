package com.winnerbook.system.dto;

import java.math.BigDecimal;
import java.util.Date;

/**
 * B2bDictionary entity. @author MyEclipse Persistence Tools
 * 字典表
 */
public class Dictionary implements java.io.Serializable {

	// Fields
	private static final long serialVersionUID = 1L;
	private Long dicId;
	private String dicItemcode;
	private String dicItemcodename;
	private String dicItemname;
	private String dicItemvalue;
	private BigDecimal dicItemsort;
	private Date dicCreatedate;
	private Date dicUpdatedate;

	// Constructors

	/** default constructor */
	public Dictionary() {
	}

	/** full constructor */
	public Dictionary(Long dicId,String dicItemcode,
			String dicItemcodename, String dicItemname, String dicItemvalue,
			BigDecimal dicItemsort, Date dicCreatedate, Date dicUpdatedate) {
		this.dicId = dicId;
		this.dicItemcode = dicItemcode;
		this.dicItemcodename = dicItemcodename;
		this.dicItemname = dicItemname;
		this.dicItemvalue = dicItemvalue;
		this.dicItemsort = dicItemsort;
		this.dicCreatedate = dicCreatedate;
		this.dicUpdatedate = dicUpdatedate;
	}
	

	public Long getDicId() {
		return dicId;
	}

	public void setDicId(Long dicId) {
		this.dicId = dicId;
	}

	public String getDicItemcode() {
		return dicItemcode;
	}

	public void setDicItemcode(String dicItemcode) {
		this.dicItemcode = dicItemcode;
	}

	public String getDicItemcodename() {
		return dicItemcodename;
	}

	public void setDicItemcodename(String dicItemcodename) {
		this.dicItemcodename = dicItemcodename;
	}

	public String getDicItemname() {
		return dicItemname;
	}

	public void setDicItemname(String dicItemname) {
		this.dicItemname = dicItemname;
	}

	public String getDicItemvalue() {
		return dicItemvalue;
	}

	public void setDicItemvalue(String dicItemvalue) {
		this.dicItemvalue = dicItemvalue;
	}

	public BigDecimal getDicItemsort() {
		return dicItemsort;
	}

	public void setDicItemsort(BigDecimal dicItemsort) {
		this.dicItemsort = dicItemsort;
	}

	public Date getDicCreatedate() {
		return dicCreatedate;
	}

	public void setDicCreatedate(Date dicCreatedate) {
		this.dicCreatedate = dicCreatedate;
	}

	public Date getDicUpdatedate() {
		return dicUpdatedate;
	}

	public void setDicUpdatedate(Date dicUpdatedate) {
		this.dicUpdatedate = dicUpdatedate;
	}

}