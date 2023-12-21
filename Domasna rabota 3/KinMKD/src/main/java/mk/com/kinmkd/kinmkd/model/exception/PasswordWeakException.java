package mk.com.kinmkd.kinmkd.model.exception;

public class PasswordWeakException extends RuntimeException {
    public PasswordWeakException() {
        super("Password must contain at least 6 characters, at least 1 letter and at least 1 digit!");
    }
}
