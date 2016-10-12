package com.merobo.dtos;

public class UserTo {

	private String id;
	private String username;
	private String password;
	private String name;
	private String teamName;
	// Timeout for cookies
	private long cookieTimeout = 15;
	private String loginTime;
	private String adminPasscode;
	private String adminToken;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTeamName() {
		return teamName;
	}

	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}

	public long getCookieTimeout() {
		return cookieTimeout;
	}

	public String getLoginTime() {
		return loginTime;
	}

	public void setLoginTime(String loginTime) {
		this.loginTime = loginTime;
	}

	public String getAdminPasscode() {
		return adminPasscode;
	}

	public void setAdminPasscode(String adminPasscode) {
		this.adminPasscode = adminPasscode;
	}

	public String getAdminToken() {
		return adminToken;
	}

	public void setAdminToken(String adminToken) {
		this.adminToken = adminToken;
	}

	@Override
	public String toString() {
		return "UserTo [id=" + id + ", username=" + username + ", password=" + password + ", name=" + name
				+ ", teamName=" + teamName + ", cookieTimeout=" + cookieTimeout + ", loginTime=" + loginTime
				+ ", adminPasscode=" + adminPasscode + ", adminToken=" + adminToken + "]";
	}

}
