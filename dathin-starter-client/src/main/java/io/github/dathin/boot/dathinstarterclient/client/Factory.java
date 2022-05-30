package io.github.dathin.boot.dathinstarterclient.client;

public interface Factory<T, C> {

    T getNewInstance(C config);

}
