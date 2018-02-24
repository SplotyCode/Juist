package group.doppeld.juist.parser.tokenizer.tokens;

import group.doppeld.juist.parser.tokenizer.Token;

public class VariableToken extends Token {

    private final String type;
    private final String name;
    private VariableValueToken value;

    public VariableToken(String type, String name) {
        this.type = type;
        this.name = name;
        value = null;
    }

    public VariableToken(String type, String name, VariableValueToken value) {
        this.type = type;
        this.name = name;
        this.value = value;
    }

    public String getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    public VariableValueToken getValue() {
        return value;
    }

    public void setValue(VariableValueToken value) {
        this.value = value;
    }
}
