package io.github.dathin.boot.dathinstartererrorhandling.response;

public class GenericResponse {

    private final String error;


    public GenericResponse(String error) {
        this.error = error;
    }

    public String getError() {
        return error;
    }

}
