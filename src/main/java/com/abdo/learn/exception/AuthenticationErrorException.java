package com.abdo.learn.exception;

import org.springframework.http.HttpStatusCode;
import org.springframework.web.server.ResponseStatusException;

public class AuthenticationErrorException extends ResponseStatusException {

    public AuthenticationErrorException(HttpStatusCode status, String reason) {
        super(status, reason);
    }

}
