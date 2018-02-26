package group.doppeld.juist.util;

public final class CharUtil {

    public static boolean isWhitespace(char cha){
        return cha == ' ' || cha == '\n' || cha == '\r' || cha == '\t';
    }
}
