,
VOIDpackage group.doppeld.juist.parser.tokenizer.tokens;

import group.doppeld.juist.parser.tokenizer.Token;

public class VariableValueToken<T> extends Token {

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
    private final T content;

    public VariableValueToken(VariableType type, T content) {
        this.type = type;
        this.content = content;
    }

    public T getContent() {
        return content;
    }

    public VariableType getType() {
        return type;
    }
}
