package org.example._2023_08_21;

public class TA1 {
    //[2, 2, 3, 3, 4, 9, 5, 6, 6, 5, 4]
    public static int getSingle(int[] nums) {
        int xor = 0;
        for (int c : nums) {
            xor = xor ^ c;
        }
        return xor;
    }

    public static void main(String[] args) {
        int[] arr = {2, 2, 3, 3, 4, 9, 5, 6, 6, 5, 4};
        int[] arr1 = {2, 3, 4, 3, 2};
        //{2, 3, 4, 3, 2};
        // 0 0 0 0 0 0 1 0
        // 0 0 0 0 0 0 1 1
        // 0 0 0 0 0 0 0 1
        // 0 0 0 0 0 1 0 0 //4
        // 0 0 0 0 0 1 0 1
        // 0 0 0 0 0 0 1 1 //3
        // 0 0 0 0 0 1 1 0
        // 0 0 0 0 0 0 1 0 //2
        // 0 0 0 0 0 1 0 0
        System.out.println(getSingle(arr));
    }
}
