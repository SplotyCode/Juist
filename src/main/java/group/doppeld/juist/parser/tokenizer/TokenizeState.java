package group.doppeld.juist.parser.tokenizer;

public abstract class TokenizeState {
    
    private boolean cancelOthers = false;
    
    public abstract void handleChar(Tokenizer tokenizer);
    
    public boolean isCancelOthers(){
        return cancelOthers;    
    }
    
    public void setCancelOthers(boolean cancelOthers){
        this.cancelOthers = cancelOthers;
        
    }
}
