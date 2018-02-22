package group.doppeld.juist.parser.tokenizer;

import java.util.ArrayList;

public class Tokenizer {

    private final String source;
    private ArrayList<Token> tokens;
    private int index;

    public Tokenizer(final String source){
        this.source = source;
        tokens = new ArrayList<>();
    }

    public Tokenizer(final String source, final ArrayList<Token> tokens){
        this.source = source;
        this.tokens = tokens;
    }

    public void process(){
        String current = "";
        TokenizeState before = TokenizeState.DEFAULT;
        TokenizeState state = TokenizeState.DEFAULT;
        while (index < source.length()){
            char cChar = source.charAt(index);
            if((cChar == '/' && next() == '/') || (cChar == '/' && next() == '*')) state = TokenizeState.COMMENT;
            switch (state){
                case COMMENT:
                    if(cChar != '\n' && !(cChar == '*' && next() == '/'))continue;
                    state = before;
                    break;
            }
        }
    }

    private char next(){
        return next(1);
    }

    private char next(int steps){
        if(index + steps > source.length()) return Character.MIN_VALUE;
        return source.charAt(steps+index);
    }
}
