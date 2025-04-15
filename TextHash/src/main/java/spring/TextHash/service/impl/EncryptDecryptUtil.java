package spring.TextHash.service.impl;

import spring.TextHash.dto.DecryptedRequest;
import spring.TextHash.dto.TextHashRequest;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class EncryptDecryptUtil {

    private static final String ALGORITHM = "AES";

    public static SecretKey generateKey() {
        try {
            var keyGen = KeyGenerator.getInstance(ALGORITHM);
            keyGen.init(128);
            return keyGen.generateKey();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("key generation failed: No such algorithm: " + e.getMessage());
        }
    }

    public static String encrypt(TextHashRequest request) {
        try {
            var cipher = Cipher.getInstance(ALGORITHM);
            cipher.init(Cipher.ENCRYPT_MODE, request.getKey());
            var encryptedBytes = cipher.doFinal(request.getPlainText().getBytes());
            return Base64.getEncoder().encodeToString(encryptedBytes);
        } catch (Exception e) {
            throw new RuntimeException("encrypt failed: " + e.getMessage());
        }
    }

    public static String encodeKey(SecretKey key) {
        return Base64.getEncoder().encodeToString(key.getEncoded());
    }

    public static String decrypt(DecryptedRequest request, SecretKey key) {
        try {
            var cipher = Cipher.getInstance(ALGORITHM);
            cipher.init(Cipher.DECRYPT_MODE, key);
            var decryptedBytes = cipher.doFinal(Base64.getDecoder().decode(request.getEncryptedText()));
            return new String(decryptedBytes);
        } catch (Exception e) {
            throw new RuntimeException("decryption failed: " + e.getMessage());
        }
    }

    public static SecretKey decodeKey(String encodedKey) {
        var decodedKey = Base64.getDecoder().decode(encodedKey);
        return new SecretKeySpec(decodedKey, 0, decodedKey.length, ALGORITHM);
    }
}
