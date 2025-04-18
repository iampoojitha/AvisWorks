package spring.TextHash.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import spring.TextHash.dto.*;
import spring.TextHash.service.EncryptionService;

import javax.crypto.SecretKey;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Value;

@Service
@RequiredArgsConstructor
public class EncryptionServiceImpl implements EncryptionService {

    private static Map<String, EncryptResponse> encryptedData = new HashMap<>();
    @Value("${token.expiration}")
    private long expiration;

    @Value("${user.password}")
    private String userPassword;

    @Override
    public String encryptData(String plainText) {
        var key = EncryptDecryptUtil.generateKey();
        String encodedKey = EncryptDecryptUtil.encodeKey(key);
        String encodedPassword = Base64.getEncoder().encodeToString(userPassword.getBytes());
        String encryptedCode = EncryptDecryptUtil.encrypt(plainText, key);
        var expirationTime = LocalDateTime.now().plusMinutes(expiration);
        String uid = UUID.randomUUID().toString();
        String url = generateUrl(uid);
        EncryptResponse encrypt = new EncryptResponse(encryptedCode, encodedKey, url, expirationTime, false);
        encryptedData.put(uid, encrypt);
        return "URL: " + url + "\n" +
                "Expires At: " + expirationTime + "\n" +
                "Password: " + encodedPassword + "\n";
    }

    public DecryptedResponse decryptData(DecryptedRequest request) {
        SecretKey decodedKey = EncryptDecryptUtil.decodeKey(request.getSecretKey());
        String decrypted = EncryptDecryptUtil.decrypt(request, decodedKey);
        return new DecryptedResponse(decrypted);
    }

    @Scheduled(cron = "0 */5 * * * ?")
    public void prune(){
        final LocalDateTime now = LocalDateTime.now();
        final Set<String> deleteKeys = encryptedData.entrySet().stream()
                .filter(e-> now.isAfter(e.getValue().getExpirationTime()))
                .map(Map.Entry::getKey)
                .collect(Collectors.toSet());
        deleteKeys.forEach(encryptedData::remove);
    }

    @Override
    public String getPlainText(String token, String password) {
        var tokenDetails = encryptedData.get(token);
        String decodedPassword = new String(Base64.getDecoder().decode(password));
        if (!decodedPassword.equals(userPassword)) {
            return "Incorrect password";
        }
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
        prune();
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