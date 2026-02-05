package org.example.SaveProgramming.T5S1P1_1;

import java.security.MessageDigest;
import java.util.Scanner;

public class T5S1P1_1 {
    public static void main(String[] args) throws Exception {
        Scanner kb = new Scanner(System.in);
        System.out.print("Introduce tu texto: ");
        String text = kb.nextLine();

        MessageDigest md = MessageDigest.getInstance("MD5");

        md.update(text.getBytes());
        byte[] hashInBytes = md.digest();

        System.out.println("El hash (resum) MD5 de '" + text + "' es: " + new String(hashInBytes));
        System.out.println("Nombre de bytes: " + md.getDigestLength());
    }
}