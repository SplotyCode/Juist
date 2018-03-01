package group.doppeld.juist.parser.tokenParser;

import group.doppeld.juist.exeptions.InvalidTypeExeption;
import group.doppeld.juist.parser.tokenizer.tokens.VariableValueToken;
import group.doppeld.juist.runbox.variable.Variable;
import group.doppeld.juist.runbox.VariableType;

public final class TokenParseHelper {

    public static VariableType getType(String must, VariableValueToken.VariableType has) {
        switch (must) {
            case "Int":
                if (has == VariableValueToken.VariableType.INTEGER) return VariableType.INTEGER;
                throw new InvalidTypeExeption("Variable MUST has type '" + must + "' but has " + has.name() + " given!");
            case "Double":
                if (has == VariableValueToken.VariableType.DOUBLE) return VariableType.DOUBLE;
                throw new InvalidTypeExeption("Variable MUST has type '" + must + "' but has " + has.name() + " given!");
            case "Float":
                if (has == VariableValueToken.VariableType.FLOAT) return VariableType.FLOAT;
                throw new InvalidTypeExeption("Variable MUST has type '" + must + "' but has " + has.name() + " given!");
            case "Short":
                if (has == VariableValueToken.VariableType.SHORT) return VariableType.SHORT;
                throw new InvalidTypeExeption("Variable MUST has type '" + must + "' but has " + has.name() + " given!");
            case "Long":
                if (has == VariableValueToken.VariableType.LONG) return VariableType.LONG;
                throw new InvalidTypeExeption("Variable MUST has type '" + must + "' but has " + has.name() + " given!");
            case "String":
                if (has == VariableValueToken.VariableType.STRING) return VariableType.STRING;
                throw new InvalidTypeExeption("Variable MUST has type '" + must + "' but has " + has.name() + " given!");
            default:
                throw new InvalidTypeExeption("Type '" + must + "' is not supported!");
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

        }
    }

    public class TypeResult {
        private boolean linked;
        private VariableType type;

        public TypeResult(boolean linked, VariableType type) {
            this.linked = linked;
            this.type = type;
        }

        public boolean isLinked() {
            return linked;
        }

        public void setLinked(boolean linked) {
            this.linked = linked;
        }

        public VariableType getType() {
            return type;
        }

        public void setType(VariableType type) {
            this.type = type;
        }
    }

    public static Variable variablefromValueToken(VariableValueToken token){
        return new Variable<>(token.getContent(), getType(token.getType()));
    }
}
