package spring.TextHash.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.crypto.SecretKey;
import java.time.Duration;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class DecryptedRequest {
    @NotBlank(message = "Encrypted data shouldn't be blank")
    private String encryptedText;
    private String secretKey;
}
