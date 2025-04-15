package spring.TextHash.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import spring.TextHash.config.AppConfig;
import spring.TextHash.dto.*;
import spring.TextHash.service.EncryptionService;

@RequestMapping(AppConfig.ENCRYPT_ROUTE)
@RestController
@RequiredArgsConstructor
public class EncryptionRoute {

    private final EncryptionService encryptionService;

    @PostMapping(path = AppConfig.ENCRYPT, consumes = MediaType.TEXT_PLAIN_VALUE)
    public String encryptData(@RequestBody String plainText) {
        try {
           return encryptionService.encryptData(plainText);
        } catch (Exception e) {
            e.getStackTrace();
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Something went wrong while encrypting the data");
        }
    }

    @GetMapping(AppConfig.GET_TEXT)
    public String getPlainText(@PathVariable String token) {
        try {
            return encryptionService.getPlainText(token);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Information expired");
        }
    }
}
