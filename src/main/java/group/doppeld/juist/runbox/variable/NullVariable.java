package group.doppeld.juist.runbox.variable;

import group.doppeld.juist.runbox.VariableType;


public class LinkedVariable<T> extends Variable<T> {
    
    public LinkedVariable(VariableType type) {
        super(type);
    }
    
    @Override
    public T getValue() {
        return null;
    }
}