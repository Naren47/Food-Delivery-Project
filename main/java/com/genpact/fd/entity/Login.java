package com.genpact.fd.entity;

import jakarta.persistence.Entity;


public class Login {
	private Long number;

	private String password;
	
	public Login() {}
	
	public Login(Long number, String password) {
		super();
		this.number = number;
		this.password = password;
	}
	public long getNumber() {
		return number;
	}
	public void setNumber(Long number) {
		this.number = number;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
}
