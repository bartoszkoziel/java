import java.sql.SQLOutput;
import java.util.Scanner;

public class Test {
    public static void main(String[] args) {
        int num1 = 15;
        int num2 = 13;

        System.out.println("cos: " + num1 + num2);

        char char1 = 'q';
        char char2 = 'p';

        System.out.println("cos: " + char1 + char2);

        String str1 = "qweqwe str1";
        String str2 = "str2 bdjqp";

        System.out.println(str1 + str2  );

        float f1 = 332F;
        float f2 = 3F;

        System.out.println("Dzielenie floatow: " + (f1 / f2));

        //stala

        final String STALA_WARTOSC = "COSTAM";

        //porownania

        int t1 = 5;
        int t2 = 10 - 5;
        String s1 = "String1";
        String s2 = "String1";

        System.out.println(t1 == t2);
        System.out.println(s1 == s2);
        System.out.println(s1.equals(s2));

        int age = 25;

        if(age > 18) {
            System.out.println("dorosla osoba");
        } else {
            System.out.println("nie dorosla osoba");
        }


    }
}