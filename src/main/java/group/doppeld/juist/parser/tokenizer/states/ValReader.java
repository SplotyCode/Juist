package group.doppeld.juist.parser.tokenizer.states;

import group.doppeld.juist.exeptions.InternalException;
import group.doppeld.juist.exeptions.UnexpectedCharException;
import group.doppeld.juist.parser.tokenizer.TokenizeReader;
import group.doppeld.juist.parser.tokenizer.TokenizeStates;
import group.doppeld.juist.parser.tokenizer.Tokenizer;
import group.doppeld.juist.parser.tokenizer.tokens.VariableToken;
import group.doppeld.juist.parser.tokenizer.tokens.VariableValueToken;

public class ValReader extends TokenizeReader {

    public enum SubState {

        IDLE,
        BEFORENAME,
        NAME,
        SPLIT,
        SPLITDONE,
        TYPE,
        EQUALSSPLIT,
        EQUALSSPLITDONE,
        END

    }

    private SubState state = SubState.IDLE;

    private String name = "";
    private String type = "";
    private VariableValueToken value;

    @Override
    public void handleChar(Tokenizer tokenizer) throws UnexpectedCharException {
        if(isCancelOthers()){
            switch (state){
                case BEFORENAME:{
                    state = SubState.NAME;
                    setIgnoreWhitespace(false);
                }case NAME:{
                    if(tokenizer.getcChar() == ' '){
                        setIgnoreWhitespace(true);
                        state = SubState.SPLIT;
                    }else if(tokenizer.getcChar() == ':'){
                        setIgnoreWhitespace(true);
                        state = SubState.SPLITDONE;
                    }else{
                        name += tokenizer.getcChar();
                    }
                    break;
                } case SPLIT:{
                    if(tokenizer.getcChar() == ':') state = SubState.SPLITDONE;
                    else throw new UnexpectedCharException(tokenizer, "Expected ':' or some withespace");
                } case SPLITDONE:{
                    setIgnoreWhitespace(false);
                    state = SubState.TYPE;
                } case TYPE:{
                    if(tokenizer.getcChar() == ' '){
                        state = SubState.EQUALSSPLIT;
                    }else if(tokenizer.getcChar() == '='){
                        state = SubState.EQUALSSPLITDONE;
                    }else{
                        type += tokenizer.getcChar();
                    }
                } case EQUALSSPLIT: {
                    if(tokenizer.getcChar() == '=') state = SubState.EQUALSSPLITDONE;
                    else throw new UnexpectedCharException(tokenizer, "Expected '=' or some withespace");
                } case EQUALSSPLITDONE: {
                    setIgnoreWhitespace(false);
                    setCancelOthers(false);
                    tokenizer.setState(TokenizeStates.VALUE);
                    TokenizeStates.VALUE.setFirstOnCloseListener((data) -> {
                        tokenizer.setState(tokenizer.getBefore());
                        setCancelOthers(true);
                        setIgnoreWhitespace(true);
                        state = SubState.END;
                        value = (VariableValueToken) data[0];
                    });
                }case END:{
                    if(tokenizer.getcChar() ==  ';'){
                        //shutdown
                        tokenizer.getTokens().add(new VariableToken(type, name, value));
                        name = "";
                        value = null;
                        type = "";
                        setCancelOthers(false);
                        setIgnoreWhitespace(false);
                        tokenizer.setState(tokenizer.getBefore());
                    }else throw new UnexpectedCharException(tokenizer, "Don't you just wont to end this variable?");
                }case IDLE: {
                    throw new InternalException("SubState == Idle?? I am working!");
                } default: throw new InternalError("I did't know that i have a SubState named '" + state + "'!");
            }
        }else if(tokenizer.isNextSkip("val")){
            setCancelOthers(true);
            setIgnoreWhitespace(true);
            state = SubState.BEFORENAME;
        }
    }
}
