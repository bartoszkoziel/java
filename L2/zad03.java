package L2;
import java.util.Scanner;

public class zad03 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String word1 = sc.nextLine();

        printReversed(word1);
    }

    static void printReversed(String s){
        for(int i = s.length() - 1; i >= 0; i--){
            System.out.print(s.charAt(i));
        }
        System.out.print("\n");
    }
}
