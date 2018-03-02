package group.doppeld.juist.parser.tokenizer.readers.value;

import group.doppeld.juist.exeptions.UnexpectedCharException;
import group.doppeld.juist.parser.tokenizer.TokenizeReader;
import group.doppeld.juist.parser.tokenizer.Tokenizer;
import group.doppeld.juist.parser.tokenizer.tokens.VariableValueToken;
import group.doppeld.juist.util.ListUtil;

public class VariableValueReader extends TokenizeReader {

    private String name;
    private String Methodname;

    private final MethodCallReader methodCallReader = new MethodCallReader();

    enum ValueStates {
        READINGVALUE,
        VALUEISMETHOD
    }

    private ValueStates valueStates;
    private final char[] VALIDCHARS = "abcdefghijklmnopqrstuvwxyz_ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();

    @Override
    public void handleChar(Tokenizer tokenizer) throws UnexpectedCharException {
        if (isCancelOthers()) {
            switch (valueStates) {
                case READINGVALUE:
                    if (ListUtil.containsArray(tokenizer.getcChar(), VALIDCHARS)) {
                        name += tokenizer.getcChar();
                    } else {
                        setIgnoreWhitespace(true);
                        valueStates = ValueStates.VALUEISMETHOD;
                    }
                    break;


               case VALUEISMETHOD:
                   if(tokenizer.getcChar() == '('){


                   }else {


                   }
                   break;
           }
        }else {
            if(ListUtil.containsArray(tokenizer.getcChar(), VALIDCHARS)) {
                setCancelOthers(true);
                name = tokenizer.getcChar()+"";
                valueStates = ValueStates.READINGVALUE;
            }
        }
    }
}
