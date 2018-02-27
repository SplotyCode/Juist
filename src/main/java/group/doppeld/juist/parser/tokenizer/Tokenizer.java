package group.doppeld.juist.parser.tokenizer;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import group.doppeld.juist.exeptions.UnexpectedCharException;
import sun.reflect.Reflection;

import java.util.ArrayList;
import java.util.Collections;

public class Tokenizer {

    private final String source;
    private ArrayList<Token> tokens;

    //The Current Char
    private int index;
    private char cChar;
    private int line = 1;

    //States
    private TokenizeStates before = TokenizeStates.DEFAULT;
    private TokenizeStates state = TokenizeStates.DEFAULT;
    
    private ArrayList<TokenizeReader> unlocked = new ArrayList<>();

    private Updater updater = () -> {};

    public Tokenizer(final String source){
        this.source = source;
        tokens = new ArrayList<Token>(){
            @Override
            public boolean add(Token o) {
                System.out.println(o.getClass().getSimpleName());
                Gson gson = new GsonBuilder().setPrettyPrinting().serializeNulls().create();
                System.out.println(gson.toJson(o));
                return super.add(o);
            }
        };
    }

    public Tokenizer(final String source, final ArrayList<Token> tokens){
        this.source = source;
        this.tokens = tokens;
    }

    public void process(){
        while (index < source.length()){
            cChar = source.charAt(index);
            boolean whitespace = cChar == '\n' || cChar == '\t' || cChar == ' ';
            updateLock();
            for(TokenizeReader state : new ArrayList<>(unlocked)){
                updateLock();
                if(!unlocked.contains(state)) continue;
                try {
                    if(!(whitespace && state.isIgnoreWhitespace())) state.handleChar(this);
                } catch (UnexpectedCharException ex) {
                    ex.printStackTrace();
                    break;
                }
                if(state.isSkip()){
                    state.setSkip(false);
                    break;
                }
            }
            //System.out.println(cChar + " " + line);
            if(cChar == '\n') line++;
            index++;
            updater.onReadNextChar();
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
        int newlines = 0;
        for(int i = 0;i < str.length();i++) {
            char c = str.charAt(i);
            if (source.charAt(i + index) != c) return false;
            else if (c == '\n') newlines++;
        }
       // System.out.println("skip" + str.length());
        if(str.charAt(0) == '\n') newlines--;
        line += newlines;
        index += str.length()-1;
        return true;
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

    public void setState(TokenizeStates state) {
        //Extremly Useful for debugging
        /*try {
            System.out.println(this.state + " to " + state + " at " + line + " from " + Class.forName(Thread.currentThread().getStackTrace()[2].getClassName()) + "[" + Thread.currentThread().getStackTrace()[2].getLineNumber() + "]");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }*/
        before = this.state;
        this.state = state;
    }

    public int getLine() {
        return line;
    }

    public void setUpdater(Updater updater) {
        this.updater = updater;
    }

    public Updater getUpdater() {
        return updater;
    }

    public interface Updater {
        void onReadNextChar();
    }
}
