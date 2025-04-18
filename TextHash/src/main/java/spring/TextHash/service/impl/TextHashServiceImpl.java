package spring.TextHash.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import spring.TextHash.dto.TextHashRequest;
import spring.TextHash.dto.TextHashResponse;
import spring.TextHash.service.TextHashService;

@Service
@RequiredArgsConstructor
public class TextHashServiceImpl implements TextHashService {

    @Override
    public TextHashResponse convertToHash(TextHashRequest request) {
        String hashCode = HashUtil.generateHash(request.getPlainText());
        return new TextHashResponse(hashCode);
    }
}
