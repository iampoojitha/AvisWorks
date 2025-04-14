package spring.TextHash.service.impl;


import org.springframework.stereotype.Component;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

@Component
public class HashUtil {

    private static final String ALGORITHM = "SHA-256";

    public static String generateHash(String plainText){
        try {
            MessageDigest digest = MessageDigest.getInstance(ALGORITHM);
            var encodedHash = digest.digest(plainText.getBytes());
            return Base64.getEncoder().encodeToString(encodedHash);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Hash generation failed: No such algorithm: " + e.getMessage());
        }
    }
}
