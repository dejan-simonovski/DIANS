package mk.com.kinmkd.kinmkd.model.exception;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(Integer s) {
        super("User " + s + " was not found!");
    }
}
