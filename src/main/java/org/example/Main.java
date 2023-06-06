package org.example;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
        String str = "Hello world!";
        Key key = new Key(TestsEnum.FermaTest, 0.95, 1024);
        try{
            key.keyGenerator(1024);
        }
        catch(Exception e){
            System.out.println("Expection while key gen!");
            System.exit(0);
        }
        RSA rsa = new RSA(key);
        byte[] encrypted = rsa.encrypt(str.getBytes());
        System.out.println(rsa.decrypt(encrypted));

    }
}