package group.doppeld.juist.parser.tokenizer;

import group.doppeld.juist.exeptions.UnexpectedCharExeption;

import java.util.ArrayList;
import java.util.Collections;

public class Tokenizer {

    private final String source;
    private ArrayList<Token> tokens;

    //The Current Char
    private int index;
    private char cChar;
    private int line = 0;

    //States
    private TokenizeStates before = TokenizeStates.DEFAULT;
    private TokenizeStates state = TokenizeStates.DEFAULT;
    
    private ArrayList<TokenizeReader> unlocked = new ArrayList<>();

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
            boolean withspace = cChar == '\n' || cChar == '\t' || cChar == ' ';
            updateLock();
            for(TokenizeReader state : new ArrayList<>(unlocked)){
                updateLock();
                if(!unlocked.contains(state)) continue;
                try {
                    if(!(withspace && state.isIgnoreWithspace())) state.handleChar(this);
                } catch (UnexpectedCharExeption ex) {
                    ex.printStackTrace();
                    break;
                }
                if(state.isSkip()){
                    state.setSkip(false);
                    break;
                }
            }
            if(cChar == '\n') line++;
            index++;    
        }
    }
    
    private void updateLock(){
        unlocked.clear();
        if(state.get() != null && state.get().isCancelOthers()){
            unlocked.add(state.get());
        }else{
            for(TokenizeReader state : state.getActive())
                if(state.isCancelOthers()){
                    unlocked.add(state);
                    break;
                }
        }
        if(unlocked.isEmpty()){
            if(state.get() != null) unlocked.add(state.get());
            Collections.addAll(unlocked, state.getActive());
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

    public int getLine() {
        return line;
    }
}
