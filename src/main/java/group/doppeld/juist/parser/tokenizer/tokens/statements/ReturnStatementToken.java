package group.doppeld.juist.parser.tokenizer.tokens.statements;

import group.doppeld.juist.parser.tokenParser.TokenParseHelper;
import group.doppeld.juist.parser.tokenizer.tokens.StatementToken;
import group.doppeld.juist.parser.tokenizer.tokens.VariableValueToken;
import group.doppeld.juist.runbox.Script;
import group.doppeld.juist.runbox.Statement;
import group.doppeld.juist.runbox.statement.ReturnStatement;
import group.doppeld.juist.runbox.variable.SignalVariable;
import group.doppeld.juist.runbox.variable.Variable;

public class ReturnStatementToken extends StatementToken {

    private final VariableValueToken returnedValue;

    public ReturnStatementToken(VariableValueToken returnedValue) {
        this.returnedValue = returnedValue;
    }

    public VariableValueToken getReturnedValue() {
        return returnedValue;
    }

    @Override
    public Statement toStatement(Script script) {
        Variable variable = null;
        if(returnedValue.getType() != VariableValueToken.VariableType.VARIABLE) {
            variable = TokenParseHelper.getRealVariablebyToken(returnedValue);
        }else {
            Variable classVariable = script.getClassVariablebyName(returnedValue.getContent());
            if (classVariable != null) variable = classVariable;
            else new SignalVariable(returnedValue.getContent());
        }
        return new ReturnStatement(variable);
    }
}
