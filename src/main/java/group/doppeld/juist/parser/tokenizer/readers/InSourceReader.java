package group.doppeld.juist.parser.tokenizer.readers;

import group.doppeld.juist.exeptions.UnexpectedCharException;
import group.doppeld.juist.parser.tokenizer.TokenizeReader;
import group.doppeld.juist.parser.tokenizer.Tokenizer;
import group.doppeld.juist.parser.tokenizer.tokens.StatementToken;

import java.util.ArrayList;

public class InSourceReader extends TokenizeReader {

    public ArrayList<StatementToken> statements = new ArrayList<>();


    public InSourceReader(){
        super(true);
    }

    @Override
    public void handleChar(Tokenizer tokenizer) throws UnexpectedCharException {
        if(tokenizer.getcChar() == '}'){
            setSkip(true);
            close(tokenizer, statements);
            //System.out.println("CLOSED!!!");
        }else {
            throw new UnexpectedCharException(tokenizer, "Invalid Statement!");
        }
    }
}
