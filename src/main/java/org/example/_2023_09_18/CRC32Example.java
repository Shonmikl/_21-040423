package org.example._2023_09_18;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.zip.CRC32;

/**
 * CRC32 Используется в сетевых протоколах
 */

public class CRC32Example {
    public static void main(String[] args) {
        String text = "text;";
        CRC32 crc32 = new CRC32();
        crc32.update(text.getBytes());
        long crc32Value = crc32.getValue();

        System.out.println(crc32Value);
    }
}

/**
 * HMAC
 * Auth
 */
class HMACExample {
    public static void main(String[] args) throws NoSuchAlgorithmException, InvalidKeyException {
        String text = "text;";
        String key = "secretKey";

        Mac hmac256 = Mac.getInstance("HmacSHA256");
        SecretKeySpec secretKeySpec = new SecretKeySpec(key.getBytes(), "HmacSHA256");
        hmac256.init(secretKeySpec);

        byte[] result = hmac256.doFinal(text.getBytes());
        StringBuilder stringBuilder = new StringBuilder();

        for (byte b : result) {
            stringBuilder.append(String.format("%02x", b));
        }

        System.out.println(stringBuilder);
    }
}
