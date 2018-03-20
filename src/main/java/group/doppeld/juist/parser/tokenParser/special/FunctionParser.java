package group.doppeld.juist.parser.tokenParser.special;

import group.doppeld.juist.exeptions.FunctionAlreadyDefined;
import group.doppeld.juist.exeptions.VariableAlreadyDefined;
import group.doppeld.juist.parser.tokenParser.SpecialTokenParser;
import group.doppeld.juist.parser.tokenParser.TokenParseHelper;
import group.doppeld.juist.parser.tokenizer.Token;
import group.doppeld.juist.parser.tokenizer.tokens.FunctionToken;
import group.doppeld.juist.parser.tokenizer.tokens.StatementToken;
import group.doppeld.juist.runbox.*;
import group.doppeld.juist.util.StringUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class FunctionParser extends SpecialTokenParser {

    private Function function;
    private ArrayList<StatementToken> rawStatements;
    private Script script;

    @Override
    public void preParse(Script script, Token rawToken) {
        FunctionToken token = (FunctionToken) rawToken;
        this.script = script;
        rawStatements = token.getStatements();
        /* Returned Type */VariableType type = TokenParseHelper.getType(token.getType());
        /* Paramters Stuff*/
        HashMap<String, VariableType> parameters = new HashMap<>();
        for(Map.Entry<String, String> pair : token.getArguments().entrySet()){
            if(parameters.containsKey(pair.getKey())) throw new VariableAlreadyDefined("Variable is already defined in this method");
            parameters.put(pair.getKey(), TokenParseHelper.getType(pair.getValue()));
        }
        ArrayList<Parameter> finalParameters = new ArrayList<>();
        for(Map.Entry<String, VariableType> entry : parameters.entrySet())
            finalParameters.add(new Parameter(entry.getValue(), entry.getKey()));
        /* Does the method already exists? */
        VariableType[] array = parameters.values().toArray(new VariableType[parameters.values().size()]);
        if(script.hasFunction(token.getName(), array, false)) throw new FunctionAlreadyDefined("Function " + token.getName() + " already exsits with " + StringUtil.join(array, Enum::name, ", "));
        function = new Function(token.getName(), new ArrayList<>(), finalParameters, type);
        script.getMethods().add(function);
    }

    public void postLoad(){
        /* What is actually in the method? */
        ArrayList<Statement> statements = new ArrayList<>();
        for(StatementToken statement : rawStatements){
            //TODO convert!!!
        }
        function.setStatements(statements);
    }
}
