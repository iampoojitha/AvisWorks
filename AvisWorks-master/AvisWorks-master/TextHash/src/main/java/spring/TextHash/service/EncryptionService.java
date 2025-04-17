package spring.TextHash.service;

import spring.TextHash.dto.*;

public interface EncryptionService {

    public String encryptData(String plainText);

    public String getPlainText(String token, String password);

}
