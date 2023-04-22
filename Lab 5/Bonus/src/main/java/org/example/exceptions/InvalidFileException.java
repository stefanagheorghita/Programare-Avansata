package org.example.exceptions;

public class InvalidFileException extends Exception{

    public InvalidFileException(String message, Throwable cause) {
        super(message, cause);
    }
}
