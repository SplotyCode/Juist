package group.doppeld.juist.parser.tokenizer;

import group.doppeld.juist.exeptions.UnexpectedCharExeption;

public abstract class TokenizeReader {
    
    private boolean cancelOthers = false;
    private boolean skip = false;

    private boolean ignoreWithspace = false;

    public TokenizeReader(){}

    public TokenizeReader(boolean ignoreWithspace){
        this.ignoreWithspace = ignoreWithspace;
    }
    
    public abstract void handleChar(Tokenizer tokenizer) throws UnexpectedCharExeption;
    
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

    public boolean isIgnoreWithspace() {
        return ignoreWithspace;
    }

    public void setIgnoreWithspace(boolean ignoreWithspace) {
        this.ignoreWithspace = ignoreWithspace;
    }
}
