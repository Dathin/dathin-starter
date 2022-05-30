package io.github.dathin.boot.dathinstarterclient.client;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.stereotype.Component;

@Component
public class JsonContentTypeAcceptInterceptor implements RequestInterceptor {

    @Override
    public void apply(RequestTemplate requestTemplate) {
        requestTemplate.header("Accept", "application/json");
        requestTemplate.header("Content-Type", "application/json");
    }

}
