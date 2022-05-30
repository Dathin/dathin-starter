package io.github.dathin.boot.dathinstarterclient.model.exception;

import io.github.dathin.boot.dathinstartermodel.exception.GenericException;

public class UnsupportedDownstreamIntegrationException extends GenericException {
    public UnsupportedDownstreamIntegrationException() {
        super("Unexpected downstream integration", 500);
    }
}
