package org.example;

import java.math.BigInteger;
import java.util.Random;

public class FermaTest implements ISimplicityTest {
    @Override
    public boolean test(BigInteger n, double precision) throws Exception {
        if (n.equals(BigInteger.TWO)) {
            return true;
        }
        if (n.compareTo(BigInteger.TWO) < 0) {
            return false;
        }
        if (n.and(BigInteger.ONE).equals(0)) {
            return false;
        }
        if (precision < 0.5 || precision >= 1) {
            System.out.println("Error!");
            throw new Exception("Precision wrong!");
        }
        double k = Math.log(1 / (1 - precision)) / Math.log(2);
        if (Math.abs(k - (int) k) > 0) {
            k++;
            k = Math.round(k);
        }
        BigInteger a;
        for (long i = 0; i < k; i++) {
            a = getRandomBase(n);
            if (!a.modPow(n.subtract(BigInteger.ONE), n).equals(BigInteger.ONE)) {
                return false;
            }
        }
        return true;
    }

    //генерируем свидетелей простоты меньше нашего числа но больше единицы
    private static BigInteger getRandomBase(BigInteger n) {
        Random random = new Random(System.currentTimeMillis());
        BigInteger a;
        do {
            a = new BigInteger(n.bitLength(), random);
        } while (a.compareTo(n) >= 0 || a.compareTo(BigInteger.ONE) <= 0);
        return a;
    }
}
