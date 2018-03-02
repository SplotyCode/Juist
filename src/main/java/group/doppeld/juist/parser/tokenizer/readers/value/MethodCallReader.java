package group.doppeld.juist.parser.tokenizer.readers.value;

import group.doppeld.juist.exeptions.UnexpectedCharException;
import group.doppeld.juist.parser.tokenizer.TokenizeReader;
import group.doppeld.juist.parser.tokenizer.Tokenizer;

public class MethodCallReader extends TokenizeReader {

    public MethodCallReader(){
        super(true);
    }

    @Override
    public void handleChar(Tokenizer tokenizer) throws UnexpectedCharException {
        if(isCancelOthers()){

        }
    }

}
