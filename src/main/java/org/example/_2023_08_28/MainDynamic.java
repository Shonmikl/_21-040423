package org.example._2023_08_28;

import java.util.ArrayList;
//!!!Диамическое програмирование
//Значит надо реазбить задачу на более мелкие
//Ключевой момент динамического програмирования это понять как это происходит

/**
 * К-й прелмет мы можем либо положить либо не положить в рюкзак
 * два варианта
 * елси мы не положили то оптимальная стоимость набора будет равна оптимальной стоимости набора
 * из к-1 предмета и того же самого рюкзака размера W
 * А если мы К-й предмет кладем то это значит сто чтоимость прибавляет к итоговой стоимост
 * и занимает место в рюкзаке равное своему весу
 * Если мы берем К-й предмет то наибольшая стоимость этого наборра будет собственно стоимость
 * К-го предмета + стоимость рюкзака в который кладется набор предметов без К-го предметов
 * и размера W минус разсер катового предмета
 * Те мы к-ый предмет фиксируем а остальная стоимость это остаток рюкзака и остаток предметов
 * и вычилисть эту стоимость
 */
public class MainDynamic {
    /**
     * ТАБЛИЦА\
     * в таблице представлена размер рюкзаков от 1 до 13
     * и к-кол-во предметов
     * Необходимо составить таблицу оптимальной стомости для всех вариантов рюкзаков
     * И заполнить значениями
     */

    public static void main(String[] args) {
        int[] weights = {3, 4, 5, 8, 9};
        int[] prices = {1, 6, 4, 7, 6};

        int count = weights.length;
        int maxWeight = 13;
        int[][] A = new int[count + 1][maxWeight + 1];

        for (int k = 1; k <= count; k++) {
            for (int s = 1; s <= maxWeight; s++) {
                if (weights[k - 1] <= s) {
                    A[k][s] = Math.max(A[k - 1][s], A[k - 1][s - weights[k - 1]] + prices[k - 1]);
                } else {
                    A[k][s] = A[k - 1][s];
                }
            }
        }

        ArrayList<Integer> result = new ArrayList<>();
        traceResult(A, weights, count, maxWeight, result);

        System.out.println("Оптимальное содержимое рюкзака:");
        for (int item : result) {
            System.out.println(item);
        }
    }

    private static void traceResult(int[][] A, int[] weights, int k, int s, ArrayList<Integer> result) {
        if (k == 0 || s == 0) {
            return;
        }

        if (A[k][s] == A[k - 1][s]) {
            traceResult(A, weights, k - 1, s, result);
        } else {
            traceResult(A, weights, k - 1, s - weights[k - 1], result);
            result.add(k);
        }
    }
}