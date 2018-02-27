package group.doppeld.juist.parser.tokenizer.states.value;

import group.doppeld.juist.exeptions.UnexpectedCharException;
import group.doppeld.juist.parser.tokenizer.TokenizeReader;
import group.doppeld.juist.parser.tokenizer.Tokenizer;
import group.doppeld.juist.parser.tokenizer.tokens.VariableValueToken;
import group.doppeld.juist.util.ListUtil;

public class VariableValueReader extends TokenizeReader {

    private String name;

    private char[] validChars = "abcdefghijklmnopqrstuvwxyz_".toCharArray();

    @Override
    public void handleChar(Tokenizer tokenizer) throws UnexpectedCharException {
        if(isCancelOthers()){
            if(ListUtil.containsArray(tokenizer.getcChar(), validChars)){
                name += tokenizer.getcChar();
            }else {
                close(tokenizer, new VariableValueToken<String>(VariableValueToken.VariableType.VARIABLE, name));
            }
        }else if(ListUtil.containsArray(tokenizer.getcChar(), validChars)){
            setCancelOthers(true);
            name = tokenizer.getcChar()+"";
        }
    }
}
