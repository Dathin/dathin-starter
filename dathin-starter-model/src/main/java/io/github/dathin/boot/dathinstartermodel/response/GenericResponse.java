package io.github.dathin.boot.dathinstartermodel.response;

public class GenericResponse {

    private final String error;


    public GenericResponse(String error) {
        this.error = error;
    }

    public String getError() {
        return error;
    }

}
