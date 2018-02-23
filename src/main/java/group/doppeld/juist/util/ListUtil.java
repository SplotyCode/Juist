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
}
