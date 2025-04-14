package spring.TextHash.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DecryptedResponse {
    private String decryptedData;
}
