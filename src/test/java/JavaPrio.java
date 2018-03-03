public class JavaPrio {



    public static String test = getTest();
    public static String abs = "abs" + test + "abc";

    public static void main(String[] args){
        System.out.println(test);
        System.out.println(abs);
    }

    public static String getTest(){
        System.out.println(test + "a");
        System.out.println(abs);
        return test+"a";
    }


}
