package com.rentapi.model;

public class ResidentIssue {
	private Integer residentIssuesID;
	private Integer maintenanceID;
	private Integer residentID;
	private String title;
	private String description;

	public Integer getResidentIssuesID() {
		return residentIssuesID;
	}

	public void setResidentIssuesID(Integer residentIssuesID) {
		this.residentIssuesID = residentIssuesID;
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

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
