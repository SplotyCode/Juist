package group.doppeld.juist.runbox;

import group.doppeld.juist.exeptions.InvalidTypeExeption;
import group.doppeld.juist.exeptions.MissingReturnException;
import group.doppeld.juist.runbox.variable.Variable;
import group.doppeld.juist.runbox.variable.VoidVariable;

import java.util.ArrayList;
import java.util.HashMap;

public class Function {

    private String name;
    private ArrayList<Statement> statements;
    private ArrayList<Parameter> parameters;
    private VariableType returnValue;
    private Variable returned;

    public Variable run(Script script, long callID, Variable... variables) {
        returned = null;
        HashMap<String, Variable> localVariables = new HashMap<>();
        for(int i = 0;i<variables.length;i++) {
            if(variables[i].getType() == parameters.get(i).getType())
                localVariables.put(parameters.get(i).getName(), variables[i]);
            else throw new InvalidTypeExeption("Wrong Methode type for argument " + i);
        }
        script.getFunctionVariables().put(callID, localVariables);
        //TODO get returned value
        for(Statement statement : statements){
            statement.run(script, callID, this);
            if(returned != null){
                if(returned.getType() != returnValue) throw new InvalidTypeExeption("Wrong Return type for '" + name + "'!");
                return returned;
            }
        }
        if(returnValue != null){
            throw new MissingReturnException("You forgot return so please add it to your code or this message will come again");
        }
        return new VoidVariable();
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

    public Variable getReturned() {
        return returned;
    }

    public void setReturned(Variable returned) {
        this.returned = returned;
    }
}
