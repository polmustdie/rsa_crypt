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
        if (this.test == TestsEnum.FERMA_TEST){
            simplicityTest = new FermaTest();
        }
        if (this.test == TestsEnum.SOLOVEY_STRASSEN_TEST){
            simplicityTest = new SoloveyStrassenTest();
        }
        if (this.test == TestsEnum.MILLER_RABIN_TEST){
            simplicityTest = new MillerRabinTest();
        }
        Random random = new Random(System.currentTimeMillis());
        BigInteger p;
        BigInteger q;
        d = BigInteger.ZERO;
        //uncomment this block in order for wiener attack to complete successfully
        //and guess d

//        p = new BigInteger("239");
//        q = new BigInteger("379");
//        n = p.multiply(q);
//        BigInteger phi = p.subtract(BigInteger.ONE).multiply(q.subtract(BigInteger.ONE));
//        e = BigInteger.valueOf(17993L);
//        Key.d = e.modInverse(phi);
//        System.out.println("Real d " + d);
//        System.out.println("Real n "+n);



        //uncomment this block in order for wiener attack to fail

        while (d.bitLength() < CRITERIA_WIENER) {
            do {
                p = new BigInteger(bitLength/2, random);
            }
            while (!simplicityTest.test(p, 0.95) || p.bitLength() != bitLength/2);
            do {
                q = new BigInteger(bitLength/2, random);
            }
            while (!simplicityTest.test(q, 0.95) || q.bitLength() != bitLength/2);
            n = p.multiply(q);

            BigInteger phi = p.subtract(BigInteger.ONE).multiply(q.subtract(BigInteger.ONE));
            e = BigInteger.valueOf(17993L);
    //        e = BigInteger.valueOf(65537);
            Key.d = e.modInverse(phi);
            System.out.println("Real d " + d);
            System.out.println("Real n " + n);
            }
    }



}
