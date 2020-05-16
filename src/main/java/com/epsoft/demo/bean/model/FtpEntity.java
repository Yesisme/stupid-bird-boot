package com.epsoft.demo.bean.model;

public class FtpEntity {
	
	private String ip;
	
	private int port;
	
	private String user;
	
	private String password;
	
	public FtpEntity(String ip,int port,String user,String password){
		this.ip=ip;
		this.port=port;
		this.user=user;
		this.password=password;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
