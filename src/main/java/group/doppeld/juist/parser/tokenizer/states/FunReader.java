package group.doppeld.juist.parser.tokenizer.states;

import group.doppeld.juist.exeptions.UnexpectedCharException;
import group.doppeld.juist.parser.tokenizer.TokenizeReader;
import group.doppeld.juist.parser.tokenizer.Tokenizer;

import java.util.HashMap;

public class FunReader extends TokenizeReader {


    private String name = "";
    private String type = "";
    private HashMap<String, String> arguments = new HashMap<>();
    private String curArgName = "", curArgType = "";

    public enum SubState {

        IDLE,
        BEFORENAME,
        NAME,
        ARGUMENTSSTART,
        BEFOREARGNAME,
        ARGNAME,
        ARGSPLIT,
        ARGSPLITDONE,
        ARGTYPE,
        ARGTYPEDONE,
        END

    }

    private ValReader.SubState state = ValReader.SubState.IDLE;

    @Override
    public void handleChar(Tokenizer tokenizer) throws UnexpectedCharException {
        if(isCancelOthers()){

        }else if(tokenizer.isNextSkip("fun")){
            setCancelOthers(true);
            setIgnoreWhitespace(true);
        }
    }
}
