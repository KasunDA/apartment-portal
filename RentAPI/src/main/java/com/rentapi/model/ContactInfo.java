package com.rentapi.model;

public class ContactInfo {
	
	private String firstName;
	private String lastName;
	private String message;
	private String emailAddress;
	
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getemailAddress() {
		return emailAddress;
	}
	public void setemailAddress(String yourEmail) {
		this.emailAddress = yourEmail;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
}
