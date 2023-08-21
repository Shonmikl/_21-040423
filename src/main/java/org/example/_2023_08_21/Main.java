package org.example._2023_08_21;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        System.out.println(getInRadix(899822, 25));
    }

    public static String getInRadix(int number, int radix) {
        List<Character> digits = getDigitTable();
        //проверка на нормальное значение radix
        if (radix < 2 || radix >= digits.size() || number < 0) {
            throw new IllegalArgumentException();
        }

        //используем для создания строки для представления числа
        StringBuilder valueStr = new StringBuilder();

        while (number > 0) {
            /**
             * добавляем
             * очередной символ для соответствующего разряда
             * в начало стринг билдера.
             * КАК
             * мы берем символ из таблицы
             * который соответствует остатку от деления числа
             * которое является нашим основанием системы счисления
             */
            valueStr.insert(0, digits.get(number % radix));
            //а сам number делим на то же основание
            number = number / radix; //сколько целых в числе number
        }
        //возвращаем строку которая содержит представление числа
        //в нашей системе счисления
        return valueStr.toString();
    }

    //таблица символов
    private static List<Character> getDigitTable() {
        ArrayList<Character> digits = new ArrayList<>();
        for (char i = '0'; i <= '9'; i++) {
            digits.add(i);
        }
        for (char i = 'A'; i <= 'Z'; i++) {
            digits.add(i);
        }
        return digits;
    }
}