package group.doppeld.juist.exeptions;

public class InternalException extends InternalError {

    public InternalException(String message){
        super(message);
    }

    public InternalException(Exception ex){
        super(ex);
    }

    public InternalException(String message, Exception ex){
        super(message, ex);
    }

}
