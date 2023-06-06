package org.example;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.math.BigInteger;
import java.util.Random;

@RequiredArgsConstructor
public class Key {
    @NonNull
    TestsEnum test;
    @NonNull
    double precision;
    @NonNull
    int keyLength;
    static ISimplicityTest simplicityTest;
    static BigInteger e;
    static BigInteger n;
    static BigInteger d;
    final int CRITERIA_WIENER = 256;


    public void keyGenerator(int bitLength) throws Exception {
        if (this.test == TestsEnum.FermaTest){
            simplicityTest = new FermaTest();
        }
        if (this.test == TestsEnum.SoloveyStrassenTest){
            simplicityTest = new SoloveyStrassenTest();
        }
        if (this.test == TestsEnum.MillerRabinTest){
            simplicityTest = new MillerRabinTest();
        }
        Random random = new Random(System.currentTimeMillis());
        BigInteger p;
        BigInteger q;
        d = BigInteger.ZERO;
//        while (d.bitLength() < CRITERIA_WIENER) {
//            do {
                p = new BigInteger("239");
//                p = new BigInteger(bitLength/2, random);

//            }
//            while (!simplicityTest.test(p, 0.95) || p.bitLength() != bitLength/2);


//            do {
            q = new BigInteger("379");
//                q = new BigInteger(bitLength/2, random);
//            }
//            while (!simplicityTest.test(q, 0.95) || q.bitLength() != bitLength/2);


            n = p.multiply(q);

            BigInteger phi = p.subtract(BigInteger.ONE).multiply(q.subtract(BigInteger.ONE));
            e = BigInteger.valueOf(17993L);
//        e = BigInteger.valueOf(65537);

            Key.d = solve(phi, e);
//            Key.d = e.modInverse(phi);
        System.out.println("REAL D " + d);
            System.out.println("REAL N "+n);
//        }
    }
    private static BigInteger solve(BigInteger a, BigInteger b)
    {
        BigInteger x = BigInteger.ZERO;
        BigInteger y = BigInteger.ONE;
        BigInteger lastx = BigInteger.ONE;
        BigInteger lasty = BigInteger.ZERO;
        BigInteger temp = BigInteger.ZERO;
        while (!b.equals(BigInteger.ZERO))
        {
            BigInteger q = a.divide(b);
            BigInteger r = a.remainder(b);

            a = b;
            b = r;

            temp = x;
            x = lastx.subtract(q.multiply(x));
            lastx = temp;

            temp = y;
            y = lasty.subtract(q.multiply(y));
            lasty = temp;
        }
        // System.out.println("Roots  x : "+ lastx +" y :"+ lasty);
        return lasty;
    }


}
