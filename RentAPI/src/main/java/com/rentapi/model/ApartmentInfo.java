package com.rentapi.model;

public class ApartmentInfo {
private Integer aptId;
private Integer aptNo;
private Integer BuildingNo;
private Integer NoOfBedrooms;
private Integer NoOfBathrooms;
private Integer Garage;
private Integer Sqft;
private Integer PropertyTypeID;

public Integer getAptId() {
	return aptId;
}

public void setAptId(Integer aptId) {
	this.aptId = aptId;
}

public Integer getAptNo() {
	return aptNo;

}

public void setAptNo(Integer aptNo) {
	this.aptNo = aptNo;
}

public Integer getNoOfBedrooms() {
	return NoOfBedrooms;
}

public void setNoOfBedrooms(Integer noOfBedrooms) {
	NoOfBedrooms = noOfBedrooms;
}

public Integer getBuildingNo() {
	return BuildingNo;
}

public void setBuildingNo(Integer buildingNo) {
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
}
