package group.doppeld.juist.parser.tokenizer.tokens.statements;

import group.doppeld.juist.parser.tokenParser.TokenParseHelper;
import group.doppeld.juist.parser.tokenizer.tokens.StatementToken;
import group.doppeld.juist.parser.tokenizer.tokens.VariableValueToken;
import group.doppeld.juist.runbox.Function;
import group.doppeld.juist.runbox.Script;
import group.doppeld.juist.runbox.Statement;
import group.doppeld.juist.runbox.VariableType;
import group.doppeld.juist.runbox.statement.FuctionCallStatement;
import group.doppeld.juist.runbox.variable.SignalVariable;
import group.doppeld.juist.runbox.variable.Variable;

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
        ArrayList<Variable> variables = new ArrayList<>();
        VariableType[] types = new VariableType[arguments.size()];
        int i = 0;
        for(VariableValueToken token : arguments){
            types[i] = TokenParseHelper.getType(token.getType());
            if(token.getType() != VariableValueToken.VariableType.VARIABLE) {
                variables.add(TokenParseHelper.getRealVariablebyToken(token));
                i++;
                continue;
            }
            Variable variable = script.getClassVariablebyName((String) token.getContent());
            if(variable != null) variables.add(variable);
            else new SignalVariable<String>((String) token.getContent());
            i++;
        }
        return new FuctionCallStatement(script, script.getFunction(getName(), types, true), variables);
    }
}
