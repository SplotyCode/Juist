package group.doppeld.juist;

import group.doppeld.juist.config.StartArguments;
import group.doppeld.juist.exeptions.FileLoadException;
import group.doppeld.juist.fileload.JuistFileLoader;
import group.doppeld.juist.parser.Parser;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;

public class Juist implements Runnable {

    private StartArguments startArguments;
    private Parser parser;

    private static Juist instance;

    public static void main(String[] args){
        new Juist(new StartArguments().parse(args)).run();
    }
    public Juist(StartArguments startArguments){
        this.startArguments = startArguments;
    }

    public void run() {
        instance = this;
        startArguments.handle(this);
        parser = new Parser();
        try {
            parser.parse(new JuistFileLoader().load(startArguments.getFile()));
        } catch (FileLoadException ex) {
            ex.printStackTrace();
        }
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

    public static Juist getInstance() {
        return instance;
    }

    public void stop(){
        System.exit(0);
    }
}
