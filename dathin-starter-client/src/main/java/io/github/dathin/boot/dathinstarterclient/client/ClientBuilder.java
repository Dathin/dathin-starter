package io.github.dathin.boot.dathinstarterclient.client;

public interface ClientBuilder {

    <T > T buildClient(Class<T> tClass, String targetUrl);

}
