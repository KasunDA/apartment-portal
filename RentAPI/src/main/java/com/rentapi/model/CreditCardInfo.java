package com.rentapi.model;

public class CreditCardInfo {

	private String name;
	private String cardNumber;
	private String expirationDate;
	private String securityCode;
	private String billAddress1;
	private String billAddress2;
	private String billCity;
	private String billState;
	private String billZipCode;

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

	public String getBillAddress1() {
		return billAddress1;
	}

	public void setBillAddress1(String billAddress1) {
		this.billAddress1 = billAddress1;
	}

	public String getBillAddress2() {
		return billAddress2;
	}

	public void setBillAddress2(String billAddress2) {
		this.billAddress2 = billAddress2;
	}

	public String getBillCity() {
		return billCity;
	}

	public void setBillCity(String billCity) {
		this.billCity = billCity;
	}

	public String getBillState() {
		return billState;
	}

	public void setBillState(String billState) {
		this.billState = billState;
	}

	public String getBillZipCode() {
		return billZipCode;
	}

	public void setBillZipCode(String billZipCode) {
		this.billZipCode = billZipCode;
	}

	public String toString() {
		return String.format(
				"Name=%s, Card Number=%s, Expiration Date=%s, Security Code=%s, Address1=%s, Address2=%s, City=%s, State=%s, Zip Cpde=%s",
				name, cardNumber, expirationDate, securityCode, billAddress1, billAddress2, billCity, billState, billZipCode);
	}

}
