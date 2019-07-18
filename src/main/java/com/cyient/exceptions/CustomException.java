package com.cyient.exceptions;

import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)

public class CustomException extends RuntimeException {
	public CustomException(String message)
	{
        super(message);
	}

}
