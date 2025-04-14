package spring.TextHash.service;

import spring.TextHash.dto.*;

public interface EncryptionService {

    public EncryptResponse encryptData(TextHashRequest request);

    public DecryptedResponse decryptData(DecryptedRequest request);

    public DecryptedResponse getPlainText(String token);

}
