package io.github.dathin.boot.dathinstarterauthorizer.client;

import io.github.dathin.boot.dathinstarterauthorizer.security.DathinSecurityConfigProperties;
import io.github.dathin.boot.dathinstarterclient.client.InternalClientBuilder;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(DathinSecurityConfigProperties.class)
public class FeignClients {

    private final DathinSecurityConfigProperties dathinSecurityConfigProperties;

    public FeignClients(DathinSecurityConfigProperties dathinSecurityConfigProperties) {
        this.dathinSecurityConfigProperties = dathinSecurityConfigProperties;
    }

    @Bean
    public DathinSessionClient dathinSessionClient(InternalClientBuilder internalClientBuilder) {
        return internalClientBuilder.buildClient(DathinSessionClient.class, dathinSecurityConfigProperties.getDathinSessionUrl());
    }
}
