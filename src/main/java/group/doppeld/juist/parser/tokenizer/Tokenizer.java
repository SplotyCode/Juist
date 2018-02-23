package group.doppeld.juist.parser.tokenizer;

import java.util.ArrayList;

public class Tokenizer {

    private final String source;
    private ArrayList<Token> tokens;

    //The Current Char
    private int index;
    private char cChar;

    //States
    TokenizeStates before = TokenizeStates.DEFAULT;
    TokenizeStates state = TokenizeStates.DEFAULT;

    public Tokenizer(final String source){
        this.source = source;
        tokens = new ArrayList<>();
    }

    public Tokenizer(final String source, final ArrayList<Token> tokens){
        this.source = source;
        this.tokens = tokens;
    }

    public void process(){
        while (index < source.length()){
            cChar = source.charAt(index);
            state.get().handleChar(this);
            for(TokenizeState state : state.getActive())
                state.handleChar(this);
            index++;    
        }
    }

    public char next(){
        return next(1);
    }

    public boolean isNextSkip(String str){
        if(index+str.length() > source.length()) return false;
        boolean result = source.substring(index, index+str.length()).equals(str);
        if(result) index += str.length();
        return result;
    }

    public char next(int steps){
        if(index + steps > source.length()) return Character.MIN_VALUE;
        return source.charAt(steps+index);
    }

    public String getSource() {
        return source;
    }

    public ArrayList<Token> getTokens() {
        return tokens;
    }

    public int getIndex() {
        return index;
    }

    public char getcChar() {
        return cChar;
    }

    public TokenizeStates getBefore() {
        return before;
    }

    public TokenizeStates getState() {
        return state;
    }

    public void setBefore(TokenizeStates before) {
        this.before = before;
    }

    public void setState(TokenizeStates state) {
        this.state = state;
    }
}
