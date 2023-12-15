package com.abdo.learn.exception;

import org.springframework.http.HttpStatusCode;
import org.springframework.web.server.ResponseStatusException;

public class UnAuthorizedOperationException extends ResponseStatusException {

    public UnAuthorizedOperationException(HttpStatusCode status, String reason) {
        super(status, reason);
    }

}
