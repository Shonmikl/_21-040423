package org.example._2023_08_28;

public class BackPackPerebor {
    public static void main(String[] args) {
        //                  1     1        1  1
        int[] weights = {3, 4, 5, 8, 9, 6, 4, 7, 6};
        int[] prices =  {1, 6, 4, 7, 6, 5, 8, 9, 6};
        int maxWeight = 25;

        int maxPrice = 0;
        long maxState = 0;

        long count = 1L << weights.length; //2^n

        for (int state = 1; state <= count; state++) {
            int price = statePrice(state, prices);
            int weight = stateWeight(state, weights);

            /**
             * weight[] = 3 4 5
             * state = 5 -----> 101 -----> в наборе 1ый и 3ий предмет
             * weight[0] + weight[2] ---> 3 + 5 = 8
             */

            if (weight <= maxWeight && price > maxPrice) {
                maxPrice = price;
                maxState = state;
            }
        }

        System.out.println("OPT: ");
        for (int i = 0; i < weights.length; i++) {
            if (((1L << i) & maxState) != 0) {
                System.out.println(i);
            }
        }
    }

    private static int statePrice(int state, int[] prices) {
        int price = 0;
        for (int i = 0; i < prices.length; i++) {
            if (((1L << i) & state) != 0) {
                price = price + prices[i];
            }
        }
        return price;
    }

    private static int stateWeight(int state, int[] weights) {
        int weight = 0;
        for (int i = 0; i < weights.length; i++) {
            if (((1L << i) & state) != 0) {
                weight = weight + weights[i];
            }
        }
        return weight;
    }
}