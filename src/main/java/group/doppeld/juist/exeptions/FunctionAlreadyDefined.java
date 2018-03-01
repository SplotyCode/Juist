package group.doppeld.juist.exeptions;

public class FunctionAlreadyDefined extends SyntaxException {
    public FunctionAlreadyDefined(String message) {
        super(message);
    }

    public FunctionAlreadyDefined(Exception ex) {
        super(ex);
    }

    public FunctionAlreadyDefined(String message, Exception ex) {
        super(message, ex);
    }
}
