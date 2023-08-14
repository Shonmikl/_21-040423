package org.example._2023_08_14;

public class Ex3333 {

    private int incRec(int a, int b) {
        if (b == 0) {
            return 1;
        }
        //////.................

        return incRec(a, b - 1) * a;
    }

    public static void main(String[] args) {
        System.out.println(new Ex3333().incRec(2, 3));
    }
}
