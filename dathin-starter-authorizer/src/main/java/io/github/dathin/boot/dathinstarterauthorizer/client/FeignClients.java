package io.github.dathin.boot.dathinstarterauthorizer.client;

import io.github.dathin.boot.dathinstarterclient.client.InternalClientBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FeignClients {

    @Bean
    public DathinSessionClient dathinSessionClient(InternalClientBuilder internalClientBuilder) {
        return internalClientBuilder.buildClient(DathinSessionClient.class, "http://localhost:9000");
    }
}
