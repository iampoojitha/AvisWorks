package spring.TextHash.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import spring.TextHash.dto.*;
import spring.TextHash.service.EncryptionService;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class EncryptionServiceImpl implements EncryptionService {

    private static Map<String, EncryptResponse> encryptedData = new HashMap<>();

    @Override
    public String encryptData(String plainText) {
        var key = EncryptDecryptUtil.generateKey();
        String encodedKey = EncryptDecryptUtil.encodeKey(key);
        String encryptedCode = EncryptDecryptUtil.encrypt(plainText, key);
        var expirationTime = LocalDateTime.now().plusSeconds(8);
        String uid = UUID.randomUUID().toString();
        String url = generateUrl(uid);
        EncryptResponse encrypt = new EncryptResponse(encryptedCode, encodedKey, url, expirationTime, false);
        encryptedData.put(uid, encrypt);
        return "URL: " + url + "\n" +
                "Expires At: " + expirationTime + "\n";
    }

    public DecryptedResponse decryptData(DecryptedRequest request) {
        SecretKey decodedKey = EncryptDecryptUtil.decodeKey(request.getSecretKey());
        String decrypted = EncryptDecryptUtil.decrypt(request, decodedKey);
        return new DecryptedResponse(decrypted);
    }

    @Override
    public String getPlainText(String token) {
        var tokenDetails = encryptedData.get(token);
        if (LocalDateTime.now().isAfter(tokenDetails.getExpirationTime())) {
            return "This data has expired.";
        }
        if (tokenDetails.getIsRead()) {
            return "This data is already read";
        }
        var response = decryptData(new DecryptedRequest(tokenDetails.getEncryptedText(), tokenDetails.getSecretKey()));
        Duration timeLeft = Duration.between(LocalDateTime.now(), tokenDetails.getExpirationTime());
        long hours = timeLeft.toHours();
        long minutes = timeLeft.toMinutes() % 60;
        long seconds = timeLeft.getSeconds() % 60;
        tokenDetails.setIsRead(true);
        return  "Output: " + response.getDecryptedData() + "\n" +
                "Read: true\n" +
                "TTD: " + hours + "h " + minutes + "m " + seconds + "s";
    }

    private String generateUrl(String token) {
        if (token != null) {
            return String.format("http://localhost:8080/api/url/%s", token);
        } else {
            return null;
        }
    }
}