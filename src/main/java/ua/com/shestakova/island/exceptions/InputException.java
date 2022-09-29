package ua.com.shestakova.island.exceptions;

public class InputException extends RuntimeException {

    public InputException() {
    }

    public InputException(String message) {
        super(message);
    }
}
