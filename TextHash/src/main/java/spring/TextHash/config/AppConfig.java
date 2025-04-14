package spring.TextHash.config;

import org.springframework.stereotype.Component;

@Component
public class AppConfig {

    public static final String HASH_ROUTE = "api/hash";
    public static final String CONVERT = "/convert";
    public static final String ENCRYPT = "/encrypt";
    public static final String DECRYPT = "/decrypt";
    public static final String ENCRYPT_ROUTE = "api";
}
