package group.doppeld.juist.parser.tokenizer.tokens.statements;

import group.doppeld.juist.parser.tokenizer.tokens.StatementToken;
import group.doppeld.juist.parser.tokenizer.tokens.VariableValueToken;

import java.util.ArrayList;

public class FunctionCallStatementToken extends StatementToken {

    private String name;
    private ArrayList<VariableValueToken> arguments;

    public FunctionCallStatementToken(String name, ArrayList<VariableValueToken> arguments) {
        this.name = name;
        this.arguments = arguments;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<VariableValueToken> getArguments() {
        return arguments;
    }

    public void setArguments(ArrayList<VariableValueToken> arguments) {
        this.arguments = arguments;
    }
}
