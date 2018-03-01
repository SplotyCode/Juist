package group.doppeld.juist.runbox.variable;

import group.doppeld.juist.runbox.VariableType;

public class RealVariable<T> extends Variable<T> {

    private T value;

    public RealVariable(T value, VariableType type) {
        super(type);
        this.value = value;
    }

    @Override
    public T getValue() {
        return value;
    }
}
