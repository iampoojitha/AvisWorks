package spring.TextHash.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import spring.TextHash.config.AppConfig;
import spring.TextHash.dto.*;
import spring.TextHash.service.EncryptionService;

@RequestMapping(AppConfig.ENCRYPT_ROUTE)
@RestController
@RequiredArgsConstructor
public class EncryptionRoute {

    private final EncryptionService encryptionService;

    @PostMapping(AppConfig.ENCRYPT)
    public ResponseEntity<EncryptResponse> encryptData(@Valid @RequestBody TextHashRequest request) {
        try {
            EncryptResponse response = encryptionService.encryptData(request);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            throw new RuntimeException("Something went wrong while hashing the text: " + e.getMessage());
        }
    }

    @PostMapping(AppConfig.DECRYPT)
    public ResponseEntity<EncryptDto> decryptData(@Valid @RequestBody DecryptedRequest request) {
        try {
            EncryptDto response = encryptionService.decryptData(request);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            throw new RuntimeException("Something went wrong while decrypting the data: " + e.getMessage());
        }
    }
}
