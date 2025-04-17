package spring.TextHash.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EncryptResponse {
    private String encryptedText;
    private String secretKey;
    private String url;
    private LocalDateTime expirationTime;
    private Boolean isRead;
}
