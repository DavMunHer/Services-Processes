package org.example.Processes.T1S2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Scanner;

public class T2S1P1QuasibashMunoz {
    public static void main(String[] args) {
        System.out.print("Introduce comando: ");
        Scanner kb = new Scanner(System.in);

        String command = kb.nextLine();

        Runtime r = Runtime.getRuntime();
        Process p = null;

        try {
            p = r.exec(command);
            InputStream is = p.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }
            br.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }


        int exitVal;

        try {
            exitVal = p.waitFor();
            System.out.println("El procés retorna: " + exitVal);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

}
