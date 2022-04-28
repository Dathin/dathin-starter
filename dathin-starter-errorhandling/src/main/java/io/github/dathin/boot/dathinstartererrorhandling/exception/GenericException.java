package io.github.dathin.boot.dathinstartererrorhandling.exception;

import org.springframework.http.HttpStatus;

public class GenericException extends RuntimeException {
    private String error;

    private HttpStatus status;

    public GenericException(HttpStatus status) {
        this.status = status;
    }

    public GenericException(String error, HttpStatus status) {
        this.error = error;
        this.status = status;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }
}
