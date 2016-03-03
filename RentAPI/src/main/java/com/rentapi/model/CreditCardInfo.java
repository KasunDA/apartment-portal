package com.rentapi.model;

public class CreditCardInfo {

	private String cardNumber;
	private String expirationDate;
	private String securityCode;
	private String name;
	private String phone;
	private Address billingAddress;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}

	public String getExpirationDate() {
		return expirationDate;
	}

	public void setExpirationDate(String expirationDate) {
		this.expirationDate = expirationDate;
	}

	public String getSecurityCode() {
		return securityCode;
	}

	public void setSecurityCode(String securityCode) {
		this.securityCode = securityCode;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Address getBillingAddress() {
		return billingAddress == null ? new Address() : billingAddress;
	}

	public void setBillingAddress(Address billingAddress) {
		this.billingAddress = billingAddress;
	}

	public String toString() {
		return String.format("Name=%s, Card Number=%s, Expiration Date=%s, Security Code=%s", name, cardNumber,
				expirationDate, securityCode);
	}

}
