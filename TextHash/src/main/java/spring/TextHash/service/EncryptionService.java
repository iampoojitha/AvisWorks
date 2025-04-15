package spring.TextHash.service;

import spring.TextHash.dto.*;

public interface EncryptionService {

    public String encryptData(String plainText);

//    public DecryptedResponse decryptData(DecryptedRequest request);

    public String getPlainText(String token);

}
