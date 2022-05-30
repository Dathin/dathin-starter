package io.github.dathin.boot.dathinstarterclient.client;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "client.internal")
public class InternalClientBuilderConfig {

    private String url;

    private String authorization;

    private boolean headerPropagation = true;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getAuthorization() {
        return authorization;
    }

    public void setAuthorization(String authorization) {
        this.authorization = authorization;
    }

    public boolean isHeaderPropagation() {
        return headerPropagation;
    }

    public void setHeaderPropagation(boolean headerPropagation) {
        this.headerPropagation = headerPropagation;
    }
}
