import java.util.Scanner;
import java.util.Arrays;

public class Test2 {
    public static void main(String[] args) {
        int[] arr1 = {1,2,1,1,1,2,2,2,1,1,1,2,2,1,2,1,};

        System.out.println(arr1[2]);
        System.out.println(arr1.length);

        int[][] arr04 = new int[][]{
                { 1, 2, 3 },
                { 4, 5, 6 },
                { 7, 8, 9 },
        };
        System.out.println(Arrays.deepToString(arr04));

        int[] arr2 = new int[100];
        System.out.println(Arrays.toString(arr2));
    }
}
