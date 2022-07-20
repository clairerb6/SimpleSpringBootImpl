package cl.bci.api.rest.exception;

import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus
public class DuplicatedResourceException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public DuplicatedResourceException(String message) {
        super(message);
    }

    public DuplicatedResourceException(String message, Throwable throwable) {
        super(message, throwable);
    }
}
