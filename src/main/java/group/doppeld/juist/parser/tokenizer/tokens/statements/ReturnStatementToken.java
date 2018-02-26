package group.doppeld.juist.parser.tokenizer.tokens.statements;

import group.doppeld.juist.parser.tokenizer.tokens.StatementToken;
import group.doppeld.juist.parser.tokenizer.tokens.VariableValueToken;

public class ReturnStatementToken extends StatementToken {

    private final VariableValueToken returnedValue;

    public ReturnStatementToken(VariableValueToken returnedValue) {
        this.returnedValue = returnedValue;
    }

    public VariableValueToken getReturnedValue() {
        return returnedValue;
    }
}
