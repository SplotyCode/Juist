package group.doppeld.juist.parser.tokenizer.states;

import group.doppeld.juist.exeptions.InternalException;
import group.doppeld.juist.exeptions.UnexpectedCharException;
import group.doppeld.juist.parser.tokenizer.TokenizeConstants;
import group.doppeld.juist.parser.tokenizer.TokenizeReader;
import group.doppeld.juist.parser.tokenizer.TokenizeStates;
import group.doppeld.juist.parser.tokenizer.Tokenizer;
import group.doppeld.juist.parser.tokenizer.tokens.FunctionToken;
import group.doppeld.juist.parser.tokenizer.tokens.StatementToken;
import group.doppeld.juist.util.CharUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class FunReader extends TokenizeReader {


    private String name = "";
    private String type = "";
    private HashMap<String, String> arguments = new HashMap<>();
    private String curArgName = "", curArgType = "";

    private TokenizeStates before;

    public enum SubState {

        IDLE,
        BEFORENAME,
        NAME,
        ARGUMENTSSTART,
        BEFOREARGNAMEFIRST,
        BEFOREARGNAME,
        ARGNAME,
        ARGSPLIT,
        ARGSPLITDONE,
        ARGTYPE,
        COMMA,
        TYPESPLIT,
        TYPESPLITDONE,
        TYPE,
        END

    }

    private SubState state = SubState.IDLE;

    @Override
    public void handleChar(Tokenizer tokenizer) throws UnexpectedCharException {
        if(isCancelOthers()){
            //if(tokenizer.getcChar() == 'x') System.out.println(state.name());
            switch (state) {
                case BEFORENAME:
                    state = SubState.NAME;
                    name += tokenizer.getcChar();
                    setIgnoreWhitespace(false);
                    break;
                case NAME:
                    if (CharUtil.isWhitespace(tokenizer.getcChar())) {
                        setIgnoreWhitespace(true);
                        state = SubState.ARGUMENTSSTART;
                    } else if (tokenizer.getcChar() == '(') {
                        setIgnoreWhitespace(true);
                        state = SubState.BEFOREARGNAMEFIRST;
                    } else {
                        name += tokenizer.getcChar();
                    }
                    break;
                case ARGUMENTSSTART:
                    if(tokenizer.getcChar() == '(') {
                        state = SubState.BEFOREARGNAMEFIRST;
                    }else throw new UnexpectedCharException(tokenizer, "Expected '(' or whitespace");
                    break;
                case BEFOREARGNAME:
                    if(tokenizer.getcChar() == ')'){
                        state = SubState.TYPESPLIT;
                    }else {
                        setIgnoreWhitespace(false);
                        state = SubState.ARGNAME;
                    }
                    break;
                case BEFOREARGNAMEFIRST:
                    if(tokenizer.getcChar() == ')'){
                        state = SubState.TYPESPLIT;
                    }else {
                        setIgnoreWhitespace(false);
                        tokenizer.reHandleChar();
                        state = SubState.ARGNAME;
                    }
                    break;
                case ARGNAME:
                    if (CharUtil.isWhitespace(tokenizer.getcChar())) {
                        setIgnoreWhitespace(true);
                        state = SubState.ARGSPLIT;
                    } else if (tokenizer.getcChar() == ':') {
                        setIgnoreWhitespace(true);
                        state = SubState.ARGSPLITDONE;
                    } else {
                        curArgName += tokenizer.getcChar();
                    }
                    break;
                case ARGSPLIT:
                    if(tokenizer.getcChar() == ':') state = SubState.ARGSPLITDONE;
                    else throw new UnexpectedCharException(tokenizer, "Expected a type-split thingy as ':' or just some kind of whitespace");
                    break;
                case ARGSPLITDONE:
                    setIgnoreWhitespace(false);
                    state = SubState.ARGTYPE;
                    tokenizer.reHandleChar();
                    break;
                case ARGTYPE:
                    if(CharUtil.isWhitespace(tokenizer.getcChar())){
                        state = SubState.COMMA;
                        setIgnoreWhitespace(true);
                    }else if(tokenizer.getcChar() == ','){
                        arguments.put(curArgName, curArgType);
                        curArgType = "";
                        curArgName = "";
                        state = SubState.BEFOREARGNAME;
                    }else if(tokenizer.getcChar() == ')'){
                        arguments.put(curArgName, curArgType);
                        curArgType = "";
                        curArgName = "";
                        state = SubState.TYPESPLIT;
                        setIgnoreWhitespace(true);
                    }else {
                        curArgType += tokenizer.getcChar();
                    }
                    break;
                case COMMA:
                    if(tokenizer.getcChar() == ','){
                        arguments.put(curArgName, curArgType);
                        curArgType = "";
                        curArgName = "";
                        state = SubState.BEFOREARGNAME;
                    }else if(tokenizer.getcChar() == ')'){
                        arguments.put(curArgName, curArgType);
                        curArgType = "";
                        curArgName = "";
                        state = SubState.TYPESPLIT;
                    }else throw new UnexpectedCharException(tokenizer, "Expect Whitespace, ',' or )");
                    break;
                case TYPESPLIT:
                    if(tokenizer.getcChar() == ':'){
                        state = SubState.TYPESPLITDONE;
                    }else if(tokenizer.getcChar() == '{'){
                        startSource(tokenizer);
                    }else throw new UnexpectedCharException(tokenizer, "Expect Whitespace, ':' or {");
                    break;
                case TYPESPLITDONE:
                    setIgnoreWhitespace(false);
                    type += tokenizer.getcChar();
                    state = SubState.TYPE;
                    break;
                case TYPE:
                    if(CharUtil.isWhitespace(tokenizer.getcChar())){
                        state = SubState.END;
                        setIgnoreWhitespace(true);
                    }else if(tokenizer.getcChar() == '{'){
                        startSource(tokenizer);
                    }else type += tokenizer.getcChar();
                    break;
                case END:
                    if(tokenizer.getcChar() == '{'){
                        startSource(tokenizer);
                    }else throw new UnexpectedCharException(tokenizer, "Expect Whitespace or '{'");
                    break;
                case IDLE:
                    throw new InternalException("SubState == Idle?? I am working!");
                default:
                    throw new InternalError("I did't know that i have a SubState named '" + state + "'!");
            }
        }else if(tokenizer.isNextSkip("fun")){
            setCancelOthers(true);
            setIgnoreWhitespace(true);
            state = SubState.BEFORENAME;
        }
    }

    private void startSource(Tokenizer tokenizer){
        setCancelOthers(false);
        setIgnoreWhitespace(false);
        before = tokenizer.getBefore();
        tokenizer.setState(TokenizeStates.SOURCE);
       // System.out.println("OPENED!!!");
        TokenizeConstants.IN_SOURCE_READER.setOnClose((data) -> {
            tokenizer.getTokens().add(new FunctionToken(name, arguments, type, (ArrayList<StatementToken>) data[0]));
            name = "";
            type = "";
            arguments.clear();
            if(!curArgName.equals("") || !curArgType.equals("")) throw new InternalException("FunReader -> Last Argument Cache is not empty in final end state!!!(@" + tokenizer.getLine() + ")");
            setCancelOthers(false);
            setIgnoreWhitespace(false);
            tokenizer.setState(before);
        });
        //TODO: START SOURCE CODE STATES... stop the state when function closes
        //System.out.println(name + " " + type);
        for(Map.Entry<String, String> pair : arguments.entrySet()){
            //System.out.println("    " + pair.getKey() + " " + pair.getValue());
        }
    }
}
