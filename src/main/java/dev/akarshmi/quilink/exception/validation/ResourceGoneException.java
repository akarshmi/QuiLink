package dev.akarshmi.quilink.exception.validation;

public class ResourceGoneException extends RuntimeException {
    public ResourceGoneException(String message) {
        super(message);
    }
    public ResourceGoneException(String message, Throwable cause) {
        super(message, cause);
    }
}
