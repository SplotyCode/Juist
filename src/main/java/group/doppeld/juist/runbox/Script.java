package group.doppeld.juist.runbox;

import group.doppeld.juist.exeptions.InternalException;
import group.doppeld.juist.exeptions.MethodeNotFoundExeption;
import group.doppeld.juist.exeptions.VariableNotFoundExeption;
import group.doppeld.juist.runbox.variable.Variable;
import group.doppeld.juist.util.StringUtil;

import java.util.ArrayList;
import java.util.HashMap;

public class Script {

    private ArrayList<Function> methods = new ArrayList<>();
    private HashMap<String, Variable> classVariables = new HashMap<>();
    private HashMap<Long, HashMap<String, Variable>> functionVariables = new HashMap<>();
    private long currentCallId = 0;

    public long getNewCallID(){
        currentCallId++;
        return currentCallId;
    }

    public Variable getClassVariablebyName(String name){
        return classVariables.get(name);
    }

    public Variable getFunctionVarbyName(long callID, String name, boolean error){
        HashMap<String, Variable> map = functionVariables.get(callID);
        if(map == null) {
            if(error) throw new InternalException("InValid Function CallID");
            else return null;
        }
        Variable variable = map.get(name);
        if(variable == null) {
            if(error) throw new VariableNotFoundExeption("Function variable '" + name + "' count not be found!");
            else return null;
        }
        return variable;
    }


    public Function getFunction(String name, VariableType[] types, boolean error){
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
        if(error) {
            if (!hasName) throw new MethodeNotFoundExeption("No Method with the name '" + name + "' was found!");
            if (!hasSize)
                throw new MethodeNotFoundExeption("No Method with the name '" + name + "' and " + types.length + " arguments had been found!");
            throw new MethodeNotFoundExeption("Count not find method '" + name + "' with arguments " + StringUtil.join(types, Enum::name, ", "));
        }
        return null;
    }

    public boolean hasFunction(String name, VariableType[] types, boolean error){
        return getFunction(name, types, error) != null;
    }

    public ArrayList<Function> getMethods() {
        return methods;
    }

    public HashMap<String, Variable> getClassVariables() {
        return classVariables;
    }

    public HashMap<Long, HashMap<String, Variable>> getFunctionVariables() {
        return functionVariables;
    }
}
