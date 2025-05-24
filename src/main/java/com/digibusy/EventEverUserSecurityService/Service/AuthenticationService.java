package com.digibusy.EventEverUserSecurityService.Service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    public static boolean verifyHash(String input,String secretKey,String expectedHash){
        String generatedHash = generateHMACSHA256(input, secretKey);
        return generatedHash.equals(expectedHash);
    }

    public static String generateHMACSHA256(String input, String secretKey) {
        try {
            // Define the HMAC SHA-256 algorithm
            Mac mac = Mac.getInstance("HmacSHA256");
            SecretKeySpec secretKeySpec = new SecretKeySpec(secretKey.getBytes(), "HmacSHA256");
            mac.init(secretKeySpec);

            // Generate the hash
            byte[] hashBytes = mac.doFinal(input.getBytes());
            // Convert the hash to Base64 for better readability (optional)
            return Base64.getEncoder().encodeToString(hashBytes);
        } catch (Exception e) {
            throw new RuntimeException("Error while generating HMAC SHA-256", e);
        }
    }

}

