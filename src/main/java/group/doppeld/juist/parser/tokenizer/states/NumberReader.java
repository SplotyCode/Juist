package group.doppeld.juist.parser.tokenizer.states;

import group.doppeld.juist.exeptions.UnexpectedCharException;
import group.doppeld.juist.parser.tokenizer.TokenizeReader;
import group.doppeld.juist.parser.tokenizer.Tokenizer;
import group.doppeld.juist.parser.tokenizer.tokens.VariableValueToken;
import group.doppeld.juist.util.ListUtil;

import java.util.Collections;

public class NumberReader extends TokenizeReader {


    private char[] startChars = new char[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9};

    private char[] inNumberChars = new char[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9, '.'};

    private char[] endChars = new char[]{'d', 'f', 'l', 's'};

    private VariableValueToken.VariableType getVariableTypebyNumber(String number, char end){
        if(end != Character.MIN_VALUE) {
            switch (end){
                case 'd':
                    return VariableValueToken.VariableType.DOUBLE;
                case 'f':
                    return VariableValueToken.VariableType.FLOAT;
                case 'l':
                    return VariableValueToken.VariableType.LONG;
                case 's':
                    return VariableValueToken.VariableType.SHORT;
            }
        }
        return hasFloatingPoint?VariableValueToken.VariableType.DOUBLE: VariableValueToken.VariableType.INTEGER;
    }

    private String content;
    private boolean hasFloatingPoint = false;

    @Override
    public void handleChar(Tokenizer tokenizer) throws UnexpectedCharException {
        if(isCancelOthers()){
            if(ListUtil.containsArray(tokenizer.getcChar(), inNumberChars)) {
                content += tokenizer.getcChar();
                if(tokenizer.getcChar() == '.') {
                    if(hasFloatingPoint) throw new UnexpectedCharException(tokenizer, "What do you want to do with that point we already have one???");
                    else hasFloatingPoint = true;
                }
            }
            else if(ListUtil.containsArray(tokenizer.getcChar(), endChars)) {
                tokenizer.getTokens().add(new VariableValueToken<>(getVariableTypebyNumber(content, tokenizer.getcChar()), content));
                content = "";
                setCancelOthers(false);
                //TODO Break
            }else{
                tokenizer.getTokens().add(new VariableValueToken<>(getVariableTypebyNumber(content, Character.MIN_VALUE), content));
                content = "";
                setCancelOthers(false);
                //TODO Break
            }
        }else if(ListUtil.containsArray(tokenizer.getcChar(), startChars)){
            hasFloatingPoint = false;
            setCancelOthers(true);
        }
    }
}
