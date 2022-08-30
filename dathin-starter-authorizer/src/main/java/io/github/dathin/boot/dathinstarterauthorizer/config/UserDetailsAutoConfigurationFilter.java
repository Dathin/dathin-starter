package io.github.dathin.boot.dathinstarterauthorizer.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.AutoConfigurationImportFilter;
import org.springframework.boot.autoconfigure.AutoConfigurationMetadata;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.List;

@Configuration
public class UserDetailsAutoConfigurationFilter implements AutoConfigurationImportFilter {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserDetailsAutoConfigurationFilter.class);

    private static final List<String> userDetailsAutoConfigurationCanonicalName = Arrays.asList("org.springframework.boot.autoconfigure.security.servlet.UserDetailsServiceAutoConfiguration");

    static {
        LOGGER.info("Auto Configuration disabled for: " + userDetailsAutoConfigurationCanonicalName);
    }
    
    @Override
    public boolean[] match(String[] autoConfigurationClasses, AutoConfigurationMetadata autoConfigurationMetadata) {
        var matches = new boolean[autoConfigurationClasses.length];
        
        for(int i = 0; i< autoConfigurationClasses.length; i++) {
            matches[i] = !userDetailsAutoConfigurationCanonicalName.contains(autoConfigurationClasses[i]);
        }
        return matches;
    }
    
}
