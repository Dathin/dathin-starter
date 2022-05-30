package io.github.dathin.boot.dathinstarterclient.client;

import feign.Feign;
import feign.RequestInterceptor;
import feign.jackson.JacksonDecoder;
import feign.jackson.JacksonEncoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
@EnableConfigurationProperties(InternalClientBuilderConfig.class)
public class InternalClientBuilder implements ClientBuilder {

    private static final Logger LOGGER = LoggerFactory.getLogger(InternalClientBuilder.class);

    private final InternalPropagationErrorDecoder internalPropagationErrorDecoder;

    private final AuthorizationPropagationInterceptor authorizationPropagationInterceptor;

    private final InternalClientBuilderConfig internalClientBuilderConfig;

    private final JsonContentTypeAcceptInterceptor jsonContentTypeAcceptInterceptor;

    private String authorization;

    public InternalClientBuilder(InternalPropagationErrorDecoder internalPropagationErrorDecoder, AuthorizationPropagationInterceptor authorizationPropagationInterceptor, JsonContentTypeAcceptInterceptor jsonContentTypeAcceptInterceptor, InternalClientBuilderConfig internalClientBuilderConfig) {
        this.internalPropagationErrorDecoder = internalPropagationErrorDecoder;
        this.authorizationPropagationInterceptor = authorizationPropagationInterceptor;
        this.jsonContentTypeAcceptInterceptor = jsonContentTypeAcceptInterceptor;
        this.internalClientBuilderConfig = internalClientBuilderConfig;
    }

    public void setAuthorization(String authorization) {
        this.authorization = authorization;
    }

    @Override
    public <T> T buildClient(Class<T> tClass, String url) {
        String name = tClass.getSimpleName();
        logForClientName(name, "Building client for url " + url);
        var requestInterceptors = new ArrayList<RequestInterceptor>();
        requestInterceptors.add(jsonContentTypeAcceptInterceptor);
        logForClientName(name, "Json Content-Type and Accept interceptor is enabled");
        if (internalClientBuilderConfig.isHeaderPropagation()) {
            requestInterceptors.add(authorizationPropagationInterceptor);
            logForClientName(name, "Authorization propagation is enabled");
        }
        if (authorization != null) {
            LOGGER.info("Authorization Interceptor enabled");
            logForClientName(name, "Fixed authorization is enabled");
            requestInterceptors.add(new FixedAuthorizationInterceptor(authorization));
        }
        logForClientName(name, "Client built");
        return Feign.builder()
                .encoder(new JacksonEncoder())
                .decoder(new JacksonDecoder())
                .errorDecoder(internalPropagationErrorDecoder)
                .requestInterceptors(requestInterceptors)
                .target(tClass, url);
    }

    private void logForClientName(String name, String message) {
        LOGGER.info(String.format("%s: %s", name, message));
    }

}
