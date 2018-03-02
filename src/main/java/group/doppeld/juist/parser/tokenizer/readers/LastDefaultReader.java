package group.doppeld.juist.parser.tokenizer.readers;

import group.doppeld.juist.exeptions.UnexpectedCharException;
import group.doppeld.juist.parser.tokenizer.TokenizeReader;
import group.doppeld.juist.parser.tokenizer.Tokenizer;

public class LastDefaultReader extends TokenizeReader {

    public LastDefaultReader(){
        super(true);
    }

    @Override
    public void handleChar(Tokenizer tokenizer) throws UnexpectedCharException {
        throw new UnexpectedCharException(tokenizer);
    }

}
