package org.example._2023_08_21;

public class BitwiseShiftExample {
    public static void main(String[] args) {
        int value = 0b11011010; // Двоичное представление: 218

        int shiftedRight = value >>> 2; // Сдвиг вправо на 2 позиции

        System.out.println("Исходное значение: " + value);
        System.out.println("Результат сдвига вправо: " + shiftedRight);
    }
}
