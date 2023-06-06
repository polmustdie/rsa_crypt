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
        byte[] copy = new byte[data.length + 1];
        copy[0] = 127;
        System.arraycopy(data, 0, copy, 1, data.length);

        BigInteger m = new BigInteger(data);
//        return message.modPow(publicKey, modulus);
        BigInteger x = m.modPow(e, n);

        return x;
    }
    public String decrypt(BigInteger data) {

//        BigInteger x = new BigInteger(data);

        BigInteger m = data.modPow(d, n);

        //removing sign
//        int length = m.toByteArray().length;
//        byte[] copy = new byte[length - 1];
//        System.arraycopy(m.toByteArray(), 1, copy, 0, length-1);

        return new String(m.toByteArray(), StandardCharsets.UTF_8);
    }

}
