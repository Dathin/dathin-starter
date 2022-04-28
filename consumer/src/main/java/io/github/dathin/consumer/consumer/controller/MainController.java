package io.github.dathin.consumer.consumer.controller;

import io.github.dathin.boot.dathinstartererrorhandling.exception.GenericException;
import io.github.dathin.consumer.consumer.mapper.ResponseMapper;
import io.github.dathin.consumer.consumer.model.Request;
import io.github.dathin.consumer.consumer.model.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pedro")
public class MainController {

    private final ResponseMapper responseMapper;

    public MainController(ResponseMapper responseMapper) {
        this.responseMapper = responseMapper;
    }

    @PostMapping
    public Response xisDe(@RequestBody Request request) {
        return responseMapper.from(request);
    }

    @PatchMapping
    public void xD() {
        throw new GenericException("fuck", HttpStatus.ALREADY_REPORTED);
    }

}
