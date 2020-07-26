package com.richer.insurance.model;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity  
public class Vehicle 
{ 
@Id  
private String registrationNumber;

private String make;

private String model;

private String year;

private String description;
@OneToOne(targetEntity=VehicleInsurance.class,cascade=CascadeType.ALL)
@JoinColumn(name="registrationNumber",referencedColumnName="registrationNumber")
private VehicleInsurance vehicleInsurances;




public Vehicle() {
	super();
	// TODO Auto-generated constructor stub
}
public Vehicle(String registrationNumber, String make, String model, String year, String description,
		VehicleInsurance vehicleInsurances) {
	super();
	this.registrationNumber = registrationNumber;
	this.make = make;
	this.model = model;
	this.year = year;
	this.description = description;
	this.vehicleInsurances = vehicleInsurances;
}
@Override
public String toString() {
	return "Vehicle [registrationNumber=" + registrationNumber + ", make=" + make + ", model=" + model + ", year="
			+ year + ", description=" + description + ", vehicleInsurances=" + vehicleInsurances + "]";
}
public String getRegistrationNumber() {
	return registrationNumber;
}
public void setRegistrationNumber(String registrationNumber) {
	this.registrationNumber = registrationNumber;
}
public String getMake() {
	return make;
}
public void setMake(String make) {
	this.make = make;
}
public String getModel() {
	return model;
}
public void setModel(String model) {
	this.model = model;
}
public String getYear() {
	return year;
}
public void setYear(String year) {
	this.year = year;
}
public String getDescription() {
	return description;
}
public void setDescription(String description) {
	this.description = description;
}
public VehicleInsurance getVehicleInsurances() {
	return vehicleInsurances;
}
public void setVehicleInsurances(VehicleInsurance vehicleInsurances) {
	this.vehicleInsurances = vehicleInsurances;
}



}
