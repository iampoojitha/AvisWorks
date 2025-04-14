package spring.TextHash.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import spring.TextHash.dto.*;
import spring.TextHash.service.EncryptionService;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;

@Service
@RequiredArgsConstructor
public class EncryptionServiceImpl implements EncryptionService {

    @Override
    public EncryptResponse encryptData(TextHashRequest request) {
        var key = EncryptDecryptUtil.generateKey();
        String encodedKey = EncryptDecryptUtil.encodeKey(key);
        request.setKey(key);
        String encryptedCode = EncryptDecryptUtil.encrypt(request);
        return new EncryptResponse(encryptedCode, encodedKey);
    }

    @Override
    public DecryptedResponse decryptData(DecryptedRequest request) {
        SecretKey decodedKey = EncryptDecryptUtil.decodeKey(request.getSecretKey());
        String decrypted = EncryptDecryptUtil.decrypt(request, decodedKey);
        return new DecryptedResponse(decrypted);
    }
}