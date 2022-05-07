package com.tests.security;

import com.security.StringHash;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.security.NoSuchAlgorithmException;

public class StringHashTest {
    private StringHash stringHash;

    @Before
    public void setup() {
        this.stringHash = new StringHash();
    }

    @Test
    public void testItReturnsTheHash() throws NoSuchAlgorithmException {
        Assert.assertEquals("0f112a4f78df37974de656f9580467a70c8f481ea433bff45735b28ddc028240", this.stringHash.hash("abc"));
    }
}
