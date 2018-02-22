package group.doppeld.juist.fileload;

import java.io.*;
import java.nio.charset.Charset;

public class FileLoader {

    private String content;
    private Charset charset;

    public FileLoader(){
        charset = Charset.defaultCharset();
        content = null;
    }

    public FileLoader(Charset charset){
        this.charset = charset;
        content = null;
    }

    public FileLoader(File file){
        charset = Charset.defaultCharset();
        readFile(file);
    }

    public FileLoader(File file, Charset charset){
        this.charset = charset;
        readFile(file);
    }

    public void readFile(InputStream stream){
        try {
            InputStreamReader input = new InputStreamReader(stream, charset);
            Reader reader = new BufferedReader(input);

            StringBuilder builder = new StringBuilder();
            char[] buffer = new char[8192];
            int read;

            while ((read = reader.read(buffer, 0, buffer.length)) > 0) {
                builder.append(buffer, 0, read);
            }
            builder.append("\n");
            content = builder.toString();
        }catch (IOException ex){
            ex.printStackTrace();
        }finally {
            if(stream != null) try {
                stream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void readFile(File file){
        InputStream stream = null;
        try {
            stream = new FileInputStream(file);
            readFile(stream);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            if(stream != null) {
                try {
                    stream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public Charset getCharset() {
        return charset;
    }

    public String getContent() {
        return content;
    }
}
