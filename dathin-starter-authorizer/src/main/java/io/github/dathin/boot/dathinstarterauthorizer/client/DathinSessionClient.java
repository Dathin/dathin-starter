package io.github.dathin.boot.dathinstarterauthorizer.client;

import feign.RequestLine;

public interface DathinSessionClient {

    @RequestLine("POST /user/validate")
    void userValidate();

}
