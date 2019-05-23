package com.winnerbook.wx.dto;

import java.util.Date;

public class WxInfo {
	private Integer id;

	private String appid;

	private String appsecret;

	private String accesstoken;

	private Date accesstokendate;

	private String jsapiticket;

	private Date jsapiticketdate;

	private String redirectUri;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getAppid() {
		return appid;
	}

	public void setAppid(String appid) {
		this.appid = appid == null ? null : appid.trim();
	}

	public String getAppsecret() {
		return appsecret;
	}

	public void setAppsecret(String appsecret) {
		this.appsecret = appsecret == null ? null : appsecret.trim();
	}

	public String getAccesstoken() {
		return accesstoken;
	}

	public void setAccesstoken(String accesstoken) {
		this.accesstoken = accesstoken == null ? null : accesstoken.trim();
	}

	public Date getAccesstokendate() {
		return accesstokendate;
	}

	public void setAccesstokendate(Date accesstokendate) {
		this.accesstokendate = accesstokendate;
	}

	public String getJsapiticket() {
		return jsapiticket;
	}

	public void setJsapiticket(String jsapiticket) {
		this.jsapiticket = jsapiticket == null ? null : jsapiticket.trim();
	}

	public Date getJsapiticketdate() {
		return jsapiticketdate;
	}

	public void setJsapiticketdate(Date jsapiticketdate) {
		this.jsapiticketdate = jsapiticketdate;
	}

	public String getRedirectUri() {
		return redirectUri;
	}

	public void setRedirectUri(String redirectUri) {
		this.redirectUri = redirectUri;
	}

}