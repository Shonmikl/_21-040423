package org.example._2023_09_25;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;

public class MediaFilter {
    public static void main(String[] args) throws IOException {
        String inputPNG = "img.png";
        String outputPNG = "outputPNG8.png";

        BufferedImage inImage = ImageIO.read(new File(inputPNG));
        BufferedImage out = new BufferedImage(inImage.getWidth(), inImage.getHeight(), BufferedImage.TYPE_INT_RGB);

        int radius = 8;

        for (int i = 0; i < inImage.getHeight(); i++) {
            for (int j = 0; j < inImage.getWidth(); j++) {
                int[] red = new int[(2 * radius + 1) * (2 * radius + 1)];
                int[] green = new int[(2 * radius + 1) * (2 * radius + 1)];
                int[] blue = new int[(2 * radius + 1) * (2 * radius + 1)];
                int index = 0;

                for (int k = -radius; k <= radius; k++) {
                    for (int l = -radius; l <= radius; l++) {
                        int x = j + l;
                        int y = i + k;

                        if (x >= 0 && x < inImage.getWidth() && y >= 0 && y < inImage.getHeight()) {
                            int pix = inImage.getRGB(x, y);
                            red[index] = (pix >> 16) & 0xFF;
                            green[index] = (pix >> 8) & 0xFF;
                            blue[index] = (pix) & 0xFF;
                            index++;
                        }
                    }
                }
                Arrays.sort(red);
                Arrays.sort(green);
                Arrays.sort(blue);

                int mRed = red[(2 * radius + 1) * (2 * radius + 1) / 2];
                int mGreen = green[(2 * radius + 1) * (2 * radius + 1) / 2];
                int mBlue = blue[(2 * radius + 1) * (2 * radius + 1) / 2];

                int generalMed = (mRed << 16) | (mGreen << 8) | mBlue;
                out.setRGB(j, i, generalMed);
            }
        }
        ImageIO.write(out, "JPEG", new File(outputPNG));
    }
}