package group.doppeld.juist.util;

import java.util.Random;

public final class NumberUtil {

    public static int getRandomRange(int min, int max){
        return ThreadLocalRandom.current().nextInt(min, max);
    }

}