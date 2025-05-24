package com.digibusy.EventEverUserSecurityService.Utils;

import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.spec.KeySpec;
import java.util.Base64;
import java.util.List;

public class HashGenerator {


        private static final Argon2PasswordEncoder argon2 = new Argon2PasswordEncoder(16, 32, 1, 4096, 3);
        private static final BCryptPasswordEncoder bcrypt = new BCryptPasswordEncoder();

        public static String generateHash(String userId, String input) {
            char c = Character.toLowerCase(userId.charAt(0));
            if (c >= 'a' && c <= 'g') return sha256(input);
            if (c >= 'h' && c <= 'k') return pbkdf2(input);
            if (c >= 'l' && c <= 'r') return argon2.encode(input);
            return bcrypt.encode(input);
        }

        public static boolean matches(char c, String rawInput, String storedHash) {
            if (c >= 'a' && c <= 'g') return sha256(rawInput).equals(storedHash);
            if (c >= 'h' && c <= 'k') return pbkdf2(rawInput).equals(storedHash);
            if (c >= 'l' && c <= 'r') return argon2.matches(rawInput, storedHash);
            return bcrypt.matches(rawInput, storedHash);
        }

        private static String sha256(String input) {
            try {
                MessageDigest digest = MessageDigest.getInstance("SHA-256");
                byte[] hash = digest.digest(input.getBytes(StandardCharsets.UTF_8));
                return Base64.getEncoder().encodeToString(hash);
            } catch (NoSuchAlgorithmException e) {
                throw new RuntimeException(e);
            }
        }

        private static String pbkdf2(String input) {
            try {
                byte[] salt = "fixedSalt".getBytes(); // should ideally be random + stored

                KeySpec spec = new PBEKeySpec(input.toCharArray(), salt, 65536, 128);
                SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
                byte[] hash = factory.generateSecret(spec).getEncoded();
                return Base64.getEncoder().encodeToString(hash);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    public static String optimizeEmail(String email){
        return email.replace(email.substring(email.indexOf("@")),"");
    }
}
