package group.doppeld.juist.parser.tokenizer.states;

import group.doppeld.juist.parser.tokenizer.TokenizeReader;
import group.doppeld.juist.parser.tokenizer.TokenizeStates;
import group.doppeld.juist.parser.tokenizer.Tokenizer;

public class CommentReader extends TokenizeReader {

    private boolean advaned = false;

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
        if(advaned){
            return tokenizer.getcChar() == '*' && tokenizer.next() == '/';
        }else{
            return tokenizer.getcChar() == '\n';
        }
    }

    private boolean isNewComment(Tokenizer tokenizer){
        if(tokenizer.getcChar() == '/' && tokenizer.next() == '/'){
            advaned = false;
            return true;
        }else if(tokenizer.getcChar() == '/' && tokenizer.next() == '*'){
            advaned = true;
            return true;
        }
        return false;
    }

}
