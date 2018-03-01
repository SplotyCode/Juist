package group.doppeld.juist.runbox.variable;

import group.doppeld.juist.exeptions.InvalidTypeExeption;
import group.doppeld.juist.runbox.VariableType;

public abstract class Variable<T> {

    private VariableType type;

    public Variable(VariableType type) {
        this.type = type;
    }

    public abstract T getValue();

    public VariableType getType() {
        return type;
    }

    public String getString(){
        if(type == VariableType.STRING) return (String) getValue();
        throw new InvalidTypeExeption("Expected String found '" + type.name() + "'!");
    }

    public double getNumber(){
        if(type.isNumeric()) return (Double) getValue();
        throw new InvalidTypeExeption("Expected Numeric ValueType");
    }

    public boolean isNumber(){
        return type.isNumeric();
    }
}
