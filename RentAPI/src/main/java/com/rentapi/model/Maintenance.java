package com.rentapi.model;

import java.util.Date;

public class Maintenance {
	
	private Integer maintanenceID;
	private Integer accountID;
	private Date createDate;
	private Date closedDate;
	private String description;
	private Integer staffID;
	private Integer maintenanceStatusCodeID;
	
	public Integer getMaintanenceID() {
		return maintanenceID;
	}
	public void setMaintanenceID(Integer maintanenceID) {
		this.maintanenceID = maintanenceID;
	}
	public Integer getAccountID() {
		return accountID;
	}
	public void setAccountID(Integer accountID) {
		this.accountID = accountID;
	}
	public Date getClosedDate() {
		return closedDate;
	}
	public void setClosedDate(Date closedDate) {
		this.closedDate = closedDate;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Integer getStaffID() {
		return staffID;
	}
	public void setStaffID(Integer staffID) {
		this.staffID = staffID;
	}
	public Integer getMaintenanceStatusCodeID() {
		return maintenanceStatusCodeID;
	}
	public void setMaintenanceStatusCodeID(Integer maintenanceStatusCodeID) {
		this.maintenanceStatusCodeID = maintenanceStatusCodeID;
	}



}
