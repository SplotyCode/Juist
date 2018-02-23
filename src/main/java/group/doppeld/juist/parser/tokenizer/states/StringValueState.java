package group.doppeld.juist.parser.tokenizer.states;

import group.doppeld.juist.parser.tokenizer.TokenizeState;
import group.doppeld.juist.parser.tokenizer.TokenizeStates;
import group.doppeld.juist.parser.tokenizer.Tokenizer;
import group.doppeld.juist.parser.tokenizer.tokens.VariableValueToken;

public class StringValueState extends TokenizeState {

    private String string = "";
    private boolean locket = false;

    @Override
    public void handleChar(Tokenizer tokenizer) {
        if(tokenizer.getState() == TokenizeStates.STRINGVALUE){
            if(tokenizer.getcChar() == '\\') locket = true;
            else if(tokenizer.getcChar() == '"' && !locket) {
                //TODO What is the next state????
                tokenizer.getTokens().add(new VariableValueToken<>(VariableValueToken.VariableType.STRING, string));
                string = "";
                setCancelOthers(false);
            } else string += tokenizer.getcChar();
        }else if(tokenizer.getcChar() == '"') {
            tokenizer.setState(TokenizeStates.STRINGVALUE);
            setCancelOthers(true);
        }
    }
}
