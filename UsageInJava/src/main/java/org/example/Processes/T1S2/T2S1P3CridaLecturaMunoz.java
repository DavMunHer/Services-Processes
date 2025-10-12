package org.example.Processes.T1S2;

import java.io.*;

/*
Make a program T2S1P3CridaLectura that calls the program
T2S1ExempleLectura.java and sends the string "Hello world". Also
It's time to print the flux of error.
 */
public class T2S1P3CridaLecturaMunoz {
    public static void main(String[] args) {
        String dirPath = System.getProperty("user.dir");
        System.out.println(dirPath);
        String projectPath = "/src/main/java/org/example/Processes/T1S2";
        String readingFilePath = dirPath + projectPath + "/T2S1ExempleLectura.java";
        String runnableFilePath = dirPath + projectPath + "/T2S1ExempleLectura";
        Runtime r = Runtime.getRuntime();
        Process p = null;

        try {
            p = r.exec("javac " + readingFilePath);

            InputStream er = p.getErrorStream();
            BufferedReader brer = new BufferedReader(new InputStreamReader(er));
            String line;

            while ((line = brer.readLine()) != null) {
                System.out.println("Error > " + line);
            }

            InputStream is = p.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }
            br.close();
            is.close();

            int exitCode;

            exitCode = p.waitFor();
            System.out.println("Compiling exit code: " + exitCode);
            // After compiling, we start another process for running the .class file

            p = r.exec("java " + runnableFilePath);

            OutputStream os = p.getOutputStream();
            os.write("Hola món!".getBytes());
            os.flush();

            er = p.getErrorStream();
            brer = new BufferedReader(new InputStreamReader(er));

            while ((line = brer.readLine()) != null) {
                System.out.println("Error > " + line);
            }

            is = p.getInputStream();
            br = new BufferedReader(new InputStreamReader(is));
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }
            br.close();
            is.close();
            os.close();

            exitCode = p.waitFor();
            System.out.println("Running exit code: " + exitCode);

        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }


}
