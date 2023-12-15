package com.abdo.learn.exception;

import org.springframework.http.HttpStatusCode;
import org.springframework.web.server.ResponseStatusException;

public class UnknownErrorException extends ResponseStatusException {

    public UnknownErrorException(HttpStatusCode status, String reason) {
        super(status, reason);
    }

}
