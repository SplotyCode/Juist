package group.doppeld.juist.parser.tokenizer.tokens;

import group.doppeld.juist.parser.tokenizer.Token;

import java.util.ArrayList;
import java.util.HashMap;

public class FunctionToken extends Token {

    private final String name;
    private HashMap<String, String> arguments;
    private String type;
    private ArrayList<StatementToken> statements;

    public FunctionToken(String name, HashMap<String, String> arguments, String type, ArrayList<StatementToken> statements) {
        this.name = name;
        this.arguments = arguments;
        this.type = type;
        this.statements = statements;
    }

    public String getName() {
        return name;
    }

    public HashMap<String, String> getArguments() {
        return arguments;
    }

    public void setArguments(HashMap<String, String> arguments) {
        this.arguments = arguments;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public ArrayList<StatementToken> getStatements() {
        return statements;
    }

    public void setStatements(ArrayList<StatementToken> statements) {
        this.statements = statements;
    }
}
