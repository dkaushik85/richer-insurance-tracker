package com.richer.insurance.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class VehicleInsurance {
	@Id 
	private String registrationNumber;
	private String policyNumber;

	private Date startDate;
	private Date endDate;

	public VehicleInsurance() {
		super();
		// TODO Auto-generated constructor stub
	}

	 

	



	@Override
	public String toString() {
		return "VehicleInsurance [registrationNumber=" + registrationNumber + ", policyNumber=" + policyNumber
				+ ", startDate=" + startDate + ", endDate=" + endDate + "]";
	}







	public VehicleInsurance(String registrationNumber, String policyNumber, Date startDate, Date endDate) {
		super();
		this.registrationNumber = registrationNumber;
		this.policyNumber = policyNumber;
		this.startDate = startDate;
		this.endDate = endDate;
	}







	public String getRegistrationNumber() {
		return registrationNumber;
	}

	public void setRegistrationNumber(String registrationNumber) {
		this.registrationNumber = registrationNumber;
	}

	public String getPolicyNumber() {
		return policyNumber;
	}

	public void setPolicyNumber(String policyNumber) {
		this.policyNumber = policyNumber;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

}
