package group.doppeld.juist.parser.tokenizer;

import group.doppeld.juist.exeptions.UnexpectedCharException;

public interface CloseListener {

    void onClose(Object[] data) throws UnexpectedCharException;

}
