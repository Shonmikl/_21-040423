package org.example._2023_08_21;

public class DataCompressionExample {
    public static void main(String[] args) {
        int[] originalColors = { 0xFF3366, 0x00AAFF, 0x99CC00 };

        int compressedData = 0; // Будущее сжатое значение

        for (int color : originalColors) {
            int red = (color >> 16) & 0x1F; // Сдвигаем и маскируем 5 бит для красной компоненты
            int green = (color >> 8) & 0x1F; // Сдвигаем и маскируем 5 бит для зеленой компоненты
            int blue = color & 0x1F; // Маскируем 5 бит для синей компоненты

            compressedData |= (red << 25) | (green << 20) | (blue << 15);
        }

        System.out.println("Сжатые данные: " + Integer.toHexString(compressedData));
    }
}
/**
 *  Каждая компонента цвета (красная, зеленая, синяя)
 *  кодируется 5 битами, а затем сжатые биты объединяются в одно число.
 *  Это может быть полезно, например, при хранении цветовых данных в ограниченных ресурсах,
 *  таких как графический чип или память.
 */