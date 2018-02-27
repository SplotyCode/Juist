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

    private void setState(SubState state){
        this.state = state;
        //System.out.println("Change to " + state.name() + " at '" + tokenizer.getcChar() + "'");
    }

    private Tokenizer tokenizer;
    private TokenizeStates before;

    @Override
    public void handleChar(Tokenizer tokenizer) throws UnexpectedCharException {
        //this.tokenizer = tokenizer;
        if(isCancelOthers()){
            //System.out.println("Reading '" + tokenizer.getcChar() + "'");
            switch (state){
                case BEFORENAME:{
                    setState(SubState.NAME);
                    name += tokenizer.getcChar();
                    setIgnoreWhitespace(false);
                    break;
                }case NAME:{
                    if(tokenizer.getcChar() == ' '){
                        setIgnoreWhitespace(true);
                        setState(SubState.SPLIT);
                    }else if(tokenizer.getcChar() == ':'){
                        setIgnoreWhitespace(true);
                        setState(SubState.SPLITDONE);
                    }else{
                        name += tokenizer.getcChar();
                    }
                    break;
                } case SPLIT:{
                    if(tokenizer.getcChar() == ':') setState(SubState.SPLITDONE);
                    else throw new UnexpectedCharException(tokenizer, "Expected ':' or some withespace");
                    break;
                } case SPLITDONE:{
                    setIgnoreWhitespace(false);
                    setState(SubState.TYPE);
                    type += tokenizer.getcChar();
                    break;
                } case TYPE:{
                    if(tokenizer.getcChar() == ' '){
                        setState(SubState.EQUALSSPLIT);
                    }else if(tokenizer.getcChar() == '='){
                        setState(SubState.EQUALSSPLITDONE);
                    }else{
                        type += tokenizer.getcChar();
                    }
                    break;
                } case EQUALSSPLIT: {
                    if(tokenizer.getcChar() == '=') setState(SubState.EQUALSSPLITDONE);
                    else throw new UnexpectedCharException(tokenizer, "Expected '=' or some withespace");
                    break;
                } case EQUALSSPLITDONE: {
                    setIgnoreWhitespace(false);
                    setCancelOthers(false);
                    before = tokenizer.getState();
                    tokenizer.setState(TokenizeStates.VALUE);
                    TokenizeStates.VALUE.setFirstOnCloseListener((data) -> {
                        tokenizer.setState(before);
                        setCancelOthers(true);
                        setIgnoreWhitespace(true);
                        setState(SubState.END);
                        value = (VariableValueToken) data[0];
                    });
                    break;
                }case END:{
                    if(tokenizer.getcChar() ==  ';'){
                        //shutdown
                        //System.out.println(type + " " + name + " " + value.getType().name() + " " + value.getContent());
                        tokenizer.getTokens().add(new VariableToken(type, name, value));
                        name = "";
                        value = null;
                        type = "";
                        setCancelOthers(false);
                        setIgnoreWhitespace(false);
                        tokenizer.setState(before);
                    }else throw new UnexpectedCharException(tokenizer, "Don't you just wont to end this variable?");
                    break;
                }case IDLE: {
                    throw new InternalException("SubState == Idle?? I am working!");
                } default: throw new InternalError("I did't know that i have a SubState named '" + state + "'!");
            }
        }else if(tokenizer.isNextSkip("val")){
            setCancelOthers(true);
            setIgnoreWhitespace(true);
            setState(SubState.BEFORENAME);
        }
    }
}
