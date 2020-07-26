package com.richer.insurance.exception;

public class VehicleInsuranceException extends Exception{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String errorCode;
	String errorMessage;
	public String getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}
	public String getErrorMessage() {
		return errorMessage;
	}
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	public VehicleInsuranceException(String errorCode, String errorMessage) {
		super();
		this.errorCode = errorCode;
		this.errorMessage = errorMessage;
	}
	
	

}
