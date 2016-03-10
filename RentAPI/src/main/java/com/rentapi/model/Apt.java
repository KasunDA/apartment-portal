package com.rentapi.model;

public class Apt {

private Integer apartmentId;
private String aptNo;
private String buildingNo;
private int bedrooms;
private int bathrooms;
private int garages;
private int sft;
private int propertyType;
private float rent;
private String propertyTypeName;
private Boolean isActive;

public Integer getApartmentId() {
	return apartmentId;
}
public void setApartmentId(Integer propertyID) {
	this.apartmentId = propertyID;
}
public String getAptNo() {
	return aptNo;
}
public void setAptNo(String aptNo) {
	this.aptNo = aptNo;
}
public String getBuildingNo() {
	return buildingNo;
}
public void setBuildingNo(String buildingNo) {
	this.buildingNo = buildingNo;
}
public int getBedrooms() {
	return bedrooms;
}
public void setBedrooms(int bedrooms) {
	this.bedrooms = bedrooms;
}
public int getBathrooms() {
	return bathrooms;
}
public void setBathrooms(int bathrooms) {
	this.bathrooms = bathrooms;
}
public int getGarages() {
	return garages;
}
public void setGarages(int garages) {
	this.garages = garages;
}
public int getSft() {
	return sft;
}
public void setSft(int sft) {
	this.sft = sft;
}
public int getPropertyType() {
	return propertyType;
}
public void setPropertyType(int propertyType) {
	this.propertyType = propertyType;
}
public String getPropertyTypeName() {
	return propertyTypeName;
}
public void setPropertyTypeName(String propertyTypeName) {
	this.propertyTypeName = propertyTypeName;
}
public Boolean getIsActive() {
	return isActive;
}
public void setIsActive(Boolean isActive) {
	this.isActive = isActive;
}
public float getRent() {
	return rent;
}
public void setRent(float rent) {
	this.rent = rent;
}
}