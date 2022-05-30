package io.github.dathin.boot.dathinstarterclient.client;

public class InternalClientBuilderFactory implements Factory<InternalClientBuilder, InternalClientBuilderConfig> {

    @Override
    public InternalClientBuilder getNewInstance(InternalClientBuilderConfig config) {
        var internalClientBuilder = new InternalClientBuilder(new InternalPropagationErrorDecoder(), new AuthorizationPropagationInterceptor(),
                new JsonContentTypeAcceptInterceptor(), config);
        internalClientBuilder.setAuthorization(config.getAuthorization());
        return internalClientBuilder;
    }

}
