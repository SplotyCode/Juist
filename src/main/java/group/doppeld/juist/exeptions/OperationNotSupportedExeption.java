package group.doppeld.juist.exeptions;

public class OperationNotSupportedExeption extends SyntaxException {

    public OperationNotSupportedExeption(String message) {
        super(message);
    }

    public OperationNotSupportedExeption(Exception ex) {
        super(ex);
    }

    public OperationNotSupportedExeption(String message, Exception ex) {
        super(message, ex);
    }
}
