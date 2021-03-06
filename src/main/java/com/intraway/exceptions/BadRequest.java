package com.intraway.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class BadRequest extends Exception {

    public BadRequest() {
        super("Los parámetros enviados son incorrectos");
    }
}
