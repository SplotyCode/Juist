package group.doppeld.juist.fileload;

import group.doppeld.juist.Constants;
import group.doppeld.juist.anotations.Nullable;
import group.doppeld.juist.exeptions.FileLoadException;
import group.doppeld.juist.util.FileUtil;
import java.io.File;
import java.io.IOException;

public class JuistFileLoader {

    private LoadType type;
    private File file;
    private String content;

    @Nullable
    private JuistArchiveLoader archive;

    public JuistFileLoader load(final File file) throws FileLoadException {
        String extension = FileUtil.getExtension(file);
        this.file = file;
        switch (extension){
            case Constants.EXTENSION: {
                type = LoadType.SINGLE;
                FileLoader loader = new FileLoader();
                loader.readFile(file);
                content = loader.getContent();
                break;
            }case Constants.PACKAGE_EXTENSION: {
                type = LoadType.PROJECT;
                try {
                    archive = new JuistArchiveLoader(file);
                    FileLoader loader = new FileLoader();
                    loader.readFile(archive.getMain());
                    content = loader.getContent();
                } catch (IOException ex) {
                    throw new FileLoadException("Error Reading File!", ex);
                }
                break;
            }default:
                throw new FileLoadException("File format '" + extension + "' is not valid! Try '" + Constants.EXTENSION + "' or '" + Constants.PACKAGE_EXTENSION + "'!");
        }
        return this;
    }

    public String getContent() {
        return content;
    }

    public File getFile() {
        return file;
    }

    @Nullable public JuistArchiveLoader getArchive() {
        return archive;
    }
}
