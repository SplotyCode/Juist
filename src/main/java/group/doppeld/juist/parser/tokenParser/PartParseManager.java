package group.doppeld.juist.parser.tokenParser;

import group.doppeld.juist.exeptions.InternalException;
import group.doppeld.juist.parser.tokenizer.Token;
import group.doppeld.juist.runbox.Script;
import group.doppeld.juist.util.ListUtil;

import java.util.ArrayList;

public class PartParseManager {

    private ArrayList<TokenParserPart> partParsers = new ArrayList<>();

    public PartParseManager(){
        partParsers.clear();

    }

    private void register(TokenParserPart part){
        for(Class<? extends Token> newClazz : part.getFrom())
            for(TokenParserPart element : partParsers)
                for(Class<? extends Token> clazz : element.getFrom())
                    if(clazz == newClazz) throw new InternalException(clazz.getSimpleName() + " was registered twice...");
        partParsers.add(part);
    }

    public TokenParserPart process(Script script, Token token){
        TokenParserPart part = getPartParserbyToken(token);
        part.preParse(script, token);
        return part;
    }

    public TokenParserPart getPartParserbyToken(Token token){
        Class<? extends Token> clazz = token.getClass();
        for(TokenParserPart partParser : partParsers)
            if(ListUtil.containsArray(clazz, partParser.getFrom())) {
                return partParser;
            }
        throw new InternalException("No one want to process '" + clazz.getSimpleName() + "' :(");
    }
}
