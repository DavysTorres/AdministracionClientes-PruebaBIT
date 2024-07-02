package org.example.administracionclientes.global.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class recursoNoEncontrado extends RuntimeException {

    public recursoNoEncontrado(String message) {
        super(message);
    }
}
