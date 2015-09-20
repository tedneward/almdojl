package com.microsoft.example.models;


/**
 * Simple model basically mimicking what's in the database
 * as part of the employees table.
 */
public class Employee
{
	public Employee() { }
	public Employee(int id, String username, String password) {
		this.id = id;
		this.username = username;
		this.password = password;
	}
	
	public int getID() { return id; }
	
	public String getUsername() { return username; }
	public void setUsername(String value) { username = value; }
	
	public String getPassword() { return password; }
	public void setPassword(String value) { password = value; }
	
	@Override
	public String toString() {
		return "[Employee " + 
			"id:" + id + " " +
			"username:" + username + " " +
			"password:" + password + " " +
		"]";
	}
	
	private int id;
	private String username;
	private String password;
}