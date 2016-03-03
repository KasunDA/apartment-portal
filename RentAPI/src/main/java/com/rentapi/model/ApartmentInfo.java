package com.rentapi.model;

public class ApartmentInfo extends ApartmentInfoBase {
	private String aptNo;
	private String BuildingNo;
	private Integer NoOfBedrooms;
	private Integer NoOfBathrooms;
	private Integer Garage;
	private Integer Sqft;
	private Integer PropertyTypeID;
	private String propertyTypeName;
	private Float rent;

	public String getAptNo() {
		return aptNo;

	}

	public void setAptNo(String aptNo) {
		this.aptNo = aptNo;
	}

	public Integer getNoOfBedrooms() {
		return NoOfBedrooms;
	}

	public void setNoOfBedrooms(Integer noOfBedrooms) {
		NoOfBedrooms = noOfBedrooms;
	}

	public String getBuildingNo() {
		return BuildingNo;
	}

	public void setBuildingNo(String buildingNo) {
		BuildingNo = buildingNo;
	}

	public Integer getNoOfBathrooms() {
		return NoOfBathrooms;
	}

	public void setNoOfBathrooms(Integer noOfBathrooms) {
		NoOfBathrooms = noOfBathrooms;
	}

	public Integer getGarage() {
		return Garage;
	}

	public void setGarage(Integer garage) {
		Garage = garage;
	}

	public Integer getSqft() {
		return Sqft;
	}

	public void setSqft(Integer sqft) {
		Sqft = sqft;
	}

	public Integer getPropertyTypeID() {
		return PropertyTypeID;
	}

	public void setPropertyTypeID(Integer propertyTypeID) {
		PropertyTypeID = propertyTypeID;
	}

	public String getPropertyTypeName() {
		return propertyTypeName;
	}

	public void setPropertyTypeName(String propertyTypeName) {
		this.propertyTypeName = propertyTypeName;
	}

	public Float getRent() {
		return rent;
	}

	public void setRent(Float rent) {
		this.rent = rent;
	}
}
