package group.doppeld.juist.runbox.variable;

import group.doppeld.juist.runbox.VariableType;

public class VoidVariable extends Variable<Object> {

    public VoidVariable(VariableType type) {        
        super(type);    
    }
    
    @Override    
    public Object getValue() {        
        return null;    
    }
}