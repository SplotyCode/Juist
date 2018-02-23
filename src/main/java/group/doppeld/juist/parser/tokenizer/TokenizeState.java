package group.doppeld.juist.parser.tokenizer;

public abstract class TokenizeState {
    
    private boolean cancelOthers = false;
    private boolean skip = false;
    
    public abstract void handleChar(Tokenizer tokenizer);
    
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
    
}
