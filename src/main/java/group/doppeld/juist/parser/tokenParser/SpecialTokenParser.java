package group.doppeld.juist.parser.tokenParser;

import group.doppeld.juist.parser.tokenizer.Token;
import group.doppeld.juist.runbox.Script;

public abstract class SpecialTokenParser extends TokenParserPart {

    public abstract void preParse(Script script, Token token);
}
