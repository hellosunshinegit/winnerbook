package com.winnerbook.base.common.dto;

public class CacheCodeBean {
	private String code;
	private String name;
	private String listSql;
	private String isCached;
	private String configType;
	public static final String CONFIG_TYPE_DICTIONARY_DATA = "DICTIONARY_DATA";

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIsCached() {
		return isCached;
	}

	public void setIsCached(String isCached) {
		this.isCached = isCached;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getListSql() {
		return listSql;
	}

	public void setListSql(String listSql) {
		this.listSql = listSql;
	}

	public String getConfigType() {
		return configType;
	}

	public void setConfigType(String configType) {
		this.configType = configType;
	}

}
