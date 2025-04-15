package spring.TextHash.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class EncryptResponse {
    private String encryptedText;
    private String secretKey;
    private String url;
    private LocalDateTime expirationTime;
    private Boolean isRead;
}
