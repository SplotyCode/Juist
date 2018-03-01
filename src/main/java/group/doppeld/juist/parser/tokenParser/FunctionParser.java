package group.doppeld.juist.parser.tokenParser;

import group.doppeld.juist.exeptions.FunctionAlreadyDefined;
import group.doppeld.juist.exeptions.VariableAlreadyDefined;
import group.doppeld.juist.parser.Parser;
import group.doppeld.juist.parser.tokenizer.tokens.FunctionToken;
import group.doppeld.juist.parser.tokenizer.tokens.StatementToken;
import group.doppeld.juist.runbox.*;
import group.doppeld.juist.util.StringUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class FunctionParser extends TokenParser {
    public FunctionParser(Parser parser) {
        super(parser);
    }

    public void parse(Script script, FunctionToken token){
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
        /* What is actually in the method? */
        ArrayList<Statement> statements = new ArrayList<>();
        for(StatementToken statement : token.getStatements()){
            //TODO convert!!!
        }
        script.getMethods().add(new Function(token.getName(), statements, finalParameters, type));
    }
}
