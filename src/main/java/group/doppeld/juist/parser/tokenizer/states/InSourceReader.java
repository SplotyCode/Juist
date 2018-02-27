package group.doppeld.juist.parser.tokenizer.states;

import group.doppeld.juist.exeptions.UnexpectedCharException;
import group.doppeld.juist.parser.tokenizer.TokenizeReader;
import group.doppeld.juist.parser.tokenizer.Tokenizer;
import group.doppeld.juist.parser.tokenizer.tokens.StatementToken;

import java.util.ArrayList;

public class InSourceReader extends TokenizeReader {

    public ArrayList<StatementToken> statements = new ArrayList<>();


    @Override
    public void handleChar(Tokenizer tokenizer) throws UnexpectedCharException {
        if(tokenizer.getcChar() == '}'){
            setSkip(true);
            close(tokenizer, statements);
            //System.out.println("CLOSED!!!");
        }
    }
}
