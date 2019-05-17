package com.winnerbook.system.dto;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * @author hanxiaoshuang
 * @date 2016/2/24 操作日志
 * 
 */
public class UcSystemLogRecord implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long logId;
	private String logDes;
	private String logIp;
	private String logType;
	private String logSource;
	private Date logCreateDate;
	private Long logCreateUserId;
	private String logCreateUserName;

	public Long getLogId() {
		return logId;
	}

	public void setLogId(Long logId) {
		this.logId = logId;
	}

	public String getLogDes() {
		return logDes;
	}

	public void setLogDes(String logDes) {
		this.logDes = logDes == null ? null : logDes.trim();
	}

	public String getLogIp() {
		return logIp;
	}

	public void setLogIp(String logIp) {
		this.logIp = logIp == null ? null : logIp.trim();
	}

	public String getLogType() {
		return logType;
	}

	public void setLogType(String logType) {
		this.logType = logType == null ? null : logType.trim();
	}

	public Date getLogCreateDate() {
		return logCreateDate;
	}

	public void setLogCreateDate(Date logCreateDate) {
		this.logCreateDate = logCreateDate;
	}

	public Long getLogCreateUserId() {
		return logCreateUserId;
	}

	public void setLogCreateUserId(Long logCreateUserId) {
		this.logCreateUserId = logCreateUserId;
	}

	public String getLogCreateUserName() {
		return logCreateUserName;
	}

	public void setLogCreateUserName(String logCreateUserName) {
		this.logCreateUserName = logCreateUserName == null ? null
				: logCreateUserName.trim();
	}

	public String getLogSource() {
		return logSource;
	}

	public void setLogSource(String logSource) {
		this.logSource = logSource;
	}
	
	
}