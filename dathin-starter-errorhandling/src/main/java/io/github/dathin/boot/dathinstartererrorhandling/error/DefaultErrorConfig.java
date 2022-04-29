package io.github.dathin.boot.dathinstartererrorhandling.error;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.web.context.request.WebRequest;


import java.util.Map;

@Configuration
public class DefaultErrorConfig {

    static {
        LoggerFactory.getLogger(DefaultErrorConfig.class).info("Default error config set to just display error attribute");
    }

    @Bean
    @Primary
    public ErrorAttributes dathinErrorAttributes() {
        return new DefaultErrorAttributes() {
            @Override
            public Map<String, Object> getErrorAttributes(WebRequest webRequest, ErrorAttributeOptions includeStackTrace) {
                Map<String, Object> defaultMap = super.getErrorAttributes(webRequest, includeStackTrace);
                defaultMap.remove("timestamp");
                defaultMap.remove("status");
                defaultMap.remove("path");
                return defaultMap;
            }
        };
    }

}
