package group.doppeld.juist.fileload;

import group.doppeld.juist.Constants;
import group.doppeld.juist.fileload.yaml.YamlConfiguration;
import group.doppeld.juist.util.FileUtil;

import java.io.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

public class JuistArchiveLoader {

    private String mainPath;
    private InputStream main;
    private String juistVersion;

    public JuistArchiveLoader(final File file) throws IOException {
        ZipFile zipFile = new ZipFile(file);
        ZipEntry manifest = zipFile.getEntry("manifest.yml");
        YamlConfiguration yamlManifest = new YamlConfiguration();
        InputStream stream = zipFile.getInputStream(manifest);
        Reader reader = new InputStreamReader(stream);
        yamlManifest.load(reader);
        mainPath = yamlManifest.getString("main");
        juistVersion = yamlManifest.getString("juistversion");
        main = zipFile.getInputStream(zipFile.getEntry(mainPath));
        reader.close();
        stream.close();
        zipFile.close();
    }

    public String getMainPath() {
        return mainPath;
    }

    public String getJuistVersion() {
        return juistVersion;
    }

    public InputStream getMain() {
        return main;
    }
}
