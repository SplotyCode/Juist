package group.doppeld.juist.parser.tokenizer.readers.statements;

import group.doppeld.juist.exeptions.UnexpectedCharException;
import group.doppeld.juist.parser.tokenizer.TokenizeConstants;
import group.doppeld.juist.parser.tokenizer.TokenizeReader;
import group.doppeld.juist.parser.tokenizer.TokenizeStates;
import group.doppeld.juist.parser.tokenizer.Tokenizer;
import group.doppeld.juist.parser.tokenizer.tokens.VariableValueToken;
import group.doppeld.juist.parser.tokenizer.tokens.statements.FunctionCallStatementToken;
import group.doppeld.juist.util.CharUtil;

import java.util.ArrayList;

public class FunctionCallStatementReader extends TokenizeReader {

    private String name = "";
    private int lastIndex = -1;

    private ArrayList<VariableValueToken> parameters = new ArrayList<>();

    enum SubState {

        COMMASPLIT,
        COMMASPLITDONE,
        END

    }

    private SubState subState;
    private TokenizeStates before;

    @Override
    public void handleChar(Tokenizer tokenizer) throws UnexpectedCharException {
        if(isCancelOthers()){
            switch (subState){
                case COMMASPLITDONE:
                    if(tokenizer.getcChar() == ')'){
                        subState = SubState.END;
                    }else {
                        setIgnoreWhitespace(false);
                        setCancelOthers(false);
                        before = tokenizer.getState();
                        tokenizer.setState(TokenizeStates.VALUE);
                        TokenizeStates.VALUE.setFirstOnCloseListener((data) -> {
                            tokenizer.setState(tokenizer.getBefore());
                            setCancelOthers(true);
                            setIgnoreWhitespace(true);
                            subState = SubState.COMMASPLIT;
                            parameters.add((VariableValueToken) data[0]);
                        });
                    }
                    break;
                case COMMASPLIT:
                    if(tokenizer.getcChar() == ','){
                        subState = SubState.COMMASPLITDONE;
                    }else if(tokenizer.getcChar() == ')'){
                        subState = SubState.END;
                    }else throw new UnexpectedCharException(tokenizer, "Expected Whitespace, ')' or ','!");
                    break;
                case END:
                    if(tokenizer.getcChar() == ';'){
                        TokenizeConstants.IN_SOURCE_READER.statements.add(new FunctionCallStatementToken(name, parameters));
                        parameters.clear();
                        name = "";
                        setIgnoreWhitespace(false);
                        setCancelOthers(false);
                        tokenizer.setState(before);
                    }else throw new UnexpectedCharException(tokenizer, "Expected Whitespace or ';'");
                    break;
            }
        }else if(lastIndex == -1 || tokenizer.getIndex() == lastIndex+1){
            if(tokenizer.getcChar() == '('){
                setCancelOthers(true);
                subState = SubState.COMMASPLITDONE;
                setIgnoreWhitespace(true);
            }else if(!CharUtil.isWhitespace(tokenizer.getcChar())){
                name += tokenizer.getcChar();
                lastIndex = tokenizer.getIndex();
            }
        }else name = "";
    }
}
