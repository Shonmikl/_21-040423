package org.example._2023_08_21;
//[2, 2, 3, 3, 4, 9, 5, 6, 6, 5, 4]
//[a a s s d f g d f h g]
public class BIT {
    public static void main(String[] args) {
        int i = 53;
        int ib = 0b00110101;
        int c = 0x777;

        System.out.println(i);
        System.out.println(binaryStr(ib));

        System.out.println("Сдвиг вправо");
        System.out.println("TEM:" + binaryStr(ib));
        System.out.println("10: " + (i >> 1));
        System.out.println("2: " + binaryStr(i >> 1));
        System.out.println("*******************************************");

        System.out.println("Сдвиг влево");
        System.out.println("TEM:" + binaryStr(ib));
        System.out.println("10: " + (i << 1));
        System.out.println("2: " + binaryStr(i << 1));

        int b1 = 0b00001001; //9
        int b2 = 0b00001010; //10
//                 76543210
//        int b2 = 0b00001111; //

        System.out.println("(AND) &");
        printBinary(b1);
        printBinary(b2);
        printBinary(b1 & b2);
        System.out.println("*******************************************");

        System.out.println("(OR) |");
        printBinary(b1);
        printBinary(b2);
        printBinary(b1 | b2);
        System.out.println("*******************************************");

        System.out.println("(XOR) ^");
        printBinary(b1);
        printBinary(b2);
        printBinary(b1 ^ b2);
        System.out.println("*******************************************");

        System.out.println("(NOT) ~");
        printBinary(b1);
        printBinary(~b1);
        System.out.println("*******************************************");
    }

    public static void printBinary(int b) {
        System.out.println("0b" + Integer.toBinaryString(0b100000000 | (b & 0xff)).substring(1));
    }

    public static String binaryStr(int b) {
        return "0b" + Integer.toBinaryString(0b100000000 | (b & 0xff)).substring(1);
    }
}