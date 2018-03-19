package group.doppeld.juist.parser.tokenParser;

import group.doppeld.juist.parser.tokenizer.Token;
import group.doppeld.juist.runbox.Script;

public abstract class TokenParserPart {

    private Class<? extends Token>[] from;

    @SafeVarargs
    public TokenParserPart(Class<? extends Token>... from){
        this.from = from;
    }

    public abstract void preParse(Script script, Token token);
    public void postParse(Script script, Token token){}

    public Class<? extends Token>[] getFrom() {
        return from;
    }
}
