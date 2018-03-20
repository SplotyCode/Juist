package group.doppeld.juist.parser.tokenParser.special;

import group.doppeld.juist.exeptions.InternalException;
import group.doppeld.juist.exeptions.InvalidTypeExeption;
import group.doppeld.juist.exeptions.VariableAlreadyDefined;
import group.doppeld.juist.exeptions.VariableNotFoundExeption;
import group.doppeld.juist.parser.tokenParser.SpecialTokenParser;
import group.doppeld.juist.parser.tokenParser.TokenParseHelper;
import group.doppeld.juist.parser.tokenizer.Token;
import group.doppeld.juist.parser.tokenizer.tokens.VariableToken;
import group.doppeld.juist.parser.tokenizer.tokens.VariableValueToken;
import group.doppeld.juist.runbox.Script;
import group.doppeld.juist.runbox.VariableType;
import group.doppeld.juist.runbox.variable.RealVariable;
import group.doppeld.juist.runbox.variable.SignalVariable;
import group.doppeld.juist.runbox.variable.Variable;

public class ClassVariableParser extends SpecialTokenParser {

    private VariableToken token;
    private Script script;


    @Override
    public void preParse(Script script, Token rawToken) {
        token = (VariableToken) rawToken;
        this.script = script;
        if(script.getClassVariables().containsKey(token.getName())) throw new VariableAlreadyDefined("'" + token.getName() + "' is already defined in class Variables!");
        script.getClassVariables().put(token.getName(), new SignalVariable());
    }

    public void postLoad(){
        VariableValueToken valueToken = token.getValue();
        VariableType must = TokenParseHelper.getType(token.getType());
        VariableValueToken.VariableType hasRaw = valueToken.getType();
        String name = token.getName();
        String value = valueToken.getContent();

        if(hasRaw == VariableValueToken.VariableType.VOID) throw new InvalidTypeExeption("Variable can not set to void(even more nothing then null)");
        if(valueToken.getContent() == null && hasRaw != VariableValueToken.VariableType.NULL) throw new InternalException("How can the contend be null?!?!");

        if(hasRaw == VariableValueToken.VariableType.VARIABLE){
            Variable variable = script.getClassVariablebyName(name);
            if(variable == null) throw new VariableNotFoundExeption("No ClassVariable '" + name + "' defined!");
            if(variable.getType() != must) throw new InvalidTypeExeption("Can not link from method with type '" + variable.getType() + "'");
            TokenParseHelper.addLinkedVariable(variable, script, name, must);
            return;
        }

        VariableType has = TokenParseHelper.getType(valueToken.getType());
        if(must != has) throw new InvalidTypeExeption("Value must be '" + must + "' but currently is '" + has + "'!");
        switch (has){
            case LONG:
                script.getClassVariables().put(name, new RealVariable<>(Long.valueOf(value), must));
                break;
            case STRING:
                script.getClassVariables().put(name, new RealVariable<>(value, must));
                break;
            case FLOAT:
                script.getClassVariables().put(name, new RealVariable<>(Float.valueOf(value), must));
                break;
            case SHORT:
                script.getClassVariables().put(name, new RealVariable<>(Short.valueOf(value), must));
                break;
            case DOUBLE:
                script.getClassVariables().put(name, new RealVariable<>(Double.valueOf(value), must));
                break;
            case INTEGER:
                script.getClassVariables().put(name, new RealVariable<>(Integer.valueOf(value), must));
                break;
            default:
                throw new InternalException("Woops - " + must.name());
        }
    }
}
