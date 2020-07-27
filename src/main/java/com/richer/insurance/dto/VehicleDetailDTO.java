package com.richer.insurance.dto;

import java.util.Date;

import com.opencsv.bean.CsvBindByName;

public class VehicleDetailDTO {
	@CsvBindByName(column = "Registration Number")
	private String registrationNumber;
	@CsvBindByName(column = "Policy Number")
	private String policyNumber;
	@CsvBindByName(column = "Start Date")
	private Date startDate;
	@CsvBindByName(column = "End Date")
	private Date endDate;
	

	 



	@Override
	public String toString() {
		return "VehicleDetailDTO [registrationNumber=" + registrationNumber + ", policyNumber=" + policyNumber
				+ ", startDate=" + startDate + ", endDate=" + endDate + "]";
	}



	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((endDate == null) ? 0 : endDate.hashCode());
		result = prime * result + ((policyNumber == null) ? 0 : policyNumber.hashCode());
		result = prime * result + ((registrationNumber == null) ? 0 : registrationNumber.hashCode());
		result = prime * result + ((startDate == null) ? 0 : startDate.hashCode());
		return result;
	}



	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		VehicleDetailDTO other = (VehicleDetailDTO) obj;
		if (endDate == null) {
			if (other.endDate != null)
				return false;
		} else if (!endDate.equals(other.endDate))
			return false;
		if (policyNumber == null) {
			if (other.policyNumber != null)
				return false;
		} else if (!policyNumber.equals(other.policyNumber))
			return false;
		if (registrationNumber == null) {
			if (other.registrationNumber != null)
				return false;
		} else if (!registrationNumber.equals(other.registrationNumber))
			return false;
		if (startDate == null) {
			if (other.startDate != null)
				return false;
		} else if (!startDate.equals(other.startDate))
			return false;
		return true;
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



	public VehicleDetailDTO(String registrationNumber, String policyNumber, Date startDate, Date endDate) {
		super();
		this.registrationNumber = registrationNumber;
		this.policyNumber = policyNumber;
		this.startDate = startDate;
		this.endDate = endDate;
	}



	public String getPolicyNumber() {
		return policyNumber;
	}



	public void setPolicyNumber(String policyNumber) {
		this.policyNumber = policyNumber;
	}



	public VehicleDetailDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	 

	public String getRegistrationNumber() {
		return registrationNumber;
	}

	public void setRegistrationNumber(String registrationNumber) {
		this.registrationNumber = registrationNumber;
	}
	
	

}
