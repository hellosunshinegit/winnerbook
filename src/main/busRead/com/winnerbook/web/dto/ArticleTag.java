package com.winnerbook.web.dto;

import java.util.Date;

public class ArticleTag {
    private Integer atId;

    private Integer articleId;

    private Integer tagId;

    private Date atCreatedate;

    public Integer getAtId() {
        return atId;
    }

    public void setAtId(Integer atId) {
        this.atId = atId;
    }

    public Integer getArticleId() {
        return articleId;
    }

    public void setArticleId(Integer articleId) {
        this.articleId = articleId;
    }

    public Integer getTagId() {
        return tagId;
    }

    public void setTagId(Integer tagId) {
        this.tagId = tagId;
    }

    public Date getAtCreatedate() {
        return atCreatedate;
    }

    public void setAtCreatedate(Date atCreatedate) {
        this.atCreatedate = atCreatedate;
    }
}