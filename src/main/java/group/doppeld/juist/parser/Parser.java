package group.doppeld.juist.parser;

import group.doppeld.juist.Juist;
import group.doppeld.juist.exeptions.OperationNotSupportedExeption;
import group.doppeld.juist.exeptions.SyntaxException;
import group.doppeld.juist.fileload.JuistFileLoader;
import group.doppeld.juist.parser.tokenParser.ClassVariableParser;
import group.doppeld.juist.parser.tokenParser.FunctionParser;
import group.doppeld.juist.parser.tokenizer.Token;
import group.doppeld.juist.parser.tokenizer.Tokenizer;
import group.doppeld.juist.parser.tokenizer.tokens.FunctionToken;
import group.doppeld.juist.parser.tokenizer.tokens.VariableToken;
import group.doppeld.juist.runbox.Application;
import group.doppeld.juist.runbox.Script;
import group.doppeld.juist.util.StringUtil;

import java.util.ArrayList;

//Singelton
public class Parser {

    private final ClassVariableParser classVariableParser = new ClassVariableParser(this);
    private final FunctionParser functionParser = new FunctionParser(this);

    public void parse(JuistFileLoader loader){
        long tokenizeStart = System.currentTimeMillis();
        Tokenizer tokenizer = new Tokenizer(loader.getContent());
        //tokenizer.setUpdater(() -> StringUtil.printProgressBar("Tokenizer", tokenizer.getIndex(), tokenizer.getSource().length(), tokenizeStart));
        tokenizer.process();
        Juist.getInstance().log("");
        Juist.getInstance().log("Tokenizer finished in " + (System.currentTimeMillis()-tokenizeStart) + "ms! And generated " + tokenizer.getTokens().size() + " tokens.");
        Script main = tokenizerToScript(tokenizer);
        Application application = new Application(main, new ArrayList<>());
        application.start();
    }

    private Script tokenizerToScript(final Tokenizer tokenizer) throws SyntaxException {
        Script script = new Script();
        ArrayList<VariableToken> classVars = new ArrayList<>();
        for(Token token : tokenizer.getTokens()){
            if(token instanceof VariableToken){
                classVars.add((VariableToken) token);
            }else if(token instanceof FunctionToken){
                functionParser.parse(script, (FunctionToken) token);
            }else throw new OperationNotSupportedExeption("Invalid Token '" + token.getClass().getSimpleName() + "'!");
        }
        for(VariableToken var : classVars){
            classVariableParser.parse(script, var);
        }
        return script;
    }


}
