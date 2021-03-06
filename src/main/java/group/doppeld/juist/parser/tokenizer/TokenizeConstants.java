package group.doppeld.juist.parser.tokenizer;

import group.doppeld.juist.parser.tokenizer.readers.*;
import group.doppeld.juist.parser.tokenizer.readers.statements.FunctionCallStatementReader;
import group.doppeld.juist.parser.tokenizer.readers.statements.ReturnStatementReader;
import group.doppeld.juist.parser.tokenizer.readers.value.*;

public final class TokenizeConstants {

    public static final CommentReader COMMENT_READER = new CommentReader();
    public static final ValReader VAL_READER = new ValReader();
    public static final LastDefaultReader LAST_DEFAULT_READER = new LastDefaultReader();
    public static final FunReader FUN_READER = new FunReader();
    public static final LastValueReader LAST_VALUE_READER = new LastValueReader();
    public static final InSourceReader IN_SOURCE_READER = new InSourceReader();

    /* Statements */
    public static final ReturnStatementReader RETURN_STATEMENT_READER = new ReturnStatementReader();
    public static final FunctionCallStatementReader FUNCTION_CALL_STATEMENT_READER = new FunctionCallStatementReader();

    /* Values */
    public static final NumberReader NUMBER_READER = new NumberReader();
    public static final StringValueReader STRING_VALUE_READER = new StringValueReader();
    public static final VariableValueReader VARIABLE_VALUE_READER = new VariableValueReader();
    public static final NullValueReader NULL_VALUE_READER = new NullValueReader();
    public static final BooleanReader BOOLEAN_VALUE_READER = new BooleanReader();

}
