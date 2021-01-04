package io.licence.webapp.utils;

import javax.persistence.PersistenceException;

public class InvalidException extends PersistenceException {

    public InvalidException() {
    }

    public InvalidException(String message) {
        super(message);
    }

}
