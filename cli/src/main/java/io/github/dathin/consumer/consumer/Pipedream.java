package io.github.dathin.consumer.consumer;

import feign.HeaderMap;
import feign.Headers;
import feign.Param;
import feign.RequestLine;

import java.util.Collections;
import java.util.Map;

public interface Pipedream {

    @Headers("authorization: {authorization}")
    @RequestLine("GET /")
    String response(@HeaderMap Map<String, String> headerMap);

    default String response() {
        return response(Collections.emptyMap());
    }


}
