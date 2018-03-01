package group.doppeld.juist.parser.tokenizer.tokens.statements;

import group.doppeld.juist.parser.tokenizer.tokens.StatementToken;
import group.doppeld.juist.parser.tokenizer.tokens.VariableValueToken;
import group.doppeld.juist.runbox.Script;
import group.doppeld.juist.runbox.Statement;
import group.doppeld.juist.runbox.statement.FuctionCallStatement;

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

    @Override
    public Statement toStatement(Script script) {
        return new FuctionCallStatement(script.getFunction());
    }
}
