package L1;
import java.util.Arrays;

public class l1z2_2 {
    public static void main(String[] args) {
        int[] arr = new int[100];

        for(int i = 0; i < 100; i++){
            arr[i] = i;
        }

        System.out.println(Arrays.toString(arr));

        int suma = 0;

        for(int i = 0; i < arr.length; i++){
            suma += arr[i];
        }

        System.out.println("SUMA OD 0 do 99 TO: " + suma);
    }
}
