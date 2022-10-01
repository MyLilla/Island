package ua.com.shestakova.island.exceptions;

public class CreateException extends RuntimeException {

    public CreateException() {
    }

    public CreateException(String message) {
        super(message);
    }
}
