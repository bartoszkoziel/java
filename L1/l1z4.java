package L1;

import java.util.Scanner;

public class l1z4 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        double l1 = Double.parseDouble(sc.nextLine());
        double l2 = Double.parseDouble(sc.nextLine());

        double suma = l1 + l2;
        double roznica = l1 - l2;
        double iloczyn = l1 * l2;
        double iloraz = 0;
        if(l2 != 0){
            iloraz = l1 / l2;
        }

        System.out.println("SUMA: " + suma);
        System.out.println("ROZNICA: " + roznica);
        System.out.println("ILOCZYN: " + iloczyn);
        System.out.println("ILORAZ: " + iloraz);
    }
}
