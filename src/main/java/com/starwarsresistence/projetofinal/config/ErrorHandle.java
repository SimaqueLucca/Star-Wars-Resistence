package com.starwarsresistence.projetofinal.config;

import com.starwarsresistence.projetofinal.exception.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@RestControllerAdvice
public class ErrorHandle {

    @ResponseStatus(code = HttpStatus.NOT_FOUND)
    @ExceptionHandler()
    public String ObjectNotFound(NotFoundException exception){
        return exception.getMessage();
    }

}
