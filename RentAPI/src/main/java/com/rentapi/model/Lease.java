package com.rentapi.model;

import java.util.Date;

public class Lease {
	private Integer leaseId;
	private Date leaseStartDate;
	private Date leaseEndDate;
	private Date leaseCreateDate;
	private String residentFirstName;
	private String residentLastName;
	private Integer residentId;
	
	public Integer getLeaseId() {
		return leaseId;
	}
	public void setLeaseId(Integer leaseId) {
		this.leaseId = leaseId;
	}
	public Date getLeaseStartDate() {
		return leaseStartDate;
	}
	public void setLeaseStartDate(Date leaseStartDate) {
		this.leaseStartDate = leaseStartDate;
	}
	public Date getLeaseEndDate() {
		return leaseEndDate;
	}
	public void setLeaseEndDate(Date leaseEnddate) {
		this.leaseEndDate = leaseEnddate;
	}
	public Date getLeaseCreateDate() {
		return leaseCreateDate;
	}
	public void setLeaseCreateDate(Date leaseCreateDate) {
		this.leaseCreateDate = leaseCreateDate;
	}
	public String getResidentFirstName() {
		return residentFirstName;
	}
	public void setResidentFirstName(String residentFirstName) {
		this.residentFirstName = residentFirstName;
	}
	public String getResidentLastName() {
		return residentLastName;
	}
	public void setResidentLastName(String residentLastName) {
		this.residentLastName = residentLastName;
	}
	public Integer getResidentId() {
		return residentId;
	}
	public void setResidentId(Integer residentId) {
		this.residentId = residentId;
	}

}
