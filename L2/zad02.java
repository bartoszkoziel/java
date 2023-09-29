package L2;
import java.util.Arrays;
import java.util.Scanner;
import java.util.ArrayList;

public class zad02 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("PODAJ N:");
        int n = Integer.parseInt(sc.nextLine());

        ArrayList<Integer> arr = new ArrayList<>();
        int suma = 0;



        System.out.println("Wszytskie elementy");
        for(int i = 0; i <= n; i++){
            arr.add(i);
            suma += i;
        }

        System.out.println(arr.toString());
        System.out.println("SUMA: " + suma);
    }
}
