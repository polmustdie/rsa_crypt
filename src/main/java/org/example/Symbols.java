package org.example;

import java.math.BigInteger;

public class Symbols {
    private Symbols() {
        throw new IllegalStateException("Utility class");
    }
    public static int jakobiSymbol(BigInteger n, BigInteger m) {
        if (n.mod(m).equals(BigInteger.ZERO)) {
            return 0; // m divides n
        }
        int j = 1;
        if (n.compareTo(BigInteger.ZERO) < 0) {
            n = n.negate();
            if (m.mod(BigInteger.valueOf(4)).equals(BigInteger.valueOf(3))) {
                j = -j;
            }
        }
        while (!n.equals(BigInteger.ZERO)) {
            while (n.mod(BigInteger.TWO).equals(BigInteger.ZERO)) {
                n = n.divide(BigInteger.TWO);
                if (m.mod(BigInteger.valueOf(8)).equals(BigInteger.valueOf(3)) || m.mod(BigInteger.valueOf(8)).equals(BigInteger.valueOf(5))) {
                    j = -j;
                }
            }
            BigInteger temp = n; // switch so n is bigger
            n = m;
            m = temp;
            if (n.mod(BigInteger.valueOf(4)).equals(BigInteger.valueOf(3)) && m.mod(BigInteger.valueOf(4)).equals(BigInteger.valueOf(3))) { // правило (a/b) = -(b/a)
                j = -j;
            }
            n = n.mod(m);

        }
        if (m.equals(BigInteger.ONE)) {
            return j;
        } else {
            return 0; // m не является взаимно простым с n
        }
    }
    public static int legendreSymbol(BigInteger a, BigInteger p) {
        BigInteger mod;

        // Первая проверка: a == 0 (mod p)
        if (a.mod(p).equals(BigInteger.ZERO)) {
            return 0;
        }

        // Вторая проверка: a == 1 (mod p)
        if (a.equals(BigInteger.ONE)) {
            return 1;
        }

        // Третья проверка: a == 2 (mod p)
        if (a.equals(BigInteger.TWO)) {
            mod = p.mod(BigInteger.valueOf(8));
            if (mod.equals(BigInteger.ONE) || mod.equals(BigInteger.valueOf(7))) {
                return 1;
            } else {
                return -1;
            }
        }

        // Четвертая проверка: a не кратно p и не равно 1
        BigInteger s = BigInteger.ZERO;
        BigInteger d = new BigInteger(a.toString());
        while (d.mod(BigInteger.TWO).equals(BigInteger.ZERO)) {
            s = s.add(BigInteger.ONE);
            d = d.divide(BigInteger.TWO);
        }
        if (s.mod(BigInteger.TWO).equals(BigInteger.ZERO)) {
            mod = p.mod(BigInteger.valueOf(8));
            if (mod.equals(BigInteger.ONE) || mod.equals(BigInteger.valueOf(7))) {
                return 1;
            } else {
                return -1;
            }
        }
        BigInteger r = d.mod(p);
        int result = 1;
        while (!r.equals(BigInteger.ONE) && !r.equals(BigInteger.ZERO)) {
            while (r.mod(BigInteger.TWO).equals(BigInteger.ZERO)) {
                r = r.divide(BigInteger.TWO);
                BigInteger mod8 = p.mod(BigInteger.valueOf(8));
                if (mod8.equals(BigInteger.valueOf(3)) || mod8.equals(BigInteger.valueOf(5))) {
                    result = -result;
                }
            }
            BigInteger c = r;
            r = p;
            p = c;
            if (r.mod(BigInteger.valueOf(4)).equals(BigInteger.valueOf(3)) && p.mod(BigInteger.valueOf(4)).equals(BigInteger.valueOf(3))) {
                result = -result;
            }
            r = r.mod(p);
        }
        if (r.equals(BigInteger.ZERO)) {
            return 0;
        } else {
            return result;
        }
    }

}
