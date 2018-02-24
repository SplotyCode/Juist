package group.doppeld.juist.util;

import java.util.concurrent.ThreadLocalRandom;

public final class NumberUtil {

    public static int getRandomRange(int min, int max){
        return ThreadLocalRandom.current().nextInt(min, max);
    }

}