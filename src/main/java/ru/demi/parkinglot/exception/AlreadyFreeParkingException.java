package ru.demi.parkinglot.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class AlreadyFreeParkingException extends RuntimeException {

    public AlreadyFreeParkingException(int slotNumber) {
        super(String.format("Parking slot #%d is already free.", slotNumber));
    }
}
