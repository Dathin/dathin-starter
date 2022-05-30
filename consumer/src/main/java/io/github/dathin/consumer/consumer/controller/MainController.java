package io.github.dathin.consumer.consumer.controller;

import io.github.dathin.boot.dathinstartermodel.exception.GenericException;
import io.github.dathin.consumer.consumer.Pipedream;
import io.github.dathin.consumer.consumer.mapper.ResponseMapper;
import io.github.dathin.consumer.consumer.model.Request;
import io.github.dathin.consumer.consumer.model.Response;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
@RequestMapping("/pedro")
public class MainController {

    private final ResponseMapper responseMapper;

    private final Pipedream pipedream;

    public MainController(ResponseMapper responseMapper, Pipedream pipedream) {
        this.responseMapper = responseMapper;
        this.pipedream = pipedream;
    }

    static boolean xD = false;

    @PostMapping
    public Response xisDe(@RequestBody Request request) {
        var stringHashMap = new HashMap<String, String>();
        stringHashMap.put("pedro", "caires");
        if(xD) {
            stringHashMap.put("authorization", "LULUL");
        }
        var a = pipedream.response(stringHashMap);
        xD = true;
        return responseMapper.from(request);
    }

    @PatchMapping
    public void xD() {
        throw new GenericException("fuck", HttpStatus.ALREADY_REPORTED.value());
    }

}
