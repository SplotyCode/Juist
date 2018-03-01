package group.doppeld.juist.runbox;

import java.util.ArrayList;

public class Appliecation {

    private Script main;
    private ArrayList<Script> other;

    public Appliecation(Script main, ArrayList<Script> other) {
        this.main = main;
        this.other = other;
    }

    public void start(){
        for(Function method : main.getMethods())
            if(method.getName().equals("startApp")){
                //TODO start arguments
                method.run(main, main.getNewCallID());
                break;
            }
    }

}
