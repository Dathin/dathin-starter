package io.github.dathin.boot.dathinstarterclient.client;

import feign.Feign;
import org.springframework.stereotype.Component;

@Component
public class InternalClientBuilder implements ClientBuilder {

    private final InternalPropagationErrorDecoder internalPropagationErrorDecoder;

    private final InternalPropagationInterceptor internalPropagationInterceptor;

    public InternalClientBuilder(InternalPropagationErrorDecoder internalPropagationErrorDecoder, InternalPropagationInterceptor internalPropagationInterceptor) {
        this.internalPropagationErrorDecoder = internalPropagationErrorDecoder;
        this.internalPropagationInterceptor = internalPropagationInterceptor;
    }

    @Override
    public <T> T buildClient(Class<T> tClass, String targetUrl){
        return Feign.builder()
                .errorDecoder(internalPropagationErrorDecoder)
                .requestInterceptor(internalPropagationInterceptor)
                .target(tClass, targetUrl);
    }

}
