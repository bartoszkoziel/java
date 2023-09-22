package L1;

import java.util.Scanner;

public class pole_prostokata {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("a: ");

        double a = Double.parseDouble(sc.nextLine());
        double b = Double.parseDouble(sc.nextLine());

        System.out.println("Pole to: " + (a*b));
    }
}
