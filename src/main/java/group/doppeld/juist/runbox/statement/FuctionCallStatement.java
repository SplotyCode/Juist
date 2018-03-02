package group.doppeld.juist.runbox.statement;

import group.doppeld.juist.runbox.Function;
import group.doppeld.juist.runbox.Script;
import group.doppeld.juist.runbox.Statement;
import group.doppeld.juist.runbox.variable.SignalVariable;
import group.doppeld.juist.runbox.variable.Variable;

import java.util.ArrayList;
import java.util.HashMap;

//TODO
public class FuctionCallStatement extends Statement {

    private Function function;
    private ArrayList<Variable> variables;


    public FuctionCallStatement(Script script, Function function, ArrayList<Variable> variables) {
        this.function = function;
        this.variables = variables;
    }

    @Override
    public void run(Script script, long currentCall, Function function) {
        ArrayList<Variable> variables = new ArrayList<>();
        int i = 0;
        for(Variable variable : new ArrayList<>(this.variables)){
            if(variable instanceof SignalVariable)
                variables.set(i, script.getFunctionVarbyName(currentCall, ((SignalVariable) variable).getPlaceholder(), true));
            i++;
        }
        function.run(script, script.getNewCallID(), variables.toArray(new Variable[variables.size()]));
    }

    public Function getFunction() {
        return function;
    }

    public void setFunction(Function function) {
        this.function = function;
    }


}
