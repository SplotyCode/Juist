package group.doppeld.juist.parser.tokenizer.tokens;

import group.doppeld.juist.parser.tokenizer.Token;
import group.doppeld.juist.runbox.Script;
import group.doppeld.juist.runbox.Statement;

public abstract class StatementToken extends Token {

    public abstract Statement toStatement(Script script);
}
