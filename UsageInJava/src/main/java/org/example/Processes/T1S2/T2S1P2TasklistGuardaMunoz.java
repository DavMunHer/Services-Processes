package org.example.Processes.T1S2;


import java.io.*;

/*
Write a program that generates a process that saves the information about the processes
in execution in a file named processes.txt.
 */
public class T2S1P2TasklistGuardaMunoz {
    public static void main(String[] args) {
        String os = System.getProperty("os.name").toLowerCase();
        String command = "";

        if (os.contains("win")) {
            command = "C:\\>tasklist /v";
        } else if (os.contains("nux") || os.contains("nix")) {
            command = "ps -ef";
        }
        Runtime r = Runtime.getRuntime();
        Process p = null;
        BufferedReader br = null;
        FileOutputStream fos = null;
        PrintWriter pw = null;

        try {
            p = r.exec(command);
            InputStream is = p.getInputStream();
            br = new BufferedReader(new InputStreamReader(is));
            fos = new FileOutputStream("processos.txt");
            pw = new PrintWriter(fos);
            String line;

            while ((line = br.readLine()) != null) {
                pw.println(line);
            }
            pw.close();
            fos.close();
            br.close();
            is.close();

            int exitCode;

            try {
                exitCode = p.waitFor();
                System.out.println("Exit code: " + exitCode);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }


        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
