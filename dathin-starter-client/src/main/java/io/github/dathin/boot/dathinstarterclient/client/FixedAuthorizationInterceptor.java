package io.github.dathin.boot.dathinstarterclient.client;

import feign.RequestInterceptor;
import feign.RequestTemplate;

public class FixedAuthorizationInterceptor implements RequestInterceptor {

    private final String authorization;

    public FixedAuthorizationInterceptor(String authorization) {
        this.authorization = authorization;
    }

    @Override
    public void apply(RequestTemplate requestTemplate) {
        requestTemplate.header("Authorization", authorization);
    }

}
