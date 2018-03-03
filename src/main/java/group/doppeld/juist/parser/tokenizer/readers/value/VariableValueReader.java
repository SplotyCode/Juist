package group.doppeld.juist.parser.tokenizer.readers.value;

import group.doppeld.juist.exeptions.UnexpectedCharException;
import group.doppeld.juist.parser.tokenizer.CloseListener;
import group.doppeld.juist.parser.tokenizer.TokenizeReader;
import group.doppeld.juist.parser.tokenizer.TokenizeStates;
import group.doppeld.juist.parser.tokenizer.Tokenizer;
import group.doppeld.juist.parser.tokenizer.readers.statements.FunctionCallStatementReader;
import group.doppeld.juist.parser.tokenizer.tokens.MethodValueToken;
import group.doppeld.juist.parser.tokenizer.tokens.VariableValueToken;
import group.doppeld.juist.runbox.Parameter;
import group.doppeld.juist.util.ListUtil;

import java.util.ArrayList;

public class VariableValueReader extends TokenizeReader {

    private String name;
    private CloseListener lastCloseListener = null;
    private ArrayList<VariableValueToken> parameters = new ArrayList<>();


    enum ValueStates {
        READINGVALUE,
        VALUEISMETHOD,
        SETPARAMETER,
        COMMASPLIT,
        END

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
                   if(tokenizer.getcChar() == '('){
                       valueStates = ValueStates.SETPARAMETER;
                   }else {
                       close(tokenizer, new VariableValueToken(VariableValueToken.VariableType.VARIABLE, name));
                       setCancelOthers(false);
                       setIgnoreWhitespace(false);
                   }
                   break;
                case SETPARAMETER:
                    if(tokenizer.getcChar() == ')'){
                        valueStates = ValueStates.END;
                    }else {
                        if(lastCloseListener == null)
                            lastCloseListener = getCurrentCloseListener(tokenizer);
                        TokenizeStates.VALUE.setFirstOnCloseListener((data) -> {
                            tokenizer.setState(tokenizer.getBefore());
                            setCancelOthers(true);
                            setIgnoreWhitespace(true);
                            valueStates = ValueStates.COMMASPLIT;
                            parameters.add((VariableValueToken) data[0]);
                        });
                    }
                    break;
                case COMMASPLIT:
                    if(tokenizer.getcChar() == ','){
                        valueStates = ValueStates.SETPARAMETER;

                    }else if(tokenizer.getcChar() == ')'){
                        valueStates = ValueStates.END;

                    }
                    break;
                case END:
                    lastCloseListener.onClose(new Object[] { new MethodValueToken(VariableValueToken.VariableType.METHOD,name,parameters)});
                    parameters.clear();
                    name = "";
                    setIgnoreWhitespace(false);
                    setCancelOthers(false);
                    break;


           }
        }else {
            if(ListUtil.containsArray(tokenizer.getcChar(), VALIDCHARS)) {
                lastCloseListener = null;
                setCancelOthers(true);
                name = tokenizer.getcChar()+"";
                valueStates = ValueStates.READINGVALUE;
            }
        }
    }
}
