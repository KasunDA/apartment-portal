
package com.rentapi.model;

import java.util.Date;

public class Referral extends ReferralBase {
	private String guestName; 
	private String emailAddress;
	private Integer userId;
	private String residentName;
	private Integer residentId;
	private Date approvedDate;
	private Integer approvedBy;
	private String approvedByName;
	
	private Address address;
	
	public String getEmailAddress() {
		return emailAddress;
	}
	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getGuestName() {
		return guestName;
	}
	public void setGuestName(String guestName) {
		this.guestName = guestName;
	}
	public Integer getResidentId() {
		return residentId;
	}
	public void setResidentId(Integer residentId) {
		this.residentId = residentId;
	}
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}
	public Date getApprovedDate() {
		return approvedDate;
	}
	public void setApprovedDate(Date approvedDate) {
		this.approvedDate = approvedDate;
	}
	public Integer getApprovedBy() {
		return approvedBy;
	}
	public void setApprovedBy(Integer approvedBy) {
		this.approvedBy = approvedBy;
	}
	public String getApprovedByName() {
		return approvedByName;
	}
	public void setApprovedByName(String approvedByName) {
		this.approvedByName = approvedByName;
	}
	public String getResidentName() {
		return residentName;
	}
	public void setResidentName(String residentName) {
		this.residentName = residentName;
	}
}
