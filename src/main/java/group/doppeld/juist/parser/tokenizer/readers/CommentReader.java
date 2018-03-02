package group.doppeld.juist.parser.tokenizer.readers;

import group.doppeld.juist.parser.tokenizer.TokenizeReader;
import group.doppeld.juist.parser.tokenizer.TokenizeStates;
import group.doppeld.juist.parser.tokenizer.Tokenizer;

public class CommentReader extends TokenizeReader {

    private boolean advanced = false;

    @Override
    public void handleChar(Tokenizer tokenizer) {
        if(tokenizer.getState() == TokenizeStates.COMMENT){
            if(isExitComment(tokenizer)) tokenizer.setState(tokenizer.getBefore());
            setCancelOthers(false);
            return;
        }

        if(isNewComment(tokenizer)){
            tokenizer.setState(TokenizeStates.COMMENT);
            setCancelOthers(true);
        }
    }

    private boolean isExitComment(Tokenizer tokenizer){
        if(advanced){
            return tokenizer.isNextSkip("*/");
        }else{
            return tokenizer.getcChar() == '\n';
        }
    }

    private boolean isNewComment(Tokenizer tokenizer){
        if(tokenizer.getcChar() == '#'){
            advanced = false;
            return true;
        }else if(tokenizer.getcChar() == '/' && tokenizer.next() == '*'){
            advanced = true;
            return true;
        }
        return false;
    }

}
