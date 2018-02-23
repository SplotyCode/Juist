package group.doppeld.juist.parser.tokenizer;

import group.doppeld.juist.parser.tokenizer.states.CommentState;
import group.doppeld.juist.parser.tokenizer.states.StringValueState;
import group.doppeld.juist.parser.tokenizer.states.ValState;

public enum TokenizeStates {

    DEFAULT(null, new CommentState(), new ValState()),
    COMMENT(new CommentState()),
    VALUE(null, new StringValueState());

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
