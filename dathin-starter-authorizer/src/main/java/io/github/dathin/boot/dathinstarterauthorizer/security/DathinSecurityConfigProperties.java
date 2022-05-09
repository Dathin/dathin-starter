package io.github.dathin.boot.dathinstarterauthorizer.security;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "security")
public class DathinSecurityConfigProperties {

    private boolean disableSwagger = false;

    private String[] publicPaths = new String[0];

    private String dathinSessionUrl = "http://localhost:9000";

    public boolean isDisableSwagger() {
        return disableSwagger;
    }

    public void setDisableSwagger(boolean disableSwagger) {
        this.disableSwagger = disableSwagger;
    }

    public String[] getPublicPaths() {
        return publicPaths;
    }

    public void setPublicPaths(String[] publicPaths) {
        this.publicPaths = publicPaths;
    }

    public String getDathinSessionUrl() {
        return dathinSessionUrl;
    }

    public void setDathinSessionUrl(String dathinSessionUrl) {
        this.dathinSessionUrl = dathinSessionUrl;
    }
}
