package com.cloudcomputing.usermanage.model;

public class User {
	
	private String username;
	private String password;
	private String location;
	private String role;
	
	public User(){
		
	}
	
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role){
		this.role=role;
	}

}
