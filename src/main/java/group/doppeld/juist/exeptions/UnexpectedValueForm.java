package group.doppeld.juist.exeptions;

import group.doppeld.juist.parser.tokenizer.Tokenizer;

public class UnexpectedValueForm extends SyntaxException {

    private final Tokenizer tokenizer;


    public UnexpectedValueForm(Tokenizer tokenizer){
        super("Values dont fit together" + tokenizer.getcChar() + "at Line" + tokenizer.getLine());
        this.tokenizer = tokenizer;
    }
    public UnexpectedValueForm(Tokenizer tokenizer, String message){
        super(message + "|" + "Values dont fit together" + tokenizer.getcChar() + "at Line" + tokenizer.getLine());
        this.tokenizer = tokenizer;
    }
}
