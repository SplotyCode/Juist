package group.doppeld.juist.parser.tokenizer.states.statements;

import group.doppeld.juist.exeptions.UnexpectedCharException;
import group.doppeld.juist.parser.tokenizer.TokenizeConstants;
import group.doppeld.juist.parser.tokenizer.TokenizeReader;
import group.doppeld.juist.parser.tokenizer.TokenizeStates;
import group.doppeld.juist.parser.tokenizer.Tokenizer;
import group.doppeld.juist.parser.tokenizer.tokens.VariableValueToken;
import group.doppeld.juist.parser.tokenizer.tokens.statements.ReturnStatementToken;

public class ReturnStatementReader extends TokenizeReader {

    private TokenizeStates before;
    private VariableValueToken value;
    private boolean end;

    @Override
    public void handleChar(Tokenizer tokenizer) throws UnexpectedCharException {
        if(isCancelOthers()){
            if(end){
                if(tokenizer.getcChar() == ';'){
                    setCancelOthers(false);
                    setIgnoreWhitespace(false);
                    tokenizer.setState(before);
                    TokenizeConstants.IN_SOURCE_READER.statements.add(new ReturnStatementToken(value));
                    value = null;
                    before = null;
                }else throw new UnexpectedCharException(tokenizer, "Expected ';' or whitespace");
            }else {
                setIgnoreWhitespace(false);
                setCancelOthers(false);
                before = tokenizer.getBefore();
                tokenizer.setState(TokenizeStates.VALUE);
                TokenizeStates.VALUE.setFirstOnCloseListener((data) -> {
                    tokenizer.setState(TokenizeStates.SOURCE);
                    setCancelOthers(true);
                    setIgnoreWhitespace(true);
                    value = (VariableValueToken) data[0];
                    end = true;
                });
            }
        }else if(tokenizer.isNextSkip("return")){
            setCancelOthers(true);
            setIgnoreWhitespace(true);
            end = false;
        }
    }
}
