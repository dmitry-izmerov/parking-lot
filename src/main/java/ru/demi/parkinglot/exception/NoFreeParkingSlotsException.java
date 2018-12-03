package ru.demi.parkinglot.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class NoFreeParkingSlotsException extends RuntimeException {
    public NoFreeParkingSlotsException() {
        super("There is no any free parking slots in the parking. Try later.");
    }
}
