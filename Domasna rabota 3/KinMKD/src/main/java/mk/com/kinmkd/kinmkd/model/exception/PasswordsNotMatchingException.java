package mk.com.kinmkd.kinmkd.model.exception;

public class PasswordsNotMatchingException extends RuntimeException{
    public PasswordsNotMatchingException() {
        super("The entered passwords must match!");
    }
}
