package group.doppeld.juist.runbox.variable;

import group.doppeld.juist.runbox.VariableType;

public class LinkedVariable<T> extends Variable<T> {

    private Variable<T> orginal;

    public LinkedVariable(Variable<T> original, VariableType type) {
        super(type);
        this.orginal = original;
    }

    @Override
    public T getValue() {
        return orginal.getValue();
    }
}
