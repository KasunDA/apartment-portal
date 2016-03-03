package com.rentapi.model;

public class SearchQuery {
	
	private Integer noOfBedrooms;
	private Integer noOfBathrooms;
	private Integer patios;
	private Integer garages;
	private Integer sqft;
	
	public Integer getNoOfBedrooms() {
		return noOfBedrooms;
	}
	public void setNoOfBedrooms(Integer noOfBedrooms) {
		this.noOfBedrooms = noOfBedrooms;
	}
	public Integer getNoOfBathrooms() {
		return noOfBathrooms;
	}
	public void setNoOfBathrooms(Integer noOfBathrooms) {
		this.noOfBathrooms = noOfBathrooms;
	}
	public Integer getPatios() {
		return patios;
	}
	public void setPatios(Integer patios) {
		this.patios = patios;
	}
	public Integer getGarages() {
		return garages;
	}
	public void setGarages(Integer garages) {
		this.garages = garages;
	}
	public Integer getSqft() {
		return sqft;
	}
	public void setSqft(Integer sqft) {
		this.sqft = sqft;
	}
}
