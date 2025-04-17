package spring.TextHash.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import spring.TextHash.dto.*;
import spring.TextHash.service.EncryptionService;

import javax.crypto.SecretKey;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Base64;
import java.util.Set;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Value;

@Service
@RequiredArgsConstructor
public class EncryptionServiceImpl implements EncryptionService {

    private final RedisTemplate<String, EncryptResponse> redisTemplate;
    private final RedisTemplate<String, String> stringRedisTemplate; // for passwords

    @Value("${token.expiration}")
    private long expiration;

    @Value("${user.password}")
    private String userPassword;

    @Value("${password.expiry}")
    private long passwordExpiry;

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
        redisTemplate.opsForValue().set(uid, encrypt, Duration.ofMinutes(expiration));
        stringRedisTemplate.opsForValue().set("pwd:" + uid, encodedPassword, Duration.ofMinutes(passwordExpiry));
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
    public void prune() {
        final LocalDateTime now = LocalDateTime.now();
        Set<String> keys = redisTemplate.keys("*"); // Get all keys
        if (keys != null) {
            ValueOperations<String, EncryptResponse> ops = redisTemplate.opsForValue();
            keys.forEach(key -> {
                EncryptResponse response = ops.get(key);
                if (response != null && now.isAfter(response.getExpirationTime())) {
                    redisTemplate.delete(key); // Remove expired entries
                }
            });
        }
    }

    @Override
    public String getPlainText(String token, String password) {
        ValueOperations<String, EncryptResponse> ops = redisTemplate.opsForValue();
        EncryptResponse tokenDetails = ops.get(token);

        ValueOperations<String, String> passwordOps = stringRedisTemplate.opsForValue();
        String storedEncodedPassword = passwordOps.get("pwd:" + token);

        if (storedEncodedPassword == null) {
            return "Password has expired.";
        }

        if (tokenDetails == null) {
            return "This data has expired.";
        }

        String decodedPassword = new String(Base64.getDecoder().decode(password));
        if (!decodedPassword.equals(userPassword)) {
            return "Incorrect password";
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
        ops.set(token, tokenDetails);

        return "Output: " + response.getDecryptedData() + "\n" +
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
