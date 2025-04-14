package spring.TextHash.service.impl;

import org.springframework.stereotype.Component;
import spring.TextHash.dto.TextHashRequest;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

@Component
public class EncryptDecryptUtil {

    private static final String ALGORITHM = "AES";

    public static SecretKey generateKey() {
        try {
            KeyGenerator keyGen = KeyGenerator.getInstance(ALGORITHM);
            keyGen.init(128);
            return keyGen.generateKey();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Hash generation failed: No such algorithm: " + e.getMessage());
        }
    }

    public static String encrypt(TextHashRequest request) {
        try {
            var cipher = Cipher.getInstance(ALGORITHM);
            cipher.init(Cipher.ENCRYPT_MODE, request.getKey());
            var encryptedBytes = cipher.doFinal(request.getPlainText().getBytes());
            return Base64.getEncoder().encodeToString(encryptedBytes);
        } catch (Exception e) {
            throw new RuntimeException("Hash generation failed: " + e.getMessage());
        }
    }

    public static String encodeKey(SecretKey key) {
        return Base64.getEncoder().encodeToString(key.getEncoded());
    }

}
