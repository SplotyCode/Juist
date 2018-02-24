package group.doppeld.juist.parser.tokenizer;

import com.sun.istack.internal.Nullable;
import group.doppeld.juist.exeptions.InternalException;
import group.doppeld.juist.exeptions.UnexpectedCharException;

public abstract class TokenizeReader {
    
    private boolean cancelOthers = false;
    private boolean skip = false;

    private boolean ignoreWhitespace = false;

    private CloseListener onClose = null;
    private boolean keepOnClose = false;

    public TokenizeReader(){}

    public TokenizeReader(boolean ignoreWhitespace){
        this.ignoreWhitespace = ignoreWhitespace;
    }

    public void close(Tokenizer tokenizer, Object... data){
        TokenizeStates state = tokenizer.getState();
        if(state.getFirstOnCloseListener() != null){
            state.getFirstOnCloseListener().onClose(data);
            state.setFirstOnCloseListener(null);
        }else {
            if (onClose != null) onClose.onClose(data);
            else throw new InternalException("No One is here to say how to close the reader '" + getClass().getSimpleName() + "'!");
            if (!keepOnClose) onClose = null;
        }
    }

    public abstract void handleChar(Tokenizer tokenizer) throws UnexpectedCharException;




    public boolean isCancelOthers(){
        return cancelOthers;    
    }
    
    public void setCancelOthers(boolean cancelOthers){
        this.cancelOthers = cancelOthers;
    }
    
    public boolean isSkip(){
        return skip;    
    }
    
    public void setSkip(boolean skip){
        this.skip = skip;
    }

    public boolean isIgnoreWhitespace() {
        return ignoreWhitespace;
    }

    public void setIgnoreWhitespace(boolean ignoreWhitespace) {
        this.ignoreWhitespace = ignoreWhitespace;
    }

    @Nullable public CloseListener getOnClose() {
        return onClose;
    }

    public void setOnClose(CloseListener onClose) {
        this.onClose = onClose;
    }

    public boolean isKeepOnClose() {
        return keepOnClose;
    }

    public void setKeepOnClose(boolean keepOnClose) {
        this.keepOnClose = keepOnClose;
    }
}
