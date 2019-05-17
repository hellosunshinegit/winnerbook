package com.winnerbook.system.dto;

public class ShowUser {
	private String id;
	private String pId;
	private String name;
	private String file;

	public String getFile() {
		return file;
	}

	public void setFile(String file) {
		this.file = file;
	}

	public ShowUser(String id, String pId, String name, String file) {
		this.id = id;
		this.pId = pId;
		this.name = name;
		this.file = file;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getpId() {
		return pId;
	}

	public void setpId(String pId) {
		this.pId = pId;
	}

	public ShowUser() {
		super();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
