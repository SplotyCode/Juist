package group.doppeld.juist.parser.tokenizer.readers.statements;

import group.doppeld.juist.exeptions.UnexpectedCharException;
import group.doppeld.juist.parser.tokenizer.TokenizeConstants;
import group.doppeld.juist.parser.tokenizer.TokenizeReader;
import group.doppeld.juist.parser.tokenizer.TokenizeStates;
import group.doppeld.juist.parser.tokenizer.Tokenizer;
import group.doppeld.juist.parser.tokenizer.tokens.VariableValueToken;
import group.doppeld.juist.parser.tokenizer.tokens.statements.ReturnStatementToken;
import group.doppeld.juist.util.CharUtil;

public class ReturnStatementReader extends TokenizeReader {

    private TokenizeStates before;
    
    @Override
    public void handleChar(Tokenizer tokenizer) throws UnexpectedCharException {
        if(isCancelOthers()){
            if(!CharUtil.isWhitespace(tokenizer.next())) {
                //System.out.println("cur2" + tokenizer.next(0));
                changeValue(tokenizer);
            }else if(tokenizer.next(0) == ';'){
                    setCancelOthers(false);
                    tokenizer.setState(tokenizer.getBefore());
                    TokenizeConstants.IN_SOURCE_READER.statements.add(new ReturnStatementToken(new VariableValueToken(VariableValueToken.VariableType.VOID, null)));
            }
        }else if(tokenizer.isNextSkip("return ")){
            setCancelOthers(true);
            System.out.println("cur" + tokenizer.next(0));
            if(!CharUtil.isWhitespace(tokenizer.next(0))) {
                tokenizer.reHandleChar();
                changeValue(tokenizer);
            }else if(tokenizer.next(0) == ';'){
                    setCancelOthers(false);
                    tokenizer.setState(tokenizer.getBefore());
                    TokenizeConstants.IN_SOURCE_READER.statements.add(new ReturnStatementToken(new VariableValueToken(VariableValueToken.VariableType.VOID, null)));
            }
        }
    }
    
    private void changeValue(Tokenizer tokenizer) throws UnexpectedCharException {
        //System.out.println(tokenizer.getcChar());
        setCancelOthers(false);
        before = tokenizer.getState();
        tokenizer.setState(TokenizeStates.VALUE);
        TokenizeStates.VALUE.setFirstOnCloseListener((data) -> {
                tokenizer.setState(TokenizeStates.SOURCE);
                setCancelOthers(true);
                setIgnoreWhitespace(true);
                System.out.println(tokenizer.getcChar());
                if(tokenizer.getcChar() == ';'){
                    setCancelOthers(false);
                    setIgnoreWhitespace(false);
                    tokenizer.setState(before);
                    TokenizeConstants.IN_SOURCE_READER.statements.add(new ReturnStatementToken((VariableValueToken) data[0]));
                    before = null;
                }else throw new UnexpectedCharException(tokenizer, "Expected ';' or whitespace");
            });
    }
}
