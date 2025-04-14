package spring.TextHash.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
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
    public ResponseEntity<DecryptedResponse> decryptData(@Valid @RequestBody DecryptedRequest request) {
        try {
            DecryptedResponse response = encryptionService.decryptData(request);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            throw new RuntimeException("Something went wrong while decrypting the data: " + e.getMessage());
        }
    }

    @GetMapping(AppConfig.GET_TEXT)
    public ResponseEntity<DecryptedResponse> getPlainText(@PathVariable String token) {
        try {
            DecryptedResponse response = encryptionService.getPlainText(token);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            throw new RuntimeException("Something went wrong while getting the plain text: \" + e.getMessage()");
        }
    }
}
