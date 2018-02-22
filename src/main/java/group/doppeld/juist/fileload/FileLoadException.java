package group.doppeld.juist.fileload;

import java.io.IOException;

public class FileLoadException extends IOException {


    public FileLoadException(){}

    public FileLoadException(String message){
        super(message);
    }

    public FileLoadException(String message, Throwable throwable) {
        super(message, throwable);
    }

    public FileLoadException(Throwable throwable) {
        super(throwable);
    }


}
