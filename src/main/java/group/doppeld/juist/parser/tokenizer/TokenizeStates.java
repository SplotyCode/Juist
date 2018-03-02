package group.doppeld.juist.parser.tokenizer;

import static group.doppeld.juist.parser.tokenizer.TokenizeConstants.*;

public enum TokenizeStates {

    DEFAULT(null, COMMENT_READER, VAL_READER, FUN_READER, LAST_DEFAULT_READER),
    COMMENT(COMMENT_READER),
    VALUE(null, COMMENT_READER, STRING_VALUE_READER, NUMBER_READER, NULL_VALUE_READER, VARIABLE_VALUE_READER, LAST_VALUE_READER),
    SOURCE(null, COMMENT_READER, VAL_READER, RETURN_STATEMENT_READER, FUNCTION_CALL_STATEMENT_READER, /* This should be the main state but it needs to be the last reader */IN_SOURCE_READER);

    private final TokenizeReader state;
    private final TokenizeReader[] active;

    //Not really a listener
    private CloseListener firstOnCloseListener = null;

    TokenizeStates(TokenizeReader state, TokenizeReader... active){
        this.state = state;
        this.active = active;
    }

    public TokenizeReader[] getActive() {
        return active;
    }

    public TokenizeReader get() {
        return state;
    }

    public CloseListener getFirstOnCloseListener() {
        return firstOnCloseListener;
    }

    public void setFirstOnCloseListener(CloseListener firstOnCloseListener) {
        this.firstOnCloseListener = firstOnCloseListener;
    }
}
