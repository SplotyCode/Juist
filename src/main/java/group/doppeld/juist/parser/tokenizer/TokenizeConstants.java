package group.doppeld.juist.parser.tokenizer;

import group.doppeld.juist.parser.tokenizer.states.*;

public final class TokenizeConstants {

    public static final CommentReader COMMENT_READER = new CommentReader();
    public static final NumberReader NUMBER_READER = new NumberReader();
    public static final StringValueReader STRING_VALUE_READER = new StringValueReader();
    public static final ValReader VAL_READER = new ValReader();
    public static final LastDefaultReader LAST_DEFAULT_READER = new LastDefaultReader();
    public static final FunReader FUN_READER = new FunReader();
    public static final LastValueReader LAST_VALUE_READER = new LastValueReader();

}
