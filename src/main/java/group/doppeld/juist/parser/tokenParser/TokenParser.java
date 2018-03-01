package group.doppeld.juist.parser.tokenParser;

import group.doppeld.juist.parser.Parser;

public class TokenParser {

    private final Parser parser;

    public TokenParser(Parser parser) {
        this.parser = parser;
    }

    public Parser getParser() {
        return parser;
    }
}
