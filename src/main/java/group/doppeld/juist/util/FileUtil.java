package group.doppeld.juist.util;

import java.io.File;

public final class FileUtil {

    public static String getExtension(final File file){
        final String fileName = file.getName();
        String extension = "";
        int i = fileName.lastIndexOf('.');
        int p = Math.max(fileName.lastIndexOf('/'), fileName.lastIndexOf('\\'));

        if (i > p) {
            extension = fileName.substring(i+1);
        }
        return extension;
    }
}
