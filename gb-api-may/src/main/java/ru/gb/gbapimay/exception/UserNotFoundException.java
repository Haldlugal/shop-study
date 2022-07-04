package ru.gb.gbshopmay.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class UserNotFoundException extends IllegalArgumentException {

    public UserNotFoundException(String msg) {
        super(msg);
    }
}