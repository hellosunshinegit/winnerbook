package com.winnerbook.system.dto;

public class UcSystemCodeCache {
    private Long id;

    private String code;

    private String name;

    private String listSql;

    private String isCached;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getListSql() {
        return listSql;
    }

    public void setListSql(String listSql) {
        this.listSql = listSql == null ? null : listSql.trim();
    }

    public String getIsCached() {
        return isCached;
    }

    public void setIsCached(String isCached) {
        this.isCached = isCached == null ? null : isCached.trim();
    }
}