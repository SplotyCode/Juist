package group.doppeld.juist.parser.tokenizer;

import group.doppeld.juist.parser.tokenizer.states.CommentState;

public enum TokenizeStates {

    DEFAULT(null, new CommentState()),
    COMMENT(new CommentState());

    private final TokenizeState state;
    private final TokenizeState[] active;

    TokenizeStates(TokenizeState state, TokenizeState... active){
        this.state = state;
        this.active = active;
    }

    public TokenizeState[] getActive() {
        return active;
    }

    public TokenizeState get() {
        return state;
    }
}
