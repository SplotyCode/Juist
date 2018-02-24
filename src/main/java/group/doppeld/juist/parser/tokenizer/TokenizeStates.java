package group.doppeld.juist.parser.tokenizer;

public enum TokenizeStates {

    DEFAULT(null, TokenizeConstants.COMMENT_READER, TokenizeConstants.VAL_READER),
    COMMENT(TokenizeConstants.COMMENT_READER),
    VALUE(null, TokenizeConstants.STRING_VALUE_READER);

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
