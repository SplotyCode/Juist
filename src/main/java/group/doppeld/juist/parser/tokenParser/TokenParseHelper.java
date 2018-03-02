package group.doppeld.juist.parser.tokenParser;

import group.doppeld.juist.exeptions.InternalException;
import group.doppeld.juist.exeptions.InvalidTypeExeption;
import group.doppeld.juist.parser.tokenizer.tokens.VariableValueToken;
import group.doppeld.juist.runbox.Script;
import group.doppeld.juist.runbox.VariableType;
import group.doppeld.juist.runbox.variable.LinkedVariable;
import group.doppeld.juist.runbox.variable.RealVariable;
import group.doppeld.juist.runbox.variable.Variable;

public final class TokenParseHelper {

    public static VariableType getType(String rawMust, VariableValueToken.VariableType rawHas) {
        VariableType must = getType(rawMust);
        boolean hasExecptiopn = rawHas == VariableValueToken.VariableType.NULL || rawHas == VariableValueToken.VariableType.VOID || rawHas == VariableValueToken.VariableType.VARIABLE;
        switch (rawMust) {
            case "Int":
                if (rawHas == VariableValueToken.VariableType.INTEGER || hasExecptiopn) return VariableType.INTEGER;
                throw new InvalidTypeExeption("Variable MUST has type '" + rawMust + "' but has " + rawHas.name() + " given!");
            case "Double":
                if (rawHas == VariableValueToken.VariableType.DOUBLE || hasExecptiopn) return VariableType.DOUBLE;
                throw new InvalidTypeExeption("Variable MUST has type '" + rawMust + "' but has " + rawHas.name() + " given!");
            case "Float":
                if (rawHas == VariableValueToken.VariableType.FLOAT || hasExecptiopn) return VariableType.FLOAT;
                throw new InvalidTypeExeption("Variable MUST has type '" + rawMust + "' but has " + rawHas.name() + " given!");
            case "Short":
                if (rawHas == VariableValueToken.VariableType.SHORT || hasExecptiopn) return VariableType.SHORT;
                throw new InvalidTypeExeption("Variable MUST has type '" + rawMust + "' but has " + rawHas.name() + " given!");
            case "Long":
                if (rawHas == VariableValueToken.VariableType.LONG || hasExecptiopn) return VariableType.LONG;
                throw new InvalidTypeExeption("Variable MUST has type '" + rawMust + "' but has " + rawHas.name() + " given!");
            case "String":
                if (rawHas == VariableValueToken.VariableType.STRING || hasExecptiopn) return VariableType.STRING;
                throw new InvalidTypeExeption("Variable MUST has type '" + rawMust + "' but has " + rawHas.name() + " given!");
            default:
                throw new InvalidTypeExeption("Type '" + rawMust + "' is not supported!");
        }
    }

    public static VariableType getType(String type){
        switch (type){
            case "Int":
                return VariableType.INTEGER;
            case "Double":
                return VariableType.DOUBLE;
            case "Float":
                return VariableType.FLOAT;
            case "Short":
                return VariableType.SHORT;
            case "Long":
                return VariableType.LONG;
            case "String":
                return VariableType.STRING;
            default:
                throw new InvalidTypeExeption("Type '" + type + "' is not supported!");
        }
    }

    public static VariableType getType(VariableValueToken.VariableType type){
        switch (type){
            case INTEGER:
                return VariableType.INTEGER;
            case DOUBLE:
                return VariableType.DOUBLE;
            case FLOAT:
                return VariableType.FLOAT;
            case LONG:
                return VariableType.LONG;
            case SHORT:
                return VariableType.SHORT;
            case STRING:
                return VariableType.STRING;
            case VARIABLE:
            case NULL:
            case VOID:
                throw new InternalException("Can not get type from " + type + "!");
            default:
                throw new InvalidTypeExeption("Type '" + type + "' is not supported!");
        }
    }

    public static RealVariable getRealVariablebyToken(VariableValueToken token){
        VariableType type = getType(token.getType());
        switch (type){
            case STRING:
                return new RealVariable<>(token.getContent(), type);
            case SHORT:
                return new RealVariable<>(Short.valueOf(token.getContent()), type);
            case DOUBLE:
                return new RealVariable<>(Double.valueOf(token.getContent()), type);
            case FLOAT:
                return new RealVariable<>(Float.valueOf(token.getContent()), type);
            case INTEGER:
                return new RealVariable<>(Short.valueOf(token.getContent()), type);
            case LONG:
                return new RealVariable<>(Long.valueOf(token.getContent()), type);
            default:
                throw new InvalidTypeExeption("Type '" + type + "' is not supported!");
        }
    }

    public static void addLinkedVariable(Variable variable, Script script, String name, VariableType type){
        LinkedVariable result = null;
        switch (type){
            case LONG:
                result = new LinkedVariable<Long>(variable, type);
                break;
            case SHORT:
                result = new LinkedVariable<Short>(variable, type);
                break;
            case STRING:
                result = new LinkedVariable<String>(variable, type);
                break;
            case FLOAT:
                result = new LinkedVariable<Float>(variable, type);
                break;
            case DOUBLE:
                result = new LinkedVariable<Double>(variable, type);
                break;
            case INTEGER:
                result = new LinkedVariable<Integer>(variable, type);
                break;
            default: throw new InternalException("");
        }
        script.getClassVariables().put(name, result);
    }
}
