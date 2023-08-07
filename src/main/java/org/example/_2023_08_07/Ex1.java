package org.example._2023_08_07;

public class Ex1 {


    public static void main(String[] args) {
        int[] a = {9, 8, 5, 63, 2, 1, 0, 21, 58, 7};
        int min = Integer.MAX_VALUE;
        int min2 = Integer.MAX_VALUE;
        for (int i = 0; i < a.length; i++) {
            if(a[i] < min) {
                min2 = min;
                min = a[i];
            } else if(a[i] < min2 && a[i] != min) {
                min2 = a[i];
            }
        }
    }
}
