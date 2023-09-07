package org.ascending.project.repository.exception;

public class UserNotFoundException extends RuntimeException {

    private String identifier;

    public UserNotFoundException(String message, String identifier){
        super(message);
        this.identifier = identifier;
    }

    public String getIdentifier() {
        return identifier;
    }
}


