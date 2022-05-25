package com.security;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Hash strings used in the application from plain text.
 */
public class StringHash {
    private static final String SALT = "jaoAdm!woG£moa£d#owAWm#w2220dmd//aWd223dw#~22322wd(";

    /**
     * Hash a given string using the SHA-256 algorithm.
     * @param stringToHash The plain text string
     * @return The hashed string
     * @throws NoSuchAlgorithmException If the hashing algorithm used cannot be found
     */
    public static String hash(String stringToHash) throws NoSuchAlgorithmException {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(SALT.getBytes(StandardCharsets.UTF_8));
            byte[] bytes = md.digest(stringToHash.getBytes(StandardCharsets.UTF_8));

            StringBuilder sb = new StringBuilder();
            for (byte aByte : bytes) {
                sb.append(Integer.toString((aByte & 0xff) + 0x100, 16).substring(1));
            }

            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new NoSuchAlgorithmException(e);
        }
    }
}
