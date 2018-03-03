package group.doppeld.juist.runbox.methodes;

import group.doppeld.juist.runbox.VariableType;
import group.doppeld.juist.runbox.variable.Variable;

public abstract class GlobalFunction {

    private String name;
    private VariableType parameters;

    public abstract void execute(Variable[] variables);

    public GlobalFunction(String name, VariableType parameters) {
        this.name = name;
        this.parameters = parameters;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public VariableType getParameters() {
        return parameters;
    }

    public void setParameters(VariableType parameters) {
        this.parameters = parameters;
    }
}
