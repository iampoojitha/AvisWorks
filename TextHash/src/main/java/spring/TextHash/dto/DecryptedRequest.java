package spring.TextHash.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class DecryptedRequest {
    @NotBlank(message = "Encrypted data shouldn't be blank")
    private String encryptedData;
    private String key;
}
