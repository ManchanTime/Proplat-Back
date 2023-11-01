package architecture.lesserpanda.exception;

public class ReplyNotFoundException extends IllegalStateException{
    public ReplyNotFoundException() {
    }

    public ReplyNotFoundException(String s) {
        super(s);
    }
}
