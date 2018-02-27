package group.doppeld.juist.parser.tokenizer.states.value;

import group.doppeld.juist.exeptions.UnexpectedCharException;
import group.doppeld.juist.parser.tokenizer.TokenizeReader;
import group.doppeld.juist.parser.tokenizer.Tokenizer;

public class LastValueReader extends TokenizeReader {

    public LastValueReader(){
        super(true);
    }

    @Override
    public void handleChar(Tokenizer tokenizer) throws UnexpectedCharException {
        throw new UnexpectedCharException(tokenizer, "This is not a Value :(");
    }
}
