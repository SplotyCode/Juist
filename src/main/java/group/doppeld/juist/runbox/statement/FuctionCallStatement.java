package group.doppeld.juist.runbox.statement;

import group.doppeld.juist.runbox.Function;
import group.doppeld.juist.runbox.Statement;
import group.doppeld.juist.runbox.variable.Variable;

//TODO
public class FuctionCallStatement extends Statement {

    private Function function;
    private Variable[] variables;


    public FuctionCallStatement(Function function, Variable[] variables) {
        this.function = function;
        this.variables = variables;
    }

    @Override
    public void run() {

    }

    public Function getFunction() {
        return function;
    }

    public void setFunction(Function function) {
        this.function = function;
    }

    public Variable[] getVariables() {
        return variables;
    }

    public void setVariables(Variable[] variables) {
        this.variables = variables;
    }
}
