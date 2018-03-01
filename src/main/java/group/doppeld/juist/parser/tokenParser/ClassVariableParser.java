package group.doppeld.juist.parser.tokenParser;

import group.doppeld.juist.exeptions.InternalException;
import group.doppeld.juist.exeptions.VariableAlreadyDefined;
import group.doppeld.juist.parser.Parser;
import group.doppeld.juist.parser.tokenizer.tokens.VariableToken;
import group.doppeld.juist.parser.tokenizer.tokens.VariableValueToken;
import group.doppeld.juist.runbox.Script;
import group.doppeld.juist.runbox.variable.Variable;
import group.doppeld.juist.runbox.VariableType;

public class ClassVariableParser extends TokenParser {

    public ClassVariableParser(Parser parser) {
        super(parser);
    }

    public void parse(Script script, VariableToken token){
        if(script.getClassVariables().containsKey(token.getName())) throw new VariableAlreadyDefined("'" + token.getName() + "' is already defined in class Variables!");
        VariableValueToken value = token.getValue();
        createVariable(script, token.getName(), TokenParseHelper.getType(token.getType(), value.getType()), value.getContent());
    }

    public void createVariable(Script script, String name, VariableType type, Object rawValue){
        String value = (String) rawValue;
        switch (type){
            case LONG:
                script.getClassVariables().put(name, new Variable<>(Long.valueOf(value), type));
                break;
            case STRING:
                script.getClassVariables().put(name, new Variable<>(value, type));
                break;
            case FLOAT:
                script.getClassVariables().put(name, new Variable<>(Float.valueOf(value), type));
                break;
            case SHORT:
                script.getClassVariables().put(name, new Variable<>(Short.valueOf(value), type));
                break;
            case DOUBLE:
                script.getClassVariables().put(name, new Variable<>(Double.valueOf(value), type));
                break;
            case INTEGER:
                script.getClassVariables().put(name, new Variable<>(Integer.valueOf(value), type));
                break;
            default:
                throw new InternalException("Woops - " + type.name());
        }
    }
}
