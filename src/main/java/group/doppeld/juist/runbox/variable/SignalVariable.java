package group.doppeld.juist.runbox.variable;

import group.doppeld.juist.exeptions.InternalException;

public class SignalVariable<T> extends Variable<T> {

    private String placeholder;

    public SignalVariable() {
        super(null);
    }

    public SignalVariable(String placeholder) {
        super(null);
        this.placeholder = placeholder;
    }

    @Override
    public T getValue() {
        throw new InternalException("Only for Singals!");
    }

    public String getPlaceholder() {
        return placeholder;
    }

    public void setPlaceholder(String placeholder) {
        this.placeholder = placeholder;
    }
}
