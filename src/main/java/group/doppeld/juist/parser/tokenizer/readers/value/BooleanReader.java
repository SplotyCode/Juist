package group.doppeld.juist.parser.tokenizer.readers.value;

import group.doppeld.juist.exeptions.UnexpectedCharException;
import group.doppeld.juist.parser.tokenizer.TokenizeReader;
import group.doppeld.juist.parser.tokenizer.Tokenizer;
import group.doppeld.juist.parser.tokenizer.tokens.VariableValueToken;

public class BooleanReader extends TokenizeReader {

    @Override
    public void handleChar(Tokenizer tokenizer) throws UnexpectedCharException {
        if(tokenizer.isNextSkip("true")){
            close(tokenizer, new VariableValueToken(VariableValueToken.VariableType.BOOLEAN, "true"));
        }else if(tokenizer.isNextSkip("false")){
            close(tokenizer, new VariableValueToken(VariableValueToken.VariableType.BOOLEAN, "false"));
        }
    }

}