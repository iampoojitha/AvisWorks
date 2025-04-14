package spring.TextHash.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import spring.TextHash.config.AppConfig;
import spring.TextHash.dto.TextHashRequest;
import spring.TextHash.dto.TextHashResponse;
import spring.TextHash.service.TextHashService;

@RequestMapping(AppConfig.APP_ROUTE)
@RestController
@RequiredArgsConstructor
public class TextHashRoute {

    private final TextHashService textHashService;

    @PostMapping(AppConfig.CONVERT)
    public ResponseEntity<TextHashResponse> convertToHash(@Valid @RequestBody TextHashRequest request) {
        try {
            TextHashResponse response = textHashService.convertToHash(request);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            throw new RuntimeException("Something went wrong while hashing the text: " + e.getMessage());
        }
    }
}
