package mk.com.kinmkd.kinmkd.model.exception;

public class InvalidArgumentsException extends RuntimeException{
    public InvalidArgumentsException() {
        super("One or more invalid arguments!");
    }
}
