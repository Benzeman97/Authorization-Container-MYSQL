package com.benz.identity.server.exception;

import com.benz.identity.server.model.ErrorMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class DataNotFoundExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<ErrorMessage> toResponse(DataNotFoundException ex){
        ErrorMessage message=new ErrorMessage(HttpStatus.NOT_FOUND.value(),ex.getMessage(),"www.voyagemovies.org");
        return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
    }
}
