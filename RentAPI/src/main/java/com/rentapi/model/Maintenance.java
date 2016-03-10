package com.rentapi.model;

import java.util.Date;

public class Maintenance {
	
	private Integer maintenanceID;
	private Integer residentID;
	private Date createDate;
	private Date closedDate;
	private String description;
	private Integer staffID;
	private Integer maintenanceStatusCodeID;
	
	public Integer getMaintenanceID() {
		return maintenanceID;
	}
	public void setMaintenanceID(Integer maintanenceID) {
		this.maintenanceID = maintanenceID;
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
	public Integer getResidentID() {
		return residentID;
	}
	public void setResidentID(Integer residentID) {
		this.residentID = residentID;
	}



}
