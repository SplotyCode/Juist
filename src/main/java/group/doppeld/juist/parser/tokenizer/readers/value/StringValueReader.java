package group.doppeld.juist.parser.tokenizer.readers.value;

import group.doppeld.juist.exeptions.UnexpectedCharException;
import group.doppeld.juist.parser.tokenizer.TokenizeReader;
import group.doppeld.juist.parser.tokenizer.Tokenizer;
import group.doppeld.juist.parser.tokenizer.tokens.VariableValueToken;

public class StringValueReader extends TokenizeReader {

    private String string = "";
    private boolean locked = false;

    @Override
    public void handleChar(Tokenizer tokenizer) throws UnexpectedCharException {
        if(isCancelOthers()){
            if(tokenizer.getcChar() == '\\') locked = true;
            else if(tokenizer.getcChar() == '"' && !locked) {
                setCancelOthers(false);
                close(tokenizer, new VariableValueToken(VariableValueToken.VariableType.STRING, string));
                string = "";
            } else {
                locked = false;
                string += tokenizer.getcChar();
            }
        }else if(tokenizer.getcChar() == '"') {
            setCancelOthers(true);
        }
    }
}
