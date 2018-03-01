package group.doppeld.juist.exeptions;

public class InvalidTypeExeption extends SyntaxException {
    public InvalidTypeExeption(String message) {
        super(message);
    }

    public InvalidTypeExeption(Exception ex) {
        super(ex);
    }

    public InvalidTypeExeption(String message, Exception ex) {
        super(message, ex);
    }
}
