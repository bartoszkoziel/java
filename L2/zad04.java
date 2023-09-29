package L2;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.HashMap;
import java.util.Set;

public class zad04 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        HashMap<String, Integer> map1 = new HashMap<String, Integer>()
        {{
            put("aeioulnrst", 1);
            put("dg", 2);
            put("bcmp", 3);
            put("fhvwy", 4);
            put("k", 5);
            put("jx", 8);
            put("qz", 10);
        }};

        int wynik = 0;

        String word1 = sc.nextLine();

        System.out.println(getKey("a", map1.keySet().stream().toList()));
    }

    static String getKey(CharSequence ch, ArrayList<String> keysArr){
        for(int i = 0; i < keysArr.size(); i++){
            if(keysArr.get(i).contains(ch)){
                return keysArr.get(i);
            }
        }
    }
}
