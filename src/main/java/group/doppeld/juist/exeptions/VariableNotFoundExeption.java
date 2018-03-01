package group.doppeld.juist.exeptions;

public class VariableNotFoundExeption extends SyntaxException {
    public VariableNotFoundExeption(String message) {
        super(message);
    }

    public VariableNotFoundExeption(Exception ex) {
        super(ex);
    }

    public VariableNotFoundExeption(String message, Exception ex) {
        super(message, ex);
    }
}
