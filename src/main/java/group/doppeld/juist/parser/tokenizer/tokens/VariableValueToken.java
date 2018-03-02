package group.doppeld.juist.parser.tokenizer.tokens;

import group.doppeld.juist.parser.tokenizer.Token;

public class VariableValueToken extends Token {

    public enum VariableType {

        STRING,
        DOUBLE,
        FLOAT,
        LONG,
        INTEGER,
        SHORT,
        VARIABLE,
        NULL,
        VOID
    }

    private final VariableType type;
    private final String content;

    public VariableValueToken(VariableType type, String content) {
        this.type = type;
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public VariableType getType() {
        return type;
    }
}
