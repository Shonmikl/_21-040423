package org.example._2023_08_07;

import java.util.stream.Stream;

//0 1 1 2 3 5 8 13 21 34 55 89
public class Ex3 {
    public static long fibStream(int index) {
        var res = Stream.iterate(new long[]{0, 1}, arr -> new long[]{arr[1], arr[0] + arr[1]})
                .limit(index + 1)
                .map(y -> y[0])
                .max(Long::compareTo);
        return res.orElseThrow(IllegalArgumentException::new);
    }

    public static long fibLoops(int index) {
        if (index < 0) {
            throw new IllegalArgumentException();
        }

        int fib1 = -1;
        int fib2 = 1;
        for (int i = 0; i <= index; i++) {
            int sum = fib1 + fib2;
            fib1 = fib2;
            fib2 = sum;
        }
        return fib2;
    }

    public static long fibRec(final int index) {
        if (index < 0) {
            throw new IllegalArgumentException();
        }

        if (index == 0) {
            return 0;
        }

        return index == 1 || index == 2 ? 1 : fibRec(index - 2) + fibRec(index - 1);
    }

    public static void main(String[] args) {
        int n = 47;
        long timeL = System.currentTimeMillis();
        fibLoops(n);
        System.out.println("LOOP: " + (System.currentTimeMillis() - timeL));

        long timeR = System.currentTimeMillis();
        fibRec(n);
        System.out.println("REC: " + (System.currentTimeMillis() - timeR));

        long timeS = System.currentTimeMillis();
        fibStream(n);
        System.out.println("STREAM: " + (System.currentTimeMillis() - timeS));
    }
}