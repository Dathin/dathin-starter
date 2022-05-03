package io.github.dathin.boot.dathinstarterauthorizer.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.dathin.boot.dathinstarterauthorizer.client.DathinSessionClient;
import io.github.dathin.boot.dathinstarterauthorizer.service.AuthenticationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SecurityFilterConfig {

    private static Logger LOGGER = LoggerFactory.getLogger(SecurityFilterConfig.class);

    @Bean
    @ConditionalOnMissingBean
    public DathinSecurityFilter defaultDathinSecurityFilter(SecurityFilterExceptionHandler securityFilterExceptionHandler, AuthenticationService authenticationService, ObjectMapper objectMapper, DathinSessionClient dathinSessionClient) {
        LOGGER.info("Using default filter");
        return new SecurityFilter(securityFilterExceptionHandler, authenticationService, objectMapper, dathinSessionClient);
    }

}
