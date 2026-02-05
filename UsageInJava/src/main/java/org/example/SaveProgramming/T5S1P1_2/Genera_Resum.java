package org.example.SaveProgramming.T5S1P1_2;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Genera_Resum {
    public static void main(String args[]) {
        try {
            // CREE OBJECTE MessageDigest
            MessageDigest md = MessageDigest.getInstance("SHA");

            // MISSATGE
            String missatge = "Con diez cañones por banda," + " viento en popa, a toda vela,"
                    + " no corta el mar, sino vuela" + " un velero bergantín." + " Bajel pirata que llaman,"
                    + " por su bravura, el Temido," + " en todo mar conocido" + " del uno al otro confín.";

            // S'INTRODUEIX EL TEXT EN BYTES A RESUMIR
            md.update(missatge.getBytes(StandardCharsets.UTF_8));

            // ES CALCULA EL RESUM
            byte resum[] = md.digest();
            System.out.println("Missatge resum " + new String(resum));

            // ESCRIC MISSATGE COM A OBJECTE AL FITXER data.dat
            FileOutputStream fosDatos = new FileOutputStream("data.dat");
            ObjectOutputStream oosDatos = new ObjectOutputStream(fosDatos);
            oosDatos.writeObject(missatge);

            // DESPRÉS ESCRIC EL RESUM COM A OBJECTE AL FITXER hash.dat
            FileOutputStream fosHash = new FileOutputStream("hash.dat");
            ObjectOutputStream oosHash = new ObjectOutputStream(fosHash);
            oosHash.writeObject(resum);

            oosDatos.close(); // TANQUE FLUX
            fosDatos.close(); // TANQUE FITXER
            oosHash.close(); // TANQUE FLUX
            fosHash.close(); // TANQUE FITXER

        } catch (IOException | NoSuchAlgorithmException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
