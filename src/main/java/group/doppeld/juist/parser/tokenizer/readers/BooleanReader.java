package group.doppeld.juist.parser.tokenizer.readers;

public class FunReader extends TokenizeReader {

    @Override
    public void handleChar(Tokenizer tokenizer) throws UnexpectedCharException {
        if(tokenizer.isNextSkip("true")){
            close(new VariableValueToken(VariableType.BOOLEAN), "true");
        }else if(tokenizer.isNextSkip("false")){
            close(new VariableValueToken(VariableType.BOOLEAN), "false");
        }
    }

}