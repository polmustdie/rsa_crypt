package org.example;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;

import static org.example.Key.*;

@RequiredArgsConstructor
public class RSA {
    @NonNull
    Key key;
    public BigInteger encrypt(byte[] data) {
        BigInteger m = new BigInteger(data);
        return m.modPow(e, n);
    }
    public String decrypt(BigInteger data) {
        BigInteger m = data.modPow(d, n);
        return new String(m.toByteArray(), StandardCharsets.UTF_8);
    }

}
