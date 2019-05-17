package com.winnerbook.base.common;

import java.util.List;

public class UITreeBean {
	private String id;
	private String pId;
	private String name;
	private boolean isParent=false;
	private boolean open=false;
	private List<UITreeBean> children;

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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isParent() {
		return isParent;
	}

	public void setParent(boolean isParent) {
		this.isParent = isParent;
	}

	public boolean isOpen() {
		return open;
	}

	public void setOpen(boolean open) {
		this.open = open;
	}

	public List<UITreeBean> getChildren() {
		return children;
	}

	public void setChildren(List<UITreeBean> children) {
		this.children = children;
	}

}
