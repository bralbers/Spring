package com.fdmgroup.springloginregisterexercise.exception;

import org.jboss.logging.Logger;

public class UserException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	String message;
	private Logger myLogger = Logger.getLogger(UserException.class);

	public UserException() {
		super();
		myLogger.info("INFO:Default exception constructor was called");
	}

	public UserException(String message) {
		super();
		this.message = message;
		myLogger.info("INFO:Constructor that takes in a supplied message was called");
	}
}