package group.doppeld.juist.parser.tokenizer.tokens;

import group.doppeld.juist.parser.tokenizer.Token;

public class VariableToken extends Token {

    private final String type;
    private final String name;

    public VariableToken(String type, String name) {
        this.type = type;
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public String getName() {
        return name;
    }
}
