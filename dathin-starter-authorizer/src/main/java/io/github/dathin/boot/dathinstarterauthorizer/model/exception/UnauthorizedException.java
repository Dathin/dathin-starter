package io.github.dathin.boot.dathinstarterauthorizer.model.exception;

import io.github.dathin.boot.dathinstartermodel.exception.GenericException;
import org.springframework.http.HttpStatus;

public class UnauthorizedException extends GenericException {

	public UnauthorizedException() {
		super("The value from Authorization header was not good. It may be invalid, expired or absent", HttpStatus.UNAUTHORIZED.value());
	}

}
