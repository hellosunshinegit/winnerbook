package com.winnerbook.base.frame.content;

import java.io.Serializable;

import com.winnerbook.system.dto.User;



public class UserContext implements Serializable {
	
	private static final long serialVersionUID = 3536587780083713324L;

	private User user;
	

	private String basePath;
	
	private String fileServerPath;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	/**
	 * @return the basePath
	 */
	public String getBasePath() {
		return basePath;
	}

	/**
	 * @param basePath the basePath to set
	 */
	public void setBasePath(String basePath) {
		this.basePath = basePath;
	}

	/**
	 * @return the fileServerPath
	 */
	public String getFileServerPath() {
		return fileServerPath;
	}

	/**
	 * @param fileServerPath the fileServerPath to set
	 */
	public void setFileServerPath(String fileServerPath) {
		this.fileServerPath = fileServerPath;
	}

	
}
