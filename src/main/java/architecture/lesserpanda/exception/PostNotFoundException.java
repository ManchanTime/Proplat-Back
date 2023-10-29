package architecture.lesserpanda.exception;

public class PostNotFoundException extends IllegalArgumentException{
    public PostNotFoundException() {
    }

    public PostNotFoundException(String s) {
        super(s);
    }
}
