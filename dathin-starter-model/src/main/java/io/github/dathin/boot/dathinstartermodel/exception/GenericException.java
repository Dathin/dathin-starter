package io.github.dathin.boot.dathinstartermodel.exception;

public class GenericException extends RuntimeException {
    private String error;

    private int status;

    public GenericException() {
    }
    public GenericException(int status) {
        this.status = status;
    }

    public GenericException(String error, int status) {
        this.error = error;
        this.status = status;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
