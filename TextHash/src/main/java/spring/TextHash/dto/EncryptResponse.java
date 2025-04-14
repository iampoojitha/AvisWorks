package spring.TextHash.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class EncryptResponse {
    private String encryptedText;
    private String secretKey;
}
