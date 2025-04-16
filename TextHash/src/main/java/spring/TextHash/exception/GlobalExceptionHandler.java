package spring.TextHash.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.server.ResponseStatusException;

import java.time.ZonedDateTime;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

        @ExceptionHandler(ResponseStatusException.class)
        public ResponseEntity<Map<String, Object>> handleResponseStatusException(ResponseStatusException ex) {
            Map<String, Object> body = new HashMap<>();
            body.put("timestamp", ZonedDateTime.now());
            body.put("status", ex.getStatusCode().value());
            body.put("error", ex.getStatusCode());
            body.put("message", ex.getReason());

            return new ResponseEntity<>(body, ex.getStatusCode());
        }
    }
