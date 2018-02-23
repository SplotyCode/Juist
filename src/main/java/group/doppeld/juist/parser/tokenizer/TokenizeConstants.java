package group.doppeld.juist.parser.tokenizer;

import group.doppeld.juist.parser.tokenizer.states.CommentReader;
import group.doppeld.juist.parser.tokenizer.states.NumberReader;
import group.doppeld.juist.parser.tokenizer.states.StringValueReader;
import group.doppeld.juist.parser.tokenizer.states.ValReader;

public final class TokenizeConstants {

    public static final CommentReader COMMENT_READER = new CommentReader();
    public static final NumberReader NUMBER_READER = new NumberReader();
    public static final StringValueReader STRING_VALUE_READER = new StringValueReader();
    public static final ValReader VAL_READER = new ValReader();

}
