package com.astronaut.scheduleorganizer.exception;

import java.time.format.DateTimeParseException;

public class InvalidTimeFormatException extends Exception {
    public InvalidTimeFormatException(String message) {
        super(message);
    }
}
