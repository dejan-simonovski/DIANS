package mk.com.kinmkd.kinmkd.model.exception;

public class IncorrectPasswordException extends RuntimeException{
    public IncorrectPasswordException() {
        super("Incorrect password!");
    }
}
