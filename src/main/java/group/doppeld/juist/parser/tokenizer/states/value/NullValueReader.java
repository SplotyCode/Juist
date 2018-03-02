package group.doppeld.juist.parser.tokenizer.states.value;

import group.doppeld.juist.exeptions.UnexpectedCharException;
import group.doppeld.juist.parser.tokenizer.TokenizeReader;
import group.doppeld.juist.parser.tokenizer.Tokenizer;
import group.doppeld.juist.parser.tokenizer.tokens.VariableValueToken;

public class NullValueReader extends TokenizeReader {
  
  public NullValueReader(){
    super(true);
  }
  
  @Override    
  public void handleChar(Tokenizer tokenizer) throws UnexpectedCharException {
    if(tokenizer.isNextSkip("null")){
        close(tokenizer, new VariableValueToken(VariableValueToken.VariableType.NULL, null));
    }
  }
  
}  