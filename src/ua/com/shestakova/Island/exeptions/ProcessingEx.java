package ua.com.shestakova.Island.exeptions;

public class ProcessingEx extends RuntimeException{

    public ProcessingEx() {
    }

    public ProcessingEx(String message) {
        super(message);
    }
}