package architecture.lesserpanda.exception;

public class PostNotFoundException extends IllegalStateException{
    public PostNotFoundException() {
    }

    public PostNotFoundException(String s) {
        super(s);
    }
}
