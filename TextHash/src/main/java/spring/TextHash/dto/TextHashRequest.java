package spring.TextHash.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class TextHashRequest {

    @NotBlank(message = "Plain text cannot be empty")
    private String plainText;
}