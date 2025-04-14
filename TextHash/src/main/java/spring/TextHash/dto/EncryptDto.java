package spring.TextHash.dto;

import lombok.Data;

@Data
public class EncryptDto {
    private String encryptedText;
    private String secretKey;
}
