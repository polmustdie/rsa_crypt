package org.example;

import java.math.BigInteger;

public class Main {
    public static void main(String[] args) {
        //we need a small string to encrypt/decrypt successfully
        // possible length depends on n (p*q)
        //uncomment this string in order for wiener attack to complete successfully
        //and guess d
//        String str = "c";
        String str = "HI WORLD!!!";
        System.out.println("Initial message:");
        System.out.println(str);
        Key key = new Key(TestsEnum.FERMA_TEST, 0.95, 1024);
        try{
            key.keyGenerator(key.keyLength);
        }
        catch(Exception e){
            System.out.println("Exception while key gen!");
            System.exit(0);
        }
        RSA rsa = new RSA(key);
        BigInteger encrypted = rsa.encrypt(str.getBytes());
        System.out.println("Decrypted message:");
        System.out.println(rsa.decrypt(encrypted));
        WienerAttack attack = new WienerAttack();
        if (WienerAttack.attack(Key.n, Key.e)){
            System.out.println("Wiener attack was successful!");
            System.out.println("Guessed d = "+attack.getDGuessed());
            System.out.println("Fraction collection:");
            for (int i = 0; i < attack.getKD().length; i++){
                int[][]arr = attack.getKD();
                System.out.println(arr[i][0] + "/" + arr[i][1]);
            }

        }
        else{
            System.out.println("Wiener attack failed!");
        }
//        try{
//            key.keyGenerator(1024);
//            System.out.println(Key.n);
//        }
//        catch(Exception e){
//            System.out.println("Expection while key gen!");
//            System.exit(0);
//        }



    }
}