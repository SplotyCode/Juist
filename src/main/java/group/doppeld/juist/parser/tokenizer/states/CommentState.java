package group.doppeld.juist.parser.tokenizer.states;

import group.doppeld.juist.parser.tokenizer.TokenizeState;
import group.doppeld.juist.parser.tokenizer.TokenizeStates;
import group.doppeld.juist.parser.tokenizer.Tokenizer;

public class CommentState extends TokenizeState {

    private boolean advaned = false;

    @Override
    public void handleChar(Tokenizer tokenizer) {
        if(tokenizer.getState() == TokenizeStates.COMMENT){
            if(isExitComment(tokenizer)) tokenizer.setState(tokenizer.getBefore());
            setCancelOthers(false);
            return;
        }

        if(isNewComment(tokenizer)){
            tokenizer.setBefore(tokenizer.getState());
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
