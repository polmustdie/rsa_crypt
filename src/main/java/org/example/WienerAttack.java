package org.example;

import java.math.BigInteger;

public class WienerAttack {
    static int[] a;
    static int[][] kd;
    static int d = 0;
    static int dGuessed = 0;
    public int getDGuessed() {
        return dGuessed;
    }
    public int[][] getKD() {
        return kd;
    }
    public static boolean attack(BigInteger n, BigInteger e){
        a = continuousFraction(n, e);
        kd = kD(a);

        for (int i = 1; i < a.length; i++){
            BigInteger func;
            func = f(e, kd[i][0], kd[i][1]);
            if (equation(n, func)) {
                d = kd[i][1];
                dGuessed = d;
                break;
            }
        }
        return checkIfAttackWorks(n, d);
    }
    public static int[] continuousFraction(BigInteger n, BigInteger e) {
        int[] a = new int[100];
        a[0] = 0;
        BigInteger temp;
        int i = 1;
        while(n.divide(e).compareTo(BigInteger.ZERO) > 0 ){
            temp = n;
            n = e;
            e = temp;
            BigInteger x = e.divide(n);
            a[i] = (int) Math.floor(x.doubleValue());
            e = e.subtract(n.multiply(BigInteger.valueOf(a[i])));
            i++;
            if (e.compareTo(BigInteger.ZERO) == 0)
                break;
        }
        int[] aCopy = new int[i];
        if (i >= 0) {
            System.arraycopy(a, 0, aCopy, 0, i);
        }
        return aCopy;

    }
    public static int[][] kD(int[] a){
        int[][] kd = new int[a.length][2];
        kd[1][0] = 1;
        kd[1][1] = a[1];

        for (int i = 2; i < a.length; i++){
            int array = 1;
            int par = 1;
            int temp1 = a[i];
            for(int j = i; j > 1; j--){
                par = a[j - 1] * temp1 + array;
                array = temp1;
                temp1 = par;
            }
            kd[i][0] = array;
            kd[i][1] = par;
        }

        return kd;

    }
    public static BigInteger f(BigInteger e, int k, int d){
        return (e.multiply(BigInteger.valueOf(d)).subtract(BigInteger.valueOf(1))).divide(BigInteger.valueOf(k));
    }
    public static boolean equation(BigInteger n, BigInteger f){
        BigInteger x1;
        BigInteger x2;
        BigInteger d;
        BigInteger temp;
        temp = n.negate().add(f).subtract((BigInteger.ONE));
        d = temp.pow(2).subtract(n.multiply(BigInteger.valueOf(4)));
        if (d.compareTo(BigInteger.ZERO) < 0)
            return false;
        if (d.compareTo(BigInteger.ZERO) == 0)
            if (temp.compareTo(n) == 0)
                return true;


        x1 = temp.subtract(d.sqrt());
        x1 = x1.divide(BigInteger.TWO);
        x2 = temp.add(d.sqrt());
        x2 = x2.divide(BigInteger.TWO);
        return x1.multiply(x2).compareTo(n) == 0;

    }
    public static boolean checkIfAttackWorks(BigInteger n, int d){
        return n.pow(1 / 4).divide(BigInteger.valueOf(3)).compareTo(BigInteger.valueOf(d)) < 0;
    }



}
