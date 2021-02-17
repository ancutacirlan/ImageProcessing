package io.licence.webapp.utils.exception;

import javax.persistence.PersistenceException;

public class InvalidException extends PersistenceException {

    public InvalidException() {
    }

    public InvalidException(String message) {
        super(message);
    }

}
