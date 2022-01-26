package com.tc.farecapping.exceptions;

import java.io.IOException;

public class InvalidFileException extends IOException{
	
	public InvalidFileException() {
		super();
	}
	
	public InvalidFileException(String message) {
		super(message);
	}
	
	public InvalidFileException(String message, Throwable e) {
		super(message, e);
	}

}
