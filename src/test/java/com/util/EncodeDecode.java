package com.util;

import java.util.Base64;



public class EncodeDecode {

	//static Login login = new Login();

    public static void main(String[] args) throws Exception {


        String str = "b&p5qJ.g[%;v*Yc$?!k3hL";

        String encoded =  encryptPassword(str);
        System.out.println(encoded);
        String decoded =decryptPassword(encoded);
        System.out.println(decoded);
  
  
    }
        // Encode data on your side using BASE64

    public static String encryptPassword(String toEncodedStr){


        byte[] encodedByte = Base64.getEncoder().encode(toEncodedStr.getBytes());
        String encodedText = new String(encodedByte);
        return encodedText;
    }


    //Function to get decrypt the password
    public static String decryptPassword(String encodedStr){
        byte[] decodeBytes = Base64.getDecoder().decode(encodedStr.getBytes());
        return new String(decodeBytes);
    }
}
