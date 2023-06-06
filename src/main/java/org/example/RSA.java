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
    public byte[] encrypt(byte[] data) {
        byte[] copy = new byte[data.length + 1];
        copy[0] = 127;
        System.arraycopy(data, 0, copy, 1, data.length);

        BigInteger m = new BigInteger(copy);
        BigInteger x = m.modPow(e, n);

        return x.toByteArray();
    }
    public String decrypt(byte[] data) {

        BigInteger x = new BigInteger(data);

        BigInteger m = x.modPow(d, n);

        //removing sign
        int length = m.toByteArray().length;
        byte[] copy = new byte[length - 1];
        System.arraycopy(m.toByteArray(), 1, copy, 0, length-1);

        return new String(copy, StandardCharsets.UTF_8);
    }

}
