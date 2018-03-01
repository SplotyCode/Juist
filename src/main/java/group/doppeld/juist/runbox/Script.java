package group.doppeld.juist.runbox;

import group.doppeld.juist.exeptions.MethodeNotFoundExeption;
import group.doppeld.juist.runbox.variable.Variable;
import group.doppeld.juist.util.StringUtil;

import java.util.ArrayList;
import java.util.HashMap;

public class Script {

    private ArrayList<Function> methods = new ArrayList<>();
    private HashMap<String, Variable> classVariables = new HashMap<>();

    public Function getFunction(String name, VariableType[] types){
        boolean hasName = false;
        boolean hasSize = false;
        for(Function function : methods){
            if(function.getName().equals(name)){
                hasName = true;
                if(function.getParameters().size() == types.length){
                    hasSize = true;
                    boolean fail = false;
                    for(int i = 0;i < types.length;i++){
                        if(function.getParameters().get(i).getType() != types[i]){
                            fail = true;
                        }
                    }
                    if(!fail) return function;
                }
            }
        }
        if(!hasName) throw new MethodeNotFoundExeption("No Method with the name '" + name + "' was found!");
        if(!hasSize) throw new MethodeNotFoundExeption("No Method with the name '" + name + "' and " + types.length + " arguments had been found!");
        throw new MethodeNotFoundExeption("Count not find method '" + name + "' with arguments " + StringUtil.join(types, Enum::name, ", "));
    }

    public boolean hasFunction(String name, VariableType[] types){
        return getFunction(name, types) != null;
    }

    public ArrayList<Function> getMethods() {
        return methods;
    }

    public HashMap<String, Variable> getClassVariables() {
        return classVariables;
    }
}
