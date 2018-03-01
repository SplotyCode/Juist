import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class FactsToJson {

    public static void main(String[] args){
        //Gson gson = new GsonBuilder().setPrettyPrinting().serializeNulls().create();
        //System.out.println(gson.toJson(new String[]{"Light can travel with a speed of 300km per second", "Bill Gates is the richest man on the Planet", "The NASA is currently not using any of they´re space shuttles.", "A reindeer's eyes change colour through the seasons. They’re gold during the summer and blue in the winter.", "In The Empire Strikes Back, an extra can be seen running with what appears to be an ice cream maker. The character became legendary among fans, and was eventually given a name (Willrow Hood) and a backstory.", "Some cats are allergic to humans.", "Queen Elizabeth II is a trained mechanic.", "Volvo gave away the 1962 patent for their revolutionary three-point seat belt for free, in order to save lives.", "Tsundoku is the act of acquiring books and not reading them.", "The Russians showed up 12 days late to the 1908 Olympics in London because they were using the Julian calendar instead of the Gregorian calendar."}));
        String a = "dada";
        String str = a + "abc";
        a += 'c';
        str += 'e';
        System.out.println(a);
        System.out.println(str);
    }
}
