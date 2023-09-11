package org.example._2023_09_11;

import java.util.Arrays;
import java.util.Random;

public class QuickSortExample {
    public static void main(String[] args) {
        Random random = new Random();
        int[] array = new int[12];

        for (int i = 0; i < array.length; i++) {
            array[i] = random.nextInt(160);
        }

        System.out.println("Unsorted: " + Arrays.toString(array));
        qs(array, 0, array.length - 1);
        System.out.println("Sorted: " + Arrays.toString(array));
    }

    private static void qs(int[] array, int low, int high) {
        if (array.length == 0) {
            return;
        }

        if (low >= high) {
            return;
        }

        int middle = low + (high - low) / 2;
        int opora = array[middle];

        int i = low;
        int j = high;

        while (i <= j) {
            while (array[i] < opora) {
                i++;
            }

            while (array[j] > opora) {
                j--;
            }

            if(i <= j) {
                int temp = array[i];
                array[i] = array[j];
                array[j] = temp;
                i++;
                j--;
            }
        }

        if(low < j) {
            qs(array, low, j);
        }

        if(high > i) {
            qs(array, i, high);
        }
    }
}
