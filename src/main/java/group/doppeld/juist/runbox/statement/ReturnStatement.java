package group.doppeld.juist.runbox.statement;


import group.doppeld.juist.runbox.Function;
import group.doppeld.juist.runbox.Script;
import group.doppeld.juist.runbox.Statement;
import group.doppeld.juist.runbox.variable.SignalVariable;
import group.doppeld.juist.runbox.variable.Variable;

public class ReturnStatement extends Statement {

    private Variable variable;

    public ReturnStatement(Variable variable) {
        this.variable = variable;
    }

    @Override
    public void run(Script script, long currentCall, Function function) {
        if(variable instanceof SignalVariable)
            variable = script.getFunctionVarbyName(currentCall, ((SignalVariable) variable).getPlaceholder(), true);
        function.setReturned(variable);
    }
}
