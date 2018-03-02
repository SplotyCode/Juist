package group.doppeld.juist.runbox.variable;

public class VoidVariable extends Variable<Object> {

    public VoidVariable() {
        super(null);
    }
    
    @Override    
    public Object getValue() {        
        return null;    
    }
}