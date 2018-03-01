package group.doppeld.juist.exeptions;

import group.doppeld.juist.exeptions.SyntaxException;

public class MethodeNotFoundExeption extends SyntaxException {

    public MethodeNotFoundExeption(String message) {
        super(message);
    }

    public MethodeNotFoundExeption(Exception ex) {
        super(ex);
    }

    public MethodeNotFoundExeption(String message, Exception ex) {
        super(message, ex);
    }
}
