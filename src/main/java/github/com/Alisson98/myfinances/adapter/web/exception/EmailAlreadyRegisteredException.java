package github.com.Alisson98.myfinances.adapter.web.exception;

public class EmailAlreadyRegisteredException extends RuntimeException {
    public EmailAlreadyRegisteredException(String msg) {
        super(msg);
    }
}
