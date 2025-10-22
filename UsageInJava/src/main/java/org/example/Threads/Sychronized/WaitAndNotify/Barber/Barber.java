package org.example.Threads.Sychronized.WaitAndNotify.Barber;

public class Barber extends Thread {
    private ButacaBarber butacaBarber;

    public Barber(ButacaBarber b) {
        this.butacaBarber = b;
    }

    @Override
    public void run() {
//        try {
//            this.sleep(1000);
//            this.butacaBarber.openBarberia();
//        } catch (InterruptedException e) {
//        }
        while (true) {
            this.butacaBarber.cutHair();
        }

    }
}

