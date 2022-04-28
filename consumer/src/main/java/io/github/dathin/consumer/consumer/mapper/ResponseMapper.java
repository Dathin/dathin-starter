package io.github.dathin.consumer.consumer.mapper;

import io.github.dathin.consumer.consumer.model.Request;
import io.github.dathin.consumer.consumer.model.Response;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ResponseMapper {

    Response from(Request request);

}
