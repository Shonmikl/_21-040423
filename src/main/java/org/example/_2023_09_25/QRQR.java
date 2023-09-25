package org.example._2023_09_25;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class QRQR {
    public static void main(String[] args) {
        String source = "https://www.msn.com/es-es/motor/noticias/" +
                "triste-noticia-sobre-la-esposa-de-michael-schumacher-es-una-prisionera" +
                "/ss-AA1hcBSr?ocid=msedgntp&cvid=1b8c8eaef649432389e21a09fe319e8c&ei=25#image=1";
        String path = "qrH.png";
        generateQR(source, path);
    }

    private static void generateQR(String url, String filePath) {
        int width = 300;
        int height = 300;
        String format = "png";

        try {
            Map<EncodeHintType, Object> hints = new HashMap<>();
            hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
            hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);

            BitMatrix bitMatrix = new QRCodeWriter().encode(url, BarcodeFormat.QR_CODE, width, height, hints);
            BufferedImage qrImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

            for (int x = 0; x < width; x++) {
                for (int y = 0; y < height; y++) {
                    qrImage.setRGB(x, y, bitMatrix.get(x, y) ? 0x000000 : 0xFFFFFF);
                }
            }

            File file = new File(filePath);
            ImageIO.write(qrImage, format, file);
            System.out.println("***DONE***");

        } catch (WriterException | IOException e) {
            throw new RuntimeException(e);
        }
    }
}