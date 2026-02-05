package org.example.SaveProgramming.T5S1P1_2;

import java.io.FileInputStream;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.security.MessageDigest;

public class Llig_Verifica {
    public static void main(String args[]) {
        try {
            // PRIMER LLIG OBJECTE MISSATGE DE TIPUS string DEL FITXER data.dat
            InputStream fisDades = new FileInputStream("data.dat");
            ObjectInputStream oisDades = new ObjectInputStream(fisDades);
            Object o = oisDades.readObject();
            String dades = (String) o;
            System.out.println("Dade llegides " + dades);

            // DESPRÉS LLIG OBJECTE resum DE TIPUS string DEL FITXER hash.dat
            InputStream fisHash = new FileInputStream("hash.dat");
            ObjectInputStream oisHash = new ObjectInputStream(fisHash);
            o = oisHash.readObject();
            byte resum_original[] = (byte[]) o;
            System.out.println("Missatge resum original " + new String(resum_original));

            // GENERE RESUM DEL MISSATGE
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(dades.getBytes());
            byte resum_actual[] = md.digest();
            System.out.println("Missatge resum actual " + new String(resum_actual));

            // COMPARE ELS DOS RESUMS
            if (MessageDigest.isEqual(resum_actual, resum_original))
                System.out.println("Dades vàlides");
            else
                System.out.println("Dades no vàlides ");

            oisDades.close(); // TANQUE FLUX
            fisDades.close(); // TANQUE FITXER
            oisHash.close(); // TANQUE FLUX
            fisHash.close(); // TANQUE FITXER

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
