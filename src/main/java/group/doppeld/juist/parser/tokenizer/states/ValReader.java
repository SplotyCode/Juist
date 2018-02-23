package group.doppeld.juist.parser.tokenizer.states;

import group.doppeld.juist.exeptions.InternalException;
import group.doppeld.juist.exeptions.UnexpectedCharExeption;
import group.doppeld.juist.parser.tokenizer.TokenizeReader;
import group.doppeld.juist.parser.tokenizer.TokenizeStates;
import group.doppeld.juist.parser.tokenizer.Tokenizer;
import group.doppeld.juist.parser.tokenizer.tokens.VariableToken;

public class ValReader extends TokenizeReader {

    public enum SubState {

        IDLE,
        BEFORENAME,
        NAME,
        SPLIT,
        SPLITDONE,
        TYPE,
        EQUALSSPLIT,
        EQUALSSPLITDONE

    }

    private SubState state = SubState.IDLE;

    private String name = "";
    private String type = "";

    @Override
    public void handleChar(Tokenizer tokenizer) throws UnexpectedCharExeption {
        if(isCancelOthers()){
            switch (state){
                case BEFORENAME:{
                    state = SubState.NAME;
                    setIgnoreWithspace(false);
                }case NAME:{
                    if(tokenizer.getcChar() == ' '){
                        setIgnoreWithspace(true);
                        state = SubState.SPLIT;
                    }else if(tokenizer.getcChar() == ':'){
                        setIgnoreWithspace(true);
                        state = SubState.SPLITDONE;
                    }else{
                        name += tokenizer.getcChar();
                    }
                    break;
                } case SPLIT:{
                    if(tokenizer.getcChar() == ':') state = SubState.SPLITDONE;
                    else throw new UnexpectedCharExeption(tokenizer, "Expected ':' or some withespace");
                } case SPLITDONE:{
                    setIgnoreWithspace(false);
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
                    else throw new UnexpectedCharExeption(tokenizer, "Expected '=' or some withespace");
                } case EQUALSSPLITDONE: {
                    setIgnoreWithspace(false);
                    tokenizer.getTokens().add(new VariableToken(type, name));
                    type = "";
                    type = "";
                    setCancelOthers(false);
                    tokenizer.setState(TokenizeStates.VALUE);
                }case IDLE: {
                    throw new InternalException("SubState == Idle?? I am working!");
                } default: throw new InternalError("I did't know that i have a SubState named '" + state + "'!");
            }
        }else if(tokenizer.isNextSkip("val")){
            setCancelOthers(true);
            setIgnoreWithspace(true);
            state = SubState.BEFORENAME;
        }
    }
}
