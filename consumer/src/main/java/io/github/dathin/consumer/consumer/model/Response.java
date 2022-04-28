package io.github.dathin.consumer.consumer.model;

import lombok.Data;

@Data
public class Response {

    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
