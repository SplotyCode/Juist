package group.doppeld.juist.runbox;

import group.doppeld.juist.runbox.variable.Variable;

import java.util.ArrayList;

public class Function {

    private String name;
    private ArrayList<Statement> statements;
    private ArrayList<Parameter> parameters;
    private VariableType returnValue;

    public void run(long callID, Variable... parameters) {

    }

    public Function(String name, ArrayList<Statement> statements, ArrayList<Parameter> parameters, VariableType returnValue) {
        this.name = name;
        this.statements = statements;
        this.parameters = parameters;
        this.returnValue = returnValue;
    }

    public ArrayList<Statement> getStatements() {
        return statements;
    }

    public void setStatements(ArrayList<Statement> statements) {
        this.statements = statements;
    }

    public ArrayList<Parameter> getParameters() {
        return parameters;
    }

    public void setParameters(ArrayList<Parameter> parameters) {
        this.parameters = parameters;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public VariableType getReturnValue() {
        return returnValue;
    }

    public void setReturnValue(VariableType returnValue) {
        this.returnValue = returnValue;
    }
}
