package com.tc.farecapping.exceptions;

public class FareCappingException extends Exception{
	
	public FareCappingException() {
		super();
	}
	
	public FareCappingException(String message) {
		super(message);
	}
	
	public FareCappingException(String message, Throwable e) {
		super(message, e);
	}

}
