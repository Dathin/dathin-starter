package io.github.dathin.boot.dathinstarterclient.model.exception;

import io.github.dathin.boot.dathinstartermodel.exception.GenericException;
import org.springframework.http.HttpStatus;

public class UnreadableClientErrorException extends GenericException {
    public UnreadableClientErrorException() {
        super("Error reading client body", HttpStatus.INTERNAL_SERVER_ERROR.value());
    }
}
