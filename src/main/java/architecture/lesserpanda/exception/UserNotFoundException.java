package architecture.lesserpanda.exception;

public class UserNotFoundException extends IllegalStateException{
    public UserNotFoundException() {
    }

    public UserNotFoundException(String s) {
        super(s);
    }
}
