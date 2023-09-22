package L1;

public class l1z2_6 {
    public static void main(String[] args) {
        int[] arr = {1,2,3,4,5,6,7,8,9,10};

        for(int x = 0; x < arr.length; x++){
            for(int y = 0; y < arr.length; y++){
                System.out.print(arr[x] * arr[y] + "\t");
            }
            System.out.print("\n");
        }
    }
}
