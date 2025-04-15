package spring.TextHash.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
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

    @PostMapping(path = AppConfig.ENCRYPT, consumes = MediaType.TEXT_PLAIN_VALUE)
    public ResponseEntity<String> encryptData(@RequestBody String plainText) {
        try {
            String response = encryptionService.encryptData(plainText);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            throw new RuntimeException("Something went wrong while hashing the text: " + e.getMessage());
        }
    }

    @GetMapping(AppConfig.GET_TEXT)
    public ResponseEntity<String> getPlainText(@PathVariable String token) {
        try {
            String response = encryptionService.getPlainText(token);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            throw new RuntimeException("Something went wrong while getting the plain text: \" + e.getMessage()");
        }
    }
}
