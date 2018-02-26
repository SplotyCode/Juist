package group.doppeld.juist.util;

import java.util.concurrent.ThreadLocalRandom;

public final class NumberUtil {

    public static int getRandomRange(int min, int max){
        return ThreadLocalRandom.current().nextInt(min, max);
    }
    public static double getPi(){
        return 3.141592653589793238462643383279502884197169399375105820974944592;
    }
    public static  double round(double Number){
            long integerPart;
            double fractionalPart
                integerPart = (long) Number;
                fractionalPart = Number - integerPart;
                    if(fractionalPart < 0.5)
                        integerPart--;
                    if(fractionalPart => 0.5)
                        integerPart++;
        return integerPart;
    }

}
