package group.doppeld.juist.exeptions;

import group.doppeld.juist.parser.tokenizer.Tokenizer;

public class UnexpectedCharExeption extends Exception {

    private final Tokenizer tokenizer;

    public UnexpectedCharExeption(Tokenizer tokenizer){
        super("Unexpected character '" + tokenizer.getcChar() + "' at '" + tokenizer.getLine() + "'");
        this.tokenizer = tokenizer;
    }

    public UnexpectedCharExeption(Tokenizer tokenizer, String message){
        super(message + " | " + "Unexpected character '" + tokenizer.getcChar() + "' at '" + tokenizer.getLine() + "'");
        this.tokenizer = tokenizer;
    }

}
