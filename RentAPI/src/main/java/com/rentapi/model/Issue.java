package com.rentapi.model;

import java.util.Date;

public class Issue {
	
	private String title;
	private String description;
	private Integer maintenanceID;
	private Integer residentID;
	private Date createDate;
	private Date closedDate;
	private Integer staffID;
	private Integer maintenanceStatusCodeID;
	private String statusName;
	private String firstName;
	private String lastName;
	private String staffFirstName;
	private String staffLastName;

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	public Integer getMaintenanceID() {
		return maintenanceID;
	}
	public void setMaintenanceID(Integer maintenanceID) {
		this.maintenanceID = maintenanceID;
	}
	public Integer getResidentID() {
		return residentID;
	}
	public void setResidentID(Integer residentID) {
		this.residentID = residentID;
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

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getStatusName() {
		return statusName;
	}

	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}

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

	public String getStaffFirstName() {
		return staffFirstName;
	}

	public void setStaffFirstName(String staffFirstName) {
		this.staffFirstName = staffFirstName;
	}

	public String getStaffLastName() {
		return staffLastName;
	}

	public void setStaffLastName(String staffLastName) {
		this.staffLastName = staffLastName;
	}
}
