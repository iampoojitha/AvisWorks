package spring.TextHash.service;

import spring.TextHash.dto.TextHashRequest;
import spring.TextHash.dto.TextHashResponse;

public interface TextHashService {
    public TextHashResponse convertToHash(TextHashRequest request);
}
