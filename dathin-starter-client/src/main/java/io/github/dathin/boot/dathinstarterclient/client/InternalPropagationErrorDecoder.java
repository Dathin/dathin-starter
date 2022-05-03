package io.github.dathin.boot.dathinstarterclient.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import feign.Response;
import feign.codec.ErrorDecoder;
import io.github.dathin.boot.dathinstarterclient.model.exception.UnreadableClientErrorException;
import io.github.dathin.boot.dathinstarterclient.model.exception.UnsupportedDownstreamIntegrationException;
import io.github.dathin.boot.dathinstartermodel.exception.GenericException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;

@Component
public class InternalPropagationErrorDecoder implements ErrorDecoder {

    private final ObjectMapper objectMapper;

    public InternalPropagationErrorDecoder(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    public Exception decode(String s, Response response) {
        if(HttpStatus.UNPROCESSABLE_ENTITY.value() == response.status()) {
            return new UnsupportedDownstreamIntegrationException();
        }
        try (InputStream bodyIs = response.body()
                .asInputStream()) {
            var genericException = objectMapper.readValue(bodyIs, GenericException.class);
            genericException.setStatus(response.status());
            return genericException;
        } catch (IOException e) {
            return new UnreadableClientErrorException();
        }
    }

}
