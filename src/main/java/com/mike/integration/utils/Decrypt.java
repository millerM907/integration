package com.mike.integration.utils;

public class Decrypt {

    private final static int KEY = 10;

    public static String getDecryptedStr(String encryptedStr){
        char[] str = encryptedStr.toCharArray();
        for(int i = 0; i < str.length; i++){
            str[i] -= KEY;
        }
        return new String(str);
    }
}
