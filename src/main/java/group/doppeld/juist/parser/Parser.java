package group.doppeld.juist.parser;

import group.doppeld.juist.Juist;
import group.doppeld.juist.fileload.JuistFileLoader;
import group.doppeld.juist.parser.tokenizer.Tokenizer;
import group.doppeld.juist.util.StringUtil;

//Singelton
public class Parser {

    public void parse(JuistFileLoader loader){
        long tokenizeStart = System.currentTimeMillis();
        Tokenizer tokenizer = new Tokenizer(loader.getContent());
        //tokenizer.setUpdater(() -> StringUtil.printProgressBar("Tokenizer", tokenizer.getIndex(), tokenizer.getSource().length(), tokenizeStart));
        tokenizer.process();
        Juist.getInstance().log("");
        Juist.getInstance().log("Tokenizer finished in " + (System.currentTimeMillis()-tokenizeStart) + "ms");
    }

}
