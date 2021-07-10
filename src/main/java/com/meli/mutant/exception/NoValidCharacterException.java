package com.meli.mutant.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "Marix has not valid character")
public class NoValidCharacterException extends Exception{
	
	public NoValidCharacterException() {
		super("Marix has not valid character");
	}
	
}
