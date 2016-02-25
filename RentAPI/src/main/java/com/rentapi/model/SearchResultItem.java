package com.rentapi.model;

public class SearchResultItem {
	private String floorPlan;
	private Integer noOfBedrooms;
	private Integer noOfBathrooms;
	private Integer sft;
	private Float rent;

	public String getFloorPlan() {
		return floorPlan;
	}

	public void setFloorPlan(String floorPlan) {
		this.floorPlan = floorPlan;
	}

	public Integer getNoOfBathrooms() {
		return noOfBathrooms;
	}

	public void setNoOfBathrooms(Integer noOfBathrooms) {
		this.noOfBathrooms = noOfBathrooms;
	}

	public Integer getNoOfBedrooms() {
		return noOfBedrooms;
	}

	public void setNoOfBedrooms(Integer noOfBedrooms) {
		this.noOfBedrooms = noOfBedrooms;
	}

	public Integer getSft() {
		return sft;
	}

	public void setSft(Integer sft) {
		this.sft = sft;
	}

	public Float getRent() {
		return rent;
	}

	public void setRent(Float rent) {
		this.rent = rent;
	}
}
