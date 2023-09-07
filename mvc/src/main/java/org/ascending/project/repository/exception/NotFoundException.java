package org.ascending.project.repository.exception;

public class NotFoundException extends RuntimeException {
    public NotFoundException(String message) {
        super(message);
    }

    public NotFoundException(String message, String cause) {
        super(message);
    }

    public NotFoundException(Throwable cause) {
        super(cause);
    }

    public NotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

}

