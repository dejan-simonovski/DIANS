package mk.com.kinmkd.kinmkd.model.exception;

public class EmailNotExistingException extends RuntimeException{
    public EmailNotExistingException(String email) {
        super(String.format("Email %s doesn't exist!", email));
    }
}
