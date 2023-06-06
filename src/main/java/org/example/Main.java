package org.example;

import java.math.BigInteger;

public class Main {
    public static void main(String[] args) {
//        System.out.println("Hello world!");
        String str = "p";
        Key key = new Key(TestsEnum.FermaTest, 0.95, 1024);
        try{
            key.keyGenerator(1024);
        }
        catch(Exception e){
            System.out.println("Expection while key gen!");
            System.exit(0);
        }
        RSA rsa = new RSA(key);
        BigInteger encrypted = rsa.encrypt(str.getBytes());
        System.out.println(rsa.decrypt(encrypted));
        WienerAttack attack = new WienerAttack();
        if (attack.attack(Key.n, Key.e)){
            System.out.println("guessed d"+attack.getDGuessed());
            for (int i = 0; i < attack.getKD().length; i++){
                int[][]arr = attack.getKD();
                System.out.println(attack.getKD()[i][0] + "/" + attack.getKD()[i][1]);
            }

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