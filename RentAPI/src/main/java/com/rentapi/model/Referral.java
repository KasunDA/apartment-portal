package com.rentapi.model;


public class Referral {
	private String ReferralFirstName;
	private String ReferralLastName;
	private String EmailAddress;
	private int UserId;
	public String getReferralFirstName() {
		return ReferralFirstName;
	}
	public void setReferralFirstName(String referralFirstName) {
		ReferralFirstName = referralFirstName;
	}
	public String getReferralLastName() {
		return ReferralLastName;
	}
	public void setReferralLastName(String referralLastName) {
		ReferralLastName = referralLastName;
	}
	public String getEmailAddress() {
		return EmailAddress;
	}
	public void setEmailAddress(String emailAddress) {
		EmailAddress = emailAddress;
	}
	public int getUserId() {
		return UserId;
	}
	public void setUserId(int userId) {
		UserId = userId;
	}

}
