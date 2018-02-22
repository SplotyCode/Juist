package group.doppeld.juist.config;

import group.doppeld.juist.Constants;
import group.doppeld.juist.Juist;

import java.io.File;

public class StartArguments {

    private boolean debug = false;
    private File logFile = null;
    private boolean version = false;
    private File file = null;

    public StartArguments parse(String[] args){
        String before = "";
        for(String arg : args){
            if(arg.equals("-d")) debug = true;
            else if(arg.equals("-logDir")) before = "-logDir";
            else if(before.equals("-logDir")) logFile = new File(arg);
            else if(arg.equals("-version") || arg.equals("-v")) version = true;
            else if(!arg.startsWith("-")) file = new File(arg);
        }
        return this;
    }

    public void handle(Juist juist){
        if(version){
            juist.log("Juist " + Constants.VERSION);
            juist.log("Java " + System.getProperty("java.version"));
            juist.log("Running on " + System.getProperty("os.name") + " " + System.getProperty("os.version") + " (" + System.getProperty("os.arch"));
            juist.stop();
        }
    }

    public boolean isDebug() {
        return debug;
    }

    public File getLogFile() {
        return logFile;
    }

    public boolean isVersion() {
        return version;
    }

    public File getFile() {
        return file;
    }
}
