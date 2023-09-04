package org.example._2023_09_04.isPrime;

public class Prime {
    public static void main(String[] args) {

        System.out.println("49 : " + isPrime(49));

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
//49
        for (int i = 5; i * i <= number; i += 6) {
            if (number % i == 0 || number % (i + 2) == 0) {
                return false;
            }
        }
        return true;
    }
}
