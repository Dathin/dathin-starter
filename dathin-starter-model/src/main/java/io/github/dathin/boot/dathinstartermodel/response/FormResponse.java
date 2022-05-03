package io.github.dathin.boot.dathinstartermodel.response;

public class FormResponse {

    private String field;

    private String error;

    public FormResponse() {
    }

    public FormResponse(String field, String error) {
        this.field = field;
        this.error = error;
    }

    public String getField() {
        return field;
    }

    public String getError() {
        return error;
    }

}
