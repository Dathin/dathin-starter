package io.github.dathin.boot.dathinstarterclient.model.exception;

import io.github.dathin.boot.dathinstartermodel.exception.GenericException;
import org.springframework.http.HttpStatus;

public class UnsupportedDownstreamIntegrationException extends GenericException {
    public UnsupportedDownstreamIntegrationException() {
        super("Unexpected downstream integration", HttpStatus.INTERNAL_SERVER_ERROR.value());
    }
}
