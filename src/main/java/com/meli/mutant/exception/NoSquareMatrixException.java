package com.meli.mutant.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "Must be square matrix.")
public class NoSquareMatrixException extends Exception{
	
	public NoSquareMatrixException() {
		super("Must be square matrix.");
	}
	
}
