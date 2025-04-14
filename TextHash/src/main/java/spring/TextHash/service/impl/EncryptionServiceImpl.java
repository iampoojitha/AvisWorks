package spring.TextHash.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import spring.TextHash.dto.*;
import spring.TextHash.service.EncryptionService;

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
    public EncryptDto decryptData(DecryptedRequest request) {
        return null;
    }


}
