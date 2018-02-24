package group.doppeld.juist.exeptions;

import group.doppeld.juist.parser.tokenizer.Tokenizer;

public class UnexpectedCharException extends SyntaxException {

    private final Tokenizer tokenizer;

    public UnexpectedCharException(Tokenizer tokenizer){
        super("Unexpected character '" + tokenizer.getcChar() + "' at '" + tokenizer.getLine() + "'");
        this.tokenizer = tokenizer;
    }

    public UnexpectedCharException(Tokenizer tokenizer, String message){
        super(message + " | " + "Unexpected character '" + tokenizer.getcChar() + "' at '" + tokenizer.getLine() + "'");
        this.tokenizer = tokenizer;
    }

}
