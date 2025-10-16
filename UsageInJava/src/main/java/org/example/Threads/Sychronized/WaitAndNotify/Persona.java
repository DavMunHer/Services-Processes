package org.example.Threads.Sychronized.WaitAndNotify;

import java.util.Random;

public class Persona extends Thread {
    private CompteMunoz c;

    public Persona(CompteMunoz c, String name) {
        this.c = c;
        this.setName(name);
    }


    @Override
    public void run() {
        for (int i = 0; i < 2; i++) {
            Random rnd = new Random();
            int ingreso = (int) (rnd.nextDouble() * 501.0);
            this.c.ingresarSaldo(ingreso, this.getName());
            int regreso = (int) (rnd.nextDouble() * 501.0);
            this.c.reintegrament(regreso, this.getName());
        }
    }
}
