package group.doppeld.juist.parser.tokenizer.tokens;

import java.util.ArrayList;

public class MethodValueToken extends VariableValueToken {

    private ArrayList<VariableValueToken> arguments = new ArrayList<>();

    public MethodValueToken(VariableType type, String content, ArrayList<VariableValueToken> arguments) {
        super(type, content);
        this.arguments = arguments;
    }

    public ArrayList<VariableValueToken> getArguments() {
        return arguments;
    }
}
