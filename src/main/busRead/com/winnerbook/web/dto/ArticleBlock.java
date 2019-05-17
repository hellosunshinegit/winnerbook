package com.winnerbook.web.dto;

import java.util.Date;

public class ArticleBlock {
	private Integer abId;

	private Integer articleId;

	private Integer blockId;

	private String blockStr;

	private Date abCreatedate;

	public Integer getAbId() {
		return abId;
	}

	public void setAbId(Integer abId) {
		this.abId = abId;
	}

	public Integer getArticleId() {
		return articleId;
	}

	public void setArticleId(Integer articleId) {
		this.articleId = articleId;
	}

	public Integer getBlockId() {
		return blockId;
	}

	public void setBlockId(Integer blockId) {
		this.blockId = blockId;
	}

	public Date getAbCreatedate() {
		return abCreatedate;
	}

	public void setAbCreatedate(Date abCreatedate) {
		this.abCreatedate = abCreatedate;
	}

	public String getBlockStr() {
		return blockStr;
	}

	public void setBlockStr(String blockStr) {
		this.blockStr = blockStr;
	}

}