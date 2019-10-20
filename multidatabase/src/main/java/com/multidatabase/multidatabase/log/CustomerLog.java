/**
 * 
 */
package com.multidatabase.multidatabase.log;

import java.util.Date;

/**
 * @author sebahatt
 *
 */
public class CustomerLog {

	Date dateAndTime;
	String message;
	String ErrorCode;

	public Date getDateAndTime() {
		return dateAndTime;
	}

	public void setDateAndTime(Date dateAndTime) {
		this.dateAndTime = dateAndTime;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getErrorCode() {
		return ErrorCode;
	}

	public void setErrorCode(String errorCode) {
		ErrorCode = errorCode;
	}

}
