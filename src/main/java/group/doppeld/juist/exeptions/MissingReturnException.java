package group.doppeld.juist.exeptions;

public class MissingReturnException extends SyntaxException {
    
    public MissingReturnException(String message){        
        super(message);    
    }
    
    public MissingReturnException(String message, Exception throwable) {
        super(message, throwable);    
    }    
    public MissingReturnException(Exception throwable) {
        super(throwable);    
    }
}