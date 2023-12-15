package com.abdo.learn.exception;

import org.springframework.http.HttpStatusCode;
import org.springframework.web.server.ResponseStatusException;

public class EmailAlreadyExistException extends ResponseStatusException {

    public EmailAlreadyExistException(HttpStatusCode status, String reason) {
        super(status, reason);
    }

}
