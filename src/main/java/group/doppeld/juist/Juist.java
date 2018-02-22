package group.doppeld.juist;

import group.doppeld.juist.config.StartArguments;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;

public class Juist implements Runnable {

    private StartArguments startArguments;

    public static void main(String[] args){
        new Juist(new StartArguments().parse(args)).run();
    }

    public Juist(StartArguments startArguments){
        this.startArguments = startArguments;
    }

    public void run() {
        startArguments.handle(this);
    }

    public void log(String message){
        if(startArguments.isDebug()){
            try (PrintStream out = new PrintStream(new FileOutputStream(startArguments.getLogFile()))) {
                out.print(message);
                out.flush();
                out.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        System.out.println(message);
    }

    public void stop(){
        System.exit(0);
    }
}
