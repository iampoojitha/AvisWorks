package spring.TextHash.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class EncryptResponse {
    private String encryptedData;
    private String secretKey;
}
