package spring.TextHash.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import spring.TextHash.dto.*;
import spring.TextHash.service.EncryptionService;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class EncryptionServiceImpl implements EncryptionService {

    private static Map<String, DecryptedRequest> encryptedDataStore = new HashMap<>();

    @Override
    public EncryptResponse encryptData(TextHashRequest request) {
        var key = EncryptDecryptUtil.generateKey();
        String encodedKey = EncryptDecryptUtil.encodeKey(key);
        request.setKey(key);
        String encryptedCode = EncryptDecryptUtil.encrypt(request);
        String url = generateUrl(getToken(encryptedCode, encodedKey));
        return new EncryptResponse(encryptedCode, encodedKey, url);
    }

    @Override
    public DecryptedResponse decryptData(DecryptedRequest request) {
        SecretKey decodedKey = EncryptDecryptUtil.decodeKey(request.getSecretKey());
        String decrypted = EncryptDecryptUtil.decrypt(request, decodedKey);
        return new DecryptedResponse(decrypted);
    }

    @Override
    public DecryptedResponse getPlainText(String token) {
        var tokenDetails = encryptedDataStore.get(token);
        return decryptData(tokenDetails);
    }

    private static String getToken(String encryptedCode, String encodedKey) {
        String uniqueId = UUID.randomUUID().toString();
        DecryptedRequest encryptData = new DecryptedRequest(encryptedCode, encodedKey);
        encryptedDataStore.put(uniqueId, encryptData);
        return uniqueId;
    }

    private String generateUrl(String token) {
        if (token != null) {
            return String.format("http://localhost:8080/api/url/%s", token);
        } else {
            return null;
        }
    }
}