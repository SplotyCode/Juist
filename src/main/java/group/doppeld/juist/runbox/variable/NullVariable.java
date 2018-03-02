package group.doppeld.juist.runbox.variable;

import group.doppeld.juist.runbox.VariableType;


public class NullVariable extends Variable<Object> {
    
    public NullVariable(VariableType type) {
        super(type);
    }
    
    @Override
    public Object getValue() {
        return null;
    }
}