package group.doppeld.juist.runbox;

public class Parameter {

    private VariableType type;
    private String name;

    public Parameter(VariableType type, String name) {
        this.type = type;
        this.name = name;
    }

    public VariableType getType() {
        return type;
    }

    public void setType(VariableType type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
