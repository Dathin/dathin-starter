package io.github.dathin.consumer.consumer;

import io.github.dathin.boot.dathinstarterclient.client.InternalClientBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FeignConfig {

    @Bean
    public Pipedream getPipedream(InternalClientBuilder internalClientBuilder){
        return internalClientBuilder.buildClient(Pipedream.class, "https://eodrf8wqf9m2bgc.m.pipedream.net");
    }

}
