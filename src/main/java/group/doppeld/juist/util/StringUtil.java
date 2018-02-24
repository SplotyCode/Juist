package group.doppeld.juist.util;

import java.util.Collections;
import java.util.concurrent.TimeUnit;

public final class StringUtil {

    public static void printProgressBar(String prefix, int value, int max, long startTime){
        long eta = value == 0 ? 0 : (max - value) * (System.currentTimeMillis() - startTime) / value;

        String etaHms = value == 0 ? "N/A" :
                String.format("%02d:%02d:%02d", TimeUnit.MILLISECONDS.toHours(eta),
                        TimeUnit.MILLISECONDS.toMinutes(eta) % TimeUnit.HOURS.toMinutes(1),
                        TimeUnit.MILLISECONDS.toSeconds(eta) % TimeUnit.MINUTES.toSeconds(1));

        StringBuilder string = new StringBuilder();
        int percent = (int) (value * 100 / max);
        string
                .append('\r')
                .append(prefix)
                .append(String.join("", Collections.nCopies(percent == 0 ? 2 : 2 - (int) (Math.log10(percent)), " ")))
                .append(String.format(" %d%% [", percent))
                .append(String.join("", Collections.nCopies(percent, "=")))
                .append('>')
                .append(String.join("", Collections.nCopies(100 - percent, " ")))
                .append(']')
                .append(String.join("", Collections.nCopies((int) (Math.log10(max)) - (int) (Math.log10(value)), " ")))
                .append(String.format(" %d/%d, %s", value, max, etaHms));

        System.out.print(string);
    }
}
