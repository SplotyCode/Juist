package group.doppeld.juist.exeptions;

public class SyntaxException extends RuntimeException {

    public SyntaxException(String message){
        super(message);
    }

    public SyntaxException(Exception ex){
        super(ex);
    }

    public SyntaxException(String message, Exception ex){
        super(message, ex);
    }

}
