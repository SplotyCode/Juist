package group.doppeld.juist.runbox;

public enum VariableType {

    STRING,
    INTEGER,
    SHORT,
    FLOAT,
    LONG,
    DOUBLE;

    public boolean isNumeric(){
        return this != STRING;
    }
}
