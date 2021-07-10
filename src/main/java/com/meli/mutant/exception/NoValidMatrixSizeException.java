package com.meli.mutant.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "The row and columns are smaller than the concurrency characters num.")
public class NoValidMatrixSizeException extends Exception{
	
	public NoValidMatrixSizeException() {
		super("The row and columns are smaller than the concurrency characters num.");
	}

}
