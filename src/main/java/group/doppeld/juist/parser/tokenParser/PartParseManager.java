package group.doppeld.juist.parser.tokenParser;

import group.doppeld.juist.exeptions.InternalException;
import group.doppeld.juist.parser.tokenParser.special.ClassVariableParser;
import group.doppeld.juist.parser.tokenParser.special.FunctionParser;
import group.doppeld.juist.parser.tokenizer.Token;
import group.doppeld.juist.parser.tokenizer.tokens.StatementToken;
import group.doppeld.juist.runbox.Script;
import group.doppeld.juist.util.ListUtil;

import java.util.ArrayList;

public class PartParseManager {

    private ArrayList<StatementParserPart> partParsers = new ArrayList<>();
    //Special PartParsers
    private ClassVariableParser classVariableParser = new ClassVariableParser();
    private FunctionParser functionParser = new FunctionParser();

    public PartParseManager(){
        partParsers.clear();

    }

    private void register(StatementParserPart part){
        for(Class newClazz : part.getFrom())
            for(StatementParserPart element : partParsers)
                for(Class clazz : element.getFrom())
                    if(clazz == newClazz) throw new InternalException(clazz.getSimpleName() + " was registered twice...");
        partParsers.add(part);
    }

    public StatementParserPart process(Script script, StatementToken token){
        StatementParserPart part = getPartParserbyToken(token);
        part.preParse(script, token);
        return part;
    }

    public StatementParserPart getPartParserbyToken(StatementToken token){
        Class<? extends StatementToken> clazz = token.getClass();
        for(StatementParserPart partParser : partParsers)
            if(ListUtil.containsArray(clazz, partParser.getFrom())) {
                return partParser;
            }
        throw new InternalException("No one want to process '" + clazz.getSimpleName() + "' :(");
    }

    public ArrayList<StatementParserPart> getPartParsers() {
        return partParsers;
    }

    public FunctionParser getFunctionParser() {
        return functionParser;
    }

    public FunctionParser getNewFunctionParser() {
        try {
            return functionParser.getClass().newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }

    public ClassVariableParser getClassVariableParser() {
        return classVariableParser;
    }

    public ClassVariableParser getnewClassVariableParser() {
        try {
            return classVariableParser.getClass().newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }
}
