package group.doppeld.juist.runbox.methodes;

import group.doppeld.juist.runbox.Function;

import java.util.HashSet;
import java.util.Set;

public class GlobalFunctionManager {

    private Set<Function> globalFunctions = new HashSet<>();

    private final static GlobalFunctionManager INSTANCE = new GlobalFunctionManager();

    private GlobalFunctionManager(){

    }

    public static GlobalFunctionManager getINSTANCE() {
        return INSTANCE;
    }
}
