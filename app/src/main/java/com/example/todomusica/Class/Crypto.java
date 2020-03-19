package com.example.todomusica.Class;

public class Crypto{

    public byte[] encrypt(byte[] data){
        byte[] enc = new  byte[data.length];

        for (int i=0; i<data.length; i++){
            enc[i] = (byte) ((i % 2 == 0) ? data[i] + 1 : data[i] -1);
        }
        return enc;
    }

    public byte[] decrypt(byte[] data){
        byte[] enc = new byte[data.length];

        for (int i=0; i<data.length; i++){
            enc[i] = (byte) ((i % 2 == 0) ? data[i] - 1 : data[i]+1);
        }
        return enc;
    }
}
