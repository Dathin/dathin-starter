package io.github.dathin.boot.dathinstartererrorhandling.controller;

import io.github.dathin.boot.dathinstartererrorhandling.exception.GenericException;
import io.github.dathin.boot.dathinstartererrorhandling.response.FormResponse;
import io.github.dathin.boot.dathinstartererrorhandling.response.GenericResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.ArrayList;
import java.util.List;

@org.springframework.web.bind.annotation.RestControllerAdvice
public class RestControllerAdvice {

    @ExceptionHandler(GenericException.class)
    public ResponseEntity<GenericResponse> genericResponseHandler(GenericException ex) {
        logInfo(ex);
        return ResponseEntity.status(ex.getStatus()).body(new GenericResponse(ex.getError()));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<GenericResponse> exceptionHandler(Exception ex) {
        logInfo(ex);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new GenericResponse(
                "Unexpected Error"));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<FormResponse>> methodArgumentNotValidException(MethodArgumentNotValidException ex) {
        logInfo(ex);
        List<FormResponse> formExceptionList = new ArrayList<>();
        for (FieldError fieldError : ex.getBindingResult().getFieldErrors()) {
            formExceptionList.add(new FormResponse(fieldError.getField(), fieldError.getDefaultMessage()));
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(formExceptionList);
    }

    private void logInfo(Exception ex) {
        System.out.println(ex.getClass());
        System.out.println(ex.getLocalizedMessage());
        ex.printStackTrace();
    }

}
