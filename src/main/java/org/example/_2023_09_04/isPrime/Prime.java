package org.example._2023_09_04.isPrime;

public class Prime {
    public static void main(String[] args) {
        for (int i = 1; i < 500; i++) {
            if(isPrime(i)) {
                System.out.println(i + " : " + isPrime(i));
            }
        }
    }
    private static boolean isPrime(int number) {
        if (number < 2) {
            return false;
        }

        if (number % 2 == 0) {
            return number == 2;
        }

        if (number % 3 == 0) {
            return number == 3;
        }

        for (int i = 5; i * i <= number; i += 6) {
            if (number % i == 0 || number % (i + 2) == 0) {
                return false;
            }
        }
        return true;
    }
}
