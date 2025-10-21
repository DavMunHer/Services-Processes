package org.example.Threads.Sychronized.WaitAndNotify.Barber;


public class Principal {
    public static void main(String[] args) {
        ButacaBarber butacaBarber = new ButacaBarber();
        CadiresEspera cadiresEspera = new CadiresEspera(3);
        Barber b = new Barber(butacaBarber);
        b.start();

        for (int i = 1; i <= 15; i++) {
            Client c = new Client(butacaBarber, cadiresEspera, i);
            c.start();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
            }
        }
    }
}
