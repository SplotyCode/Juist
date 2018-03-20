package group.doppeld.juist.parser.tokenParser;

import group.doppeld.juist.parser.tokenizer.Token;
import group.doppeld.juist.parser.tokenizer.tokens.StatementToken;
import group.doppeld.juist.runbox.Script;
import group.doppeld.juist.runbox.Statement;

import java.util.jar.JarEntry;

public abstract class StatementParserPart<T extends Statement> {

    private Class<? extends StatementToken>[] from;

    @SafeVarargs
    public StatementParserPart(Class<? extends StatementToken>... from){
        this.from = from;
    }

    public abstract void preParse(Script script, StatementToken token);
    public void postParse(Script script, T statement){}

    public Class<? extends StatementToken>[] getFrom() {
        return from;
    }
}
