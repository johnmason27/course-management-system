package com.security;

import org.junit.jupiter.api.Test;

import java.security.NoSuchAlgorithmException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StringHashTest {
    @Test
    public void testItReturnsTheHash() throws NoSuchAlgorithmException {
        assertEquals("0f112a4f78df37974de656f9580467a70c8f481ea433bff45735b28ddc028240", StringHash.hash("abc"));
    }
}
