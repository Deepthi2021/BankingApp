package com.example.exception;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class AccountControllerException extends RuntimeException {
    public AccountControllerException(String message) {
        super(message);
    }

    public AccountControllerException(String message, Throwable cause) {
        super(message, cause);
    }
}