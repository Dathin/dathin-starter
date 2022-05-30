package io.github.dathin.boot.dathinstarterclient.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import feign.Response;
import feign.codec.ErrorDecoder;
import io.github.dathin.boot.dathinstarterclient.model.exception.UnreadableClientErrorException;
import io.github.dathin.boot.dathinstarterclient.model.exception.UnsupportedDownstreamIntegrationException;
import io.github.dathin.boot.dathinstartermodel.exception.GenericException;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;

@Component
public class InternalPropagationErrorDecoder implements ErrorDecoder {

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    @Override
    public Exception decode(String s, Response response) {
        if(422 == response.status()) {
            return new UnsupportedDownstreamIntegrationException();
        }
        try (InputStream bodyIs = response.body()
                .asInputStream()) {
            var genericException = OBJECT_MAPPER.readValue(bodyIs, GenericException.class);
            genericException.setStatus(response.status());
            return genericException;
        } catch (IOException e) {
            return new UnreadableClientErrorException();
        }
    }

}
