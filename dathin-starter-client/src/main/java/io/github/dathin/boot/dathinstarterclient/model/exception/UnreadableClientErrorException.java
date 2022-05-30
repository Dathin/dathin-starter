package io.github.dathin.boot.dathinstarterclient.model.exception;

import io.github.dathin.boot.dathinstartermodel.exception.GenericException;

public class UnreadableClientErrorException extends GenericException {
    public UnreadableClientErrorException() {
        super("Error reading client body", 500);
    }
}
