package org.example.Threads.Sychronized.WaitAndNotify;

public class UsPersonaCompte {
    public static void main(String[] args) {
        CompteMunoz c = new CompteMunoz(40, 500);

        Persona p1 = new Persona(c, "Juan");
        Persona p2 = new Persona(c, "Marina");

        p1.start();
        p2.start();
    }
}
