package org.example.SaveProgramming.T5S2P2;

import java.security.*;

public class SignaturaDigital {

    public static void main(String[] args) {
        try {

            System.out.println("Primera fase: Creació de claus pública i privada");

            // 1. Generació del parell de claus
            KeyPairGenerator keyGen = KeyPairGenerator.getInstance("DSA");
            SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
            keyGen.initialize(1024, random);

            KeyPair pair = keyGen.generateKeyPair();
            PrivateKey privateKey = pair.getPrivate();
            PublicKey publicKey = pair.getPublic();

            System.out.println("Segona fase: Signar les dades");

            // Missatge a signar
            String missatge = "Missatge a signar";
            byte[] dades = missatge.getBytes();

            // 2. Signar les dades
            Signature signatura = Signature.getInstance("SHA1withDSA");
            signatura.initSign(privateKey);
            signatura.update(dades);

            byte[] signaturaDigital = signatura.sign();

            System.out.println("Tercera fase: Verificar dades");

            // 3. Verificar la signatura
            Signature verificador = Signature.getInstance("SHA1withDSA");
            verificador.initVerify(publicKey);
            verificador.update(dades);

            boolean correcta = verificador.verify(signaturaDigital);

            // Resultat final
            if (correcta) {
                System.out.println("La signatura digital S'HA verificat correctament.");
            } else {
                System.out.println("La signatura digital NO és vàlida.");
            }

        } catch (Exception e) {
            System.out.println("Error durant el procés de signatura/verificació");
            e.printStackTrace();
        }
    }
}

