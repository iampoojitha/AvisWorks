package spring.TextHash.service;

import spring.TextHash.dto.*;

public interface EncryptionService {

    public EncryptResponse encryptData(TextHashRequest request);

    public EncryptDto decryptData(DecryptedRequest request);

}
