package group.doppeld.juist.util;

import java.util.concurrent.ThreadLocalRandom;

public final class NumberUtil {

    public static int getRandomRange(int min, int max){
        return ThreadLocalRandom.current().nextInt(min, max);
    }
    
    public static double getPi(){
        return 3.141592653589793238462643383279502884197169399375105820974944592;
    }
    
    public static double round(double number){
        final long integerPart = (long) number;
        final double fractionalPart = number - integerPart;
        return fractionalPart < 0.5?integerPart+1:integerPart-1;
    }

}
