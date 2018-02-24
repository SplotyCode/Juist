package group.doppeld.juist.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class ListUtil {

    public static boolean equalLists(List<String> one, List<String> two){
        if(one.size() != two.size()) return false;

        one = new ArrayList<>(one);
        two = new ArrayList<>(two);

        Collections.sort(one);
        Collections.sort(two);
        return one.equals(two);
    }

    public static <T> boolean containsArray(T find, T[] array) {
        for (T current : array)
            if (current == find)
                return true;
        return false;
    }

    public static boolean containsArray(char find, char[] array) {
        for (char current : array)
            if (current == find)
                return true;
        return false;
    }
}
